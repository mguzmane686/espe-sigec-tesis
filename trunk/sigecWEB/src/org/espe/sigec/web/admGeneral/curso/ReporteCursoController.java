package org.espe.sigec.web.admGeneral.curso;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.PensumAcademico;
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

	private Collection<Curso> lstCursos;

	private String nameFilter;
	private SortOrder namesOrder = SortOrder.unsorted;

	public ReporteCursoController() {
		setLstCursos(new ArrayList<Curso>());
	}

	@PostConstruct
	public void loadCursos() {
		setLstCursos(cursoServicio.findCursos());
		for (Curso curso : getLstCursos()) {
			curso.setPensumAcademicoCollection(new ArrayList<PensumAcademico>());
		}
	}

	public void btnExpandContractCurso(Curso curso, boolean expanded) {
		curso.setShowCursoPeriodoCollection(expanded);
		if (expanded) {
			curso.setPensumAcademicoCollection(cursoServicio
					.findTemasCurso(curso.getIdCurso()));
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

}
