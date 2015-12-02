package com.foodniche.rest.services;

import com.foodniche.db.dao.ProductDao;
import com.foodniche.db.entities.Content;
import com.foodniche.db.entities.Product;
import com.foodniche.rest.model.Comment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Alexey Dubrov
 */

@Component
@Path("/api/product")
@Api(value = "/api/product", description = "Product service")
public class ProductService {

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
    @Path("/comment/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiOperation(value = "Add comment to product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Content addComment(@PathParam("id") Integer id, Comment comment) {
        Content cn = productDao.addContent(comment.getName(), comment.getContent(), "Review", id);

        return cn;
    }
}
