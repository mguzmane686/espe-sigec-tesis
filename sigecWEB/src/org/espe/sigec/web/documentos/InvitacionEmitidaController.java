package org.espe.sigec.web.documentos;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.servicio.documentos.DocumentoServicio;

@SuppressWarnings("serial")
@ManagedBean(name ="invitacionEmitidaController")
@ViewScoped
public class InvitacionEmitidaController implements Serializable{
	private Collection<InvitacionDocente> lstInvitacionDocentes;
	private InvitacionDocente invitacionDocenteSelected;
	
	private String estadoInvFilter;
	@Inject
	private DocumentoServicio documentoServicio;
	
	@PostConstruct
	public void cargarInvitacionesEmitidasNoRespondidas(){
		try {
			setEstadoInvFilter("ALL");
			setLstInvitacionDocentes(documentoServicio.findInvitacionesByEstado(getEstadoInvFilter()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnBuscarInvitacion(){
		try {
			setLstInvitacionDocentes(documentoServicio.findInvitacionesByEstado(getEstadoInvFilter()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void btnAceptarCambioEstadoInv(){
		try {
			documentoServicio.actualizarInvitacion(getInvitacionDocenteSelected());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void btnCancelarCambioEstadoInv(){
		setInvitacionDocenteSelected(null);
		
	}

	public void btnActualizarEstadoInvitacion(ActionEvent e){
		
	}
	
	public Collection<InvitacionDocente> getLstInvitacionDocentes() {
		return lstInvitacionDocentes;
	}

	public void setLstInvitacionDocentes(
			Collection<InvitacionDocente> lstInvitacionDocentes) {
		this.lstInvitacionDocentes = lstInvitacionDocentes;
	}

	public InvitacionDocente getInvitacionDocenteSelected() {
		return invitacionDocenteSelected;
	}

	public void setInvitacionDocenteSelected(
			InvitacionDocente invitacionDocenteSelected) {
		this.invitacionDocenteSelected = invitacionDocenteSelected;
	}

	public String getEstadoInvFilter() {
		return estadoInvFilter;
	}

	public void setEstadoInvFilter(String estadoInvFilter) {
		this.estadoInvFilter = estadoInvFilter;
	}
	
}
