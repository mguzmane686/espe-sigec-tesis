package org.espe.sigec.web.controller.login;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.servicio.login.LoginServicio;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController implements Serializable{
	@Inject
	private LoginServicio loginServicio;
	private Usuario usuario;
	
	public LoginController() {
		usuario = new Usuario();
		usuario.setIdentificador("maniac787");
		usuario.setClave("qwe123");
	}
	
	public void btnSignIn(ActionEvent e) {
		Usuario usuario = loginServicio.validateLogin(getUsuario().getIdentificador(), getUsuario().getClave());
		if(null != usuario){
			
			try {
//				((HomeSessionController)FacesUtils.getManagedBean("homeSessionController")).setLstModulos(loginServicio.getMenuByProfile(usuario));
//				((HomeSessionController)FacesUtils.getManagedBean("homeSessionController")).setUiPanelMenu(FacesUtils.buildUserMenu(loginServicio.getMenuByProfile(usuario)));
				FacesContext.getCurrentInstance().getExternalContext().redirect("modules/home.jsf?faces-redirect=true");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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

}
