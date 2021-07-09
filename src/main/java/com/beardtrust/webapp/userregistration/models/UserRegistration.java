package com.beardtrust.webapp.userregistration.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegistration {

	private String userId;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String role;
}
