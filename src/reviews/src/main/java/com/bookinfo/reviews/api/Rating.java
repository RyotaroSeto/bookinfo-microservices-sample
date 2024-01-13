package com.bookinfo.reviews.api;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class Rating {
    private int id;
    private String reviewer;
    private int stars;

    @JsonbCreator
    public Rating(
            @JsonbProperty("id") int id,
            @JsonbProperty("reviewer") String reviewer,
            @JsonbProperty("stars") int stars) {
        this.id = id;
        this.reviewer = reviewer;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getReviewer() {
        return reviewer;
    }

    public int getStars() {
        return stars;
    }

    public String getColor() {
        return "black";
    }

}
