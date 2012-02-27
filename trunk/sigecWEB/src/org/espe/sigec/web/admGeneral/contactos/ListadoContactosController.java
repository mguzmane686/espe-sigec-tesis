package org.espe.sigec.web.admGeneral.contactos;

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
@ManagedBean(name="listadoContactosController")
@ViewScoped
public class ListadoContactosController implements Serializable{
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Collection<Persona> lstPersonas;
	private String primerApellidoFilter;
	private String primerNombreFilter;
	@PostConstruct
	public void cargarContactos(){
		setLstPersonas(admGeneralServicio.cargarContactos());
		System.out.println("personas");
	}
	
	public ListadoContactosController() {
		
	}

	public Collection<Persona> getLstPersonas() {
		return lstPersonas;
	}

	public void setLstPersonas(Collection<Persona> lstPersonas) {
		this.lstPersonas = lstPersonas;
	}

	public String getPrimerApellidoFilter() {
		return primerApellidoFilter;
	}

	public void setPrimerApellidoFilter(String primerApellidoFilter) {
		this.primerApellidoFilter = primerApellidoFilter;
	}

	public String getPrimerNombreFilter() {
		return primerNombreFilter;
	}

	public void setPrimerNombreFilter(String primerNombreFilter) {
		this.primerNombreFilter = primerNombreFilter;
	}
	
	
}
