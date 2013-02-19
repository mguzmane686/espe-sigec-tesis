package org.espe.sigec.web.encuesta;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.espe.sigec.model.entities.CursoEstudiante;
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
	private Collection<CursoEstudiante> lstCursosEstudiante; 
	
	private CursoEstudiante cursoEstudianteSeleccionado;
	
	private Estudiante estudiante;
	
	
	private boolean showListaCursos;
	
	public EncuestaController() {
		setEncuesta(new Encuesta());
		getEncuesta().setEncuestaPK(new EncuestaPK());
		getEncuesta().setFechaEncuesta(Calendar.getInstance().getTime());
	}
	
	
	public void btnGuardarEncuesta(){
		System.out.println("Guardando encuesta");
		getEncuesta().setCursoEstudiante(getCursoEstudianteSeleccionado());
		getEncuesta().getEncuestaPK().setEstIdEstudiante(getEstudiante().getIdEstudiante());
		getEncuesta().getEncuestaPK().setIdCursoPeriodo(getCursoEstudianteSeleccionado().getCursoEstudiantePK().getIdCursoPeriodo());
		getEncuesta().getEncuestaPK().setPrgId(getCursoEstudianteSeleccionado().getIdPrograma());
		try {
			portalServicio.guardarEncuesta(getEncuesta());
			FacesUtils.addInfoMessage("Encuesta guardada");
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Ocurrion un erro al guardar la encuesta");
		}
		
	}
	
	public void btnBuscarPersona(){
		if(StringUtils.isNotEmpty(getCedulaEstudiante())){
			System.out.println("Ingresa a buscar");
			Estudiante estudiante = inscripcionServicio.buscarEstudinateByCedula(getCedulaEstudiante());
			
			if(estudiante!=null){
				setEstudiante(estudiante);
				//BUSCAMOS LOS CURSOS DEL ESTUDINATE
				FacesUtils.addInfoMessage("Estudiante encontrado");
				buscarCursosEstudiante(estudiante.getIdEstudiante());
			}else{
				FacesUtils.addInfoMessage("No se encontro ningun estudiante");
			}
		}
	}
	
	/**
	 * @param idEstudiante
	 * Busca los cursos en los cuales el estudiante esta inscrito
	 */
	private void buscarCursosEstudiante(int idEstudiante){
		Collection<CursoEstudiante> lstCursoEstudiantesTMP = portalServicio.buscarCursosEstudiante(idEstudiante); 
		setShowListaCursos(Boolean.FALSE);
		
		if(lstCursoEstudiantesTMP !=null){
			setLstCursosEstudiante(lstCursoEstudiantesTMP);
			setCursoEstudianteSeleccionado(lstCursoEstudiantesTMP.iterator().next());
			setShowListaCursos(Boolean.TRUE);
		}else{
			FacesUtils.addInfoMessage("No se encontro ningun curso para el estudiante");
		}
	}
	@PostConstruct
	public void initController(){
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


	public Collection<CursoEstudiante> getLstCursosEstudiante() {
		return lstCursosEstudiante;
	}


	public void setLstCursosEstudiante(
			Collection<CursoEstudiante> lstCursosEstudiante) {
		this.lstCursosEstudiante = lstCursosEstudiante;
	}


	public boolean isShowListaCursos() {
		return showListaCursos;
	}


	public void setShowListaCursos(boolean showListaCursos) {
		this.showListaCursos = showListaCursos;
	}


	public CursoEstudiante getCursoEstudianteSeleccionado() {
		return cursoEstudianteSeleccionado;
	}


	public void setCursoEstudianteSeleccionado(
			CursoEstudiante cursoEstudianteSeleccionado) {
		this.cursoEstudianteSeleccionado = cursoEstudianteSeleccionado;
	}
	
}
