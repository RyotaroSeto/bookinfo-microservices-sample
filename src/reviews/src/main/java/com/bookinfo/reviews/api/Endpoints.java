package com.bookinfo.reviews.api;

import com.bookinfo.reviews.repository.ReviewsServiceImpl;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * Root resource (exposed at "reviews" path)
 */
@Path("/")
public class Endpoints {

    private ReviewsService reviewsService;

    public Endpoints() {
        this.reviewsService = new ReviewsServiceImpl();
    }

    /**
     * @return Reviews that will be returned as a application/json response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReviews(@QueryParam("productId") int productId) {
        return reviewsService.findReviews(productId);
    }

    /**
     * @return Reviews that will be returned as a application/json response.
     */
    @GET
    @Path("/ratings")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rating> getRatings(@QueryParam("productId") int productId) {
        return reviewsService.findRatings(productId);
    }
    
    /**
     * @return Reviews that has been created by the request.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Review putReview(Review review) {
        reviewsService.createOrUpdateReview(review);
        return review;
    }

    /**
     * @return Reviews that has been created by the request.
     */
    @PUT
    @Path("/ratings")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Rating putRating(Rating rating) {
        reviewsService.createOrUpdateRating(rating);
        return rating;
    }

}
