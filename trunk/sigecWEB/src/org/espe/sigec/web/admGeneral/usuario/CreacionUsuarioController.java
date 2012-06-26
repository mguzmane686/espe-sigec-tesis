package org.espe.sigec.web.admGeneral.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
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
	
	private String filtroBusqueda;
	private String txtFiltroBusqueda;
	
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
			getUsuarioPerfil().setLstPerfils(new ArrayList<Perfil>());
			for(Perfil perfil: getLstPerfils()){
				if(perfil.isSelected()){
					getUsuarioPerfil().getLstPerfils().add(perfil);
				}
			}
			
			if(CollectionUtils.isEmpty(getUsuarioPerfil().getLstPerfils())){
				FacesUtils.addErrorMessage("Seleccione al menos un perfil");
				throw new Exception("Seleccione al menos un perfil");
			}
			seguridadServicio.crearUsuario(getUsuarioPerfil());
			FacesUtils.addInfoMessage("Usuario creado");
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Ocurrio un error al guardar el usuario");
		}
	}
	
	public void btnFindUsr(){
		System.out.println(getTxtFiltroBusqueda());
		System.out.println(getFiltroBusqueda());
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

	public String getFiltroBusqueda() {
		return filtroBusqueda;
	}

	public void setFiltroBusqueda(String filtroBusqueda) {
		this.filtroBusqueda = filtroBusqueda;
	}

	public String getTxtFiltroBusqueda() {
		return txtFiltroBusqueda;
	}

	public void setTxtFiltroBusqueda(String txtFiltroBusqueda) {
		this.txtFiltroBusqueda = txtFiltroBusqueda;
	}	
	
}
