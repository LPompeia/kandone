package br.com.kandone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kandone.controller.dto.UserDTO;
import br.com.kandone.model.User;
import br.com.kandone.repository.UserRepository;
import br.com.kandone.service.exception.ResourceAlreadyCreateException;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
		
	public UserDTO registerUser(User user) {
		
		if(this.checkUserExistByUsernameOrEmail(user)) { 
			throw new ResourceAlreadyCreateException();
		}
		
		user.encodePasswordPersist();
		this.userRepository.save(user);
		
		return new UserDTO(user);
	}
	
	public Boolean checkUserExistByUsername(String username) { 
		
		Optional<User> userExists = this.userRepository.findByUsername(username);
		
		if(userExists.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkUserExistByUsernameOrEmail(User user) { 
		Optional<User> userFindByEmail = this.userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
		
		if(userFindByEmail.isEmpty()) {
			return false;
		}
		
		return true;
	}

}
