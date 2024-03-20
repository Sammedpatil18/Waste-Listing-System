package com.waste.listing.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.waste.listing.model.User;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByEmail(String email);

	Optional<User> findById(String userId);
}