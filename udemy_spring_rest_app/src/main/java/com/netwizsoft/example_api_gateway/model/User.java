package com.netwizsoft.example_api_gateway.model;


import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netwizsoft.example_api_gateway.constants.AppConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @JsonIgnoreProperties(value = {"id", "email"})
@Entity
@JsonInclude(Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@JsonProperty("first_name")
	@NotBlank(message = "first name is required")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("email")
	@Column(length = 20)
	@Pattern(regexp = AppConstants.EMAIL_REGEXPR, message = "email must be valid")
	private String email;
	
	@JsonIgnore
	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Date regDate;
	
//	@Embedded
//	@AttributeOverrides({
//		@AttributeOverride(name = "gender", column = @Column(name = "user_gender")),
//		@AttributeOverride(name = "phone", column = @Column(name = "user_phone"))
//	})
//	private PersonContact personContact;
}
