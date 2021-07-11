package com.beardtrust.webapp.userregistration.services;

import com.beardtrust.webapp.userrservice.services.AuthorizationServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beardtrust.webapp.userrservice.dtos.UserDTO;
import com.beardtrust.webapp.userrservice.entities.UserEntity;
import com.beardtrust.webapp.userrservice.repos.AuthorizationRepository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthorizationServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class AuthorizationServiceImplTest {
	@MockBean
	private AuthorizationRepository authorizationRepository;

	@Autowired
	private AuthorizationServiceImpl authorizationServiceImpl;

	@Test
	public void testConstructor() {
		// TODO: This test is incomplete.
		//   Reason: Nothing to assert: the constructed class does not have observers (e.g. getters or public fields).
		//   Add observers (e.g. getters or public fields) to the class.
		//   See https://diff.blue/R002

		new AuthorizationServiceImpl(mock(AuthorizationRepository.class));
	}

	@Test
	public void testGetUserByUserId() {
		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
		userEntity.setDateOfBirth(ofEpochDayResult);
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");
		Optional<UserEntity> ofResult = Optional.<UserEntity>of(userEntity);
		when(this.authorizationRepository.findByUserId(anyString())).thenReturn(ofResult);
		UserDTO actualUserByUserId = this.authorizationServiceImpl.getUserByUserId("42");
		assertSame(ofEpochDayResult, actualUserByUserId.getDateOfBirth());
		assertEquals("janedoe", actualUserByUserId.getUsername());
		assertEquals("jane.doe@example.org", actualUserByUserId.getEmail());
		assertEquals("Doe", actualUserByUserId.getLastName());
		assertEquals("4105551212", actualUserByUserId.getPhone());
		assertEquals("Jane", actualUserByUserId.getFirstName());
		assertEquals("Role", actualUserByUserId.getRole());
		assertEquals("42", actualUserByUserId.getUserId());
		verify(this.authorizationRepository).findByUserId(anyString());
	}

	@Test
	public void testGetUserByUserId2() {
		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Last Name");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");
		Optional<UserEntity> ofResult = Optional.<UserEntity>of(userEntity);
		when(this.authorizationRepository.findByUserId(anyString())).thenReturn(ofResult);
		UserDTO actualUserByUserId = this.authorizationServiceImpl.getUserByUserId("42");
		assertEquals("UserDTO(userId=42, username=janedoe, email=jane.doe@example.org, phone=4105551212, firstName=Jane,"
				+ " lastName=Last Name, dateOfBirth=1970-01-02, role=Role)", actualUserByUserId.toString());
		assertEquals("jane.doe@example.org", actualUserByUserId.getEmail());
		assertEquals("Last Name", actualUserByUserId.getLastName());
		assertEquals("janedoe", actualUserByUserId.getUsername());
		assertEquals("4105551212", actualUserByUserId.getPhone());
		assertEquals("Jane", actualUserByUserId.getFirstName());
		assertEquals("Role", actualUserByUserId.getRole());
		assertEquals("42", actualUserByUserId.getUserId());
		verify(this.authorizationRepository).findByUserId(anyString());
	}

	@Test
	public void testGetUserByUserId3() {
		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("42");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");
		Optional<UserEntity> ofResult = Optional.<UserEntity>of(userEntity);
		when(this.authorizationRepository.findByUserId(anyString())).thenReturn(ofResult);
		UserDTO actualUserByUserId = this.authorizationServiceImpl.getUserByUserId("42");
		assertEquals("UserDTO(userId=42, username=janedoe, email=jane.doe@example.org, phone=4105551212, firstName=Jane,"
				+ " lastName=42, dateOfBirth=1970-01-02, role=Role)", actualUserByUserId.toString());
		assertEquals("jane.doe@example.org", actualUserByUserId.getEmail());
		assertEquals("42", actualUserByUserId.getLastName());
		assertEquals("janedoe", actualUserByUserId.getUsername());
		assertEquals("4105551212", actualUserByUserId.getPhone());
		assertEquals("Jane", actualUserByUserId.getFirstName());
		assertEquals("Role", actualUserByUserId.getRole());
		assertEquals("42", actualUserByUserId.getUserId());
		verify(this.authorizationRepository).findByUserId(anyString());
	}

	@Test
	public void testGetUserByUserId4() {
		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(null);
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");
		Optional<UserEntity> ofResult = Optional.<UserEntity>of(userEntity);
		when(this.authorizationRepository.findByUserId(anyString())).thenReturn(ofResult);
		UserDTO actualUserByUserId = this.authorizationServiceImpl.getUserByUserId("42");
		assertNull(actualUserByUserId.getDateOfBirth());
		assertEquals("janedoe", actualUserByUserId.getUsername());
		assertEquals("42", actualUserByUserId.getUserId());
		assertEquals("Role", actualUserByUserId.getRole());
		assertEquals("4105551212", actualUserByUserId.getPhone());
		assertEquals("Doe", actualUserByUserId.getLastName());
		assertEquals("Jane", actualUserByUserId.getFirstName());
		assertEquals("jane.doe@example.org", actualUserByUserId.getEmail());
		verify(this.authorizationRepository).findByUserId(anyString());
	}

	@Test
	public void testGetUserByUserId5() {
		when(this.authorizationRepository.findByUserId(anyString())).thenReturn(Optional.<UserEntity>empty());
		assertNull(this.authorizationServiceImpl.getUserByUserId("42"));
		verify(this.authorizationRepository).findByUserId(anyString());
	}
}

