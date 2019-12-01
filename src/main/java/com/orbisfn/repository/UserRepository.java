package com.orbisfn.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orbisfn.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("select u from User u where u.username = ?1 and u.password= ?2")
	public User findUser(String username, String password);

	public User findByUsername(String username);

}
