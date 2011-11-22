package org.espe.sigec.web.registro;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.sessionBeans.PersonaFacadeLocal;
import org.espe.sigec.model.sessionBeans.ProfesorFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioFacadeLocal;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.SigecConstantes;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="registroDocenteController")
@ViewScoped
public class RegistroDocenteController implements Serializable{
	@EJB
	private ProfesorFacadeLocal profesorFacadeLocal;
	@EJB
	private UsuarioFacadeLocal usuarioFacadeLocal;
	@EJB
	private PersonaFacadeLocal personaFacadeLocal;
	
	private Profesor profesor;
	private Usuario usuario;
	
	public RegistroDocenteController() {
		initEntities();
	}

	private void initEntities(){
		setProfesor(new Profesor());
		getProfesor().setTituloNivelTres(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
		getProfesor().setExperiencia((double) 2);
		getProfesor().setPersona(new Persona());
		setUsuario(new Usuario());
	}
	public void btnSaveProfesor(ActionEvent e){
		try {
			getUsuario().setIdentificador(getProfesor().getPersona().getCedula());
			getUsuario().setClave(getProfesor().getPersona().getCedula());
			usuarioFacadeLocal.create(getUsuario());
			profesor.getPersona().setUsuario(getUsuario());
			personaFacadeLocal.create(profesor.getPersona());
			profesorFacadeLocal.create(profesor);
			initEntities();
			FacesUtils.addInfoMessage("El docente fue agregado correctamente");
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("Ocurrio un error al agregar al docente");
		}
	}
	
	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
