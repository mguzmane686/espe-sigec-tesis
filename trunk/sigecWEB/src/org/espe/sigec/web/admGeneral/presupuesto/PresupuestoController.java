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

	public PresupuestoController(){
		setPresupuesto(new Presupuesto());
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
			Integer anioInicio = new Integer(0);
			Integer anioFin = new Integer(0);
			
			anioInicio = presupuesto.getFechaInicio().getYear();
			anioFin = presupuesto.getFechaFin().getYear();
			
			if (anioInicio != anioFin){
				FacesUtils.addInfoMessage("El rango de fecha debe ser para el mismo periodo");
			}
		else{		
			
			Collection<Presupuesto> lstTemp;
			lstTemp = admGeneralServicio.findPresupuesto(String.valueOf(anioInicio + 1900));
			
				if (lstTemp.size() > 0){
					FacesUtils.addInfoMessage("Ya existe un presupuesto asignado a ese periodo");
				}
				else{
					presupuesto.setRecursoActual(presupuesto.getRecursoInicial());
					presupuesto.setCodigoAnio(String.valueOf(anioInicio + 1900));
					
					Date fechaInicio = new Date();
					Date fechaFin = new Date();
					
					fechaInicio.setDate(1);
					fechaInicio.setMonth(0);
					fechaInicio.setYear(anioInicio);
					
					presupuesto.setFechaInicio(fechaInicio);
					
					fechaFin.setDate(31);
					fechaFin.setMonth(11);
					fechaFin.setYear(anioFin);
					
					presupuesto.setFechaFin(fechaFin);
			
					admGeneralServicio.createPresupuesto(getPresupuesto());
					generarCodigoPresupuesto(presupuesto.getIdPresupuesto());
					admGeneralServicio.editPresupuesto(getPresupuesto());
					setPresupuesto(new Presupuesto());
					FacesUtils.addInfoMessage("El presupuesto se cre&oacute exitosamente");
				}
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
