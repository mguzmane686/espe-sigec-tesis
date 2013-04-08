package org.espe.sigec.web.admGeneral.presupuesto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.espe.sigec.model.entities.Presupuesto;
import org.espe.sigec.model.entities.PresupuestoDetalle;
import org.espe.sigec.model.entities.PresupuestoDetallePK;
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
		initController();
	}
	
	private void initController(){
		setPresupuesto(new Presupuesto());
		presupuesto.setCodigoAnio(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		getPresupuesto().setLstPresupuestoDetalles(new ArrayList<PresupuestoDetalle>());
	}
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	public void btnAgregarPresupuesto(){
		PresupuestoDetalle presupuestoDetalle = new PresupuestoDetalle();
		presupuestoDetalle.setPresupuestoDetallePK(new PresupuestoDetallePK());
		System.out.println(presupuestoDetalle.hashCode());
		getPresupuesto().getLstPresupuestoDetalles().add(presupuestoDetalle);
	}
	
	public void btnEliminarDetalle(PresupuestoDetalle presupuestoDetalle){
		getPresupuesto().getLstPresupuestoDetalles().remove((presupuestoDetalle));
	}
	
	public void btnSavePresupuesto(ActionEvent e){
		try {
					
			Collection<Presupuesto> lstTemp;
			lstTemp = admGeneralServicio.findPresupuesto(presupuesto.getCodigoAnio());
			
				if (lstTemp.size() > 0){
					FacesUtils.addErrorMessage("Ya existe un presupuesto asignado a ese periodo");
				}
				else{
					if(CollectionUtils.isEmpty(getPresupuesto().getLstPresupuestoDetalles())){
						FacesUtils.addErrorMessage("Se debe ingresar como minimo un detalle al presupuesto");						
					}else{
						Calendar fechaInicio = Calendar.getInstance();
						Calendar fechaFin = Calendar.getInstance();
						
						fechaInicio.set(Calendar.DATE, 1);
						fechaInicio.set(Calendar.MONTH, 0);
						fechaInicio.set(Calendar.YEAR, Integer.valueOf(presupuesto.getCodigoAnio()));
						
						fechaFin.set(Calendar.DATE, 31);
						fechaFin.set(Calendar.MONTH, 11);
						fechaFin.set(Calendar.YEAR, Integer.valueOf(presupuesto.getCodigoAnio()));
				
						if(NumberUtils.createInteger(presupuesto.getCodigoAnio()) == Calendar.getInstance().get(Calendar.YEAR)  ){
							presupuesto.setRecursoActual(presupuesto.getRecursoInicial());
							presupuesto.setFechaInicio(fechaInicio.getTime());
							presupuesto.setFechaFin(fechaFin.getTime());
							
							System.out.println(presupuesto.getFechaInicio());
							System.out.println(presupuesto.getFechaFin());
							
							admGeneralServicio.createPresupuesto(getPresupuesto());
//							generarCodigoPresupuesto(presupuesto.getIdPresupuesto());
//							admGeneralServicio.editPresupuesto(getPresupuesto());
							setPresupuesto(new Presupuesto());
							FacesUtils.addInfoMessage("El presupuesto se cre&oacute exitosamente");
							initController();
						}else{
							FacesUtils.addErrorMessage("No se puede crear una presupuesto correspondiente a un periodo diferente al actual");
						}
					}
				}
						
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("No se pudo guardar el presupuesto");
		}
	}
	
}
