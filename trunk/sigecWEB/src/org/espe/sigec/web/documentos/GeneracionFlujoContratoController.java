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
	
	public void btnGenerarDocumento(){
		
	}
	
	@PostConstruct
	public void cargarInvitacionesEmitidasNoRespondidas(){
		try {
			
			setLstInvitacionDocentes(documentoServicio.findInvitacionesByEstado("ALL"));
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
