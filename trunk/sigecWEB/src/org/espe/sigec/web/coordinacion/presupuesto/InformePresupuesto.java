package org.espe.sigec.web.coordinacion.presupuesto;

import java.math.BigDecimal;

/**
 * @author Roberto
 *
 */
public class InformePresupuesto {

	private PuntoEquilibrio puntoEquilibrio;
	private ResumenCostosCurso resumenCostosCurso;
	private BigDecimal totalListaDetalle;
	
	public InformePresupuesto() {
		setPuntoEquilibrio(new PuntoEquilibrio());
		setResumenCostosCurso(new ResumenCostosCurso());
	}
	
	public InformePresupuesto(BigDecimal totalListaDetalle) {
		setPuntoEquilibrio(new PuntoEquilibrio());
		setResumenCostosCurso(new ResumenCostosCurso());
		setTotalListaDetalle(totalListaDetalle);
		calculoInforme();
	}
	
	private void calculoInforme(){
		if(getTotalListaDetalle() !=null){
			calculoResumenCostosCurso(getTotalListaDetalle());
			calculoPuntoEquilibrio();
		}
	}
	
	private void calculoResumenCostosCurso(BigDecimal totalLista){
		getResumenCostosCurso().setSuministrosMatOfi(totalLista.multiply(new BigDecimal(.05)));
	}
	
	private void calculoPuntoEquilibrio(){
		
	}

	public PuntoEquilibrio getPuntoEquilibrio() {
		return puntoEquilibrio;
	}
	public void setPuntoEquilibrio(PuntoEquilibrio puntoEquilibrio) {
		this.puntoEquilibrio = puntoEquilibrio;
	}
	public ResumenCostosCurso getResumenCostosCurso() {
		return resumenCostosCurso;
	}
	public void setResumenCostosCurso(ResumenCostosCurso resumenCostosCurso) {
		this.resumenCostosCurso = resumenCostosCurso;
	}
	public BigDecimal getTotalListaDetalle() {
		return totalListaDetalle;
	}
	public void setTotalListaDetalle(BigDecimal totalListaDetalle) {
		this.totalListaDetalle = totalListaDetalle;
	}
	
}
