package com.netwizsoft.rentalgateway.proxy;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.netwizsoft.rentalgateway.model.PatchUserRequest;
import com.netwizsoft.rentalgateway.model.User;

import reactor.core.publisher.Mono;

@Component
public class UserProxy {
	
	private final WebClient webClient;
	
	public UserProxy(WebClient webClient) {
		this.webClient = webClient;
	}
	
	public Mono<User[]> getUsers() {
		return webClient.get()
				.uri("/users")
				.retrieve()
				.bodyToMono(User[].class);
	}
	
	public Mono<User> create(User user) {
		return webClient.post()
			.uri("/users")
			.bodyValue(user)
			.retrieve()
			.bodyToMono(User.class);
	}
	
	public Mono<Void> remove(String id) {
		return webClient.delete()
			.uri("/users/{id}", id)
			.retrieve()
			.bodyToMono(Void.class);
	}
	
	public Mono<Void> update(String id, PatchUserRequest request) {
		return webClient.patch()
				.uri("/users/{id}", id)
				.retrieve()
				.bodyToMono(Void.class);
	}
}
