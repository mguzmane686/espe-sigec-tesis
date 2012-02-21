package org.espe.sigec.web.admGeneral.usuario;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Perfil;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.model.entities.UsuarioPerfilPK;
import org.espe.sigec.servicio.seguridad.SeguridadServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name = "creacionUsuarioController")
@ViewScoped
public class CreacionUsuarioController implements Serializable{
	@Inject
	private SeguridadServicio seguridadServicio;
	
	private UsuarioPerfil usuarioPerfil;
	private Collection<Perfil> lstPerfils;
	
	public CreacionUsuarioController() {
		initEntities();
	}
	
	private void initEntities(){
		setUsuarioPerfil(new UsuarioPerfil());
		getUsuarioPerfil().setUsuarioPerfilPK(new UsuarioPerfilPK());
		getUsuarioPerfil().setPersona(new Persona());
		getUsuarioPerfil().setUsuario(new Usuario());
		
	}
	@PostConstruct
	public void postInit(){
		setLstPerfils(seguridadServicio.findPerfiles());
	}
	
	public void btnCrearUsuario(){
		try {
			seguridadServicio.crearUsuario(getUsuarioPerfil());
			FacesUtils.addInfoMessage("Usuario creado");
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Ocurrio un error al guardar el usuario");
			e.printStackTrace();
		}
	}
	
	public UsuarioPerfil getUsuarioPerfil() {
		return usuarioPerfil;
	}

	public void setUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
		this.usuarioPerfil = usuarioPerfil;
	}

	public Collection<Perfil> getLstPerfils() {
		return lstPerfils;
	}

	public void setLstPerfils(Collection<Perfil> lstPerfils) {
		this.lstPerfils = lstPerfils;
	}
	
}
