package com.mario.spring.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mario.spring.entities.User;

public class SignupForm {

	@NotNull
	@Size(min=1, max=255)
	@Pattern(regexp=User.EMAIL_PATTERN,
				message="{emailPatternError}")
	private String email;
	
	@NotNull
	@Size(min=1, max=User.NAME_MAX,message="{nameSizeError}")
	private String name;
	
	@NotNull
	@Size(min=1, max=User.PASSWORD_MAX)
	private String password;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignupForm [email=" + email + ", name=" + name + ", password="
				+ password + "]";
	}
	
	
	
	
}
