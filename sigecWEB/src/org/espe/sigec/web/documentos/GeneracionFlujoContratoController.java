package org.espe.sigec.web.documentos;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.ContratoProfesor;
import org.espe.sigec.model.entities.ContratoProfesorPK;
import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.servicio.documentos.DocumentoServicio;
import org.espe.sigec.web.reportes.ReporteFlujoContrato;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name ="generacionFlujoContratoController")
@ViewScoped
public class GeneracionFlujoContratoController implements Serializable{
	@Inject
	private DocumentoServicio documentoServicio;
	
	private Collection<InvitacionDocente> lstInvitacionDocentes;
	private InvitacionDocente invitacionDocenteSelected;
	private ContratoProfesor contratoProfesor;
	
	public GeneracionFlujoContratoController() {
		instanciarEntidades();
	}

	public void btnGenerarFlujoContrato(){
		btnGenerarDocumento(getInvitacionDocenteSelected());
		
	}
	
	private void instanciarEntidades(){
		setContratoProfesor(new ContratoProfesor());
		getContratoProfesor().setContratoProfesorPK(new ContratoProfesorPK());
	}
	
	public void btnGenerarDocumento(InvitacionDocente invitacionDocente){
		setInvitacionDocenteSelected(invitacionDocente);
		ContratoProfesor contratoProfesorTMP =null;
		try {
			contratoProfesorTMP = documentoServicio.obtenerContratoDocente(getInvitacionDocenteSelected().getInvitacionDocentePK().getIdCursoPeriodo(), getInvitacionDocenteSelected().getInvitacionDocentePK().getPrfIdProfesor());
			if(contratoProfesorTMP ==null){
//				contratoProfesor = new ContratoProfesor();
				contratoProfesor.setIdCursoPeriodo(getInvitacionDocenteSelected().getInvitacionDocentePK().getIdCursoPeriodo());
				contratoProfesor.setIdProfesor(getInvitacionDocenteSelected().getInvitacionDocentePK().getPrfIdProfesor());
				contratoProfesor.setInvitacionDocente(getInvitacionDocenteSelected());
				contratoProfesor.getContratoProfesorPK().setDocNumInvit(getInvitacionDocenteSelected().getInvitacionDocentePK().getDocNumInvit());
				documentoServicio.crearContratoDocente(contratoProfesor);
				contratoProfesor = documentoServicio.obtenerContratoDocente(getInvitacionDocenteSelected().getInvitacionDocentePK().getIdCursoPeriodo(), getInvitacionDocenteSelected().getInvitacionDocentePK().getPrfIdProfesor());
			}
			generarReporte(contratoProfesor);
			instanciarEntidades();
			setInvitacionDocenteSelected(null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void btnSeleccionarInvitacion(InvitacionDocente invitacionDocente){
		setInvitacionDocenteSelected(invitacionDocente);
		
		try {
			setContratoProfesor(documentoServicio.obtenerContratoDocente(getInvitacionDocenteSelected().getInvitacionDocentePK().getIdCursoPeriodo(), getInvitacionDocenteSelected().getInvitacionDocentePK().getPrfIdProfesor()));
			if(getContratoProfesor() == null){
				instanciarEntidades();
			}
		} catch (Exception e) {
			instanciarEntidades();
			e.printStackTrace();
		}
	}
	
	private void generarReporte(ContratoProfesor contratoProfesor){
		ReporteFlujoContrato reporteFlujoContrato = new ReporteFlujoContrato(contratoProfesor);
		reporteFlujoContrato.reporteContratoP1();
		reporteFlujoContrato.reporteContratoP2();
		reporteFlujoContrato.reporteContratoP3();
		reporteFlujoContrato.reporteContratoP4();
		reporteFlujoContrato.reporteContratoP6();
		reporteFlujoContrato.reporteContratoP7();
		
		try {
			reporteFlujoContrato.generarContrato("flujo_contrato.doc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void cargarInvitacionesEmitidasNoRespondidas(){
		try {
			
			setLstInvitacionDocentes(documentoServicio.findInvitacionesByEstado("PCT"));
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public ContratoProfesor getContratoProfesor() {
		return contratoProfesor;
	}

	public void setContratoProfesor(ContratoProfesor contratoProfesor) {
		this.contratoProfesor = contratoProfesor;
	}
}
