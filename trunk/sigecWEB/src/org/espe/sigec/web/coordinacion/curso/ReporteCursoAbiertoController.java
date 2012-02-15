package org.espe.sigec.web.coordinacion.curso;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.servicio.curso.CursoServicio;
import org.espe.sigec.web.seguridad.HomeSessionController;
import org.espe.sigec.web.utils.FacesUtils;
import org.richfaces.component.SortOrder;
import org.richfaces.model.Filter;
@SuppressWarnings("serial")
@ManagedBean(name="reporteCursoAbiertoController")
@ViewScoped
public class ReporteCursoAbiertoController implements Serializable{

	@Inject
	private CursoServicio cursoServicio;
	
	private Collection<CursoPeriodo> lstCursoPeriodos;
	
	private SortOrder namesOrder = SortOrder.unsorted;
	private String nameFilter;
	
	private Collection<SelectItem> itemsEstado;
	public ReporteCursoAbiertoController() {
		setLstCursoPeriodos(new ArrayList<CursoPeriodo>());
	}
	
	@PostConstruct
	public void loadCursoAbierto(){
		setLstCursoPeriodos(cursoServicio.findCursoAbierto());
		
		setLstCursoPeriodos(cursoServicio.findCursoAbiertoByUser(((HomeSessionController) FacesUtils.getManagedBean("homeSessionController")).getUsuarioPerfil().getPersona().getIdPersona()));
		
		setItemsEstado(new ArrayList<SelectItem>());
		getItemsEstado().add(new SelectItem(null, "TODOS"));
		getItemsEstado().add(new SelectItem("ABIERTO", "ABIERTO"));
		getItemsEstado().add(new SelectItem("EJECUCION", "EJECUCION"));
		
	}
	
	public void btnAdministrarCursoAbierto(CursoPeriodo cursoPeriodo){
		try {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("curso", cursoPeriodo);
			FacesContext.getCurrentInstance().getExternalContext().redirect("coor_administrar_curso_abierto.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void sortByNames() {	
		if (namesOrder.equals(SortOrder.ascending)) {
			setNamesOrder(SortOrder.descending);
		} else {
			setNamesOrder(SortOrder.ascending);
		}
	}

	public Filter<?> getFilterVendor() {
        return new Filter<String>() {
            public boolean accept(String t) {
                String vendor = getNameFilter();
                if (vendor == null || vendor.length() == 0 || vendor.equals(t)) {
                    return true;
                }
                return false;
            }
        };
    }
	
	public Collection<CursoPeriodo> getLstCursoPeriodos() {
		return lstCursoPeriodos;
	}
	public void setLstCursoPeriodos(Collection<CursoPeriodo> lstCursoPeriodos) {
		this.lstCursoPeriodos = lstCursoPeriodos;
	}

	public SortOrder getNamesOrder() {
		return namesOrder;
	}

	public void setNamesOrder(SortOrder namesOrder) {
		this.namesOrder = namesOrder;
	}

	public String getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}

	public Collection<SelectItem> getItemsEstado() {
		return itemsEstado;
	}

	public void setItemsEstado(Collection<SelectItem> itemsEstado) {
		this.itemsEstado = itemsEstado;
	}
	
}
