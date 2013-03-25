package org.espe.sigec.web.admGeneral.presupuesto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Presupuesto;
import org.espe.sigec.model.entities.PresupuestoDetalle;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author diego
 *
 */
@ManagedBean(name = "administrarPresupuestoController")
@ViewScoped
public class AdministrarPresupuestoController {

	@Inject
	AdmGeneralServicio admGeneralServicio;
	
	private Presupuesto presupuesto;
	private boolean editMode;
	
	private Collection<PresupuestoDetalle> lstPresupuestoDetalles;
	
	public AdministrarPresupuestoController(){
		setPresupuesto((Presupuesto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("presupuestoToEdit"));
		setLstPresupuestoDetalles(new ArrayList<PresupuestoDetalle>());
	}
	
	public void btnAgregarDetalle(){
		getLstPresupuestoDetalles().add(new PresupuestoDetalle());
	}
	
	public void btnEliminarDetalle(PresupuestoDetalle presupuestoDetalle){
		getLstPresupuestoDetalles().remove(presupuestoDetalle);
	}
	
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
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
			admGeneralServicio.editPresupuesto(getPresupuesto());
			FacesUtils.addInfoMessage("Presupuesto actualizado exitosamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("Ha ocurrido un error al editar el presupuesto");
			e1.printStackTrace();
		}
	}
	
	public void btnReturnReportePresupuesto(ActionEvent e){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adm_reporte_presupuesto.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public Collection<PresupuestoDetalle> getLstPresupuestoDetalles() {
		return lstPresupuestoDetalles;
	}

	public void setLstPresupuestoDetalles(
			Collection<PresupuestoDetalle> lstPresupuestoDetalles) {
		this.lstPresupuestoDetalles = lstPresupuestoDetalles;
	}
	
	
}
