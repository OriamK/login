package com.mario.spring.validators;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


import com.mario.spring.dto.ForgotPasswordForm;
import com.mario.spring.dto.ResetPasswordForm;
import com.mario.spring.entities.User;
import com.mario.spring.repositories.UserRepository;

@Component
public class ResetPasswordFormValidator extends LocalValidatorFactoryBean {

	private static Logger logger = LoggerFactory
			.getLogger(ResetPasswordFormValidator.class);
	
	UserRepository userRepository;
	
	@Resource
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(ResetPasswordForm.class);
	}
	
	@Override
	public void validate(Object target, Errors errors,	Object... validationHints) {
		
		super.validate(target, errors, validationHints);
		
		
		if(!errors.hasErrors()) {
			
			ResetPasswordForm resetPasswordForm = (ResetPasswordForm) target;
			
			if(!resetPasswordForm.getPassword().equals(resetPasswordForm.getRetypePassword())) {
				errors.reject("passwordDoNotMatch");
			}
			
		}
		
	}
	
}
