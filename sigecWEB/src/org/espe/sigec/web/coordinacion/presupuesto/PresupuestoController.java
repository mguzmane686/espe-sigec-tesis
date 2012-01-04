package org.espe.sigec.web.coordinacion.presupuesto;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.CatalogoSigec;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.servicio.coordinacion.PresupuestoServicio;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.SigecConstantes;

@SuppressWarnings("serial")
@ManagedBean(name="presupuestoController")
@ViewScoped
public class PresupuestoController implements Serializable {
	@Inject 
	private PresupuestoServicio presupuestoServicio; 
	private CursoPeriodo cursoPeriodo;
	
	private Collection<CatalogoSigec> lstCatalogoSigecs;
	private Collection<DetallePresupuestoCurso> lstDetallePresupuestoCursos;
	
	
	public PresupuestoController() {
		setCursoPeriodo((CursoPeriodo) FacesUtils.getFlashObject("cursoPeriodo"));
		setLstDetallePresupuestoCursos(new ArrayList<DetallePresupuestoCurso>());
	}
	
	@PostConstruct
	public void cargarCatalogo(){
		lstCatalogoSigecs =  presupuestoServicio.findCatalogo(SigecConstantes.CATALOGO_COSTOS_GASTOS);
		for(CatalogoSigec catalogoSigec: lstCatalogoSigecs){
			System.out.println(catalogoSigec.getCodigo());
		}
		for(CatalogoSigec catalogoSigec: lstCatalogoSigecs){
			DetallePresupuestoCurso detallePresupuestoCurso = new DetallePresupuestoCurso();
			detallePresupuestoCurso.setCodElemento(catalogoSigec.getCodigo());
			detallePresupuestoCurso.setDescripcionCatalogo(catalogoSigec.getDescripcion());
			getLstDetallePresupuestoCursos().add(detallePresupuestoCurso);
		}
		
	}
	
	public void btnAtras(ActionEvent e){
		try {
			FacesUtils.redirectPage("coor_administrar_curso_abierto.jsf");
			FacesUtils.putFlashObject("curso", getCursoPeriodo());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void btnSavePresupuesto(ActionEvent e){
		try {
			FacesUtils.addInfoMessage("Presupuesto guardado");
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("Presupuesto no guardado");
		}
	}
	
	public Collection<CatalogoSigec> getLstCatalogoSigecs() {
		return lstCatalogoSigecs;
	}

	public void setLstCatalogoSigecs(Collection<CatalogoSigec> lstCatalogoSigecs) {
		this.lstCatalogoSigecs = lstCatalogoSigecs;
	}

	public Collection<DetallePresupuestoCurso> getLstDetallePresupuestoCursos() {
		return lstDetallePresupuestoCursos;
	}

	public void setLstDetallePresupuestoCursos(
			Collection<DetallePresupuestoCurso> lstDetallePresupuestoCursos) {
		this.lstDetallePresupuestoCursos = lstDetallePresupuestoCursos;
	}

	public CursoPeriodo getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
	}		
}
