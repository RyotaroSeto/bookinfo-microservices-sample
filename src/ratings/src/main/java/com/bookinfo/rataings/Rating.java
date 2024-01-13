package com.bookinfo.rataings;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Rating {
    private int productId;
    private String reviewer;
    private int stars;

    @JsonCreator
    public Rating(int productId, String reviewer, int stars) {
        this.productId = productId;
        this.reviewer = reviewer;
        this.stars = stars;
    }

    public int getProductId() {
        return productId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public int getStars() {
        return stars;
    }

    public String getColor() {
        return "red";
    }

}