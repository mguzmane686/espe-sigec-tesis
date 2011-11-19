package org.espe.sigec.web.inscripcion.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.web.utils.CommonController;
import org.espe.sigec.web.utils.FacesUtils;
import org.richfaces.component.UIPanelMenu;

@SuppressWarnings("serial")
@ManagedBean(name="detalleInscripcionController")
@SessionScoped
public class DetalleInscripcionController extends CommonController implements Serializable{

	private Estudiante estudiante;
	private boolean editMode;
	
	public DetalleInscripcionController() {
		setUiPanelMenu((UIPanelMenu) FacesUtils.getFlashObject("menuSigec"));
		setEstudiante((Estudiante) FacesUtils.getFlashObject("estudiante"));
		if(getEstudiante()==null){
			instanciarEntidades();
		}
	}
	private void instanciarEntidades(){
		setEstudiante(new Estudiante());
		getEstudiante().setPersona(new Persona()); 
		getEstudiante().getPersona().setUsuario(new Usuario());
	}
	public void btnEditUser(){
		setEditMode(Boolean.TRUE);
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}


	public boolean isEditMode() {
		return editMode;
	}


	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
}
