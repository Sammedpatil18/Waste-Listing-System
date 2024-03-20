package com.waste.listing.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waste.listing.model.Bid;
import com.waste.listing.service.BidService;

@RestController
@RequestMapping("/bids")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping("/place")
    public ResponseEntity<Bid> placeBid(@RequestParam String listingId, @RequestParam String userId, @RequestParam double amount) {
        Bid placedBid = bidService.placeBid(listingId, userId, amount);
        return ResponseEntity.ok(placedBid);
    }

    @GetMapping("/{listingId}")
    public ResponseEntity<List<Bid>> getBidsForListing(@PathVariable String listingId) {
        List<Bid> bidsForListing = bidService.getBidsForListing(listingId);
        return ResponseEntity.ok(bidsForListing);
    }
}