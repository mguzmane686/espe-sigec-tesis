package org.espe.sigec.web.coordinacion.presupuesto;

import java.math.BigDecimal;
import java.util.Collection;

import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.utils.SigecConstantes;

public class PresupuestoUtil {
	private static final PresupuestoUtil PRESUPUESTO_UTIL = new PresupuestoUtil();

	public static PresupuestoUtil getPresupuestoUtil() {
		return PRESUPUESTO_UTIL;
	}
	
	
	public BigDecimal getValorManuales(Collection<DetallePresupuestoCurso> lstDetalles){
		for(DetallePresupuestoCurso detallePresupuestoCurso: lstDetalles){
			if(SigecConstantes.CATALOGO_DETALLE_MANUALES.equals(detallePresupuestoCurso.getDetallePresupuestoCursoPK().getCodElemento())){
				
				return detallePresupuestoCurso.getCostoTotalUSD();
			}
		}
		return BigDecimal.ZERO;
	}
	
	public BigDecimal getValorRefrigerios(Collection<DetallePresupuestoCurso> lstDetalles){
		for(DetallePresupuestoCurso detallePresupuestoCurso: lstDetalles){
			if(SigecConstantes.CATALOGO_DETALLE_REFRIGERIOS.equals(detallePresupuestoCurso.getDetallePresupuestoCursoPK().getCodElemento())){
				return detallePresupuestoCurso.getCostoTotalUSD();
			}
		}
		return BigDecimal.ZERO;
	}
}
