package br.com.kandone.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.kandone.model.User;
import br.com.kandone.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByEmail(username);
		
		if(user.isEmpty()) { 
			throw new UsernameNotFoundException("User/Password incorrects");
		}
		
		return user.get();
	}
	
	public String recoveryEmailAuthentication()  { 
		String usernameAuthentication = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> userFindEmail = userRepository.findByUsername(usernameAuthentication);

		if(userFindEmail.isEmpty()) { 
			throw new UsernameNotFoundException("User not found during recovery email with token");
		}
		
		return userFindEmail.get().getEmail();
	}

}
