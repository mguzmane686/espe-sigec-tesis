package org.espe.sigec.web.admGeneral.curso;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.ModuloCurso;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.servicio.planificacion.PlanificacionServicio;
import org.espe.sigec.web.utils.CommonController;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.SigecConstantes;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="cursoController")
@ViewScoped
public class NuevoCursoController extends CommonController{
	
	@Inject
	PlanificacionServicio planificacionServicio;
	
	private Collection<PensumAcademico> lstPensumAcademicos;
	private Collection<ModuloCurso> lstModuloCursos;
	private ModuloCurso moduloCurso;
	
	private Collection<SelectItem> itemEspecialidades;
	
	private PensumAcademico pensumAcademicoNuevo;
	private Curso curso;
	
	public NuevoCursoController() {
		initEntities();
		setItemEspecialidades(new ArrayList<SelectItem>());
	}
	private void initEntities(){
		setCurso(new Curso());
		getCurso().setEstadoCur(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
		getCurso().setEspecialidad(new Especialidad());
		setPensumAcademicoNuevo(new PensumAcademico());
		setLstPensumAcademicos(new ArrayList<PensumAcademico>());
		setLstModuloCursos(new ArrayList<ModuloCurso>());
		setModuloCurso(new ModuloCurso());
	}
	
	@PostConstruct
	public void loadEspecialidades(){
		for(Especialidad especialidadTMP: planificacionServicio.findEspecialidades()){
			getItemEspecialidades().add(new SelectItem(especialidadTMP.getIdEspecialidad(), especialidadTMP.getNombre()));
		}
	}
	
	public void btnAddModuloCurso(ActionEvent e){
		getLstModuloCursos().add(getModuloCurso());
		setModuloCurso(new ModuloCurso());
	}
	
	public void btnAddTemaPensum(ActionEvent e){
		getLstPensumAcademicos().add(pensumAcademicoNuevo);
		pensumAcademicoNuevo = new PensumAcademico();
	}

	public void btnSaveCurso(ActionEvent e){
		try {
			planificacionServicio.crearNuevoCursoModulo(getCurso(), getLstModuloCursos());
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
	public Collection<ModuloCurso> getLstModuloCursos() {
		return lstModuloCursos;
	}
	public void setLstModuloCursos(Collection<ModuloCurso> lstModuloCursos) {
		this.lstModuloCursos = lstModuloCursos;
	}
	public ModuloCurso getModuloCurso() {
		return moduloCurso;
	}
	public void setModuloCurso(ModuloCurso moduloCurso) {
		this.moduloCurso = moduloCurso;
	}
	
}
