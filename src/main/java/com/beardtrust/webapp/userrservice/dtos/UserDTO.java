package com.beardtrust.webapp.userrservice.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The UserEntity Data Transfer Object.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Data
public class UserDTO implements Serializable {

	private String userId;
	private String username;
	private String email;
	private String phone;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String role;
}
