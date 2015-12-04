package com.foodniche.rest.services;

import com.foodniche.db.dao.ContentDao;
import com.foodniche.db.dao.ProductDao;
import com.foodniche.db.entities.Content;
import com.foodniche.db.entities.Product;
import com.foodniche.rest.model.ContentModel;
import com.foodniche.rest.security.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.*;
import java.util.List;

/**
 * @author Alexey Dubrov
 */

@Component
@Path("/api/product")
@Api(value = "/api/product", description = "Product service")
public class ProductService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private ProductDao productDao;

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @ApiOperation(value = "Create product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Product addProduct(Product product) {
        return productDao.save(product);
    }

    @GET
    @Path("/{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @ApiOperation(value = "Get products for businesses")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Product> getProducts(@PathParam("id") Integer businessId) {
        return productDao.getBusinessProducts(businessId);
    }

    @POST
    @Path("/content/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiOperation(value = "Add comment to product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Content addContent(@PathParam("id") Integer id, ContentModel content) {
        return productDao.addContent(securityService.getCurrentUser(), content.getContent(), content.getTypeName(), id);
    }

    @GET
    @Path("/content/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiOperation(value = "Add comment to product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Content> getContents(@PathParam("id") Integer id, @QueryParam("type") String contentType) {
        Product product = productDao.get(id);

        return contentDao.getProductContents(product, contentType);
    }
}
