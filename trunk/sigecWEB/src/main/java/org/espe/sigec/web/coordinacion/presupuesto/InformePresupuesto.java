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
	private BigDecimal porcenUtilidadEspe;
	private BigDecimal porcenMatOfi;
	public InformePresupuesto() {
		setPuntoEquilibrio(new PuntoEquilibrio());
		setResumenCostosCurso(new ResumenCostosCurso());
	}
	
	public InformePresupuesto(BigDecimal totalListaDetalle, int numeroParticipantes, Integer porceUtiEspe, Integer porceMatOfi) {
		porcenUtilidadEspe = convertirPorcentage(porceUtiEspe);
		porcenMatOfi = convertirPorcentage(porceMatOfi);
		
		setPuntoEquilibrio(new PuntoEquilibrio());
		setResumenCostosCurso(new ResumenCostosCurso());
		setTotalListaDetalle(totalListaDetalle);
		setNumeroParticipantes(numeroParticipantes);
		calculoInforme();
		
	}
	
	private BigDecimal convertirPorcentage(Integer cantidad){
		BigDecimal valor = new BigDecimal(cantidad);
		try {
			valor = valor.multiply(new BigDecimal(.01));
		} catch (Exception e) {
			new BigDecimal(0);
		}
		
		return valor;
	}
	
	private void calculoInforme(){
		if(getTotalListaDetalle() !=null && !getTotalListaDetalle().equals(BigDecimal.ZERO)){
			calculoResumenCostosCurso(getTotalListaDetalle());
		}
	}
	
	private void calculoResumenCostosCurso(BigDecimal totalLista){
		getResumenCostosCurso().setSuministrosMatOfi(totalLista.multiply(porcenMatOfi));
		getResumenCostosCurso().setCostoTotal(getResumenCostosCurso().getSuministrosMatOfi().add(totalLista));
		try {
			getResumenCostosCurso().setPrecioParicipante(getResumenCostosCurso().getCostoTotal().divide(new BigDecimal(getNumeroParticipantes()), BigDecimal.ROUND_HALF_UP, BigDecimal.ROUND_UP));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getResumenCostosCurso().setUtilidadEspe(getResumenCostosCurso().getPrecioParicipante().multiply(porcenUtilidadEspe));
		getResumenCostosCurso().setPrecioParicipanteFinal(getResumenCostosCurso().getPrecioParicipante().add(getResumenCostosCurso().getUtilidadEspe()));
		
		getResumenCostosCurso().setIngresoTotal(getResumenCostosCurso().getPrecioParicipanteFinal().multiply(new BigDecimal(getNumeroParticipantes())));
		
		getResumenCostosCurso().setUtilidad(getResumenCostosCurso().getIngresoTotal().subtract(getResumenCostosCurso().getCostoTotal()));
		try {
			getResumenCostosCurso().setMargenUtilidad(getResumenCostosCurso().getUtilidad().divide(getResumenCostosCurso().getCostoTotal(), BigDecimal.ROUND_UNNECESSARY, BigDecimal.ROUND_FLOOR));
			getResumenCostosCurso().setMargenUtilidad(getResumenCostosCurso().getMargenUtilidad().multiply(new BigDecimal(100)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void calculoPuntoEquilibrio(BigDecimal manuales, BigDecimal refrigerios){
		setCostosFijos(manuales, refrigerios);
	}
	
	private void setCostosFijos(BigDecimal manuales, BigDecimal refrigerios){
		BigDecimal suma = manuales.add(refrigerios).add(getResumenCostosCurso().getSuministrosMatOfi());
		
		getPuntoEquilibrio().setCostoFijo(getResumenCostosCurso().getCostoTotal().subtract(suma));
		getPuntoEquilibrio().setCostoVariable(suma);
		getPuntoEquilibrio().setCostoVariableUnitario(suma.divide(new BigDecimal(getNumeroParticipantes()), BigDecimal.ROUND_HALF_UP, BigDecimal.ROUND_UP));
		getPuntoEquilibrio().setPrecio(getResumenCostosCurso().getPrecioParicipante());
		getPuntoEquilibrio().setParticipante(new BigDecimal(getNumeroParticipantes()));
		
		calucularPEUno();
		
		getPuntoEquilibrio().setVerifIngresos(getPuntoEquilibrio().getPrecio().multiply(getPuntoEquilibrio().getPuntoEquilibrioUno()));
		getPuntoEquilibrio().setVerifCostoVariable(getPuntoEquilibrio().getCostoVariableUnitario().multiply(getPuntoEquilibrio().getPuntoEquilibrioUno()));
		getPuntoEquilibrio().setVerifCostoFijo(getPuntoEquilibrio().getCostoFijo());
		getPuntoEquilibrio().setVerifUtilidad(getPuntoEquilibrio().getVerifIngresos().subtract(getPuntoEquilibrio().getVerifCostoVariable()).subtract(getPuntoEquilibrio().getVerifCostoFijo()));
		
	}
	
	private void calucularPEUno(){
		BigDecimal costo_participantes = getPuntoEquilibrio().getCostoVariable().divide(getPuntoEquilibrio().getParticipante(),BigDecimal.ROUND_HALF_UP, BigDecimal.ROUND_UP);
		BigDecimal divisionPE = getPuntoEquilibrio().getPrecio().subtract(costo_participantes);
		getPuntoEquilibrio().setPuntoEquilibrioUno(getPuntoEquilibrio().getCostoFijo().divide(divisionPE,BigDecimal.ROUND_HALF_UP, BigDecimal.ROUND_UP));
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
