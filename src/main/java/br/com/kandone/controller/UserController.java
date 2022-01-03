package br.com.kandone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.kandone.config.security.TokenService;
import br.com.kandone.controller.dto.TokenDto;
import br.com.kandone.controller.dto.UserDTO;
import br.com.kandone.controller.form.LoginFormDTO;
import br.com.kandone.model.User;
import br.com.kandone.service.UserService;
import br.com.kandone.service.exception.ResourceAlreadyCreateException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> signup(@RequestBody User user) { 
		try {
			UserDTO userSaved = this.userService.registerUser(user);
			return new ResponseEntity<UserDTO>(userSaved, HttpStatus.CREATED);
		} catch(ResourceAlreadyCreateException exception) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client already exists");
		}
	}
	
	@GetMapping("/exists/{username}") 
	public ResponseEntity<Boolean> userExists(@PathVariable String username) {
		Boolean statusUser = this.userService.checkUserExistByUsername(username);
		return new ResponseEntity<Boolean>(statusUser, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenDto> authentication(@RequestBody LoginFormDTO loginFormDto) { 
		UsernamePasswordAuthenticationToken dadosLogin = loginFormDto.converter();
		try {
			Authentication authentication =	authManager.authenticate(dadosLogin);
			String token = tokenService.generatedToken(authentication);
			return new ResponseEntity<TokenDto>(new TokenDto(token, "Bearer"), HttpStatus.OK);
		} catch(AuthenticationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect credentials");
		}
	}
	
}
