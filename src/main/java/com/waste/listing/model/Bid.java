package com.waste.listing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Bid {
    @Id
    private String id;
    private double bidAmount;
    private int bidQuantity;
    private boolean isAccepted;
    @DBRef
    private User user;
    @DBRef
    private Listing listing;

    public Bid() {
    	
    }

    public Bid(String id, double bidAmount, int bidQuantity, boolean accepted, User user, Listing listing) {
        this.id = id;
        this.bidAmount = bidAmount;
        this.bidQuantity = bidQuantity;
        this.isAccepted = accepted;
        this.user = user;
        this.listing = listing;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public int getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(int bidQuantity) {
        this.bidQuantity = bidQuantity;
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

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@Override
	public String toString() {
		return "Bid [id=" + id + ", bidAmount=" + bidAmount + ", bidQuantity=" + bidQuantity + ", isAccepted="
				+ isAccepted + ", user=" + user + ", listing=" + listing + "]";
	}

  
}
