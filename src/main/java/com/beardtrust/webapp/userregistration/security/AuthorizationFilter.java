package com.beardtrust.webapp.userregistration.security;


import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Authorization filter.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Slf4j
public class AuthorizationFilter extends BasicAuthenticationFilter {
	private final Environment environment;

	/**
	 * Instantiates a new Authorization filter.
	 *
	 * @param authenticationManager the authentication manager
	 * @param environment           the environment
	 */
	@Autowired
	public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
		super(authenticationManager);
		this.environment = environment;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain chain) throws IOException, ServletException {
		String authorizationHeader = request.getHeader(environment.getProperty("authorization.token.header.name"));

		if (authorizationHeader != null && authorizationHeader.startsWith(environment.getProperty("authorization" +
				".token.header.prefix"))) {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		System.out.println("Authorization header: " + authorizationHeader);
		chain.doFilter(request, response);
	}

	/**
	 * This function builds the authentication token to be used in authorization.
	 *
	 * @param request
	 * @return
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(environment.getProperty("authorization.token.header.name"));
		UsernamePasswordAuthenticationToken authenticationToken = null;
		if (authorizationHeader != null) {
			String token = authorizationHeader.replace(environment.getProperty("authorization.token" +
					".header.prefix"), "");

			String userId = Jwts.parser()
					.setSigningKey(environment.getProperty("token.secret"))
					.parseClaimsJws(token)
					.getBody()
					.getSubject();

			if (userId != null) {
				authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
			}
		}
		return authenticationToken;
	}


}
