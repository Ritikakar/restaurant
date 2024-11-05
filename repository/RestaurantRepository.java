package com.example.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.Restaurant; // Import the Restaurant model class

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByCuisine(String cuisine);

    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE %?1%")
    List<Restaurant> searchByName(String name);

    @Query("SELECT r FROM Restaurant r WHERE r.cuisine LIKE %?1%") // Corrected typo from 'cusine' to 'cuisine'
    List<Restaurant> searchByCuisine(String cuisine); // Corrected method name from 'searchByCusine' to 'searchByCuisine'

    @Query("SELECT r FROM Restaurant r JOIN r.reviews rv GROUP BY r HAVING AVG(rv.rating) > ?1")
    List<Restaurant> searchByRating(double rating);
}
