package com.mario.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mario.spring.dto.SignupForm;
import com.mario.spring.dto.UserDetailsImpl;
import com.mario.spring.entities.User;
import com.mario.spring.repositories.UserRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void signup(SignupForm signupForm) {
	
		User user = new User();
		user.setEmail(signupForm.getEmail());
		user.setName(signupForm.getName());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		userRepository.save(user);
		
		//int j= 20/0;
		
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		System.out.println("username12" +username);
		
		User user = userRepository.findByEmail(username);
		System.out.println(user);
		if(user == null) {
				System.out.println("No encontrado");
			throw new UsernameNotFoundException(username);
			
		}		
		return new UserDetailsImpl(user);
	}

	
	
	
}
