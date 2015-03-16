package org.espe.sigec.web.seguridad;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService , UserDetailsService{
	
	
	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager; // specific for Spring Security

	@Override
	public boolean login(String username, String password) {
		try {
			
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authenticate);				
				return true;
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		//currentUser.unauthenticate();
	}

	
	private HashMap<String, org.springframework.security.core.userdetails.User> users = new HashMap<String, org.springframework.security.core.userdetails.User>();
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		org.springframework.security.core.userdetails.User user = users.get(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("UserAccount for name \"" + username + "\" not found.");
		}
		
		return user;
	}

	
}
