package org.espe.sigec.web.inscripcion.alumno_curso;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.servicio.inscripcion.InscripcionServicio;

/**
 * @author roberto
 *
 */
@ManagedBean(name = "inscripcionAlumnoCursoController")
@ViewScoped
public class InscripcionAlumnoCursoController {
	private String cedulaUsr;
	private CursoEstudiante cursoEstudiante;
	@Inject
	private InscripcionServicio inscripcionServicio;
	
	private Collection<SelectItem> itemCursos;
	
	public InscripcionAlumnoCursoController() {
		initEntities();
	}
	private void initEntities(){
		setCursoEstudiante(new CursoEstudiante());
		getCursoEstudiante().setCursoPeriodo(new CursoPeriodo());
		getCursoEstudiante().setEstudiante(new Estudiante());
	}
	
	@PostConstruct 
	public void cargarCursos(){
		setItemCursos(new ArrayList<SelectItem>());
		for(CursoPeriodo cursoPeriodoTMP: inscripcionServicio.cargarCursoLanzado()){
			getItemCursos().add(new SelectItem(cursoPeriodoTMP.getIdCursoPeriodo(), cursoPeriodoTMP.getCurso().getNombreCurso()));
		}
	}
	public CursoEstudiante getCursoEstudiante() {
		return cursoEstudiante;
	}
	
	public void btnBuscarEstudiante(ActionEvent e){
		getCursoEstudiante().setEstudiante(inscripcionServicio.buscarEstudinateByCedula(getCedulaUsr()));
	}
	public void btnInscripcionEstudinateCurso(ActionEvent e){
		System.out.println("Guardado");
	}
	public void setCursoEstudiante(CursoEstudiante cursoEstudiante) {
		this.cursoEstudiante = cursoEstudiante;
	}
	
	public Collection<SelectItem> getItemCursos() {
		return itemCursos;
	}

	public void setItemCursos(Collection<SelectItem> itemCursos) {
		this.itemCursos = itemCursos;
	}
	public String getCedulaUsr() {
		return cedulaUsr;
	}
	public void setCedulaUsr(String cedulaUsr) {
		this.cedulaUsr = cedulaUsr;
	}
	
}
