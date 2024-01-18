package com.netwizsoft.rentalgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netwizsoft.rentalgateway.model.PatchUserRequest;
import com.netwizsoft.rentalgateway.model.User;
import com.netwizsoft.rentalgateway.proxy.UserProxy;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/users")
public class GatewayUserController {

	@Autowired
	private UserProxy userProxy;
	
	@GetMapping
	public Mono<User[]> getUsers() {
		return userProxy.getUsers();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<User> create(@RequestBody User user) {
		return userProxy.create(user);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(name = "id") String id) {
		userProxy.remove(id);
	}
	
	@PatchMapping("/{id}")
	public void update(@PathVariable(name = "id") String id, @RequestBody PatchUserRequest request) {
		userProxy.update(id, request);
	}
}
