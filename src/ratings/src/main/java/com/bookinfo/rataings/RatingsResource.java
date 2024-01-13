package com.bookinfo.rataings;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/ratings")
public class RatingsResource {

    @Inject
    RatingsService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rating> get(@QueryParam("productId") int productId) {
        return service.getByProductId(productId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Rating put(Rating rating) {
        service.createOrUpdate(rating);;
        return rating;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Rating delete(Rating rating) {
        service.delete(rating);
        return rating;
    }
}
