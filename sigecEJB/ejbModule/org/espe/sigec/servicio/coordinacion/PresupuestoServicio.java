package org.espe.sigec.servicio.coordinacion;

import java.util.Collection;

import org.espe.sigec.model.entities.CatalogoSigec;
import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.model.entities.PresupuestoCurso;

@QPresupuesto
public interface PresupuestoServicio {
	public void guardarPresupuesto(PresupuestoCurso presupuestoCurso,
								   Collection<DetallePresupuestoCurso> lstDetPreCur) throws Exception;

	public Collection<CatalogoSigec> findCatalogo(String parentId);
}
