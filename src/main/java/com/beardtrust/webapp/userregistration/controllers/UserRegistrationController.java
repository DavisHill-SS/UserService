package com.beardtrust.webapp.userregistration.controllers;


import com.beardtrust.webapp.userregistration.api.UsersApi;
import com.beardtrust.webapp.userregistration.entities.UserDTO;
import com.beardtrust.webapp.userregistration.entities.UserRegistration;
import com.beardtrust.webapp.userregistration.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import java.util.List;

/**
 * The type User registration controller.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@RestController
@CrossOrigin
public class UserRegistrationController implements UsersApi {

	private final UserRegistrationService userRegistrationService;

	/**
	 * Instantiates a new User registration controller.
	 *
	 * @param userRegistrationService the user registration service
	 */
	@Autowired
	public UserRegistrationController(UserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
	}

	@Override
	@Consumes({MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegistration body) {
		ResponseEntity<UserDTO> response;
		UserDTO user = userRegistrationService.registerUser(body);
		if(!user.getUserId().equals("")){
			response = new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	/**
	 * Display users response entity.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<UserDTO> displayUsers(@PathVariable("id")String id){
		ResponseEntity<UserDTO> response = null;
		UserDTO userDetails = userRegistrationService.displayUser(id);
		if(!userDetails.getUserId().equals("")){
			response = new ResponseEntity<>(userDetails, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(userDetails, HttpStatus.I_AM_A_TEAPOT);
		}
		return response;
	}
}