package com.mario.spring.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mario.spring.entities.User;


public interface UserRepository extends JpaRepository<User, Long>{

	//@Query("select u from user where u.email = ?1")
	User findByEmail(String email);
	
}

