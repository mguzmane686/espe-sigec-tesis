package org.espe.sigec.web.documentos;

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
import org.espe.sigec.utils.SigecConstantes;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="invitacionesAceptadasController")
@ViewScoped
public class InvitacionesAceptadasController implements Serializable{
	private Collection<InvitacionDocente> lstInvitacionDocentes;
	private InvitacionDocente invitacionDocenteSelected;

	@Inject
	private DocumentoServicio documentoServicio;
	
	public InvitacionesAceptadasController() {
		super();
	}
	
	@PostConstruct
	public void loadInvitacionesAceptadas(){
		try {
			setLstInvitacionDocentes(documentoServicio.verificarInivtacionAceptada());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnAecptarGenerarContrato(){
		System.out.println("Generar Contrato");
		
		try {
			getInvitacionDocenteSelected().setContratoGenerado(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
			documentoServicio.actualizarInvitacion(getInvitacionDocenteSelected());
			
			ContratoProfesor contratoProfesor = new ContratoProfesor();
			contratoProfesor.setIdCursoPeriodo(getInvitacionDocenteSelected().getInvitacionDocentePK().getIdCursoPeriodo());
			contratoProfesor.setIdProfesor(getInvitacionDocenteSelected().getInvitacionDocentePK().getPrfIdProfesor());
			contratoProfesor.setInvitacionDocente(getInvitacionDocenteSelected());
			contratoProfesor.setContratoProfesorPK(new ContratoProfesorPK());
//			contratoProfesor.getContratoProfesorPK().setCtrNumContrato(1);
			contratoProfesor.getContratoProfesorPK().setDocNumInvit(getInvitacionDocenteSelected().getInvitacionDocentePK().getDocNumInvit());
			documentoServicio.crearContratoDocente(contratoProfesor);
			FacesUtils.addInfoMessage("En contrato se genero correctamente");
			loadInvitacionesAceptadas();
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Ocurrio un error al generar el contrato");
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