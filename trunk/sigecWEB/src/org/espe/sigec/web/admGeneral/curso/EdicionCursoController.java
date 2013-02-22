package org.espe.sigec.web.admGeneral.curso;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Curso;
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
//	private ContenidoCurso moduloCurso;
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
		getCurso().setPensumAcademicoCollection(cursoServicio.findTemasModulo(curso.getIdCurso()));
//		
		if(getCurso().getPensumAcademicoCollection() == null){
			getCurso().setPensumAcademicoCollection(new ArrayList<PensumAcademico>());
		}else{
			for(PensumAcademico pensumAcademicoTMP: getCurso().getPensumAcademicoCollection()){
				pensumAcademicoTMP.setExistInBase(Boolean.TRUE);
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
			planificacionServicio.editarCurso(getCurso(), getCurso().getPensumAcademicoCollection());
			FacesUtils.addInfoMessage("El curso se edito con &eacutexito");
			setRenderEditButtons(Boolean.FALSE);
		} catch (Exception e1) {
			e1.printStackTrace();
			FacesUtils.addErrorMessage("El curso no pudo editarse");
		}
	}
	
//	public void btnExpandContractModulo(ContenidoCurso modulo, boolean expanded) {
//		modulo.setShowPensum(expanded);
//		if (expanded) {
//			modulo.setPensumAcademicoCollection(cursoServicio.findTemasModulo(modulo.getIdModuloCurso()));
//		}
//	}
	
	public void btnAddTemaPensum(ActionEvent e){
		setPensumAcademicoNuevo(new PensumAcademico());
	}
	
	public void btnAddModuloCurso(ActionEvent e){
		getCurso().getPensumAcademicoCollection().add(getPensumAcademicoNuevo());
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
		setPensumAcademicoNuevo(new PensumAcademico());
//		setModuloCurso(new ContenidoCurso());
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

//	public ContenidoCurso getModuloCurso() {
//		return moduloCurso;
//	}

//	public void setModuloCurso(ContenidoCurso moduloCurso) {
//		this.moduloCurso = moduloCurso;
//	}

	public boolean isRenderEditButtons() {
		return renderEditButtons;
	}

	public void setRenderEditButtons(boolean renderEditButtons) {
		this.renderEditButtons = renderEditButtons;
	}
	
	
}
