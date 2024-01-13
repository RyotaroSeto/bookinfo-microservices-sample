package com.bookinfo.rataings.repository;

import com.bookinfo.rataings.Rating;
import com.bookinfo.rataings.RatingsService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class RatingsServiceImpl implements RatingsService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Rating> getByProductId(int productId) {
        List<RatingEntity> entities = em.createQuery("select r from RatingEntity r where r.productId = :productId", RatingEntity.class)
            .setParameter("productId", productId)
            .getResultList();

        List<Rating> ratings = new ArrayList<>(entities.size());
        for (RatingEntity entity : entities) {
            ratings.add(new Rating(entity.getProductId(), entity.getReviewer(), entity.getStars()));
        }

        return ratings;
    }

    @Override
    public void createOrUpdate(Rating rating) {
        List<RatingEntity> entities = em.createQuery("select r from RatingEntity r where r.productId = :productId and r.reviewer = :reviewer", RatingEntity.class)
            .setParameter("productId", rating.getProductId())
            .setParameter("reviewer", rating.getReviewer())
            .getResultList();

        RatingEntity entity = null;
        if (entities.size() == 0) {
            entity = new RatingEntity();
            entity.setProductId(rating.getProductId());
            entity.setReviewer(rating.getReviewer());
            entity.setStars(rating.getStars());
        } else {
            entity = entities.get(0);
            entity.setStars(rating.getStars());
        }

        em.persist(entity);
    }

    @Override
    public void delete(Rating rating) {
        em.createQuery("delete from RatingEntity r where r.productId = :productId and r.reviewer = :reviewer")
            .setParameter("productId", rating.getProductId())
            .setParameter("reviewer", rating.getReviewer())
            .executeUpdate();
    }

}
