package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.userExistsException;
import com.stacksimplify.restservices.exceptions.userNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

// Service
@Service
public class UserService {
	
	// Autowire the userRepository
	
	@Autowired
	private UserRepository userRepository;
	
	// getAllUsers method
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
	}
	
	// CreateUser method
	public User createUser(User user) throws userExistsException {
		//if user exist using userName
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		//if exists throw userExistsException
		if(existingUser != null) {
			throw new userExistsException("user already exists in the repository");
		}
			
		return userRepository.save(user);
	}
	
	//getUserById method
	public Optional<User> getUserById(Long id) throws userNotFoundException {
		
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new userNotFoundException("User not found in user Repository");
		}
		return user;
		
	}
	
	//updateUserById method
	public User updateUserById(Long id, User user) throws userNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(!optionalUser.isPresent()) {
			throw new userNotFoundException("User not found in user Repository, provide a correct User ID");
		}
		user.setId(id);
		return userRepository.save(user);
		
	}

	//deleteUserById method
	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in user Repository, provide a correct User ID");
		}
		
		userRepository.deleteById(id);
		
	}
	
	//getUserByUsername method
	public User getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
		

	}
}
