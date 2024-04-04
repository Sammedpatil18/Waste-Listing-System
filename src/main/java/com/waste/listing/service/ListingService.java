package com.waste.listing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waste.listing.model.Bid;
import com.waste.listing.model.Listing;
import com.waste.listing.model.User;
import com.waste.listing.repository.BidRepository;
import com.waste.listing.repository.ListingRepository;
import com.waste.listing.repository.UserRepository;

@Service
public class ListingService {

	@Autowired
	private ListingRepository listingRepository;

    @Autowired
    private BidRepository bidRepository;
    
	@Autowired
	private UserRepository userRepository;

	public Listing createListing(String userId, Listing listing) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User is not available with this ID" + userId));
		listing.setUser(user);
		return listingRepository.save(listing);
	}

	public Listing getListingById(String listingId) {
		Optional<Listing> optionalListing = listingRepository.findById(listingId);
		if (optionalListing.isPresent()) {
			return optionalListing.get();
		} else {
			throw new RuntimeException("Listing not found with ID: " + listingId);
		}
	}

	public List<Listing> getAllListings() {
		try {
			return listingRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("No Adds are Available", e);
		}
	}

	public void deleteListing(String listingId) {
		try {
			listingRepository.deleteById(listingId);
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete listing with ID: " + listingId, e);
		}
	}

	public Listing updateListing(String listingId, Listing updatedListing) {
        Optional<Listing> optionalListing = listingRepository.findById(listingId);
        if (optionalListing.isPresent()) {
            Listing listing = optionalListing.get();
            // Update fields with values from updatedListing
            updateListingFields(listing, updatedListing);
            return listingRepository.save(listing);
        } else {
            throw new RuntimeException("Listing not found with ID: " + listingId);
        }
    }

    private void updateListingFields(Listing listing, Listing updatedListing) {
        listing.setMediaFiles(updatedListing.getMediaFiles());
        listing.setWasteType(updatedListing.getWasteType());
        listing.setWasteCategory(updatedListing.getWasteCategory());
        listing.setLogistics(updatedListing.isLogistics());
        listing.setDescription(updatedListing.getDescription());
        listing.setWasteQuantity(updatedListing.getWasteQuantity());
        listing.setPrice(updatedListing.getPrice());
        listing.setListingExpiryDate(updatedListing.getListingExpiryDate());
        listing.setSellerName(updatedListing.getSellerName());
        listing.setLocationShop(updatedListing.getLocationShop());
        listing.setPickupLocation(updatedListing.getPickupLocation());
    }
    

    public void acceptBid(String bidId ) {
        // Get the bid by bidId
        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new RuntimeException("Bid not found with id: " + bidId));

        // Get the user by userId from the bid
        String userId = bid.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

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

        // Save the updated listing
        listingRepository.save(listing);

        // Update the bid
        bidRepository.save(bid);

        System.out.println("Bid accepted successfully.");
    }




}
