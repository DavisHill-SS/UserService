package com.beardtrust.webapp.userrservice.repos;

import com.beardtrust.webapp.userrservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface UserEntity repository.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	UserEntity findByEmail(String s);
}
