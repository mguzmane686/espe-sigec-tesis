package org.espe.sigec.servicio.coordinacion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.model.entities.CatalogoSigec;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.model.entities.Presupuesto;
import org.espe.sigec.model.entities.PresupuestoCurso;
import org.espe.sigec.model.sessionBeans.CatalogoSigecFacadeLocal;
import org.espe.sigec.model.sessionBeans.DetallePresupuestoCursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.PresupuestoCursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.PresupuestoFacadeLocal;

public class PresupuestoServicioImpl implements PresupuestoServicio{

	@EJB
	private PresupuestoCursoFacadeLocal presupuestoCursoFacadeLocal;
	@EJB
	private PresupuestoFacadeLocal presupuestoFacadeLocal;
	@EJB
	private DetallePresupuestoCursoFacadeLocal detallePresupuestoCursoFacadeLocal;
	@EJB
	private CatalogoSigecFacadeLocal catalogoSigecFacadeLocal;
	@Resource
	private UserTransaction userTransaction;
	@Override
	public void guardarPresupuesto(CursoPeriodo cursoPeriodo, PresupuestoCurso presupuestoCurso,
								   Collection<DetallePresupuestoCurso> lstDetPreCur) throws Exception {
		synchronized (this) {
			userTransaction.begin();
			BigDecimal editarPresupuesto = new BigDecimal(0);
			try {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
				
				Presupuesto  presupuesto = presupuestoFacadeLocal.findByCodAnio(simpleDateFormat.format(calendar.getTime()));
				if(presupuesto !=null){
					presupuestoCurso.setCursoPeriodo(cursoPeriodo);
					presupuestoCurso.setPresupuesto(presupuesto);
					presupuestoCurso.setIdCursoPeriodo(cursoPeriodo.getIdCursoPeriodo());
					presupuestoCursoFacadeLocal.create(presupuestoCurso);
					
					long i=1;
					for (DetallePresupuestoCurso objDetPresCurso:lstDetPreCur){
						
						objDetPresCurso.getDetallePresupuestoCursoPK().setIdCursoPeriodo(BigInteger.valueOf(cursoPeriodo.getIdCursoPeriodo().longValue()));
						objDetPresCurso.setPresupuestoCurso(presupuestoCurso);
						objDetPresCurso.getDetallePresupuestoCursoPK().setIdDetalle(BigInteger.valueOf(i));
						
						if(objDetPresCurso.getDescripcionCatalogo()!=null){
							objDetPresCurso.setDetalle(objDetPresCurso.getDescripcionCatalogo());
						}
						
						editarPresupuesto = editarPresupuesto.add(objDetPresCurso.getCostoTotalUSD());
						detallePresupuestoCursoFacadeLocal.create(objDetPresCurso);
						i++;
					}
					
					int val = presupuesto.getRecursoActual().compareTo(presupuesto.getRecursoActual().subtract(BigDecimal.valueOf(presupuestoCurso.getDineroAsignado())));
					if(val >= 0){
						presupuesto.setRecursoActual(presupuesto.getRecursoActual().subtract(BigDecimal.valueOf(presupuestoCurso.getDineroAsignado())));
					}else{
						throw new Exception("El la cantidad asignada al curso sobrepasa el presupuesto restante");
					}
					
					presupuestoFacadeLocal.edit(presupuesto);
					userTransaction.commit();
				}else{
					throw new Exception("No se ha asignado un presupuesto para el anio especificado");
				}
			} catch (Exception e) {
				userTransaction.rollback();
				throw new Exception(e);
			}
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
			presupuestoCursoFacadeLocal.edit(presupuestoCurso);
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
