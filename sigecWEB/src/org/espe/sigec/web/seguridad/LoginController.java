package org.espe.sigec.web.seguridad;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.servicio.seguridad.SeguridadServicio;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController implements Serializable{
	@Inject
	private SeguridadServicio loginServicio;
	private Usuario usuario;
	
	public LoginController() {
		usuario = new Usuario();
		usuario.setIdentificador("");
		usuario.setClave("");
	}
	
	public void btnSignIn(ActionEvent e) {
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
