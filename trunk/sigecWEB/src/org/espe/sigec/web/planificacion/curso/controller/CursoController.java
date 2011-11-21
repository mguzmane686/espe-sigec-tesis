package org.espe.sigec.web.planificacion.curso.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.PensumAcademicoFacadeLocal;
import org.espe.sigec.web.utils.CommonController;
import org.espe.sigec.web.utils.FacesUtils;
import org.richfaces.component.UIPanelMenu;

@SuppressWarnings("serial")
@ManagedBean(name="cursoController")
@ViewScoped
public class CursoController extends CommonController{
	@EJB
	private PensumAcademicoFacadeLocal pensumAcademicoFacadeLocal;
	@EJB
	private CursoFacadeLocal cursoFacadeLocal;
	
	private Collection<PensumAcademico> lstPensumAcademicos;
	private PensumAcademico pensumAcademicoNuevo;
	private Curso curso;
	
	public CursoController() {
		setUiPanelMenu( (UIPanelMenu) FacesUtils.getFlashObject("menuSigec"));
		curso = new Curso();
		lstPensumAcademicos = new ArrayList<PensumAcademico>();
		pensumAcademicoNuevo = new PensumAcademico();
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
			FacesUtils.addInfoMessage("Curso creado satisfactoriamente");

		} catch (Exception e1) {
			e1.printStackTrace();
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
	
}
