package org.espe.sigec.web.admGeneral.usuario;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name = "creacionUsuarioController")
@ViewScoped
public class CreacionUsuarioController implements Serializable{
	private UsuarioPerfil usuarioPerfil;
	
	public CreacionUsuarioController() {
		initEntities();
	}
	
	private void initEntities(){
		setUsuarioPerfil(new UsuarioPerfil());
		getUsuarioPerfil().setPersona(new Persona());
		getUsuarioPerfil().setUsuario(new Usuario());
		
	}
	
	public void btnCrearUsuario(){
		
	}
	
	public UsuarioPerfil getUsuarioPerfil() {
		return usuarioPerfil;
	}

	public void setUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
		this.usuarioPerfil = usuarioPerfil;
	}
	
}
