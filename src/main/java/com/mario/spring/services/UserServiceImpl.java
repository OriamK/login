package com.mario.spring.services;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.validation.BindingResult;

import com.mario.spring.controller.RootController;
import com.mario.spring.dto.ForgotPasswordForm;
import com.mario.spring.dto.ResetPasswordForm;
import com.mario.spring.dto.SignupForm;
import com.mario.spring.dto.UserDetailsImpl;
import com.mario.spring.entities.User;
import com.mario.spring.entities.User.Role;
import com.mario.spring.mail.MailSender;
import com.mario.spring.repositories.UserRepository;
import com.mario.spring.ultil.MyUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

	private static Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private MailSender mailSender;

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			PasswordEncoder passwordEncoder, MailSender mailSender) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.mailSender = mailSender;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void signup(SignupForm signupForm) {

		User user = new User();
		user.setEmail(signupForm.getEmail());
		user.setName(signupForm.getName());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		user.getRoles().add(Role.UNVERIFIED);
		user.setVerificationCode(RandomStringUtils.randomAlphanumeric(16));
		userRepository.save(user);
		logger.info(user.toString());
		String verifyLink = MyUtil.hostUrl() + "/users/"
				+ user.getVerificationCode() + "/verify";

		TransactionSynchronizationManager
				.registerSynchronization(new TransactionSynchronizationAdapter() {

					@Override
					public void afterCommit() {
						try {
							mailSender.send(user.getEmail(), MyUtil
									.getMessage("verifySubject"), MyUtil
									.getMessage("verifyEmail", verifyLink));
							logger.info("Verification mail to "
									+ user.getEmail() + " queued.");
						} catch (MessagingException e) {
							logger.error(ExceptionUtils.getStackTrace(e));
						}
					}
				});
		// int j= 20/0;
	}

	@Override
	public List<User> selectAll() {

		return userRepository.findAll();
	}

	// @Override
	// public void delete(int id) {
	//
	// userRepository.delete((long) id);
	// }

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void verify(String verificationCode) {

		long loggedInUserId = MyUtil.getSessionUser().getId();
		User user = userRepository.findOne(loggedInUserId);

		MyUtil.validate(user.getRoles().contains(Role.UNVERIFIED),
				"alreadyVerified");
		MyUtil.validate(user.getVerificationCode().equals(verificationCode),
				"incorrect", "verification code");

		user.getRoles().remove(Role.UNVERIFIED);
		user.setVerificationCode(null);
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);
		logger.info("Usuario " +user);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
	
		return new UserDetailsImpl(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void forgotPassword(ForgotPasswordForm forgotPasswordForm) {

		User user = userRepository.findByEmail(forgotPasswordForm.getEmail());
		String forgotPasswordCode = RandomStringUtils
				.randomAlphanumeric(User.RANDOM_CODE_LENGTH);

		user.setForgotPasswordCode(forgotPasswordCode);

		User savedUser = userRepository.save(user);

		TransactionSynchronizationManager
				.registerSynchronization(new TransactionSynchronizationAdapter() {

					@Override
					public void afterCommit() {
						try {
							mailForgotPasswordLink(savedUser);
							logger.info("Verification mail to "
									+ user.getEmail() + " queued.");
						} catch (MessagingException e) {
							logger.error(ExceptionUtils.getStackTrace(e));
						}
					}
				});

	}

	private void mailForgotPasswordLink(User user) throws MessagingException {

		String forgotPasswordLink = MyUtil.hostUrl() + "/reset-password/"
				+ user.getForgotPasswordCode();
		mailSender.send(user.getEmail(),
				MyUtil.getMessage("forgotPasswordSubject"),
				MyUtil.getMessage("forgotPasswordEmail", forgotPasswordLink));

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void resetPassword(String forgotPasswordCode,
			ResetPasswordForm resetPasswordForm, BindingResult result) {

		User user = userRepository.findByForgotPasswordCode(forgotPasswordCode);

		if (user == null) {

			result.reject("invalidForgotPassword");
		}

		if (result.hasErrors())
			return;

		user.setForgotPasswordCode(null);
		user.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword()
				.trim()));
		userRepository.save(user);
	}
}
