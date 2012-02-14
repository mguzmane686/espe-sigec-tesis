package org.espe.sigec.web.coordinacion.presupuesto;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
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
import org.espe.sigec.model.entities.DetallePresupuestoCursoPK;
import org.espe.sigec.model.entities.PresupuestoCurso;
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
	private boolean updatePresupuesto;
	
	private PresupuestoCurso presupuestoCurso;
	
	public PresupuestoController() {
		setCursoPeriodo((CursoPeriodo) FacesUtils.getFlashObject("cursoPeriodo"));
		setLstDetallePresupuestoCursos(new ArrayList<DetallePresupuestoCurso>());
	}
	
	@PostConstruct
	public void cargarCatalogo(){
		PresupuestoCurso presupuestoCurso = presupuestoServicio.findPresupuestoCurso(getCursoPeriodo().getIdCursoPeriodo());
		
		if(presupuestoCurso !=null){
			setPresupuestoCurso(presupuestoCurso);
			
			if(getPresupuestoCurso().getDetallePresupuestoCursoCollection()==null || getPresupuestoCurso().getDetallePresupuestoCursoCollection().isEmpty()){
				loadCatalogoPresupuesto();
			}else{
				setLstDetallePresupuestoCursos(presupuestoCurso.getDetallePresupuestoCursoCollection());
				updatePresupuesto = Boolean.TRUE;
			}
		}else{
			setPresupuestoCurso(new PresupuestoCurso());
			loadCatalogoPresupuesto();
		}
	}
	
	private void loadCatalogoPresupuesto(){
		lstCatalogoSigecs =  presupuestoServicio.findCatalogo(SigecConstantes.CATALOGO_COSTOS_GASTOS);
		
		for(CatalogoSigec catalogoSigec: lstCatalogoSigecs){
			System.out.println(catalogoSigec.getCodigo());
		}
		for(CatalogoSigec catalogoSigec: lstCatalogoSigecs){
			DetallePresupuestoCurso detallePresupuestoCurso = new DetallePresupuestoCurso();
			detallePresupuestoCurso.setDetallePresupuestoCursoPK(new DetallePresupuestoCursoPK());
			
			detallePresupuestoCurso.getDetallePresupuestoCursoPK().setCodElemento(catalogoSigec.getCodigo());
			detallePresupuestoCurso.setDescripcionCatalogo(catalogoSigec.getDescripcion());
			detallePresupuestoCurso.setDetalle(catalogoSigec.getDescripcion());
			detallePresupuestoCurso.setUnidad(catalogoSigec.getTipoUnidad());
			getLstDetallePresupuestoCursos().add(detallePresupuestoCurso);
		}
	}
	public void btnAtras(ActionEvent e){
		try {
			FacesUtils.redirectPage("coor_reporte_curso_abierto.jsf");
			FacesUtils.putFlashObject("curso", getCursoPeriodo());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void btnSavePresupuesto(ActionEvent e){
		try {
			if(isUpdatePresupuesto()){
				presupuestoServicio.actualizarPresupuesto(getCursoPeriodo(), getPresupuestoCurso(), lstDetallePresupuestoCursos);
			}else{
				presupuestoServicio.guardarPresupuesto(getCursoPeriodo(), getPresupuestoCurso(), lstDetallePresupuestoCursos);
				setUpdatePresupuesto(Boolean.TRUE);
			}
			
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

	public PresupuestoCurso getPresupuestoCurso() {
		return presupuestoCurso;
	}
	

	public boolean isUpdatePresupuesto() {
		return updatePresupuesto;
	}

	public void setUpdatePresupuesto(boolean updatePresupuesto) {
		this.updatePresupuesto = updatePresupuesto;
	}

	public void setPresupuestoCurso(PresupuestoCurso presupuestoCurso) {
		this.presupuestoCurso = presupuestoCurso;
	}
	public BigDecimal getTotalLista(){
		BigDecimal totalLista = new BigDecimal(0);
		if(getPresupuestoCurso() != null){
			if(getPresupuestoCurso().getDetallePresupuestoCursoCollection() != null){
				for(DetallePresupuestoCurso detallePresupuestoCurso: getPresupuestoCurso().getDetallePresupuestoCursoCollection()){
					detallePresupuestoCurso.getCostoTotalUSD();
					totalLista = totalLista.add(detallePresupuestoCurso.getCostoTotalUSD());
				}
			}
		}
		return totalLista;
	}
}