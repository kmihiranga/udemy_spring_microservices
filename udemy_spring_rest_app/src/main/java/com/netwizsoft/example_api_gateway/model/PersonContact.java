package com.netwizsoft.example_api_gateway.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PersonContact {
	
	private String gender;
	private String phone;

}
