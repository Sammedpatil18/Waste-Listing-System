package com.waste.listing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.waste.listing.model.Listing;

public interface ListingRepository extends MongoRepository<Listing, String>{

}
