package org.espe.sigec.web.admGeneral.usuario;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name = "listadoUsuarioController")
@ViewScoped
public class ListadoUsuarioController implements Serializable{
	private Collection<Persona> lstPersonas;
	
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	@PostConstruct
	public void inicializarControlador(){
		setLstPersonas(admGeneralServicio.loadPerson());
	}
	public ListadoUsuarioController() {
	}

	public Collection<Persona> getLstPersonas() {
		return lstPersonas;
	}

	public void setLstPersonas(Collection<Persona> lstPersonas) {
		this.lstPersonas = lstPersonas;
	}
	
	
}
