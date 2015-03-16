package org.espe.sigec.web.admGeneral.especialidad;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.GeneralFunctions;

@SuppressWarnings("serial")
@ManagedBean(name="especialidadController")
@ViewScoped
public class EspecialidadController implements Serializable {

	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Especialidad especialidad;

	public EspecialidadController(){
		setEspecialidad(new Especialidad());
	}
	
	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	public void btnSaveEspecialidad(ActionEvent e){
		try {
			admGeneralServicio.createEspecialidad(getEspecialidad());
			generarCodigoEspecialidad(especialidad.getIdEspecialidad());
			admGeneralServicio.editEspecialidad(getEspecialidad());
			setEspecialidad(new Especialidad());
			FacesUtils.addInfoMessage("La especialidad se cre&oacute exitosamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("No se pudo guardar la especialidad");
		}
	}
	
	private void generarCodigoEspecialidad(int nextRegistro){
		int cantidadNumeros;
		
		String prefijo;
		String sufijo;
		
		GeneralFunctions generalFunctions = new GeneralFunctions();
		
		cantidadNumeros = Integer.toString(nextRegistro).length();
		
		prefijo = "ESP";		
		sufijo = generalFunctions.completarCeros(nextRegistro, cantidadNumeros);
		
		especialidad.setIdEspecialidadPrefijo(prefijo + sufijo);
	}
	
}
