package org.espe.sigec.web.coordinacion.presupuesto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.CatalogoSigec;
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
	
//	private PresupuestoCurso objPreCurso;
	private Collection<CatalogoSigec> lstCatalogoSigecs;
	
	private Collection<DetallePresupuestoCurso> lstDetallePresupuestoCursos;
	
	
	public PresupuestoController() {
		
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

	public void btnSavePresupuesto(ActionEvent e){
		try {
//			presupuestoServicio.guardarPresupuesto(getObjPreCurso(), getLstDetallePresupuestoCursos());
			FacesUtils.addInfoMessage("Presupuesto guardado");
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("Presupuesto no guardado");
		}
	}

//	public PresupuestoCurso getObjPreCurso() {
//		return objPreCurso;
//	}
//
//	public void setObjPreCurso(PresupuestoCurso objPreCurso) {
//		this.objPreCurso = objPreCurso;
//	}

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

		
}
