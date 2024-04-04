package com.waste.listing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waste.listing.model.Bid;
import com.waste.listing.model.Listing;
import com.waste.listing.model.User;
import com.waste.listing.repository.BidRepository;
import com.waste.listing.repository.ListingRepository;
import com.waste.listing.repository.UserRepository;

@Service
public class BidService {

	@Autowired
	private BidRepository bidRepository;

	@Autowired
	private ListingRepository listingRepository;

	@Autowired
	private UserRepository userRepository;

//    public Bid placeBid(String listingId, double amount) {
//        // Fetch the listing object from the repository
//        Listing listing = listingRepository.findById(listingId)
//                .orElseThrow(() -> new RuntimeException("Listing not found with id: " + listingId));
//
//        // Create a new bid
//        Bid bid = new Bid();
//        bid.setListing(listing); // Set the listing
//        bid.setBidAmount(amount);
//
//        // Save the bid to the repository
//        return bidRepository.save(bid);
//    }
	
	  public Bid placeBid(String listingId, double amount, int bidQuantity, String userId) {
	        Listing listing = listingRepository.findById(listingId)
	                .orElseThrow(() -> new RuntimeException("Listing not found with id: " + listingId));

	        Bid bid = new Bid();
	        bid.setListing(listing);
	        bid.setBidAmount(amount);
	        bid.setBidQuantity(bidQuantity);
	        bid.setUserId(userId);
	        bid.setAccepted(false); // By default, the bid is not accepted
	       

	        return bidRepository.save(bid);
	    }
	  
	public List<Bid> getBidsForListing(String listingId) {
		// Implement logic to fetch all bids for a listing from the database
		return bidRepository.findByListingId(listingId);
	}

	public void acceptBid(String userId, String bidId) {
	    // Get the user by userId
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

	    // Get the bid by bidId
	    Bid bid = bidRepository.findById(bidId)
	            .orElseThrow(() -> new RuntimeException("Bid not found with id: " + bidId));

	    // Check if the bid exists in the user's bids list
	    if (!user.getBids().contains(bid)) {
	        System.out.println("Bid does not exist for this user.");
	        return;
	    }

	    // Check if the bid has already been accepted
	    if (bid.isAccepted()) {
	        System.out.println("Bid has already been accepted.");
	        return;
	    }

	    // Mark the bid as accepted
	    bid.setAccepted(true);

	    // Deduct price and quantity from the listing
	    Listing listing = bid.getListing();
	    int updatedQuantity = listing.getWasteQuantity() - bid.getBidQuantity();
	    double totalPrice = bid.getBidAmount() * bid.getBidQuantity();
	    double updatedPrice = listing.getPrice() - totalPrice;

	    // Update the listing
	    listing.setWasteQuantity(updatedQuantity);
	    listing.setPrice(updatedPrice);

	    // Update the listing
	    listingRepository.save(listing);

	    // Update the user and bid
	    userRepository.save(user);
	    bidRepository.save(bid);

	    System.out.println("Bid accepted successfully.");
	}

	public void rejectBid(String userId, String bidId) {
		// Get the user by userId
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

		// Get the bid by bidId
		Bid bid = bidRepository.findById(bidId)
				.orElseThrow(() -> new RuntimeException("Bid not found with id: " + bidId));

		// Check if the bid exists in the user's bids list
		if (!user.getBids().contains(bid)) {
			System.out.println("Bid does not exist for this user.");
			return;
		}

		// Remove the bid from the user's bids list
		user.getBids().remove(bid);

		// Update the user
		userRepository.save(user);

		// Delete the bid from the database
		bidRepository.delete(bid);

		System.out.println("Bid rejected and removed successfully.");
	}

//    public void acceptBid(String listingId, String bidId) {
//        
//        Listing listing = listingRepository.findById(listingId)
//                .orElseThrow(() -> new RuntimeException("Listing not found with id: " + listingId));
//
//        // Retrieve the bid from the database
//        Bid bid = bidRepository.findById(bidId)
//                .orElseThrow(() -> new RuntimeException("Bid not found with id: " + bidId)); bid.setAccepted(true);
//
//        bidRepository.save(bid);
//    }
//    

//    public void rejectBid(String listingId, String bidId) {
//        // Retrieve the listing from the database
//        Listing listing = listingRepository.findById(listingId)
//                .orElseThrow(() -> new RuntimeException("Listing not found with id: " + listingId));
//
//        Bid bid = bidRepository.findById(bidId)
//                .orElseThrow(() -> new RuntimeException("Bid not found with id: " + bidId));
//
//         bid.setAccepted(false);
//         
//        bidRepository.save(bid);
//    }
}
