package com.waste.listing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Bid {
    @Id
    private String id;
    private double amount;
    @DBRef
    private User user;
    @DBRef
    private Listing listing;
    
    

	public Bid(){
		
	}
    
	public Bid(String id, double amount, User user, Listing listing) {
		super();
		this.id = id;
		this.amount = amount;
		this.user = user;
		this.listing = listing;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}

	@Override
	public String toString() {
		return "Bid [id=" + id + ", amount=" + amount + ", user=" + user + ", listing=" + listing + "]";
	}
  
    
    
}