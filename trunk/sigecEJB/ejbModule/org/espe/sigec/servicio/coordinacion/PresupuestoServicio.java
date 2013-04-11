package org.espe.sigec.servicio.coordinacion;

import java.math.BigDecimal;
import java.util.Collection;

import org.espe.sigec.model.entities.CatalogoSigec;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.model.entities.PresupuestoCurso;
import org.espe.sigec.model.entities.PresupuestoDetalle;

@QPresupuesto
public interface PresupuestoServicio {
	public void guardarPresupuesto(CursoPeriodo cursoPeriodo, PresupuestoCurso presupuestoCurso,
								   Collection<DetallePresupuestoCurso> lstDetPreCur) throws Exception;
	public void actualizarPresupuesto(CursoPeriodo cursoPeriodo, PresupuestoCurso presupuestoCurso,
			   Collection<DetallePresupuestoCurso> lstDetPreCur) throws Exception;
	public Collection<CatalogoSigec> findCatalogo(String parentId);
	
	public PresupuestoCurso findPresupuestoCurso(BigDecimal idCursoPeriodo);
	
	public Collection<PresupuestoDetalle> findDetallesPresupestoActual(int anioActual);
}
