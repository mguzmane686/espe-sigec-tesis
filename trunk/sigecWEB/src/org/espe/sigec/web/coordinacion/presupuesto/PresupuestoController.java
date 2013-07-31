package org.espe.sigec.web.coordinacion.presupuesto;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang3.SerializationUtils;
import org.espe.sigec.model.entities.CatalogoSigec;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.model.entities.DetallePresupuestoCursoPK;
import org.espe.sigec.model.entities.PresupuestoCurso;
import org.espe.sigec.model.entities.PresupuestoDetalle;
import org.espe.sigec.servicio.coordinacion.PresupuestoServicio;
import org.espe.sigec.utils.SigecConstantes;
import org.espe.sigec.web.utils.FacesUtils;

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
	
	private InformePresupuesto informePresupuesto;
	
	
	private boolean editMode;
	
	public PresupuestoController() {
		setCursoPeriodo((CursoPeriodo) FacesUtils.getFlashObject("cursoPeriodo"));
		setLstDetallePresupuestoCursos(new ArrayList<DetallePresupuestoCurso>());
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void cargarCatalogo(){
		PresupuestoCurso presupuestoCurso = presupuestoServicio.findPresupuestoCurso(getCursoPeriodo().getIdCursoPeriodo());
		
		if(presupuestoCurso !=null){
			setEditMode(Boolean.TRUE);
			
			setPresupuestoCurso(presupuestoCurso);
			
			if(getPresupuestoCurso().getDetallePresupuestoCursoCollection()==null || getPresupuestoCurso().getDetallePresupuestoCursoCollection().isEmpty()){
				loadCatalogoPresupuesto();
			}else{
				
				
				try {
					if(presupuestoCurso.getDetallePresupuestoCursoCollection() !=null){
//						BeanComparator reverseOrderBeanComparator = new BeanComparator("detallePresupuestoCursoPK.idDetalle", new ReverseComparator(new ComparableComparator()));
						BeanComparator reverseOrderBeanComparator = new BeanComparator("detallePresupuestoCursoPK.idDetalle");
						Collections.sort((List<DetallePresupuestoCurso>) presupuestoCurso.getDetallePresupuestoCursoCollection(), reverseOrderBeanComparator);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				setLstDetallePresupuestoCursos(presupuestoCurso.getDetallePresupuestoCursoCollection());
				cargarCuentasPresupuesto();
				
				setInformePresupuesto(new InformePresupuesto(getTotalLista(), getCursoPeriodo().getMaximoEstudiantes(), getPresupuestoCurso().getPorcentageUtiEspe() , getPresupuestoCurso().getPorcentageMatOfi()));
				getInformePresupuesto().calculoPuntoEquilibrio(PresupuestoUtil.getPresupuestoUtil().getValorManuales(getLstDetallePresupuestoCursos()), PresupuestoUtil.getPresupuestoUtil().getValorRefrigerios(getLstDetallePresupuestoCursos()));
				
				setUpdatePresupuesto(Boolean.TRUE);
			}
		}else{
			setPresupuestoCurso(new PresupuestoCurso());
			loadCatalogoPresupuesto();
			setEditMode(Boolean.FALSE);
			setInformePresupuesto(new InformePresupuesto(BigDecimal.ZERO,0,0,0));
			cargarCuentasPresupuesto();
		}
	}
	
	private void cargarCuentasPresupuesto(){
		Collection<PresupuestoDetalle> lstCuentasPresupuesto = presupuestoServicio.findDetallesPresupestoActual(Calendar.getInstance().get(Calendar.YEAR));
		for(DetallePresupuestoCurso detallePresupuestoCurso: getLstDetallePresupuestoCursos()){
			detallePresupuestoCurso.setLstCuentasPresupuesto((Collection<PresupuestoDetalle>) SerializationUtils.clone((Serializable)lstCuentasPresupuesto));
			detallePresupuestoCurso.setIdCuenta( SerializationUtils.clone(((List<PresupuestoDetalle>) lstCuentasPresupuesto).get(0).getPresupuestoDetallePK().getIdCuenta()));
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
	
	public void btnEditPresupuesto(){
		setEditMode(Boolean.FALSE);
	}
	
	public void btnCancelEditPresupuesto(){
		setEditMode(Boolean.TRUE);
	}

	public void btnSavePresupuesto(ActionEvent e){
		try {
			if(isUpdatePresupuesto()){
				presupuestoServicio.actualizarPresupuesto(getCursoPeriodo(), getPresupuestoCurso(), lstDetallePresupuestoCursos);
			}else{
				presupuestoServicio.guardarPresupuesto(getCursoPeriodo(), getPresupuestoCurso(), lstDetallePresupuestoCursos);
				setUpdatePresupuesto(Boolean.TRUE);
			}
			setEditMode(Boolean.TRUE);
			setInformePresupuesto(new InformePresupuesto(getTotalLista(), getCursoPeriodo().getMaximoEstudiantes(), getPresupuestoCurso().getPorcentageUtiEspe() , getPresupuestoCurso().getPorcentageMatOfi()));
			getInformePresupuesto().calculoPuntoEquilibrio(PresupuestoUtil.getPresupuestoUtil().getValorManuales(getLstDetallePresupuestoCursos()), PresupuestoUtil.getPresupuestoUtil().getValorRefrigerios(getLstDetallePresupuestoCursos()));
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
		
		if(getLstDetallePresupuestoCursos() != null){
			for(DetallePresupuestoCurso detallePresupuestoCurso: getLstDetallePresupuestoCursos()){
				detallePresupuestoCurso.getCostoTotalUSD();
				totalLista = totalLista.add(detallePresupuestoCurso.getCostoTotalUSD());
			}
		}
		
		return totalLista;
	}

	public InformePresupuesto getInformePresupuesto() {
		return informePresupuesto;
	}

	public void setInformePresupuesto(InformePresupuesto informePresupuesto) {
		this.informePresupuesto = informePresupuesto;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
}
