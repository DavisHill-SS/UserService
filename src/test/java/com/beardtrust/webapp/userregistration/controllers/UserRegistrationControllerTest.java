package com.beardtrust.webapp.userregistration.controllers;

import com.beardtrust.webapp.userrservice.controllers.UserRegistrationController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beardtrust.webapp.userrservice.dtos.UserDTO;
import com.beardtrust.webapp.userrservice.models.UserRegistration;
import com.beardtrust.webapp.userrservice.repos.UserRepository;
import com.beardtrust.webapp.userrservice.services.UserRegistrationService;
import com.beardtrust.webapp.userrservice.services.UserRegistrationServiceImpl;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserRegistrationController.class})
@ExtendWith(SpringExtension.class)
public class UserRegistrationControllerTest {
	@Autowired
	private UserRegistrationController userRegistrationController;

	@MockBean
	private UserRegistrationService userRegistrationService;

	@Test
	public void testRegisterUser() {
		UserDTO userDTO = new UserDTO();
		userDTO.setLastName("Doe");
		userDTO.setEmail("jane.doe@example.org");
		userDTO.setRole("Role");
		userDTO.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userDTO.setUserId("42");
		userDTO.setUsername("janedoe");
		userDTO.setPhone("4105551212");
		userDTO.setFirstName("Jane");
		when(this.userRegistrationService.registerUser((UserRegistration) any())).thenReturn(userDTO);

		UserRegistration userRegistration = new UserRegistration();
		userRegistration.setLastName("Doe");
		userRegistration.setPassword("iloveyou");
		userRegistration.setEmail("jane.doe@example.org");
		userRegistration.setRole("Role");
		userRegistration.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userRegistration.setUserId("42");
		userRegistration.setUsername("janedoe");
		userRegistration.setPhone("4105551212");
		userRegistration.setFirstName("Jane");
		ResponseEntity<UserDTO> actualRegisterUserResult = this.userRegistrationController.registerUser(userRegistration);
		assertTrue(actualRegisterUserResult.hasBody());
		assertTrue(actualRegisterUserResult.getHeaders().isEmpty());
		assertEquals(HttpStatus.CREATED, actualRegisterUserResult.getStatusCode());
		verify(this.userRegistrationService).registerUser((UserRegistration) any());
	}

	@Test
	public void testRegisterUser2() {
		UserDTO userDTO = new UserDTO();
		userDTO.setLastName("Doe");
		userDTO.setEmail("jane.doe@example.org");
		userDTO.setRole("Role");
		userDTO.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userDTO.setUserId("");
		userDTO.setUsername("janedoe");
		userDTO.setPhone("4105551212");
		userDTO.setFirstName("Jane");
		when(this.userRegistrationService.registerUser((UserRegistration) any())).thenReturn(userDTO);

		UserRegistration userRegistration = new UserRegistration();
		userRegistration.setLastName("Doe");
		userRegistration.setPassword("iloveyou");
		userRegistration.setEmail("jane.doe@example.org");
		userRegistration.setRole("Role");
		userRegistration.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userRegistration.setUserId("42");
		userRegistration.setUsername("janedoe");
		userRegistration.setPhone("4105551212");
		userRegistration.setFirstName("Jane");
		ResponseEntity<UserDTO> actualRegisterUserResult = this.userRegistrationController.registerUser(userRegistration);
		assertEquals(
				"<400 BAD_REQUEST Bad Request,UserDTO(userId=, username=janedoe, email=jane.doe@example.org, phone=4105551212,"
						+ " firstName=Jane, lastName=Doe, dateOfBirth=1970-01-02, role=Role),[]>",
				actualRegisterUserResult.toString());
		assertTrue(actualRegisterUserResult.getHeaders().isEmpty());
		assertTrue(actualRegisterUserResult.hasBody());
		assertEquals(HttpStatus.BAD_REQUEST, actualRegisterUserResult.getStatusCode());
		verify(this.userRegistrationService).registerUser((UserRegistration) any());
	}

	@Test
	public void testDisplayUsers() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setLastName("Doe");
		userDTO.setEmail("jane.doe@example.org");
		userDTO.setRole("Role");
		userDTO.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userDTO.setUserId("42");
		userDTO.setUsername("janedoe");
		userDTO.setPhone("4105551212");
		userDTO.setFirstName("Jane");
		when(this.userRegistrationService.displayUser(anyString())).thenReturn(userDTO);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/id/{id}", "42");
		MockMvcBuilders.standaloneSetup(this.userRegistrationController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content()
						.string(
								"{\"userId\":\"42\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"phone\":\"4105551212\",\"firstName\":"
										+ "\"Jane\",\"lastName\":\"Doe\",\"dateOfBirth\":[1970,1,2],\"role\":\"Role\"}"));
	}

	@Test
	public void testDisplayUsers2() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setLastName("Doe");
		userDTO.setEmail("jane.doe@example.org");
		userDTO.setRole("Role");
		userDTO.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userDTO.setUserId("");
		userDTO.setUsername("janedoe");
		userDTO.setPhone("4105551212");
		userDTO.setFirstName("Jane");
		when(this.userRegistrationService.displayUser(anyString())).thenReturn(userDTO);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/id/{id}", "42");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userRegistrationController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content()
						.string(
								"{\"userId\":\"\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"phone\":\"4105551212\",\"firstName\":"
										+ "\"Jane\",\"lastName\":\"Doe\",\"dateOfBirth\":[1970,1,2],\"role\":\"Role\"}"));
	}
}

