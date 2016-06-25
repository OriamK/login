package com.mario.spring.controller;

import java.lang.reflect.Method;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mario.spring.dto.SignupForm;
import com.mario.spring.mail.MailSender;
import com.mario.spring.mail.SmtpMailSender;
import com.mario.spring.services.UserService;
import com.mario.spring.ultil.MyUtil;
import com.mario.spring.validators.SignupFormValidator;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RootController {
   
	private static Logger logger = LoggerFactory.getLogger(RootController.class); 
	private MailSender mailSender;
	private UserService userService;
	private SignupFormValidator signupFormValidator;
	
	@Autowired
	public RootController(MailSender mailSender, UserService userService,SignupFormValidator signupFormValidator) {
		this.mailSender = mailSender;
		this.userService = userService;
		this.signupFormValidator = signupFormValidator;
	}
	
	@InitBinder("signupForm")
	protected void initSignupBinder(WebDataBinder binder) {
		binder.setValidator(signupFormValidator);
	}
	
	
//    @RequestMapping("/")
//    public String index() throws MessagingException {
//    	
//    	mailSender.send("to", "s", "b");
//    	
//    	
//    	
//        return "home";
//    }
	
	@RequestMapping(value="/signup",method = RequestMethod.GET)
	public String singup(Model model) {
		
		model.addAttribute("signupForm", new SignupForm());		
		return "signup";		
	}
	
	@RequestMapping(value="/signup",method = RequestMethod.POST)
	public String singup(@ModelAttribute("signupForm") @Valid SignupForm signupForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return "signup";
		}		
		
		logger.info(signupForm.toString());
		userService.signup(signupForm);
		
		MyUtil.flash(redirectAttributes, "success", "signupSuccess");
				
		
		return "redirect:/";	
	}
    
 
}
