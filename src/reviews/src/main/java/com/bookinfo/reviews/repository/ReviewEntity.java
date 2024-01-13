package com.bookinfo.reviews.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "reviews")
@NamedQueries({
    @NamedQuery(
        name = "findReivewEntitiesByProductId",
        query = "select r from ReviewEntity r where r.productId = :productId"
    ),
    @NamedQuery(
        name = "findReivewEntityByProductIdAndReviewer",
        query = "select r from ReviewEntity r where r.productId = :productId and r.reviewer = :reviewer"
    )
})
public class ReviewEntity implements Serializable {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "reviewer")
    private String reviewer;

    @Column(name = "text")
    private String text;

    @Column(name = "stars")
    private int stars;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
