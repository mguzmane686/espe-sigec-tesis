package org.espe.sigec.web.seguridad;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.servicio.seguridad.SeguridadServicio;
import org.espe.sigec.web.utils.FacesUtils;


@SuppressWarnings("serial")
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable{
	@Inject
	private SeguridadServicio loginServicio;
	private Usuario usuario;
	
	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService; // injected Spring defined service for bikes
	
	public LoginController() {
		usuario = new Usuario();
		usuario.setIdentificador("");
		usuario.setClave("");
//		authenticationService = new AuthenticationServiceImpl();
	}
	
	public void btnSignIn(ActionEvent e) {
		
		boolean success = authenticationService.login(getUsuario().getIdentificador(), getUsuario().getClave());
		
		if (success){
			Usuario usuario = loginServicio.validateLogin(getUsuario().getIdentificador(), getUsuario().getClave());
			if(null != usuario){
				UsuarioPerfil usuarioPerfil = loginServicio.getUsuarioPerfil(usuario);
				
				if(usuarioPerfil !=null){
				
					((HomeSessionController)FacesUtils.getManagedBean("homeSessionController")).setUsuarioPerfil(usuarioPerfil);
					try {
		//				((HomeSessionController)FacesUtils.getManagedBean("homeSessionController")).setLstModulos(loginServicio.getMenuByProfile(usuario));
		//				((HomeSessionController)FacesUtils.getManagedBean("homeSessionController")).setUiPanelMenu(FacesUtils.buildUserMenu(loginServicio.getMenuByProfile(usuario)));
						FacesContext.getCurrentInstance().getExternalContext().redirect("modules/home.jsf");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else{
					FacesUtils.addErrorMessage("El usuario no tiene un perfil");
				}
			}else{
				FacesUtils.addErrorMessage("Usuario o clave erroneas");
			} 
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login or password incorrect."));			
			try {
				FacesUtils.redirectPage("login.xml");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
	}
	public void btnLogOut(){
		try {
			FacesUtils.removeManagedBean("homeSessionController");
			FacesContext.getCurrentInstance().getExternalContext().redirect("../index.jsf");
			authenticationService.logout();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void btnCreateAccount(ActionEvent e){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("modules/registro_usuario.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

}
