package com.mario.spring.validators;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.mario.spring.controller.RootController;
import com.mario.spring.dto.SignupForm;
import com.mario.spring.entities.User;
import com.mario.spring.repositories.UserRepository;

@Component
public class SignupFormValidator extends LocalValidatorFactoryBean {

	private static Logger logger = LoggerFactory
			.getLogger(SignupFormValidator.class);
	
	UserRepository userRepository;
	
	@Resource
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(SignupForm.class);
	}
	
	@Override
	public void validate(Object target, Errors errors,	Object... validationHints) {
		
		super.validate(target, errors, validationHints);
		
		
		if(!errors.hasErrors()) {
			
			SignupForm signupForm = (SignupForm) target;
			
			User user = userRepository.findByEmail(signupForm.getEmail());
			logger.info("Validando el email...");
			if(user != null) {
				errors.rejectValue("email", "emailNotUnique");
			}
			
		}
		
	}
	
}
