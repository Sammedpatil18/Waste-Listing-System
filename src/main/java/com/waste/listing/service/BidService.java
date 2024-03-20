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

   public Bid placeBid(String listingId, String userId, double amount) {
        // Fetch the listing and user objects from their respective repositories or services
	   Listing listing = listingRepository.findById(listingId)
               .orElseThrow(() -> new RuntimeException("Listing not found with id: " + listingId));
	   
       User user = userRepository.findById(userId)
               .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
  
       // Create a new bid
        Bid bid = new Bid();
        bid.setListing(listing); // Set the listing
        bid.setUser(user); // Set the user
        bid.setAmount(amount);

        // Save the bid to the repository
        return bidRepository.save(bid);
    }

    public List<Bid> getBidsForListing(String listingId) {
        // Implement logic to fetch all bids for a listing from the database
        return bidRepository.findByListingId(listingId);
    }
}
