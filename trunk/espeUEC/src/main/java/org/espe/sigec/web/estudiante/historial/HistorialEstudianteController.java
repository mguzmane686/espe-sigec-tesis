package org.espe.sigec.web.estudiante.historial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.servicio.portal.PortalServicio;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="historialEstudianteController")
@ViewScoped
public class HistorialEstudianteController implements Serializable{
	@Inject 
	private PortalServicio portalServicio;
	private String cedula;
	private Collection<CursoEstudiante> lstCursoEstudiantes;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public void cargarHistorialEstudiante(){
		
		try {
			setLstCursoEstudiantes(portalServicio.buscarHistorialEstudiante(getCedula()));
			if(getLstCursoEstudiantes()== null || CollectionUtils.isEmpty(getLstCursoEstudiantes())){
				setLstCursoEstudiantes(new ArrayList<CursoEstudiante>(1));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Collection<CursoEstudiante> getLstCursoEstudiantes() {
		return lstCursoEstudiantes;
	}

	public void setLstCursoEstudiantes(Collection<CursoEstudiante> lstCursoEstudiantes) {
		this.lstCursoEstudiantes = lstCursoEstudiantes;
	}
	
	
	
}
