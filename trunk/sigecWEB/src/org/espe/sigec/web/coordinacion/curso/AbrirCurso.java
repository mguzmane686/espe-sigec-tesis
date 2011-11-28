package org.espe.sigec.web.coordinacion.curso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.PeriodoAcademico;
import org.espe.sigec.servicio.coordinacion.CoordinacionServicio;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.SigecConstantes;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="planearCursoController")
@ViewScoped
public class AbrirCurso implements Serializable{
	
	@Inject
	private CoordinacionServicio coordinacionServicio;
	private Collection<SelectItem> itemAulas;
	private Collection<SelectItem> itemCursos;
	private Collection<SelectItem> itemEspecialidades;
	
	private PeriodoAcademico periodoAcademico;
	private CursoPeriodo cursoPeriodo;
	
	public AbrirCurso() {
		initEntities();
		loadParametrosGenerales();
	}
	
	private void initEntities(){
		setPeriodoAcademico(new PeriodoAcademico());
		setCursoPeriodo(new CursoPeriodo());
		getCursoPeriodo().setCurso(new Curso());
		getCursoPeriodo().setAula(new Aula());
		getCursoPeriodo().setTipoCurso(SigecConstantes.TIPO_CURSO_INDIVIDUAL);
	}
	private void loadParametrosGenerales(){
		getCursoPeriodo().setMinimoEstudiantes(SigecConstantes.MINIMO_ESTUDIANTES);
		getCursoPeriodo().setMaximoEstudiantes(SigecConstantes.MAXIMO_ESTUDIANTES);
	}
	@PostConstruct
	public void loadItems(){
		setItemEspecialidades(new ArrayList<SelectItem>());
		for(Especialidad especialidad: coordinacionServicio.findEspecialidades()){
			getItemEspecialidades().add(new SelectItem(especialidad.getIdEspecialidad(), especialidad.getNombre()));
		}
		if(!getItemEspecialidades().isEmpty()){
			Integer integer = (Integer) getItemEspecialidades().iterator().next().getValue();
			loadCursos(integer);
		}
		setItemAulas(new ArrayList<SelectItem>());
		for(Aula aula: coordinacionServicio.findAulas()){
			getItemAulas().add(new SelectItem(aula.getIdAula(), aula.getCodAula() +" ; "+ aula.getNombreAula()));
		}
		
	}
	private void loadCursos(Integer especialidad){
		setItemCursos(new ArrayList<SelectItem>());
		Collection<Curso> lst = coordinacionServicio.findCursoByEspecialidad(especialidad);
		for(Curso curso: lst){
			getItemCursos().add(new SelectItem(curso.getIdCurso(), curso.getNombreCurso()));
		}
	}
	public void somChangeEspecialidad(ValueChangeEvent e){
		Integer integer = Integer.parseInt( e.getNewValue().toString());
		loadCursos(integer);
	}
	public void btnSaveAbrirCurso(ActionEvent e){
		try {
			coordinacionServicio.abrirCurso(getPeriodoAcademico(), getCursoPeriodo());
			initEntities();
			FacesUtils.addInfoMessage("El curso fue abierto con &eacutexito");
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("No se pudo abrir el curso");
		}
	}
	
	public Collection<SelectItem> getItemCursos() {
		return itemCursos;
	}

	public void setItemCursos(Collection<SelectItem> itemCursos) {
		this.itemCursos = itemCursos;
	}

	public PeriodoAcademico getPeriodoAcademico() {
		return periodoAcademico;
	}

	public void setPeriodoAcademico(PeriodoAcademico periodoAcademico) {
		this.periodoAcademico = periodoAcademico;
	}

	public CursoPeriodo getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
	}

	public Collection<SelectItem> getItemEspecialidades() {
		return itemEspecialidades;
	}

	public void setItemEspecialidades(Collection<SelectItem> itemEspecialidades) {
		this.itemEspecialidades = itemEspecialidades;
	}

	public Collection<SelectItem> getItemAulas() {
		return itemAulas;
	}

	public void setItemAulas(Collection<SelectItem> itemAulas) {
		this.itemAulas = itemAulas;
	}

}
