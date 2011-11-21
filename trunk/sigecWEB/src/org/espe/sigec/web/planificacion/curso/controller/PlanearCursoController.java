package org.espe.sigec.web.planificacion.curso.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.PeriodoAcademico;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoPeriodoFacadeLocal;
import org.espe.sigec.model.sessionBeans.PeriodoAcademicoFacadeLocal;
import org.espe.sigec.web.utils.CommonController;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name="planearCursoController")
@ViewScoped
public class PlanearCursoController extends CommonController{
	@EJB
	private CursoFacadeLocal cursoFacadeLocal;
	@EJB
	private PeriodoAcademicoFacadeLocal academicoFacadeLocal;
	@EJB
	private CursoPeriodoFacadeLocal cursoPeriodoFacadeLocal;
	
	private Collection<SelectItem> itemCursos;
	
	private PeriodoAcademico periodoAcademico;
	private CursoPeriodo cursoPeriodo;
	
	public PlanearCursoController() {
//		setUiPanelMenu((UIPanelMenu) FacesUtils.getFlashObject("menuSigec"));
		setPeriodoAcademico(new PeriodoAcademico());
		setCursoPeriodo(new CursoPeriodo());
		getCursoPeriodo().setCurso(new Curso());
//		cargarCursos();
	}
	@PostConstruct
	public void cargarCursos(){
		itemCursos = new ArrayList<SelectItem>();
		for(Curso curso: cursoFacadeLocal.findAll()){
			itemCursos.add(new SelectItem(String.valueOf(curso.getIdCurso()), curso.getNombreCurso()));
		}
	}
	
	public void btnSaveAbrirCurso(ActionEvent e){
		try {
			academicoFacadeLocal.create(getPeriodoAcademico());
			getCursoPeriodo().setPeriodoAcademico(getPeriodoAcademico());
			cursoPeriodoFacadeLocal.create(getCursoPeriodo());
			FacesUtils.addInfoMessage("El curso fue abierto");
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
	
	
}
