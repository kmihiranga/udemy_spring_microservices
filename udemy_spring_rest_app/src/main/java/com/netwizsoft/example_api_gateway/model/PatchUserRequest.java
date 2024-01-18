package com.netwizsoft.example_api_gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PatchUserRequest {
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("email")
	private String email;
}
