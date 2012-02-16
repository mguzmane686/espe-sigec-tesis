package org.espe.sigec.web.admGeneral.especialidad;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author diego
 *
 */
@ManagedBean(name = "administrarEspecialidadController")
@ViewScoped
public class AdministrarEspecialidadController {

	@Inject
	AdmGeneralServicio admGeneralServicio;
	
	private Especialidad especialidad;
	private boolean editMode;
	
	public AdministrarEspecialidadController(){
		setEspecialidad((Especialidad) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("especialidadToEdit"));
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	public void btnEdit(ActionEvent e){
		setEditMode(Boolean.TRUE);
	}
	
	public void btnCancelEdit(ActionEvent e){
		setEditMode(Boolean.FALSE);
	}
	
	public void btnSave(ActionEvent e){
		try {
			setEditMode(Boolean.FALSE);
			admGeneralServicio.editEspecialidad(getEspecialidad());
			FacesUtils.addInfoMessage("Especialidad actualizada exitosamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("Ha ocurrido un error al editar la especialidad");
			e1.printStackTrace();
		}
	}
	
	public void btnReturnReporteEspecialidades(ActionEvent e){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adm_reporte_especialidad.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
