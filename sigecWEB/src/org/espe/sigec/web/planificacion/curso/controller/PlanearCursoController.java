package org.espe.sigec.web.planificacion.curso.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.PeriodoAcademico;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.web.utils.CommonController;
import org.espe.sigec.web.utils.FacesUtils;
import org.richfaces.component.UIPanelMenu;

@SuppressWarnings("serial")
@ManagedBean(name="planearCursoController")
@ViewScoped
public class PlanearCursoController extends CommonController{
	@EJB
	private CursoFacadeLocal cursoFacadeLocal;
	private Collection<SelectItem> itemCursos;
	
	private PeriodoAcademico periodoAcademico;
	private CursoPeriodo cursoPeriodo;
	
	public PlanearCursoController() {
		setUiPanelMenu((UIPanelMenu) FacesUtils.getFlashObject("menuSigec"));
		periodoAcademico = new PeriodoAcademico();
		cursoPeriodo = new CursoPeriodo();
//		cargarCursos();
	}
	@PostConstruct
	public void cargarCursos(){
		itemCursos = new ArrayList<SelectItem>();
		for(Curso curso: cursoFacadeLocal.findAll()){
			itemCursos.add(new SelectItem(String.valueOf(curso.getIdCurso()), curso.getNombreCurso()));
		}
//		FacesUtils.refresh();
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
	
	
}
