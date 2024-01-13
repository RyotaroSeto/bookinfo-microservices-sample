package com.bookinfo.reviews.repository;

import com.bookinfo.reviews.api.Rating;
import com.bookinfo.reviews.api.Review;
import com.bookinfo.reviews.api.ReviewsService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.*;

@ApplicationScoped
@Transactional
public class ReviewsServiceImpl implements ReviewsService {

    @PersistenceContext
    private EntityManager em;

    public ReviewsServiceImpl() {
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<String, Object>();

        for (String envName : env.keySet()) {
            if (envName.contains("DB_URL")) {
                configOverrides.put("jakarta.persistence.jdbc.url", env.get(envName));
            }
            if (envName.contains("DB_USER")) {
                configOverrides.put("jakarta.persistence.jdbc.user", env.get(envName));
            }
            if (envName.contains("DB_PASSWORD")) {
                configOverrides.put("jakarta.persistence.jdbc.password", env.get(envName));
            }
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReviewsService", configOverrides);

        this.em = emf.createEntityManager();
    }

    @Override
    public List<Review> findReviews(int productId) {
        List<ReviewEntity> entities = em.createNamedQuery("findReivewEntitiesByProductId", ReviewEntity.class)
                .setParameter("productId", productId)
                .getResultList();

        List<Review> reviews = new ArrayList<>(entities.size());
        for (ReviewEntity entity : entities) {
            reviews.add(new Review(entity.getProductId(), entity.getReviewer(), entity.getText()));
        }
        return reviews;
    }

    @Override
    public List<Rating> findRatings(int productId) {
        List<ReviewEntity> entities = em.createNamedQuery("findReivewEntitiesByProductId", ReviewEntity.class)
                .setParameter("productId", productId)
                .getResultList();

        List<Rating> ratings = new ArrayList<>(entities.size());
        for (ReviewEntity entity : entities) {
            ratings.add(new Rating(entity.getProductId(), entity.getReviewer(), entity.getStars()));
        }
        return ratings;
    }

    @Override
    public void createOrUpdateReview(Review review) {
        List<ReviewEntity> entities = em.createNamedQuery("findReivewEntityByProductIdAndReviewer", ReviewEntity.class)
                .setParameter("productId", review.getId())
                .setParameter("reviewer", review.getReviewer())
                .getResultList();

        ReviewEntity entity = null;
        if (entities.size() == 0) {
            entity = new ReviewEntity();
            entity.setProductId(review.getId());
            entity.setReviewer(review.getReviewer());
            entity.setText(review.getText());
            entity.setStars(-1);
        } else {
            entity = entities.get(0);
            entity.setText(review.getText());
        }

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void createOrUpdateRating(Rating rating) {
        List<ReviewEntity> entities = em.createNamedQuery("findReivewEntityByProductIdAndReviewer", ReviewEntity.class)
                .setParameter("productId", rating.getId())
                .setParameter("reviewer", rating.getReviewer())
                .getResultList();

        ReviewEntity entity = null;
        if (entities.size() == 0) {
            entity = new ReviewEntity();
            entity.setProductId(rating.getId());
            entity.setReviewer(rating.getReviewer());
            entity.setStars(rating.getStars());
        } else {
            entity = entities.get(0);
            entity.setStars(rating.getStars());
        }

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }
}
