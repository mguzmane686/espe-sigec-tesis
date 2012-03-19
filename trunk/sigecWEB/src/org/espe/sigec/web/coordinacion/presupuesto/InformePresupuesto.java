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
	private int numeroParticipantes;
	public InformePresupuesto() {
		setPuntoEquilibrio(new PuntoEquilibrio());
		setResumenCostosCurso(new ResumenCostosCurso());
	}
	
	public InformePresupuesto(BigDecimal totalListaDetalle, int numeroParticipantes) {
		setPuntoEquilibrio(new PuntoEquilibrio());
		setResumenCostosCurso(new ResumenCostosCurso());
		setTotalListaDetalle(totalListaDetalle);
		setNumeroParticipantes(numeroParticipantes);
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
		getResumenCostosCurso().setCostoTotal(getResumenCostosCurso().getSuministrosMatOfi().add(totalLista));
		try {
			getResumenCostosCurso().setPrecioParicipante(getResumenCostosCurso().getCostoTotal().divide(new BigDecimal(getNumeroParticipantes()), BigDecimal.ROUND_HALF_UP, BigDecimal.ROUND_UP));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getResumenCostosCurso().setUtilidadEspe(getResumenCostosCurso().getPrecioParicipante().multiply(new BigDecimal(0.3)));
		getResumenCostosCurso().setPrecioParicipanteFinal(getResumenCostosCurso().getPrecioParicipante().add(getResumenCostosCurso().getUtilidadEspe()));
		
		getResumenCostosCurso().setIngresoTotal(getResumenCostosCurso().getPrecioParicipanteFinal().multiply(new BigDecimal(getNumeroParticipantes())));
//		getResumenCostosCurso().setValorHoraClase(valorHoraClase)
		getResumenCostosCurso().setUtilidad(getResumenCostosCurso().getIngresoTotal().subtract(getResumenCostosCurso().getCostoTotal()));
		try {
			getResumenCostosCurso().setMargenUtilidad(getResumenCostosCurso().getUtilidad().divide(getResumenCostosCurso().getCostoTotal(), BigDecimal.ROUND_UNNECESSARY, BigDecimal.ROUND_FLOOR));
			getResumenCostosCurso().setMargenUtilidad(getResumenCostosCurso().getMargenUtilidad().multiply(new BigDecimal(100)));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
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

	public int getNumeroParticipantes() {
		return numeroParticipantes;
	}

	public void setNumeroParticipantes(int numeroParticipantes) {
		this.numeroParticipantes = numeroParticipantes;
	}
	
}
