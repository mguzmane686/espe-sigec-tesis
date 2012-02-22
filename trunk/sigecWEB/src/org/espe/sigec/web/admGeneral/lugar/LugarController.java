package org.espe.sigec.web.admGeneral.lugar;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.LugarCurso;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.GeneralFunctions;

@SuppressWarnings("serial")
@ManagedBean(name="lugarController")
@ViewScoped
public class LugarController implements Serializable {
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private LugarCurso lugarCurso;

	public LugarController(){
		setLugarCurso(new LugarCurso());	
	}

	public LugarCurso getLugarCurso() {
		return lugarCurso;
	}
	
	public void setLugarCurso(LugarCurso lugarCurso) {
		this.lugarCurso = lugarCurso;
	}
	
	public void btnSaveLugar(ActionEvent e){
		try {
			generarCodigoLugar();
			admGeneralServicio.createLugar(getLugarCurso());
			setLugarCurso(new LugarCurso());
			FacesUtils.addInfoMessage("El lugar se cre&oacute exitosamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("No se pudo guardar el lugar");
		}
	}
	
	private void generarCodigoLugar(){
		int nextRegistro;
		int cantidadNumeros;
		
		String prefijo;
		String sufijo;
		
		GeneralFunctions generalFunctions = new GeneralFunctions();
		
		nextRegistro = admGeneralServicio.findLugar().size() + 1;
		cantidadNumeros = Integer.toString(nextRegistro).length();
		
		prefijo = "LUG";		
		sufijo = generalFunctions.completarCeros(nextRegistro, cantidadNumeros);
		
		lugarCurso.setIdLugar(prefijo + sufijo);
	}

}
