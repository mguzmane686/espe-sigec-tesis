package org.espe.sigec.web.coordinacion.curso;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.servicio.curso.CursoServicio;
@SuppressWarnings("serial")
@ManagedBean(name="reporteCursoAbiertoController")
@ViewScoped
public class ReporteCursoAbiertoController implements Serializable{

	@Inject
	private CursoServicio cursoServicio;
	
	private Collection<CursoPeriodo> lstCursoPeriodos;
	
	public ReporteCursoAbiertoController() {
		setLstCursoPeriodos(new ArrayList<CursoPeriodo>());
	}
	
	@PostConstruct
	public void loadCursoAbierto(){
		setLstCursoPeriodos(cursoServicio.findCursoAbierto());
	}
	
	public void btnAdministrarCursoAbierto(CursoPeriodo cursoPeriodo){
		try {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("curso", cursoPeriodo);
			FacesContext.getCurrentInstance().getExternalContext().redirect("coor_administrar_curso_abierto.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Collection<CursoPeriodo> getLstCursoPeriodos() {
		return lstCursoPeriodos;
	}
	public void setLstCursoPeriodos(Collection<CursoPeriodo> lstCursoPeriodos) {
		this.lstCursoPeriodos = lstCursoPeriodos;
	}
	
}
