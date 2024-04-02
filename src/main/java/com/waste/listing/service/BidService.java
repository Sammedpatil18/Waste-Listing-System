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
    
    public Bid placeBid(String listingId, double amount) {
        // Fetch the listing object from the repository
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found with id: " + listingId));

        // Create a new bid
        Bid bid = new Bid();
        bid.setListing(listing); // Set the listing
        bid.setBidAmount(amount);

        // Save the bid to the repository
        return bidRepository.save(bid);
    }

    public List<Bid> getBidsForListing(String listingId) {
        // Implement logic to fetch all bids for a listing from the database
        return bidRepository.findByListingId(listingId);
    }

    public void acceptBid(String userId, Bid bid) {
        // Get the user by userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

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
}
