package org.espe.sigec.web.documentos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.servicio.documentos.DocumentoServicio;
import org.espe.sigec.utils.SigecConstantes;
import org.espe.sigec.web.seguridad.HomeSessionController;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name ="respuestaDocenteController")
@ViewScoped
public class RespuestaDocenteController implements Serializable{
	@ManagedProperty(value="#{homeSessionController}")
	private HomeSessionController homeSessionController;
	@Inject
	private DocumentoServicio documentoServicio;
	
	private Collection<InvitacionDocente> lstInvitacionDocentes;
	private InvitacionDocente invitacionDocenteSelected;
	
	public RespuestaDocenteController() {
		setLstInvitacionDocentes(new ArrayList<InvitacionDocente>());
		setInvitacionDocenteSelected(new InvitacionDocente());
	}

	@PostConstruct
	public void respuestaDocenteControllerPostConstruct(){
		try {
			setLstInvitacionDocentes(documentoServicio.verificarInivtacionDocente(getHomeSessionController().getUsuarioPerfil().getUsuarioPerfilPK().getIdUsuario()));
//			setLstInvitacionDocentes(documentoServicio.verificarInivtacionDocente(87));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void btnAceptarInvitacion(){
		try {
			getInvitacionDocenteSelected().setEstado(SigecConstantes.INVITACION_ACEPTADA);
			documentoServicio.actualizarInvitacion(getInvitacionDocenteSelected());
			setLstInvitacionDocentes(documentoServicio.verificarInivtacionDocente(getHomeSessionController().getUsuarioPerfil().getUsuarioPerfilPK().getIdUsuario()));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void btnIgnorarInvitacion(){
		try {
			getInvitacionDocenteSelected().setEstado(SigecConstantes.INVITACION_RECHAZADA);
			documentoServicio.actualizarInvitacion(getInvitacionDocenteSelected());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public HomeSessionController getHomeSessionController() {
		return homeSessionController;
	}

	public void setHomeSessionController(HomeSessionController homeSessionController) {
		this.homeSessionController = homeSessionController;
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