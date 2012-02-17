package org.espe.sigec.web.admGeneral.curso;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.servicio.curso.CursoServicio;
import org.espe.sigec.web.reportes.ReporteGenerico;
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
	
	public void btnPDF(ActionEvent e){
		ReporteGenerico reporteGenerico = new ReporteGenerico();
		reporteGenerico .generarReporteSimple("curso_existentes", getLstCursos());
	}
	
	public void paint(OutputStream out, Object data)  {
		if (data instanceof String) {
			java.io.InputStream file = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream((String) data);
			int size;
			try {
				size = file.available();
				byte[] pdf = new byte[size];
				file.read(pdf);
				file.close();
				out.write(pdf);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void paint2(OutputStream out, Object obj) throws IOException{
		java.io.InputStream file = 
				FacesContext.getCurrentInstance().getExternalContext(). getResourceAsStream(this.getClass().getClassLoader().getResource("/home/roberto/Desktop/JSF2/test.pdf").getFile());
	              
	              if(file== null) {
	            	  System.out.println("File Not Found in classpath");
	              }
	              int size = file.available();
	              System.out.println("size of file:"+size);
	              byte[] pdf = new byte[size];

	              file.read(pdf);

	              file.close();

	              out.write(pdf);

	    	
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
