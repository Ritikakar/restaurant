package com.example.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.Restaurant; // Import the Restaurant model class
import com.example.restaurant.model.Review; // Import the Review model class

/**
 * This class provides service methods for managing restaurants and their reviews.
 * It utilizes Spring Data JPA repositories for database operations.
 */
@Service
public class RestaurantService {
    @Autowired
    private com.example.restaurant.repository.RestaurantRepository restaurantRepository; // Injects the RestaurantRepository for database operations on restaurants.

    @Autowired
    private com.example.restaurant.repository.ReviewRepository reviewRepository; // Injects the ReviewRepository for database operations on reviews.

    /**
     * Adds a new restaurant to the database.
     * 
     * @param restaurant The restaurant object to be saved.
     * @return The saved restaurant object.
     */
    public com.example.restaurant.model.Restaurant addRestaurant(com.example.restaurant.model.Restaurant restaurant) {
        return restaurantRepository.save(restaurant); // Saves the restaurant to the database and returns the saved object.
    }

    /**
     * Retrieves all restaurants from the database.
     * 
     * @return A list of all restaurants in the database.
     */
    public List<com.example.restaurant.model.Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll(); // Retrieves all restaurants from the database and returns them as a list.
    }

    /**
     * Retrieves a specific restaurant by its ID from the database.
     * 
     * @param id The ID of the restaurant to be retrieved.
     * @return An Optional containing the restaurant if found, otherwise an empty Optional.
     */
    public Optional<com.example.restaurant.model.Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id); // Tries to find the restaurant by its ID and returns an Optional containing the result.
    }

    /**
     * Adds a review to a specific restaurant.
     * 
     * @param restaurantId The ID of the restaurant to which the review is being added.
     * @param review The review object to be added.
     * @return The saved review object if the restaurant exists, otherwise null.
     */
    public com.example.restaurant.model.Review addReview(Long restaurantId, com.example.restaurant.model.Review review) {
        Optional<com.example.restaurant.model.Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId); // Tries to find the restaurant by its ID.
        if (restaurantOpt.isPresent()) {
            com.example.restaurant.model.Restaurant restaurant = restaurantOpt.get(); // Gets the restaurant if found.
            review.setRestaurant(restaurant); // Sets the restaurant for the review.
            return reviewRepository.save(review); // Saves the review to the database and returns the saved object.
        }
        return null; // Returns null if the restaurant is not found.
    }

    /**
     * Retrieves all reviews for a specific restaurant.
     * 
     * @param restaurantId The ID of the restaurant for which the reviews are being retrieved.
     * @return A list of reviews if the restaurant exists, otherwise null.
     */
    public List<com.example.restaurant.model.Review> getReviewsForRestaurant(Long restaurantId) {
        Optional<com.example.restaurant.model.Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId); // Tries to find the restaurant by its ID.
        if (restaurantOpt.isPresent()) {
            com.example.restaurant.model.Restaurant restaurant = restaurantOpt.get(); // Gets the restaurant if found.
            // Assuming there's a method in RestaurantRepository to find reviews by restaurant
            return reviewRepository.findByRestaurant(restaurant); // Gets the list of reviews for the restaurant.
        }
        return null; // Returns null if the restaurant is not found.
    }
}
