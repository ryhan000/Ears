package com.livingoncodes.service;

import com.livingoncodes.domain.User;
import com.livingoncodes.dto.RegistrationForm;

public interface UserService {

	public User findByEmail(String email);
	
	//public User registerNewUserAccount(RegistrationForm registrationForm);
	public User registerNewUserAccount(RegistrationForm registrationForm) throws DuplicateEmailException;
}
