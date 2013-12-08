package org.espe.sigec.web.estudiante.historial;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="historialEstudiante")
@ViewScoped
public class HistorialEstudiante implements Serializable{
	private String cedula;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
}
