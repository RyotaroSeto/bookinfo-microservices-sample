package com.bookinfo.rataings;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Ratings {
    private int productId;
    List<Rating> ratings;

    @JsonCreator
    public Ratings(int productId, List<Rating> ratings) {
        this.productId = productId;
        this.ratings = ratings;
    }

    public int getProductId() {
        return productId;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}
