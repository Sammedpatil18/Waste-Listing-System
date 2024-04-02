package com.waste.listing.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.waste.listing.model.Bid;

public interface BidRepository extends MongoRepository<Bid, String>  {

	List<Bid> findByListingId(String listingId);
	


}
