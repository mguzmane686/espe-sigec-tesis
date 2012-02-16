package org.espe.sigec.web.admGeneral.presupuesto;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Presupuesto;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name="presupuestoAdmController")
@ViewScoped
public class PresupuestoController implements Serializable{

	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Presupuesto presupuesto;

	public PresupuestoController(){
		setPresupuesto(new Presupuesto());
	}
	
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	public void btnSavePresupuesto(ActionEvent e){
		try {
			admGeneralServicio.createPresupuesto(getPresupuesto());
			setPresupuesto(new Presupuesto());
			FacesUtils.addInfoMessage("El presupuesto se cre&oacute exitosamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("No se pudo guardar el presupuesto");
		}
	}
	
}
