package org.espe.sigec.web.admGeneral.curso;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.ModuloCurso;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.servicio.curso.CursoServicio;
import org.espe.sigec.servicio.planificacion.PlanificacionServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author roberto
 *
 */
@ManagedBean(name ="edicionCursoController")
@ViewScoped
public class EdicionCursoController {
	private PensumAcademico pensumAcademicoNuevo;
	private Curso curso;
	@Inject
	private PlanificacionServicio planificacionServicio;
	@Inject
	private CursoServicio cursoServicio;
	
	public EdicionCursoController() {
		setCurso((Curso) FacesUtils.getFlashObject("cursoToEdit"));
		initEntities();
	}
	
	@PostConstruct
	public void addLostFields(){
		getCurso().setModuloCursoCollection(cursoServicio.findModulosCurso(curso.getIdCurso()));
		
		if(getCurso().getModuloCursoCollection() == null){
			getCurso().setModuloCursoCollection(new ArrayList<ModuloCurso>());
		}else{
			for(ModuloCurso moduloCursoTMP: getCurso().getModuloCursoCollection()){
				moduloCursoTMP.setExistInBase(Boolean.TRUE);
			}
		}
	}
	
	public void btnSaveEditCurso(ActionEvent e){
		try {
			planificacionServicio.editarCursoModulo(getCurso(), getCurso().getModuloCursoCollection());
			FacesUtils.addInfoMessage("El curso se edito con &eacutexito");
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("El curso no pudo editarse");
		}
	}
	public void btnAddTemaPensum(ActionEvent e){
//		getCurso().getPensumAcademicoCollection().add(pensumAcademicoNuevo);
		setPensumAcademicoNuevo(new PensumAcademico());
	}
	
	public void btnAtras(ActionEvent e){
		try {
			FacesUtils.redirectPage("reporte_cursos.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void initEntities(){
//		setLstPensumAcademicos(new ArrayList<PensumAcademico>());
		setPensumAcademicoNuevo(new PensumAcademico());
	}
//	public Collection<PensumAcademico> getLstPensumAcademicos() {
//		return lstPensumAcademicos;
//	}
//	public void setLstPensumAcademicos(
//			Collection<PensumAcademico> lstPensumAcademicos) {
//		this.lstPensumAcademicos = lstPensumAcademicos;
//	}
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
	
	
}
