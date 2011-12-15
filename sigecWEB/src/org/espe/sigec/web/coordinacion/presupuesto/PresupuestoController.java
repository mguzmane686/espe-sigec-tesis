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
	@Inject 
	private PresupuestoServicio objPreServicio; 
	private Collection<DetallePresupuestoCurso> lstDetPreCurso;
	private PresupuestoCurso objPreCurso;
	
	public PresupuestoController() {
		lstDetPreCurso = new ArrayList<DetallePresupuestoCurso>();
		DetallePresupuestoCurso curso = new DetallePresupuestoCurso("INSTRUCTOR");
		lstDetPreCurso.add(curso);
		curso = new DetallePresupuestoCurso("TRANSPORTE");
		lstDetPreCurso.add(curso);
	}

	public void btnSavePresupuesto(ActionEvent e){
		try {
			objPreServicio.guardarPresupuesto(getObjPreCurso(), getLstDetPreCurso());
			FacesUtils.addInfoMessage("Presupuesto guardado");
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("Presupuesto no guardado");
		}
	}

	public Collection<DetallePresupuestoCurso> getLstDetPreCurso() {
		return lstDetPreCurso;
	}

	public void setLstDetPreCurso(Collection<DetallePresupuestoCurso> lstDetPreCurso) {
		this.lstDetPreCurso = lstDetPreCurso;
	}

	public PresupuestoCurso getObjPreCurso() {
		return objPreCurso;
	}

	public void setObjPreCurso(PresupuestoCurso objPreCurso) {
		this.objPreCurso = objPreCurso;
	}
		
}
