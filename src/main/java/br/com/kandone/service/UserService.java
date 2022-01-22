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
	
	@Autowired
	BoardService boardService;
		
	public UserDTO registerUser(User user) {
		
		if(this.checkUserExistByUsernameOrEmail(user)) { 
			throw new ResourceAlreadyCreateException();
		}
		
		user.encodePasswordPersist();
		this.userRepository.save(user);
		this.boardService.createBoardDefault(user);
		
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
		Optional<User> userFindByEmail = this.userRepository.findByUsername(user.getUsername());
		
		if(userFindByEmail.isEmpty()) {
			return false;
		}
		
		return true;
	}

	public Boolean checkUserExistsByEmail(String email) {
		Optional<User> userFindByEmail = this.userRepository.findByEmail(email);
		if(userFindByEmail.isEmpty()) { 
			return false;
		}
		return true;
	}

}
