package org.espe.sigec.web.documentos;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.servicio.documentos.DocumentoServicio;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="invitacionesAceptadasController")
@ViewScoped
public class InvitacionesAceptadasController implements Serializable{
	private Collection<InvitacionDocente> lstInvitacionDocentes;

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

	public Collection<InvitacionDocente> getLstInvitacionDocentes() {
		return lstInvitacionDocentes;
	}

	public void setLstInvitacionDocentes(
			Collection<InvitacionDocente> lstInvitacionDocentes) {
		this.lstInvitacionDocentes = lstInvitacionDocentes;
	}
	
}
