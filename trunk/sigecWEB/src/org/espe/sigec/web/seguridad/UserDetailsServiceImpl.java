package org.espe.sigec.web.seguridad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * Spring-security requires an implementation of UserDetailService. 
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	/* 
	 * Mock for users from database. 
	 * In the real application users will be retrieved from database and 
	 * proper Spring UserDetails object will be created then for each database user. 
	 */
	private HashMap<String, org.springframework.security.core.userdetails.User> users = new HashMap<String, org.springframework.security.core.userdetails.User>();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		
		Context initContext = null;
		Connection conn = null;
		String usuario = null;
		String clave = null;
		try {
			initContext = new InitialContext();
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		DataSource ds = null;
		try {
//			ds = (DataSource)envContext.lookup("java:/PostgresDS");
			ds = (DataSource) initContext.lookup("java:/sigec_pool");
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int cont=0;
		try {
			conn = ds.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select identificador,clave from usuario where identificador = '"+username+"'");
			
			while(rs.next()){
//				getLstItemsUsuario().add(new SelectItem(rs.getString(1), rs.getString(2)));
				usuario = rs.getString(1);
				clave = rs.getString(2);
				cont++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		org.springframework.security.core.userdetails.User user = null;   
		if(cont>0){
			// sample roles		
			Collection<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
			adminAuthorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
			
			Collection<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
			userAuthorities.add(new GrantedAuthorityImpl("ROLE_REGISTERED"));
			
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			
			// sample users with roles set
			users.put(username, new org.springframework.security.core.userdetails.User(usuario, clave, enabled, accountNonExpired,
					credentialsNonExpired, accountNonLocked, adminAuthorities));
			
			user = users.get(username);
		}
		
		
		if (user == null) {
			throw new UsernameNotFoundException("UserAccount for name \""+ username + "\" not found.");
		}
		
		return user;
	}

//	@PostConstruct
	public void init() {
		
		// sample roles		
		Collection<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
		adminAuthorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		
		Collection<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
		userAuthorities.add(new GrantedAuthorityImpl("ROLE_REGISTERED"));
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		// sample users with roles set
		users.put("admin", new org.springframework.security.core.userdetails.User("admin", "admin", enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, adminAuthorities));
		
		users.put("user", new org.springframework.security.core.userdetails.User("user", "user", enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, userAuthorities));
	}
}
