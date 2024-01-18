package com.netwizsoft.example_api_gateway.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netwizsoft.example_api_gateway.model.PatchUserRequest;
import com.netwizsoft.example_api_gateway.model.User;

@Service
public interface UserService {
	
	List<User> getUsers();
	
	User getUser(Long userId);
	
	void create(User user);
	
	void delete(Long userId);
	
	void update(User user, PatchUserRequest request);
}
