package com.beardtrust.webapp.userservice.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * The type UserEntity.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Entity
@Table(name = "users")
@Data
public class UserEntity implements Serializable {
	@Id
	@Column(unique = true)
	private String userId;
	@Column(unique = true)
	private String username;
	private String password;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String phone;
	private String firstName;
	private String lastName;
	@Column(name = "dob")
	private LocalDate dateOfBirth;
	private String role;

	/**
	 * Instantiates a new UserEntity.
	 */
	public UserEntity() {
		this.userId = UUID.randomUUID().toString();
	}
}
