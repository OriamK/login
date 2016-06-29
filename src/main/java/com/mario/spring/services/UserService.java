package com.mario.spring.services;



import java.util.List;

import com.mario.spring.dto.SignupForm;
import com.mario.spring.entities.User;


public interface UserService {

	public void signup(SignupForm signupForm);
	
	public List<User> selectAll();
	
//	public void delete(int id);

	public void verify(String verificationCode);
	
}
