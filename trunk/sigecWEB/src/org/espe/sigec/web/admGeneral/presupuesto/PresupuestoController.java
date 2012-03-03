package org.espe.sigec.web.admGeneral.presupuesto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Presupuesto;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.GeneralFunctions;

@SuppressWarnings("serial")
@ManagedBean(name="presupuestoAdmController")
@ViewScoped
public class PresupuestoController implements Serializable{

	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Presupuesto presupuesto;

	@SuppressWarnings("deprecation")
	public PresupuestoController(){
		setPresupuesto(new Presupuesto());
		java.util.Date fecha = new Date();
		presupuesto.setCodigoAnio(String.valueOf(fecha.getYear()+1900));
	}
	
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	@SuppressWarnings("deprecation")
	public void btnSavePresupuesto(ActionEvent e){
		try {
					
			Collection<Presupuesto> lstTemp;
			lstTemp = admGeneralServicio.findPresupuesto(presupuesto.getCodigoAnio());
			
				if (lstTemp.size() > 0){
					FacesUtils.addInfoMessage("Ya existe un presupuesto asignado a ese periodo");
				}
				else{
					
					Date fechaInicio = new Date();
					Date fechaFin = new Date(); 
					
					fechaInicio.setDate(1);
					fechaInicio.setMonth(0);
					fechaInicio.setYear(Integer.valueOf(presupuesto.getCodigoAnio()) - 1900);
					
					fechaFin.setDate(31);
					fechaFin.setMonth(11);
					fechaFin.setYear(Integer.valueOf(presupuesto.getCodigoAnio()) - 1900);
			
					presupuesto.setRecursoActual(presupuesto.getRecursoInicial());
					presupuesto.setFechaInicio(fechaInicio);
					presupuesto.setFechaFin(fechaFin);
					
					admGeneralServicio.createPresupuesto(getPresupuesto());
					generarCodigoPresupuesto(presupuesto.getIdPresupuesto());
					admGeneralServicio.editPresupuesto(getPresupuesto());
					setPresupuesto(new Presupuesto());
					FacesUtils.addInfoMessage("El presupuesto se cre&oacute exitosamente");
				}
						
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("No se pudo guardar el presupuesto");
		}
	}
	
	private void generarCodigoPresupuesto(int nextRegistro){
		int cantidadNumeros;
		
		String prefijo;
		String sufijo;
		
		GeneralFunctions generalFunctions = new GeneralFunctions();
		
		cantidadNumeros = Integer.toString(nextRegistro).length();
		
		prefijo = "PRE";		
		sufijo = generalFunctions.completarCeros(nextRegistro, cantidadNumeros);
		
		presupuesto.setIdPrefijoPresupuesto(prefijo + sufijo);
		
	}
	
}
