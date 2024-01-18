package com.netwizsoft.example_api_gateway.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netwizsoft.example_api_gateway.constants.AppConstants;
import com.netwizsoft.example_api_gateway.model.PatchUserRequest;
import com.netwizsoft.example_api_gateway.model.User;
import com.netwizsoft.example_api_gateway.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}
	
	@Override
	public User getUser(Long userId) {
		return this.userRepository.findByUserId(userId);
	}
	
	@Override
	public void create(User user) {
		this.userRepository.save(user);
	}
	
	@Override
	public void delete(Long userId) {
		this.userRepository.deleteByUserId(userId);
	}
	
	@Override
	public void update(User user, PatchUserRequest request) {
		validateForPatch(request);
		updateUser(user, request);
		this.userRepository.save(user);
	}
	
	private void validateForPatch(PatchUserRequest request) {
		if (request.getEmail() != null && request.getEmail().isBlank()) {
			throw new IllegalArgumentException("Email can not be blank");
		}
		
		// verify regular expressions
		if (request.getEmail() != null && !Pattern.matches(AppConstants.EMAIL_REGEXPR, request.getEmail())) {
			throw new IllegalArgumentException("Email is not valid");
		}
		
		if (request.getFirstName() != null && request.getFirstName().isBlank()) {
			throw new IllegalArgumentException("First name is not valid");
		}
		
		if (request.getLastName() != null && request.getLastName().isBlank()) {
			throw new IllegalArgumentException("Last name is not valid");
		}
	}
	
	private void updateUser(User user, PatchUserRequest request) {
		// update the values that are present in the request
		if (request.getFirstName() != null)
			user.setFirstName(request.getFirstName());
		
		if (request.getLastName() != null)
			user.setLastName(request.getLastName());
		
		if (request.getEmail() != null)
			user.setEmail(request.getEmail());
	}
	
}
