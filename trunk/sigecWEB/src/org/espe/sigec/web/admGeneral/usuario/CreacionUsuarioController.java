package org.espe.sigec.web.admGeneral.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.espe.sigec.model.entities.Perfil;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.model.entities.UsuarioPerfilPK;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.servicio.seguridad.SeguridadServicio;
import org.espe.sigec.web.utils.FacesUtils;
import org.richfaces.event.DataScrollEvent;

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
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private UsuarioPerfil usuarioPerfil;
	private Collection<Perfil> lstPerfils;
	private Collection<UsuarioPerfil> lstPerfilesUsuario;
	private Collection<UsuarioPerfil> lstPerfilesUsuarioClone;
	
	private String filtroBusqueda;
	private String txtFiltroBusqueda;
	
	private Collection<Persona> lstPersonas;
	private Persona personaSeleccionada;
	public CreacionUsuarioController() {
		initEntities();
	}
	
	private void initEntities(){
		setUsuarioPerfil(new UsuarioPerfil());
		getUsuarioPerfil().setUsuarioPerfilPK(new UsuarioPerfilPK());
		getUsuarioPerfil().setPersona(new Persona());
		getUsuarioPerfil().setUsuario(new Usuario());
		setPersonaSeleccionada(new Persona());
		setFiltroBusqueda("usr");
		
	}
	@PostConstruct
	public void postInit(){
		setLstPerfils(seguridadServicio.findPerfiles());
	}
	
	/**
	 * Metodo que se jecuta desde la creacion del usuario
	 */
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
	
	/**
	 * Metodo para actualizar el usuario y su perfil
	 */
	public void btnActualizarUsuario(){
		try {
			Collection<Perfil> lstPerfilTMP = new ArrayList<Perfil>();
			for(UsuarioPerfil usuarioPerfil: getLstPerfilesUsuario()){
				if(usuarioPerfil.isSelected()){
					if(!usuarioPerfil.getPerfil().isSelected()){
						usuarioPerfil.getPerfil().setSelected(Boolean.TRUE);
						lstPerfilTMP.add(usuarioPerfil.getPerfil());
					}
				}else{
					if(usuarioPerfil.getPerfil().isSelected()){
						usuarioPerfil.getPerfil().setSelected(Boolean.FALSE);
						lstPerfilTMP.add(usuarioPerfil.getPerfil());
					}
				}
			}
			seguridadServicio.actualizarUsuarioPerfil(getPersonaSeleccionada(), lstPerfilTMP);
			FacesUtils.addInfoMessage("Usuario actualizado");
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Ocurrio un error al guardar el usuario");
		}
	}
	
	public void scrollListener(DataScrollEvent e){
		System.out.println("Pagina "+e.getPage());
		System.out.println("Old "+e.getOldScrolVal());
		System.out.println("New "+e.getNewScrolVal());
		System.out.println(e);
	}
	public void btnFindUsr(){
		setLstPersonas(admGeneralServicio.findPersonByCriteria(getFiltroBusqueda(), getTxtFiltroBusqueda().trim()));
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

	public Collection<Persona> getLstPersonas() {
		return lstPersonas;
	}

	public void setLstPersonas(Collection<Persona> lstPersonas) {
		this.lstPersonas = lstPersonas;
	}

	public Persona getPersonaSeleccionada() {
		return personaSeleccionada;
	}
	
	public void setPersonaSeleccionada(Persona personaSeleccionada) {
		this.personaSeleccionada = personaSeleccionada;
	}
	
	@SuppressWarnings("unchecked")
	public void setPersonaSeleccionadaBucarPerfil(Persona personaSeleccionada) {
		setLstPerfilesUsuario(new ArrayList<UsuarioPerfil>());
		try {
			Collection<UsuarioPerfil> lstUsrPerfil = seguridadServicio.findPerfilesUsuario(personaSeleccionada.getUsuario().getIdUsuario());
			for(Perfil perfil: getLstPerfils()){
				UsuarioPerfil usuarioPerfilTMP = new UsuarioPerfil( );
				usuarioPerfilTMP.setUsuarioPerfilPK(new UsuarioPerfilPK(personaSeleccionada.getUsuario().getIdUsuario(), perfil.getIdPerfil()));
				usuarioPerfilTMP.setPerfil(perfil);
				for(UsuarioPerfil usuarioPerfil: lstUsrPerfil){
					if(usuarioPerfil.getUsuarioPerfilPK().getIdPerfil().equals(perfil.getIdPerfil())){
						usuarioPerfilTMP.setSelected(Boolean.TRUE);
						usuarioPerfilTMP.getPerfil().setExistInBase(Boolean.TRUE);
						usuarioPerfilTMP.getPerfil().setSelected(Boolean.TRUE);
					}
				}
				getLstPerfilesUsuario().add(usuarioPerfilTMP);
				
			}
			lstPerfilesUsuarioClone =  (Collection<UsuarioPerfil>) SerializationUtils.clone((Serializable)getLstPerfilesUsuario()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.personaSeleccionada = personaSeleccionada;
	}

	public Collection<UsuarioPerfil> getLstPerfilesUsuario() {
		return lstPerfilesUsuario;
	}

	public void setLstPerfilesUsuario(Collection<UsuarioPerfil> lstPerfilesUsuario) {
		this.lstPerfilesUsuario = lstPerfilesUsuario;
	}
	
}
