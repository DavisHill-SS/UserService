package com.beardtrust.webapp.userrservice.security;

import com.beardtrust.webapp.userrservice.security.AuthorizationFilter;
import com.beardtrust.webapp.userrservice.services.AuthorizationService;
import com.beardtrust.webapp.userrservice.services.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The application's security configuration class.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final Environment environment;
	private final UserRegistrationService userRegistrationService;
	private final PasswordEncoder passwordEncoder;
	private final AuthorizationService authorizationService;

	/**
	 * Instantiates a new Security config.
	 * @param environment the environment
	 * @param userRegistrationService
	 * @param passwordEncoder
	 * @param authorizationService
	 */
	@Autowired
	public SecurityConfig(Environment environment, UserRegistrationService userRegistrationService, PasswordEncoder passwordEncoder, AuthorizationService authorizationService) {
		this.environment = environment;
		this.userRegistrationService = userRegistrationService;
		this.passwordEncoder = passwordEncoder;
		this.authorizationService = authorizationService;
	}

	@Description("Configure HTTP Security")
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/users").hasIpAddress(environment.getProperty("gateway.ip"))
				.antMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(new AuthorizationFilter(authenticationManager(), environment, authorizationService));
		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userRegistrationService).passwordEncoder(passwordEncoder);
	}
}
