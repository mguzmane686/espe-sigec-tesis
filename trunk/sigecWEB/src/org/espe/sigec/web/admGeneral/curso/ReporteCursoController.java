package org.espe.sigec.web.admGeneral.curso;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.ModuloCurso;
import org.espe.sigec.servicio.coordinacion.CoordinacionServicio;
import org.espe.sigec.servicio.curso.CursoServicio;
import org.espe.sigec.web.utils.FacesUtils;
import org.richfaces.component.SortOrder;

/**
 * @author Roberto
 * 
 */
@SuppressWarnings("serial")
@ManagedBean(name = "reporteCursoController")
@ViewScoped
public class ReporteCursoController implements Serializable {
	@Inject
	private CursoServicio cursoServicio;

	@Inject
	private CoordinacionServicio coordinacionServicio;
	private Collection<Curso> lstCursos;

	private String nameFilter;
	private String nameEspecialidadFilter;
	private SortOrder namesOrder = SortOrder.unsorted;
	private Collection<SelectItem> itemsEspecialidadesFiltro;
	
	public ReporteCursoController() {
		setLstCursos(new ArrayList<Curso>());
	}

	@PostConstruct
	public void loadCursos() {
		setItemsEspecialidadesFiltro(new ArrayList<SelectItem>());
		getItemsEspecialidadesFiltro().add(new SelectItem(null, "TODOS"));
		for(Especialidad especialidadTMP: coordinacionServicio.findEspecialidades()){
			getItemsEspecialidadesFiltro().add(new SelectItem(especialidadTMP.getNombre(), especialidadTMP.getNombre()));
		}
		
		setLstCursos(cursoServicio.findCursos());
		for (Curso curso : getLstCursos()) {
			curso.setModuloCursoCollection(new ArrayList<ModuloCurso>());
		}
	}
		 
	public void btnExpandContractCurso(Curso curso, boolean expanded) {
		curso.setShowCursoPeriodoCollection(expanded);
		if (expanded) {
			curso.setModuloCursoCollection(cursoServicio.findModulosCurso(curso.getIdCurso()));
		}
	}

	public void btnExpandContractModulo(ModuloCurso modulo, boolean expanded) {
		modulo.setShowPensum(expanded);
		if (expanded) {
			modulo.setPensumAcademicoCollection(cursoServicio.findTemasModulo(modulo.getIdModuloCurso()));
		}
	}
	
	public void btnShowCursoDetail(Curso curso) {
		try {
			FacesUtils.putFlashObject("cursoToEdit", curso);
			FacesUtils.redirectPage("pla_edicion_curso.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void sortByNames() {
		
		if (namesOrder.equals(SortOrder.ascending)) {
			setNamesOrder(SortOrder.descending);
		} else {
			setNamesOrder(SortOrder.ascending);
		}
	}

	public Collection<Curso> getLstCursos() {
		return lstCursos;
	}

	public void setLstCursos(Collection<Curso> lstCursos) {
		this.lstCursos = lstCursos;
	}

	public String getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}

	public SortOrder getNamesOrder() {
		return namesOrder;
	}

	public void setNamesOrder(SortOrder namesOrder) {
		this.namesOrder = namesOrder;
	}

	public String getNameEspecialidadFilter() {
		return nameEspecialidadFilter;
	}

	public void setNameEspecialidadFilter(String nameEspecialidadFilter) {
		this.nameEspecialidadFilter = nameEspecialidadFilter;
	}

	public Collection<SelectItem> getItemsEspecialidadesFiltro() {
		return itemsEspecialidadesFiltro;
	}

	public void setItemsEspecialidadesFiltro(
			Collection<SelectItem> itemsEspecialidadesFiltro) {
		this.itemsEspecialidadesFiltro = itemsEspecialidadesFiltro;
	}
	
}
