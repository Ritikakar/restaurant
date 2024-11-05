package com.example.restaurant.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Review;
import com.example.restaurant.repository.RestaurantRepository;
import com.example.restaurant.repository.ReviewRepository;

/**
 * This class is a Spring Boot REST controller that handles HTTP requests related to restaurants.
 * It provides endpoints for creating a new restaurant, retrieving all restaurants, getting a specific restaurant by ID,
 * adding a review to a restaurant, and retrieving all reviews for a restaurant.
 */
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository; // Injects the RestaurantRepository for database operations on restaurants.
    @Autowired
    private ReviewRepository reviewRepository; // Injects the ReviewRepository for database operations on reviews.

    /**
     * Creates a new restaurant and saves it to the database.
     * 
     * @param restaurant The restaurant object to be saved.
     * @return A ResponseEntity containing the saved restaurant and a HTTP status of CREATED.
     */
    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantRepository.save(restaurant); // Saves the restaurant to the database.
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED); // Returns the saved restaurant with a HTTP status of CREATED.
    }

    /**
     * Retrieves all restaurants from the database.
     * 
     * @return A ResponseEntity containing a list of all restaurants and a HTTP status of OK.
     */
    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll(); // Retrieves all restaurants from the database.
        return new ResponseEntity<>(restaurants, HttpStatus.OK); // Returns the list of restaurants with a HTTP status of OK.
    }

    /**
     * Retrieves a specific restaurant by its ID from the database.
     * 
     * @param id The ID of the restaurant to be retrieved.
     * @return A ResponseEntity containing the restaurant if found, otherwise a HTTP status of NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id); // Tries to find the restaurant by its ID.
        if (restaurant.isPresent()) {
            return new ResponseEntity<>(restaurant.get(), HttpStatus.OK); // Returns the restaurant if found with a HTTP status of OK.
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns a HTTP status of NOT_FOUND if the restaurant is not found.
    }

    /**
     * Adds a review to a specific restaurant.
     * 
     * @param id The ID of the restaurant to which the review is being added.
     * @param review The review object to be added.
     * @return A ResponseEntity containing the saved review and a HTTP status of CREATED if the restaurant exists, otherwise a HTTP status of NOT_FOUND.
     */
    @PostMapping("/{id}/reviews")
    public ResponseEntity<Review> addReview(@PathVariable Long id, @RequestBody Review review) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(id); // Tries to find the restaurant by its ID.
        if (restaurantOpt.isPresent()) {
            Restaurant restaurant = restaurantOpt.get(); // Gets the restaurant if found.
            review.setRestaurant(restaurant); // Sets the restaurant for the review.
            Review savedReview = reviewRepository.save(review); // Saves the review to the database.
            return new ResponseEntity<>(savedReview, HttpStatus.CREATED); // Returns the saved review with a HTTP status of CREATED.
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns a HTTP status of NOT_FOUND if the restaurant is not found.
    }

    /**
     * Retrieves all reviews for a specific restaurant.
     * 
     * @param id The ID of the restaurant for which the reviews are being retrieved.
     * @return A ResponseEntity containing a list of reviews if the restaurant exists, otherwise a HTTP status of NOT_FOUND.
     */
    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> getReviewsForRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(id); // Tries to find the restaurant by its ID.
        if (restaurantOpt.isPresent()) {
            Restaurant restaurant = restaurantOpt.get(); // Gets the restaurant if found.
            List<Review> reviews = reviewRepository.findByRestaurant(restaurant); // Gets the list of reviews for the restaurant.
            return new ResponseEntity<>(reviews, HttpStatus.OK); // Returns the list of reviews with a HTTP status of OK.
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns a HTTP status of NOT_FOUND if the restaurant is not found.
    }
}
