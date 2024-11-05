package com.example.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This class represents a Restaurant entity in the database.
 * It contains the restaurant's id, name, cuisine, and a list of reviews.
 */
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cuisine;
    // Removed the problematic field due to the missing Review class
    //getters and setters
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }   
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getCuisine(){
        return cuisine;
    }
    public void setCuisine(String cuisine){
        this.cuisine = cuisine;
    }
    // Removed the problematic methods due to the missing Review class
}