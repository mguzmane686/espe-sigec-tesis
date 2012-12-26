package org.espe.sigec.web.admGeneral.contactos;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.reportes.ReporteGenerico;
import org.espe.sigec.web.utils.FacesUtils;

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
	private Persona personaSelected;
	private String primerApellidoFilter;
	private String primerNombreFilter;
	
	private boolean editContactMode;
	@PostConstruct
	public void cargarContactos(){
		setLstPersonas(admGeneralServicio.cargarContactos());
		System.out.println("personas");
	}
	
	public ListadoContactosController() {
		
	}
	
	public void btnEditarContacto(){
		setEditContactMode(Boolean.TRUE);
	}
	
	public void btnCancelEditarContacto(){
		setEditContactMode(Boolean.FALSE);
	}
	
	public void btnGuardarContacto(){
		try {
			admGeneralServicio.editPersona(getPersonaSelected());
			setEditContactMode(Boolean.FALSE);
			FacesUtils.addInfoMessage("Contacto actualizado");
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Ocurrio un error al actualizar el contacto");
			e.printStackTrace();
		}
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

	public Persona getPersonaSelected() {
		return personaSelected;
	}

	public void setPersonaSelected(Persona personaSelected) {
		this.personaSelected = personaSelected;
	}
	
	public void btnPDF(ActionEvent e){
		ReporteGenerico reporteGenerico = new ReporteGenerico();
		reporteGenerico.generarReporteSimple("contactos",getLstPersonas());
	}

	public boolean isEditContactMode() {
		return editContactMode;
	}

	public void setEditContactMode(boolean editContactMode) {
		this.editContactMode = editContactMode;
	}
	
}
