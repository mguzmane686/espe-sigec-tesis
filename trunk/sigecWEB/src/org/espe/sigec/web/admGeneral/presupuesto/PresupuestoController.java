package org.espe.sigec.web.admGeneral.presupuesto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

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
		initController();
	}
	
	private void initController(){
		setPresupuesto(new Presupuesto());
		presupuesto.setCodigoAnio(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
	}
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	public void btnSavePresupuesto(ActionEvent e){
		try {
					
			Collection<Presupuesto> lstTemp;
			lstTemp = admGeneralServicio.findPresupuesto(presupuesto.getCodigoAnio());
			
				if (lstTemp.size() > 0){
					FacesUtils.addInfoMessage("Ya existe un presupuesto asignado a ese periodo");
				}
				else{
					
					Calendar fechaInicio = Calendar.getInstance();
					Calendar fechaFin = Calendar.getInstance();
					
					fechaInicio.set(Calendar.DATE, 1);
					fechaInicio.set(Calendar.MONTH, 0);
					fechaInicio.set(Calendar.YEAR, Integer.valueOf(presupuesto.getCodigoAnio()));
					
					System.out.println(fechaInicio.getTime());
					
					fechaFin.set(Calendar.DATE, 31);
					fechaFin.set(Calendar.MONTH, 11);
					fechaFin.set(Calendar.YEAR, Integer.valueOf(presupuesto.getCodigoAnio()));
			
					presupuesto.setRecursoActual(presupuesto.getRecursoInicial());
					presupuesto.setFechaInicio(fechaInicio.getTime());
					presupuesto.setFechaFin(fechaFin.getTime());
					
					System.out.println(presupuesto.getFechaInicio());
					System.out.println(presupuesto.getFechaFin());
					
					admGeneralServicio.createPresupuesto(getPresupuesto());
					generarCodigoPresupuesto(presupuesto.getIdPresupuesto());
					admGeneralServicio.editPresupuesto(getPresupuesto());
					setPresupuesto(new Presupuesto());
					FacesUtils.addInfoMessage("El presupuesto se cre&oacute exitosamente");
					initController();
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
