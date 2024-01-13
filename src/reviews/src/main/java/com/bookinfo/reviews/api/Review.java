package com.bookinfo.reviews.api;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class Review {
    private int id;
    private String reviewer;
    private String text;

    @JsonbCreator
    public Review(
            @JsonbProperty("id") int id,
            @JsonbProperty("reviewer") String reviewer,
            @JsonbProperty("text") String text) {
        this.id = id;
        this.reviewer = reviewer;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getText() {
        return text;
    }

}
