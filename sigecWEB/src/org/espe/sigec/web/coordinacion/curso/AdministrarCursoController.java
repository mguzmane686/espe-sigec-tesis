package org.espe.sigec.web.coordinacion.curso;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.HistoricoCursoEstado;
import org.espe.sigec.servicio.coordinacion.CoordinacionServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author roberto
 *
 */
@ManagedBean(name = "administrarCursoController")
@ViewScoped
public class AdministrarCursoController {
	@Inject
	private CoordinacionServicio coordinacionServicio;
	
	private CursoPeriodo cursoPeriodo;
	private boolean editMode;
	private int numeroAlumnosInscritos;
	
	public AdministrarCursoController() {
		setCursoPeriodo((CursoPeriodo) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("curso"));
		if(getCursoPeriodo()!=null && getCursoPeriodo().getHistoricoCursoEstadoCollection()==null){
			getCursoPeriodo().setHistoricoCursoEstadoCollection(new HistoricoCursoEstado());
		}
	}
	
	@PostConstruct
	public void cargarNumeroEstudiantesInscritos(){
		try {
			setNumeroAlumnosInscritos(coordinacionServicio.numeroEstudiantesInscritos(getCursoPeriodo().getIdCursoPeriodo()));
		} catch (Exception e) {
			System.out.println("Error al cargar el numero de estudiantes inscritos");
		}
		
	}

	public void btnEdit(ActionEvent e){
		setEditMode(Boolean.TRUE);
	}
	
	public void btnCancelEdit(ActionEvent e){
		setEditMode(Boolean.FALSE);
	}
	
	public void btnSave(ActionEvent e){
		setEditMode(Boolean.FALSE);
		try {
			coordinacionServicio.administrarCurso(getCursoPeriodo());
			FacesUtils.addInfoMessage("Curso actualizado");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("Ha ocurrido un error al editar el curso");
			e1.printStackTrace();
		}
	}
	
	public void btnPresupuestoCurso(ActionEvent e){
		FacesUtils.putFlashObject("cursoPeriodo", getCursoPeriodo());
		try {
			FacesUtils.redirectPage("presupuestos.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void btnReturnCursosAbiertos(ActionEvent e){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("coor_reporte_curso_abierto.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public CursoPeriodo getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
	}


	public boolean isEditMode() {
		return editMode;
	}


	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public int getNumeroAlumnosInscritos() {
		return numeroAlumnosInscritos;
	}

	public void setNumeroAlumnosInscritos(int numeroAlumnosInscritos) {
		this.numeroAlumnosInscritos = numeroAlumnosInscritos;
	}
	
}
