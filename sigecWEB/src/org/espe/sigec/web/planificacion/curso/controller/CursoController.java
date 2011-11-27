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
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.EspecialidadFacadeLocal;
import org.espe.sigec.model.sessionBeans.PensumAcademicoFacadeLocal;
import org.espe.sigec.web.utils.CommonController;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.SigecConstantes;

@SuppressWarnings("serial")
@ManagedBean(name="cursoController")
@ViewScoped
public class CursoController extends CommonController{
	@EJB
	private PensumAcademicoFacadeLocal pensumAcademicoFacadeLocal;
	@EJB
	private CursoFacadeLocal cursoFacadeLocal;
	@EJB
	private EspecialidadFacadeLocal especialidadFacadeLocal;
	
	private Collection<PensumAcademico> lstPensumAcademicos;
	private Collection<SelectItem> itemEspecialidades;
	
	private PensumAcademico pensumAcademicoNuevo;
	private Curso curso;
	
	public CursoController() {
		initEntities();
		setItemEspecialidades(new ArrayList<SelectItem>());
	}
	private void initEntities(){
		setCurso(new Curso());
		getCurso().setEstadoCur(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
		getCurso().setEspecialidad(new Especialidad());
		setPensumAcademicoNuevo(new PensumAcademico());
		setLstPensumAcademicos(new ArrayList<PensumAcademico>());
	}
	@PostConstruct
	public void loadEspecialidades(){
		for(Especialidad especialidadTMP: especialidadFacadeLocal.findAll()){
			getItemEspecialidades().add(new SelectItem(especialidadTMP.getIdEspecialidad(), especialidadTMP.getNombre()));
		}
	}
	public void btnAddTemaPensum(ActionEvent e){
		System.out.println("nuevo tema");
		getLstPensumAcademicos().add(pensumAcademicoNuevo);
		pensumAcademicoNuevo = new PensumAcademico();
	}

	public void btnSaveCurso(ActionEvent e){
		try {
			cursoFacadeLocal.create(getCurso());
			for(PensumAcademico pensumAcademicoTMP: getLstPensumAcademicos()){
				pensumAcademicoTMP.setCurso(getCurso());
				pensumAcademicoFacadeLocal.create(pensumAcademicoTMP);
			}
			initEntities();
			FacesUtils.addInfoMessage("Curso creado satisfactoriamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("Ha ocurrido un error al crear el Curso");
		}
	}
	
	public Collection<PensumAcademico> getLstPensumAcademicos() {
		return lstPensumAcademicos;
	}

	public void setLstPensumAcademicos(
			Collection<PensumAcademico> lstPensumAcademicos) {
		this.lstPensumAcademicos = lstPensumAcademicos;
	}

	public PensumAcademico getPensumAcademicoNuevo() {
		return pensumAcademicoNuevo;
	}

	public void setPensumAcademicoNuevo(PensumAcademico pensumAcademicoNuevo) {
		this.pensumAcademicoNuevo = pensumAcademicoNuevo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Collection<SelectItem> getItemEspecialidades() {
		return itemEspecialidades;
	}

	public void setItemEspecialidades(Collection<SelectItem> itemEspecialidades) {
		this.itemEspecialidades = itemEspecialidades;
	}
	
}
