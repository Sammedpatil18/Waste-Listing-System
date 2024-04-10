package com.waste.listing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waste.listing.model.User;
import com.waste.listing.repository.UserRepository;

@Service
public class UserService {

	 private UserRepository userRepository;

	    @Autowired
	    public UserService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    public User register(User user) throws RuntimeException {
	        if (userRepository.findByEmail(user.getEmail()) != null) {
	            throw new RuntimeException("Email is already registered");
	        }

	        return userRepository.save(user);
	    }

	    public User login(String email, String password) {
	        User user = userRepository.findByEmail(email);

	        if (user == null) {
	            throw new RuntimeException("User not found");
	        }

	        if (!password.equals(user.getPassword())) {
	            throw new RuntimeException("Invalid password");
	        }

	        return user;
	    }

		public User getUserByID(Long id) {
			return userRepository.findById(id).orElse(null);
		}
}
