package org.espe.sigec.web.encuesta;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Encuesta;
import org.espe.sigec.model.entities.EncuestaPK;
import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.servicio.curso.CursoServicio;
import org.espe.sigec.servicio.inscripcion.InscripcionServicio;
import org.espe.sigec.servicio.portal.PortalServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="encuestaController")
@ViewScoped
public class EncuestaController implements Serializable{
	@Inject
	private CursoServicio cursoServicio;
	
	@Inject
	private PortalServicio portalServicio;
	@Inject
	private InscripcionServicio inscripcionServicio;
	
	private Encuesta encuesta;
	private String cedulaEstudiante;
	
	private Estudiante estudiante;
	
	Collection<CursoPeriodo> lstCursosAbiertos;
	public EncuestaController() {
		setEncuesta(new Encuesta());
		getEncuesta().setFechaEncuesta(Calendar.getInstance().getTime());
	}
	
	
	public void btnGuardarEncuesta(){
		System.out.println("Guardando encuesta");
//		portalServicio.guardarEncuesta(getEncuesta());
		EncuestaPK encuestaPK = new EncuestaPK();
		portalServicio.buscarEncuesta(encuestaPK);
	}
	
	public void btnBuscarPersona(){
		if(StringUtils.isNotEmpty(getCedulaEstudiante())){
			System.out.println("Ingresa a buscar");
			Estudiante estudiante =inscripcionServicio.buscarEstudinateByCedula(getCedulaEstudiante());
			
			if(estudiante!=null){
				setEstudiante(estudiante);	
			}else{
				FacesUtils.addInfoMessage("No se encontro ningun estudiante");
			}
		}
	}
	
	@PostConstruct
	public void initController(){
		setLstCursosAbiertos(cursoServicio.findCursoAbierto());
//		getEncuesta().setCtnConocimiento(10);
	}

	public Collection<CursoPeriodo> getLstCursosAbiertos() {
		return lstCursosAbiertos;
	}

	public void setLstCursosAbiertos(Collection<CursoPeriodo> lstCursosAbiertos) {
		this.lstCursosAbiertos = lstCursosAbiertos;
	}

	public Encuesta getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}


	public String getCedulaEstudiante() {
		return cedulaEstudiante;
	}


	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}


	public Estudiante getEstudiante() {
		return estudiante;
	}


	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	
}
