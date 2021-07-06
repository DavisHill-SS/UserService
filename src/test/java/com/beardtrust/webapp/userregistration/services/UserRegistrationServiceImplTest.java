package com.beardtrust.webapp.userregistration.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beardtrust.webapp.userregistration.entities.UserRegistration;
import com.beardtrust.webapp.userregistration.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserRegistrationServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class UserRegistrationServiceImplTest {
	@MockBean
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRegistrationServiceImpl userRegistrationServiceImpl;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void testConstructor() {
		// TODO: This test is incomplete.
		//   Reason: Nothing to assert: the constructed class does not have observers (e.g. getters or public fields).
		//   Add observers (e.g. getters or public fields) to the class.
		//   See https://diff.blue/R002

		UserRepository userRepository = mock(UserRepository.class);
		new UserRegistrationServiceImpl(userRepository, new Argon2PasswordEncoder());

	}

	@Test
	public void testRegisterUser() {
		when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("foo");
		UserRegistration userRegistration = new UserRegistration();
		assertNull(this.userRegistrationServiceImpl.registerUser(userRegistration));
		verify(this.passwordEncoder).encode((CharSequence) any());
		assertEquals("foo", userRegistration.getPassword());
	}
}

