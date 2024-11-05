package com.example.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Review; // Import the Review model class

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findByRestaurant(Restaurant restaurant);
}