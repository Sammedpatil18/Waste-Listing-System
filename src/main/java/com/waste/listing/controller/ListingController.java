package com.waste.listing.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waste.listing.model.Listing;
import com.waste.listing.service.ListingService;

@RestController
@RequestMapping("/v1/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @GetMapping("/getlistings")
    public ResponseEntity<List<Listing>> getAllListings() {
        List<Listing> listings = listingService.getAllListings();
        return new ResponseEntity<>(listings, HttpStatus.OK);
    }
    
    @GetMapping("getlisting/{listingId}")
    public Listing getListingById(@PathVariable String listingId) {
        return listingService.getListingById(listingId);
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Listing> createListing(@PathVariable String userId, @RequestBody Listing listing) {
        Listing createdListing = listingService.createListing(userId, listing);
        return new ResponseEntity<>(createdListing, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{listingId}")
    public ResponseEntity<Void> deleteListing(@PathVariable String listingId) {
        listingService.deleteListing(listingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/update/{listingId}")
    public ResponseEntity<Listing> updateListing(@PathVariable String listingId, @RequestBody Listing updatedListing) {
        try {
            Listing updatedListingResult = listingService.updateListing(listingId, updatedListing);
            return new ResponseEntity<>(updatedListingResult, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @PostMapping("/accept")
    public ResponseEntity<String> acceptBidForListing(@RequestBody Map<String, String> requestData) {
        String bidId = requestData.get("bidId");
        listingService.acceptBid(bidId);
        return ResponseEntity.status(HttpStatus.OK).body("Bid accepted successfully.");
    }
    
//    @GetMapping("/getbids/{listingId}")
//    public ResponseEntity<List<Bid>> getBidsForListing(@PathVariable String listingId) {
//        List<Bid> bids = listingService.getBidsForListing(listingId);
//        return new ResponseEntity<>(bids, HttpStatus.OK);
//    }


}