package com.beardtrust.webapp.userregistration.services;

import com.beardtrust.webapp.userregistration.entities.User;
import com.beardtrust.webapp.userregistration.entities.UserDTO;
import com.beardtrust.webapp.userregistration.entities.UserRegistration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * The interface User registration service.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
public interface UserRegistrationService extends UserDetailsService {

	/**
	 * Register user user.
	 *
	 * @param user the user
	 * @return the user
	 */
	UserDTO registerUser(UserRegistration user);


	/**
	 * Display user user dto.
	 *
	 * @param userId the user id
	 * @return the user dto
	 */
	UserDTO displayUser(String userId);


	/**
	 * Gets user details by email.
	 *
	 * @param username the username
	 * @return the user details by email
	 */
	UserDTO getUserDetailsByEmail(String username);
}
