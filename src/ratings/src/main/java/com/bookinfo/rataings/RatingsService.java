package com.bookinfo.rataings;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface RatingsService {

    public List<Rating> getByProductId(int productId);

    public void createOrUpdate(Rating rating);

    public void delete(Rating rating);
}
