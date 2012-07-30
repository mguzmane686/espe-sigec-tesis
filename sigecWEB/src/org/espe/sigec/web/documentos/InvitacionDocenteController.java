package org.espe.sigec.web.documentos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.servicio.curso.CursoServicio;

/**
 * @author Roberto
 *
 */
@ManagedBean(name ="invitacionDocenteController")
@ViewScoped
public class InvitacionDocenteController {

	@Inject
	private CursoServicio cursoServicio;
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private InvitacionDocente invitacionDocente;
	
	public InvitacionDocenteController() {
		
	}
	public InvitacionDocente getInvitacionDocente() {
		return invitacionDocente;
	}
	public void setInvitacionDocente(InvitacionDocente invitacionDocente) {
		this.invitacionDocente = invitacionDocente;
	}
	
	
	
}
