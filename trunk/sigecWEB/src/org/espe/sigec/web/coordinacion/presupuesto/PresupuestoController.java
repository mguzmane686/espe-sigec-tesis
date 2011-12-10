package org.espe.sigec.web.coordinacion.presupuesto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.model.entities.PresupuestoCurso;
import org.espe.sigec.servicio.coordinacion.PresupuestoServicio;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name="presupuestoController")
@ViewScoped
public class PresupuestoController implements Serializable {

	private int n1;
	private int n2;
	private int total;
	@Inject 
	private PresupuestoServicio presupuestoServicio; 
	private Collection<DetallePresupuestoCurso> lstDetallePresupuestoCursos;
	private PresupuestoCurso presupuestoCurso;
	public PresupuestoController() {
		lstDetallePresupuestoCursos = new ArrayList<DetallePresupuestoCurso>();
		DetallePresupuestoCurso curso = new DetallePresupuestoCurso("INSTRUCTOR");
		lstDetallePresupuestoCursos.add(curso);
	}

	public void btnSavePresupuesto(ActionEvent e){
		try {
			presupuestoServicio.guardarPresupuesto(getPresupuestoCurso(), getLstDetallePresupuestoCursos());
			FacesUtils.addInfoMessage("Presupuesto guardado");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			FacesUtils.addErrorMessage("Presupuesto no guardado");
		}
	}
	public int getN1() {
		return n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}

	public int getN2() {
		return n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Collection<DetallePresupuestoCurso> getLstDetallePresupuestoCursos() {
		return lstDetallePresupuestoCursos;
	}

	public void setLstDetallePresupuestoCursos(
			Collection<DetallePresupuestoCurso> lstDetallePresupuestoCursos) {
		this.lstDetallePresupuestoCursos = lstDetallePresupuestoCursos;
	}

	public PresupuestoCurso getPresupuestoCurso() {
		return presupuestoCurso;
	}

	public void setPresupuestoCurso(PresupuestoCurso presupuestoCurso) {
		this.presupuestoCurso = presupuestoCurso;
	}
	

	
}
