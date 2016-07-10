package com.mario.spring.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mario.spring.dto.UserEditForm;
import com.mario.spring.entities.User;
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
	
	@RequestMapping(value="/{userId}/edit",method = RequestMethod.GET)
	public String edit(@PathVariable("userId") long userId, Model model) {
		
		User user = userService.findOne(userId);
		
		UserEditForm form = new UserEditForm();
		form.setName(user.getName());
		form.setRoles(user.getRoles());
		
		model.addAttribute(form);
		
		return "user-edit";
	}
	
	@RequestMapping(value="/{userId}/edit",method = RequestMethod.POST)
	public String edit(@PathVariable("userId") long userId, @ModelAttribute("userEditForm") @Valid UserEditForm userEditForm,
			BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) throws ServletException {
		
		if(result.hasErrors()) {
			return "user-edit";
		}
		
		userService.update(userId, userEditForm);
		MyUtil.flash(redirectAttributes, "success", "editSuccessful");
		request.logout();
		
		return "redirect:/";
	}
	
	
	
}
