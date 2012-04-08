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
	private ModuloCurso moduloCurso;
	private Curso curso;
	private boolean renderEditButtons;
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
				moduloCursoTMP.setPensumAcademicoCollection(new ArrayList<PensumAcademico>());
			}
		}
	}
	
	public void btnEditCurso(ActionEvent e){
		setRenderEditButtons(Boolean.TRUE);
	}
	
	public void btnCancelEditCurso(ActionEvent e){
		setRenderEditButtons(Boolean.FALSE);
	}
	public void btnSaveEditCurso(ActionEvent e){
		try {
			planificacionServicio.editarCursoModulo(getCurso(), getCurso().getModuloCursoCollection());
			FacesUtils.addInfoMessage("El curso se edito con &eacutexito");
			setRenderEditButtons(Boolean.FALSE);
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("El curso no pudo editarse");
		}
	}
	
	public void btnExpandContractModulo(ModuloCurso modulo, boolean expanded) {
		modulo.setShowPensum(expanded);
		if (expanded) {
			modulo.setPensumAcademicoCollection(cursoServicio.findTemasModulo(modulo.getIdModuloCurso()));
		}
	}
	
	public void btnAddTemaPensum(ActionEvent e){
		setPensumAcademicoNuevo(new PensumAcademico());
	}
	
	public void btnAddModuloCurso(ActionEvent e){
		getCurso().getModuloCursoCollection().add(getModuloCurso());
		setModuloCurso(new ModuloCurso());
	}
	
	public void btnAtras(ActionEvent e){
		try {
			FacesUtils.redirectPage("reporte_cursos.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void initEntities(){
		setPensumAcademicoNuevo(new PensumAcademico());
		setModuloCurso(new ModuloCurso());
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

	public ModuloCurso getModuloCurso() {
		return moduloCurso;
	}

	public void setModuloCurso(ModuloCurso moduloCurso) {
		this.moduloCurso = moduloCurso;
	}

	public boolean isRenderEditButtons() {
		return renderEditButtons;
	}

	public void setRenderEditButtons(boolean renderEditButtons) {
		this.renderEditButtons = renderEditButtons;
	}
	
	
}
