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
	
	public void btnGenerarDocumento(InvitacionDocente invitacionDocente){
		setInvitacionDocenteSelected(invitacionDocente);
		ContratoProfesor contratoProfesor =null;
		try {
			contratoProfesor = documentoServicio.obtenerContratoDocente(getInvitacionDocenteSelected().getInvitacionDocentePK().getIdCursoPeriodo(), getInvitacionDocenteSelected().getInvitacionDocentePK().getPrfIdProfesor());
			if(contratoProfesor ==null){
				contratoProfesor = new ContratoProfesor();
				contratoProfesor.setIdCursoPeriodo(getInvitacionDocenteSelected().getInvitacionDocentePK().getIdCursoPeriodo());
				contratoProfesor.setIdProfesor(getInvitacionDocenteSelected().getInvitacionDocentePK().getPrfIdProfesor());
				contratoProfesor.setInvitacionDocente(getInvitacionDocenteSelected());
				contratoProfesor.setContratoProfesorPK(new ContratoProfesorPK());
				contratoProfesor.getContratoProfesorPK().setDocNumInvit(getInvitacionDocenteSelected().getInvitacionDocentePK().getDocNumInvit());
				documentoServicio.crearContratoDocente(contratoProfesor);
			}
			generarReporte();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	private void generarReporte(){
		ReporteFlujoContrato reporteFlujoContrato = new ReporteFlujoContrato();
		reporteFlujoContrato.reporteContratoP1();
		reporteFlujoContrato.reporteContratoP2();
		reporteFlujoContrato.reporteContratoP3();
		reporteFlujoContrato.reporteContratoP4();
		reporteFlujoContrato.reporteContratoP5();
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
	
	
}
