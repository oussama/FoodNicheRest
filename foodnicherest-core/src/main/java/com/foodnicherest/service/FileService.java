package com.foodnicherest.service;

import com.foodnicherest.FileType;
import com.foodnicherest.UploadedFiles;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @author Alexey Dubrov
 *
 * Service for uploading images and videos.
 */

@Stateless
@Path("/files")
@Api(value = "/files", description = "This Rest Service for uploading images and video")
public class FileService {

    @PersistenceContext(unitName = "foodnicherest-core-1.0-SNAPSHOT")
    private EntityManager em;

    private File imagesFolder;
    private File videosFolder;

    public FileService() {
        // creates folders in withing glassfish for file upload videos and images

        File file = new File(System.getProperty("user.dir"));
        file = new File(file, "uploads");

        if (!file.exists()) {
            file.mkdir();
        }

        imagesFolder = new File(file, "images");
        if (!imagesFolder.exists()) {
            imagesFolder.mkdir();
        }
        videosFolder = new File(file, "videos");
        if (!videosFolder.exists()) {
            videosFolder.mkdir();
        }
    }

    @POST
    @Consumes("multipart/form-data")
    @Produces({"application/json"})
    @Path("/image")
    @ApiOperation(value = "Upload image")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server")})
    public UploadedFiles uploadImage(@Context HttpHeaders headers,
                               @FormDataParam("file") InputStream fileInputStream,
                               @FormDataParam("file") FormDataBodyPart body) {

        UploadedFiles entity = createFileEntity(headers, body, FileType.IMAGE);

        File file = new File(imagesFolder, entity.getServerFileName());
        if (storeToFileSystem(file, fileInputStream)) {
            entity.setFileSize(FileUtils.sizeOf(file));
            em.persist(entity);
        } else {
            entity = null;
        }

        return entity;
    }

    @GET
    @Path("/image/{id}")
    @ApiOperation(value = "Get image by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Image was not found")})
    public Response getImage(@PathParam("id") Integer id, @Context HttpServletResponse response) {
        return getFileResponse(id);
    }

    @POST
    @Consumes("multipart/form-data")
    @Produces({"application/json"})
    @Path("/video")
    @ApiOperation(value = "Upload video")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server")})
    public UploadedFiles uploadVideo(@Context HttpHeaders headers,
                               @FormDataParam("file") InputStream fileInputStream,
                               @FormDataParam("file") FormDataBodyPart body) {

        UploadedFiles entity = createFileEntity(headers, body, FileType.VIDEO);

        File file = new File(videosFolder, entity.getServerFileName());
        if (storeToFileSystem(file, fileInputStream)) {
            entity.setFileSize(FileUtils.sizeOf(file));
            em.persist(entity);
        } else {
            entity = null;
        }

        return entity;
    }

    @GET
    @Path("/video/{id}")
    @ApiOperation(value = "Get video by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Video was not found")})
    public Response getVideo(@PathParam("id") Integer id, @Context HttpServletResponse response) {
        return getFileResponse(id);
    }

    /**
     * Get File response for image or video.
     * @param id file id
     * @return response
     */
    protected Response getFileResponse(Integer id) {
        UploadedFiles fileEntity = em.find(UploadedFiles.class, id);

        File file = null;
        if (fileEntity != null) {
            file = new File(imagesFolder, fileEntity.getServerFileName());
        }

        if (file != null && file.exists()) {
            try {
                final FileInputStream fin = new FileInputStream(file);
                StreamingOutput sout = (os) -> {
                    IOUtils.copy(fin, os);
                };

                return Response.ok(sout, fileEntity.getContentType()).header("content-disposition","attachment; filename = " + fileEntity.getFileName()).build();
            } catch (FileNotFoundException e) {
                //TODO: add logging
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Stores file to file system.
     * @param filePath path to file
     * @param inpStream input stream
     * @return true if stored successfully
     */
    protected boolean storeToFileSystem(File filePath, InputStream inpStream) {
        boolean isStored = false;

        try {
            FileOutputStream fout = new FileOutputStream(filePath);
            IOUtils.copy(inpStream, fout);
            fout.close();

            isStored = true;
        } catch (IOException e) {
            //TODO: add logging
        }

        return isStored;
    }

    /**
     * Create file entity with file meta-data
     * @param headers list of headers
     * @param body file body
     * @param fileType file type
     * @return entity
     */
    protected UploadedFiles createFileEntity(HttpHeaders headers, FormDataBodyPart body, FileType fileType) {
        String filename = body.getContentDisposition().getFileName();
        String serverFileName = new Date().getTime() + "-" + UUID.randomUUID().toString() + "-" + filename;

        UploadedFiles entity = new UploadedFiles();

        entity.setContentType(body.getMediaType().toString());
        entity.setServerFileName(serverFileName);
        entity.setFileName(filename);
        entity.setFileType(fileType);

        return entity;
    }
}
