package com.livingoncodes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livingoncodes.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
}
