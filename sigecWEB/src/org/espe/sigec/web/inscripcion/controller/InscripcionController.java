package org.espe.sigec.web.inscripcion.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.commons.lang3.SerializationUtils;
import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.servicio.inscripcion.InscripcionServicio;
import org.espe.sigec.web.utils.CommonController;
import org.espe.sigec.web.utils.FacesUtils;
import org.richfaces.component.UIPanelMenu;

/**
 * @author Roberto Chasipanta, Diego Ramos
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="inscripcion")
@ViewScoped
public class InscripcionController extends CommonController{
	
	@Inject
	private InscripcionServicio inscripcionServicio;
	
	private Estudiante estudiante;
	
	
	public InscripcionController() {
		setUiPanelMenu((UIPanelMenu) FacesUtils.getFlashObject("menuSigec"));
		instanciarEntidades();
	}
	
	private void instanciarEntidades(){
		setEstudiante(new Estudiante());
		getEstudiante().setPersona(new Persona()); 
		getEstudiante().getPersona().setUsuario(new Usuario());
	}

	public void btnGuardarEstudiante(ActionEvent e){
		try {
			inscripcionServicio.registrarEstudiante(getEstudiante().getPersona().getUsuario(), getEstudiante().getPersona(), getEstudiante());
			
			FacesUtils.putFlashObject("estudiante", SerializationUtils.clone(getEstudiante()));
			FacesContext.getCurrentInstance().getExternalContext().redirect("detalle_inscripcion.jsf");
			
			instanciarEntidades();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}	
}
