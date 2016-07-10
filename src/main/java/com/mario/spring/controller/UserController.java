package com.mario.spring.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mario.spring.services.UserService;
import com.mario.spring.ultil.MyUtil;

@Controller
@RequestMapping("/users")
public class UserController {

	private static Logger logger = LoggerFactory
			.getLogger(UserController.class);
	
	UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/{verificationCode}/verify")
	public String verify(@PathVariable("verificationCode") String verificationCode,RedirectAttributes redirectAttributes,
						HttpServletRequest request) throws ServletException {
		
		logger.info("Antes de la verificacion");
		userService.verify(verificationCode);
		
		logger.info("Despues de la verificacion");
		
		MyUtil.flash(redirectAttributes, "success", "verificationSuccess");	
		request.logout();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/{userId}")
	public String getById(@PathVariable("userId") long userId, Model model) {
		
		model.addAttribute("user",userService.findOne(userId));
		
		return "user";
	}
	
	
	
	
}
