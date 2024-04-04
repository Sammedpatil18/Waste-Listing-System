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
    private String userId;
    @DBRef
    private Listing listing;

    public Bid() {
    	
    }

	@Override
	public String toString() {
		return "Bid [id=" + id + ", bidAmount=" + bidAmount + ", bidQuantity=" + bidQuantity + ", isAccepted="
				+ isAccepted + ", userId=" + userId + ", listing=" + listing + "]";
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

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}

  
}
