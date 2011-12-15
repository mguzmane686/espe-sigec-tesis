package org.espe.sigec.servicio.coordinacion;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.model.entities.PresupuestoCurso;
import org.espe.sigec.model.sessionBeans.DetallePresupuestoCursoFacadeLocal;

public class PresupuestoServicioImpl implements PresupuestoServicio{

	@EJB
	private DetallePresupuestoCursoFacadeLocal objDetPreCurFacLoc;
	
	@Override
	public void guardarPresupuesto(PresupuestoCurso presupuestoCurso,
								   Collection<DetallePresupuestoCurso> lstDetPreCur) throws Exception {
		for (DetallePresupuestoCurso objDetPresCurso:lstDetPreCur){
			objDetPresCurso.setPresupuestoCurso(presupuestoCurso);
			objDetPreCurFacLoc.create(objDetPresCurso);
		}
	}
	
}
