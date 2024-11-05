package com.example.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * This class represents a Review entity in the database.
 * It contains the review's id, reviewer's name, rating, associated restaurant, and the comment.
 */
@Entity
@Table(name = "review")
public class Review {
    /**
     * The unique identifier for the review.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The name of the reviewer. This field cannot be null.
     */
    @Column(nullable = false)
    private String reviewerName;
    /**
     * The rating given by the reviewer. This field cannot be null.
     */
    @Column(nullable = false)
    private int rating;
    /**
     * The restaurant associated with this review. This field cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    /**
     * The comment left by the reviewer. This field cannot be null.
     */
    @Column(nullable = false)
    private String comment;

    //getters and setters
    /**
     * Returns the unique identifier for the review.
     * @return the review's id
     */
    public Long getId(){
        return id;
    }
    /**
     * Sets the unique identifier for the review.
     * @param id the review's id
     */
    public void setId(Long id){
        this.id = id;
    }   
    /**
     * Returns the name of the reviewer.
     * @return the reviewer's name
     */
    public String getReviewerName(){
        return reviewerName;
    }
    /**
     * Sets the name of the reviewer.
     * @param reviewerName the reviewer's name
     */
    public void setReviewerName(String reviewerName){
        this.reviewerName = reviewerName;
    }
    /**
     * Returns the rating given by the reviewer.
     * @return the rating
     */
    public int getRating(){
        return rating;
    }
    /**
     * Sets the rating given by the reviewer.
     * @param rating the rating
     */
    public void setRating(int rating){
        this.rating = rating;
    }
    /**
     * Returns the restaurant associated with this review.
     * @return the associated restaurant
     */
    public Restaurant getRestaurant(){
        return restaurant;
    }
    /**
     * Sets the restaurant associated with this review.
     * @param restaurant the associated restaurant
     */
    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    /**
     * Returns the comment left by the reviewer.
     * @return the comment
     */
    public String getComment(){
        return comment;
    }
    /**
     * Sets the comment left by the reviewer.
     * @param comment the comment
     */
    public void setComment(String comment){
        this.comment = comment;
    }
    
}