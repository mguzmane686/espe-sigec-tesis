package org.espe.sigec.servicio.coordinacion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.model.entities.CatalogoSigec;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.model.entities.PresupuestoCurso;
import org.espe.sigec.model.sessionBeans.CatalogoSigecFacadeLocal;
import org.espe.sigec.model.sessionBeans.DetallePresupuestoCursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.PresupuestoCursoFacadeLocal;

public class PresupuestoServicioImpl implements PresupuestoServicio{

	@EJB
	private PresupuestoCursoFacadeLocal presupuestoCursoFacadeLocal;
	
	@EJB
	private DetallePresupuestoCursoFacadeLocal detallePresupuestoCursoFacadeLocal;
	@EJB
	private CatalogoSigecFacadeLocal catalogoSigecFacadeLocal;
	@Resource
	private UserTransaction userTransaction;
	@Override
	public void guardarPresupuesto(CursoPeriodo cursoPeriodo, PresupuestoCurso presupuestoCurso,
								   Collection<DetallePresupuestoCurso> lstDetPreCur) throws Exception {
		
		userTransaction.begin();
			
		try {
			presupuestoCurso.setCursoPeriodo(cursoPeriodo);
			presupuestoCurso.setIdCursoPeriodo(cursoPeriodo.getIdCursoPeriodo());
			presupuestoCursoFacadeLocal.create(presupuestoCurso);
			
			long i=1;
			for (DetallePresupuestoCurso objDetPresCurso:lstDetPreCur){
				
				objDetPresCurso.getDetallePresupuestoCursoPK().setIdCursoPeriodo(BigInteger.valueOf(cursoPeriodo.getIdCursoPeriodo().longValue()));
				objDetPresCurso.setPresupuestoCurso(presupuestoCurso);
				objDetPresCurso.getDetallePresupuestoCursoPK().setIdDetalle(BigInteger.valueOf(i));
				detallePresupuestoCursoFacadeLocal.create(objDetPresCurso);
				i++;
			}
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			throw new Exception(e);
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

	@Override
	public void actualizarPresupuesto(CursoPeriodo cursoPeriodo,
			PresupuestoCurso presupuestoCurso,
			Collection<DetallePresupuestoCurso> lstDetPreCur) throws Exception {
		userTransaction.begin();
		try {
			for(DetallePresupuestoCurso detallePresupuestoCurso: lstDetPreCur){
				detallePresupuestoCursoFacadeLocal.edit(detallePresupuestoCurso);
			}
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			throw new Exception(e);
		}
		
		
	}
	
}
