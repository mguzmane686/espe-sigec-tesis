package org.espe.sigec.web.admGeneral.presupuesto;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.commons.lang3.SerializationUtils;
import org.espe.sigec.model.entities.Presupuesto;
import org.espe.sigec.model.entities.PresupuestoDetalle;
import org.espe.sigec.model.entities.PresupuestoDetallePK;
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
	private Collection<PresupuestoDetalle> lstPresupuestoDetallesClone;
	
	public AdministrarPresupuestoController(){
		setPresupuesto((Presupuesto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("presupuestoToEdit"));
		
	}
	
	@PostConstruct
	public void cargarListas(){
		setLstPresupuestoDetalles(admGeneralServicio.findBuscarDetallePresupuesto(getPresupuesto().getIdPresupuesto()));
	}
	public void btnAgregarDetalle(){
		PresupuestoDetalle presupuestoDetalle = new PresupuestoDetalle();
		presupuestoDetalle.setPresupuestoDetallePK(new PresupuestoDetallePK());
		presupuestoDetalle.setEditMode(Boolean.TRUE);
		getLstPresupuestoDetalles().add(presupuestoDetalle);
	}
	
	public void btnEliminarDetalle(PresupuestoDetalle presupuestoDetalle){
		getLstPresupuestoDetalles().remove(presupuestoDetalle);
	}
	
	public void btnEdit(ActionEvent e){
		setEditMode(Boolean.TRUE);
		setLstPresupuestoDetallesClone((Collection<PresupuestoDetalle>)SerializationUtils.clone((Serializable)getLstPresupuestoDetalles()));
	}
	
	public void btnCancelEdit(ActionEvent e){
		setEditMode(Boolean.FALSE);
		setLstPresupuestoDetalles((Collection<PresupuestoDetalle>)SerializationUtils.clone((Serializable)getLstPresupuestoDetallesClone()));
	}
	
	public void btnSave(ActionEvent e){
		try {
			setEditMode(Boolean.FALSE);
			getPresupuesto().setLstPresupuestoDetalles(getLstPresupuestoDetalles());
			admGeneralServicio.editPresupuesto(getPresupuesto());
			FacesUtils.addInfoMessage("Presupuesto actualizado exitosamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("Ha ocurrido un error al editar el presupuesto");
			e1.printStackTrace();
		}
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

	public Collection<PresupuestoDetalle> getLstPresupuestoDetallesClone() {
		return lstPresupuestoDetallesClone;
	}

	public void setLstPresupuestoDetallesClone(
			Collection<PresupuestoDetalle> lstPresupuestoDetallesClone) {
		this.lstPresupuestoDetallesClone = lstPresupuestoDetallesClone;
	}
	
	
}
