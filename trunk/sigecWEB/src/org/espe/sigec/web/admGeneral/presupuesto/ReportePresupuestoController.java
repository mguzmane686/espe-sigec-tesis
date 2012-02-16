package org.espe.sigec.web.admGeneral.presupuesto;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Presupuesto;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name="reportePresupuestoController")
@ViewScoped
public class ReportePresupuestoController implements Serializable{

	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	Collection<Presupuesto> lstPresupuestos;
	
	public ReportePresupuestoController(){
		setLstPresupuestos(new ArrayList<Presupuesto>());
	}
	
	@PostConstruct
	public void loadPresupuestos(){
		setLstPresupuestos(admGeneralServicio.findPresupuesto());
	}

	public Collection<Presupuesto> getLstPresupuestos() {
		return lstPresupuestos;
	}

	public void setLstPresupuestos(Collection<Presupuesto> lstPresupuestos) {
		this.lstPresupuestos = lstPresupuestos;
	}
	
	public void btnShowPresupuestoDetail(Presupuesto presupuesto){
		FacesUtils.putFlashObject("presupuestoToEdit",presupuesto);
		try {
			FacesUtils.redirectPage("adm_administrar_presupuesto.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}	
	
}
