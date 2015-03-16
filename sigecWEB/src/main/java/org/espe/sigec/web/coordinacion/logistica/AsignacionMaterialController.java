package org.espe.sigec.web.coordinacion.logistica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.espe.sigec.exception.SigecException;
import org.espe.sigec.model.entities.MaterialDidacticoCatalogo;
import org.espe.sigec.model.entities.MaterialDidacticoCurso;
import org.espe.sigec.model.entities.MaterialDidacticoCursoPK;
import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.servicio.coordinacion.CoordinacionServicio;
import org.espe.sigec.servicio.planificacion.PlanificacionServicio;

@SuppressWarnings("serial")
@ManagedBean(name="asignacionMaterialController")
@ViewScoped
public class AsignacionMaterialController implements Serializable{
	@Inject
	private PlanificacionServicio planificacionServicio;
	@Inject
	private CoordinacionServicio coordinacionServicio;
	
	private Programa programa;
	
	private Collection<Programa> lstProgramas;
	private Collection<ProgramaCurso> lstProgramaCursos;
	private ProgramaCurso programaCursoSeleccionado;
	
	private Collection<MaterialDidacticoCurso> lstMaterialDidacticosCursos;
	private Collection<MaterialDidacticoCurso> lstMaterialDidacticosCursosClone;
	private Collection<MaterialDidacticoCatalogo> lstMaterialDidacticoCatalogos;
	
	private boolean editMode;
	
	public AsignacionMaterialController() {
		setPrograma(new Programa());
	}
	
	@PostConstruct
	public void initController(){
		setLstProgramas(planificacionServicio.buscarPrograma());
	}


	public void rsCargarCursosPrograma(ValueChangeEvent valueChangeEvent){		
		getPrograma().setIdPrograma(Integer.parseInt(String.valueOf(valueChangeEvent.getNewValue())));
		setLstProgramaCursos(planificacionServicio.buscarCursosAsignadosPrograma(getPrograma()));
		setProgramaCursoSeleccionado(null);
		this.lstMaterialDidacticosCursosClone = null;
	}

	public void btnAgregarMaterial(){
		lstMaterialDidacticosCursosClone = (Collection<MaterialDidacticoCurso>)SerializationUtils.clone((Serializable)getLstMaterialDidacticosCursos());
		setEditMode(Boolean.TRUE);
		try {
			
			Integer []idMateriales = null;
			if(CollectionUtils.isNotEmpty(getLstMaterialDidacticosCursos())){
				idMateriales = new Integer[getLstMaterialDidacticosCursos().size()];
				int cont =0;
				for(MaterialDidacticoCurso materialDidacticoCurso: getLstMaterialDidacticosCursos()){
					idMateriales[cont] = materialDidacticoCurso.getMaterialDidacticoCursoPK().getIdMaterial();
					cont++;
				}
			}
			
			setLstMaterialDidacticoCatalogos(coordinacionServicio.findMaterialDidacticoCatalogos(idMateriales, "ACT"));
			for(MaterialDidacticoCatalogo materialDidacticoCatalogo:  getLstMaterialDidacticoCatalogos()){
				MaterialDidacticoCurso materialDidacticoCurso = new MaterialDidacticoCurso();
				materialDidacticoCurso.setMaterialDidacticoCursoPK(new MaterialDidacticoCursoPK(materialDidacticoCatalogo.getIdMaterial(), getProgramaCursoSeleccionado().getProgramaCursoPK().getIdCursoPeriodo()));
				materialDidacticoCurso.setMaterialDidacticoCatalogo(materialDidacticoCatalogo);
				getLstMaterialDidacticosCursos().add(materialDidacticoCurso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void btnCancelarAgregarMaterial(){
		setLstMaterialDidacticosCursos(lstMaterialDidacticosCursosClone);
		setEditMode(Boolean.FALSE);
	}
	
	public void btnGuardarAgregarMaterial(){
		setEditMode(Boolean.FALSE);
		try {
			coordinacionServicio.actualizarListaMaterialesCurso(lstMaterialDidacticosCursosClone, getLstMaterialDidacticosCursos());
			Collection<MaterialDidacticoCurso> ltTmp = new ArrayList<MaterialDidacticoCurso>();
			for(MaterialDidacticoCurso materialDidacticoCurso: getLstMaterialDidacticosCursos()){
				if(materialDidacticoCurso.isSelected()){
					ltTmp.add(materialDidacticoCurso);
				}
			}
			
			setLstMaterialDidacticosCursos(ltTmp);
			
		} catch (SigecException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void btnCargarMaterialesCurso(ProgramaCurso programaCurso){
		setEditMode(Boolean.FALSE);
		setProgramaCursoSeleccionado(programaCurso);
		System.out.println(programaCurso);
		try {
			setLstMaterialDidacticosCursos(coordinacionServicio.findMaterialDidacticoAsignado(programaCurso.getProgramaCursoPK().getIdCursoPeriodo()));
			
			for(MaterialDidacticoCurso materialDidacticoCurso: getLstMaterialDidacticosCursos()){
				materialDidacticoCurso.setSelected(Boolean.TRUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Collection<ProgramaCurso> getLstProgramaCursos() {
		return lstProgramaCursos;
	}

	public void setLstProgramaCursos(Collection<ProgramaCurso> lstProgramaCursos) {
		this.lstProgramaCursos = lstProgramaCursos;
	}

	public Collection<Programa> getLstProgramas() {
		return lstProgramas;
	}
	
	public void setLstProgramas(Collection<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}
	
	public Collection<MaterialDidacticoCurso> getLstMaterialDidacticosCursos() {
		return lstMaterialDidacticosCursos;
	}

	public void setLstMaterialDidacticosCursos(
			Collection<MaterialDidacticoCurso> lstMaterialDidacticosCursos) {
		this.lstMaterialDidacticosCursos = lstMaterialDidacticosCursos;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Collection<MaterialDidacticoCatalogo> getLstMaterialDidacticoCatalogos() {
		return lstMaterialDidacticoCatalogos;
	}

	public void setLstMaterialDidacticoCatalogos(
			Collection<MaterialDidacticoCatalogo> lstMaterialDidacticoCatalogos) {
		this.lstMaterialDidacticoCatalogos = lstMaterialDidacticoCatalogos;
	}

	public ProgramaCurso getProgramaCursoSeleccionado() {
		return programaCursoSeleccionado;
	}

	public void setProgramaCursoSeleccionado(ProgramaCurso programaCursoSeleccionado) {
		this.programaCursoSeleccionado = programaCursoSeleccionado;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	
}
