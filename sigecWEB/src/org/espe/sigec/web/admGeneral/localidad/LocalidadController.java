package org.espe.sigec.web.admGeneral.localidad;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.LugarCurso;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name="localidadController")
@ViewScoped
public class LocalidadController implements Serializable{
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private LugarCurso lugarCurso;
	
	public LocalidadController() {
		setLugarCurso(new LugarCurso());
	}
	
	public void btnSaveLocalidad(ActionEvent e){
		try {
			admGeneralServicio.createLocalidad(getLugarCurso());
			setLugarCurso(new LugarCurso());
			FacesUtils.addInfoMessage("La localidad se cre&oacute exitosamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("No se pudo guardar la localidad");
		}
	}
	
	public LugarCurso getLugarCurso() {
		return lugarCurso;
	}

	public void setLugarCurso(LugarCurso lugarCurso) {
		this.lugarCurso = lugarCurso;
	}
	
}
