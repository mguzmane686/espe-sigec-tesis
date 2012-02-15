package org.espe.sigec.web.admGeneral.aula;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author diego
 *
 */
@ManagedBean(name = "administrarAulaController")
@ViewScoped
public class AdministrarAulaController {
	
	@Inject
	AdmGeneralServicio admGeneralServicio;
	
	Aula aula;
	private boolean editMode;
	
	public AdministrarAulaController(){
		setAula((Aula) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("aulaToEdit"));
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
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
			admGeneralServicio.editAula(getAula());
			FacesUtils.addInfoMessage("Aula actualizada exitosamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("Ha ocurrido un error al editar el aula");
			e1.printStackTrace();
		}
	}
	
	public void btnReturnReporteAulas(ActionEvent e){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adm_reporte_aula.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
