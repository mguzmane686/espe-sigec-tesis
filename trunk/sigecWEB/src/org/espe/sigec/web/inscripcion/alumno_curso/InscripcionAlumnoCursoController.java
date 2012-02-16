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
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.servicio.inscripcion.InscripcionServicio;
import org.espe.sigec.web.utils.FacesUtils;

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
	Collection<CursoPeriodo> lstCursoPeriodos;
	private boolean showFieldsNewStudent;
	
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
		lstCursoPeriodos = inscripcionServicio.cargarCursoLanzado();
		for(CursoPeriodo cursoPeriodoTMP: lstCursoPeriodos){
			getItemCursos().add(new SelectItem(cursoPeriodoTMP.getIdCursoPeriodo(), cursoPeriodoTMP.getCurso().getNombreCurso()));
		}
	}
	public CursoEstudiante getCursoEstudiante() {
		return cursoEstudiante;
	}
	
	public void btnBuscarEstudiante(ActionEvent e){
		setShowFieldsNewStudent(Boolean.FALSE);
		getCursoEstudiante().setEstudiante(inscripcionServicio.buscarEstudinateByCedula(getCedulaUsr()));
		if(getCursoEstudiante().getEstudiante()==null){
			FacesUtils.addInfoMessage("El usuario no existe. Para crearlo hagalo con el boton Nuevo");
		}else{
			setShowFieldsNewStudent(Boolean.FALSE);
		}
	}
	
	public void btnFindEstudiante(ActionEvent e){
		setShowFieldsNewStudent(Boolean.FALSE);
	}
	public void btnInscripcionEstudinateCurso(ActionEvent e){
		try {
			for(CursoPeriodo cursoPeriodoTMP: lstCursoPeriodos){
				if(cursoPeriodoTMP.getIdCursoPeriodo().compareTo(getCursoEstudiante().getCursoPeriodo().getIdCursoPeriodo()) == 0){
					getCursoEstudiante().setCursoPeriodo(cursoPeriodoTMP);
					break;
				}
			}
			inscripcionServicio.inscripcionEstudianteCurso(getCursoEstudiante().getEstudiante(), getCursoEstudiante().getCursoPeriodo(), getCursoEstudiante(), isShowFieldsNewStudent());
			FacesUtils.addInfoMessage("Se ha inscrito a un curso");
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("Ocurrio un error inesperado");
		}
	}
	
	public void btnNuevoEstudiante(ActionEvent e){
		setCedulaUsr("");
		getCursoEstudiante().setEstudiante(new Estudiante());
		getCursoEstudiante().getEstudiante().setPersona(new Persona());
		getCursoEstudiante().getEstudiante().getPersona().setUsuario(new Usuario());
		setShowFieldsNewStudent(Boolean.TRUE);
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
	public boolean isShowFieldsNewStudent() {
		return showFieldsNewStudent;
	}
	public void setShowFieldsNewStudent(boolean showFieldsNewStudent) {
		this.showFieldsNewStudent = showFieldsNewStudent;
	}
	
}
