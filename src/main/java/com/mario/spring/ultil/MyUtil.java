package com.mario.spring.ultil;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mario.spring.controller.RootController;
import com.mario.spring.dto.UserDetailsImpl;
import com.mario.spring.entities.User;

@Component
public class MyUtil {
	
	private static Logger logger = LoggerFactory
			.getLogger(MyUtil.class);
	private static MessageSource messageSource;

	@Autowired
	public MyUtil(MessageSource messageSource) {
		MyUtil.messageSource = messageSource;
	}

	public static void flash(RedirectAttributes redirectAttributes,
			String kind, String messageKey) {

		redirectAttributes.addFlashAttribute("flashKind", kind);
		redirectAttributes.addFlashAttribute("flashMessage",
				MyUtil.getMessage(messageKey));

	}

	private static String hostAndPort;

	@Value("${hostAndPort}")
	public void setHostAndPort(String hostAndPort) {
		MyUtil.hostAndPort = hostAndPort;
	}

	public static String hostUrl() {
		return (isDev() ? "http://" : "http://") + hostAndPort;
	}

	private static String activeProfiles;

	@Value("${spring.profiles.active}")
	public void setActiveProfiles(String activeProfiles) {

		MyUtil.activeProfiles = activeProfiles;
	}

	public static boolean isDev() {
		return activeProfiles.equals("dev");
	}

	public static String getMessage(String messageKey, Object... args) {

		return messageSource.getMessage(messageKey, args, Locale.getDefault());
	}

	public static void validate(boolean valid, String msgContent, Object... args) {
		
		if(!valid) {
			throw new RuntimeException(getMessage(msgContent, args));
		}
		
	}

	public static User getSessionUser() {
		
		UserDetailsImpl auth = getAuth();
		
		logger.info("Obteniendo autentificacion");
		
		return auth == null ? null : auth.getUser();
	}
	
	public static UserDetailsImpl getAuth() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth!=null) {
			Object principal = auth.getPrincipal();
			if(principal instanceof UserDetailsImpl) {
				return (UserDetailsImpl) principal;
			}
		}
		return null;
	}

}
