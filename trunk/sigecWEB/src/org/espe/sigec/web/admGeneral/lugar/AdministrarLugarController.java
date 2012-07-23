package org.espe.sigec.web.admGeneral.lugar;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Establecimiento;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author diego
 *
 */
@ManagedBean(name = "administrarLugarController")
@ViewScoped
public class AdministrarLugarController {

	@Inject
	AdmGeneralServicio admGeneralServicio;
	
	Establecimiento lugarCurso;
	private boolean editMode;
	
	public AdministrarLugarController(){
		setLugarCurso((Establecimiento) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("lugarToEdit"));
	}
		
	public Establecimiento getLugarCurso() {
		return lugarCurso;
	}
	
	public void setLugarCurso(Establecimiento lugarCurso) {
		this.lugarCurso = lugarCurso;
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
			admGeneralServicio.editLugar(getLugarCurso());
			FacesUtils.addInfoMessage("Lugar actualizado exitosamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("Ha ocurrido un error al editar el lugar");
			e1.printStackTrace();
		}
	}
	
	public void btnReturnReporteLugares(ActionEvent e){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adm_reporte_lugar.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
