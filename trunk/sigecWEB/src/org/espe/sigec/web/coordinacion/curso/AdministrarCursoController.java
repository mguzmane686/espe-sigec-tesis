package org.espe.sigec.web.coordinacion.curso;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author roberto
 *
 */
@ManagedBean(name = "administrarCursoController")
@ViewScoped
public class AdministrarCursoController {
	private CursoPeriodo cursoPeriodo;
	private boolean editMode;
	
	public AdministrarCursoController() {
		setCursoPeriodo((CursoPeriodo) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("curso"));
	}

	public void btnEdit(ActionEvent e){
		setEditMode(Boolean.TRUE);
	}
	
	public void btnCancelEdit(ActionEvent e){
		setEditMode(Boolean.FALSE);
	}
	
	public void btnSave(ActionEvent e){
		setEditMode(Boolean.FALSE);
	}
	
	public void btnPresupuestoCurso(ActionEvent e){
		FacesUtils.putFlashObject("cursoPeriodo", getCursoPeriodo());
		try {
			FacesUtils.redirectPage("presupuestos.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void btnReturnCursosAbiertos(ActionEvent e){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("coor_reporte_curso_abierto.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public CursoPeriodo getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
	}


	public boolean isEditMode() {
		return editMode;
	}


	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
}
