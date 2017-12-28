package com.livingoncodes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.livingoncodes.domain.Role;
import com.livingoncodes.domain.User;
import com.livingoncodes.dto.RegistrationForm;
import com.livingoncodes.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

	private UserRepository repository;
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public User registerNewUserAccount(RegistrationForm registrationForm) throws DuplicateEmailException {
		
		if( repository.findByEmail(registrationForm.getEmail()) != null ) {
			throw new DuplicateEmailException("The email address: " + registrationForm.getEmail()
					+ " is already in use.");
		}
		
		String encodedPassword = passwordEncoder.encode(registrationForm.getPassword());
		
		User.Builder user = User.getBuilder()
				.email(registrationForm.getEmail())
				.firstName(registrationForm.getFirstName())
				.lastName(registrationForm.getLastName())
				.role(Role.ROLE_USER)
				.enabled(true)
				.locked(false)
				.password(encodedPassword);
				
		User registered = user.build();
		
		
		
		return repository.save(registered);
	}
	
	
	
}