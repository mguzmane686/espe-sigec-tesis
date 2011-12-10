package org.espe.sigec.servicio.coordinacion;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.model.entities.PresupuestoCurso;
import org.espe.sigec.model.sessionBeans.DetallePresupuestoCursoFacadeLocal;

public class PresupuestoServicioImpl implements PresupuestoServicio{

	@EJB
	private DetallePresupuestoCursoFacadeLocal detallePresupuestoCursoFacadeLocal;
	
	@Override
	public void guardarPresupuesto(PresupuestoCurso presupuestoCurso,
			Collection<DetallePresupuestoCurso> lstDetallePresuCurso)
			throws Exception {
		for (DetallePresupuestoCurso detallePresuCurso:lstDetallePresuCurso){
			detallePresuCurso.setPresupuestoCurso(presupuestoCurso);
			detallePresupuestoCursoFacadeLocal.create(detallePresuCurso);
		}
		
	}
	

}
