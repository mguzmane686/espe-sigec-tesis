package org.espe.sigec.servicio.coordinacion;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.CatalogoSigec;
import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.model.entities.PresupuestoCurso;
import org.espe.sigec.model.sessionBeans.CatalogoSigecFacadeLocal;
import org.espe.sigec.model.sessionBeans.DetallePresupuestoCursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.PresupuestoCursoFacadeLocal;

public class PresupuestoServicioImpl implements PresupuestoServicio{

	@EJB
	private PresupuestoCursoFacadeLocal presupuestoCursoFacadeLocal;
	
	@EJB
	private DetallePresupuestoCursoFacadeLocal objDetPreCurFacLoc;
	@EJB
	private CatalogoSigecFacadeLocal catalogoSigecFacadeLocal;
	
	@Override
	public void guardarPresupuesto(PresupuestoCurso presupuestoCurso,
								   Collection<DetallePresupuestoCurso> lstDetPreCur) throws Exception {
		for (DetallePresupuestoCurso objDetPresCurso:lstDetPreCur){
			objDetPresCurso.setPresupuestoCurso(presupuestoCurso);
			objDetPreCurFacLoc.create(objDetPresCurso);
		}
	}

	@Override
	public Collection<CatalogoSigec> findCatalogo(String parentId) {
		return catalogoSigecFacadeLocal.findCatalogo(parentId);
	}

	@Override
	public PresupuestoCurso findPresupuestoCurso(BigDecimal idCursoPeriodo) {
		return presupuestoCursoFacadeLocal.findPresupuestoCurso(idCursoPeriodo);
	}
	
}
