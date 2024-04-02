package com.waste.listing.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waste.listing.model.Bid;
import com.waste.listing.service.BidService;

@RestController
@RequestMapping("/api/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    @PostMapping("/place")
    public Bid placeBid(@RequestBody Map<String, Object> requestData) {
        String listingId = (String) requestData.get("listingId");
        double amount = (double) requestData.get("amount");
        return bidService.placeBid(listingId, amount);
    }


    @GetMapping("/listing/{listingId}")
    public List<Bid> getBidsForListing(@PathVariable String listingId) {
        return bidService.getBidsForListing(listingId);
    }

    @PostMapping("/accept/{userId}")
    public void acceptBid(@PathVariable String userId, @RequestBody Bid bid) {
        bidService.acceptBid(userId, bid);
    }
}
