package com.waste.listing.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "listings")
public class Listing {
	
	@Id
	private String id;
	// UserDetails
	private User user;
	// Product Details
	private List<String> mediaFiles;
	private String wasteType;
	private String wasteCategory;
	private boolean logistics;
	private String description;
	private int wasteQuantity;
	private double price;
	private LocalDateTime listingExpiryDate;
	// Seller Details
	private String sellerName;
	private String locationShop;
	private String pickupLocation;

	public Listing() {

	}

	public Listing(String id, User user, List<String> mediaFiles, String wasteType, String wasteCategory,
			boolean logistics, String description, int wasteQuantity, double price, LocalDateTime listingExpiryDate,
			String sellerName, String locationShop, String pickupLocation) {
		super();
		this.id = id;
		this.user = user;
		this.mediaFiles = mediaFiles;
		this.wasteType = wasteType;
		this.wasteCategory = wasteCategory;
		this.logistics = logistics;
		this.description = description;
		this.wasteQuantity = wasteQuantity;
		this.price = price;
		this.listingExpiryDate = listingExpiryDate;
		this.sellerName = sellerName;
		this.locationShop = locationShop;
		this.pickupLocation = pickupLocation;
	}

	
	
	@Override
	public String toString() {
		return "Listing [id=" + id + ", user=" + user + ", mediaFiles=" + mediaFiles + ", wasteType=" + wasteType
				+ ", wasteCategory=" + wasteCategory + ", logistics=" + logistics + ", description=" + description
				+ ", wasteQuantity=" + wasteQuantity + ", price=" + price + ", listingExpiryDate=" + listingExpiryDate
				+ ", sellerName=" + sellerName + ", locationShop=" + locationShop + ", pickupLocation=" + pickupLocation
				+ "]";
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getMediaFiles() {
		return mediaFiles;
	}

	public void setMediaFiles(List<String> mediaFiles) {
		this.mediaFiles = mediaFiles;
	}

	public String getWasteType() {
		return wasteType;
	}

	public void setWasteType(String wasteType) {
		this.wasteType = wasteType;
	}

	public String getWasteCategory() {
		return wasteCategory;
	}

	public void setWasteCategory(String wasteCategory) {
		this.wasteCategory = wasteCategory;
	}

	public boolean isLogistics() {
		return logistics;
	}

	public void setLogistics(boolean logistics) {
		this.logistics = logistics;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWasteQuantity() {
		return wasteQuantity;
	}

	public void setWasteQuantity(int wasteQuantity) {
		this.wasteQuantity = wasteQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getListingExpiryDate() {
		return listingExpiryDate;
	}

	public void setListingExpiryDate(LocalDateTime listingExpiryDate) {
		this.listingExpiryDate = listingExpiryDate;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getLocationShop() {
		return locationShop;
	}

	public void setLocationShop(String locationShop) {
		this.locationShop = locationShop;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	
	
	

}
