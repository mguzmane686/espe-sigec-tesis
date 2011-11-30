package org.espe.sigec.web.admGeneral.estudiante;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.web.utils.CommonController;
import org.espe.sigec.web.utils.FacesUtils;
import org.richfaces.component.UIPanelMenu;

@SuppressWarnings("serial")
@ManagedBean(name="detalleInscripcionController")
@SessionScoped
public class DetalleRegistroEstudianteController extends CommonController implements Serializable{

	private Estudiante estudiante;
	private boolean editMode;
	
	public DetalleRegistroEstudianteController() {
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
