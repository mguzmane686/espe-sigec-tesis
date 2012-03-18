package org.espe.sigec.web.coordinacion.presupuesto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
public class PuntoEquilibrio implements Serializable{

	public PuntoEquilibrio() {
	}
	
	private BigDecimal costoFijo;
	private BigDecimal costoVariable;
	private BigDecimal costoVariableUnitario;
	private BigDecimal precio;
	private BigDecimal participante;
	
	private BigDecimal verifIngresos;
	private BigDecimal verifCostoVariable;
	private BigDecimal verifCostoFijo;
	private BigDecimal verifUtilidad;
	
	public BigDecimal getCostoFijo() {
		return costoFijo;
	}
	public void setCostoFijo(BigDecimal costoFijo) {
		this.costoFijo = costoFijo;
	}
	public BigDecimal getCostoVariable() {
		return costoVariable;
	}
	public void setCostoVariable(BigDecimal costoVariable) {
		this.costoVariable = costoVariable;
	}
	public BigDecimal getCostoVariableUnitario() {
		return costoVariableUnitario;
	}
	public void setCostoVariableUnitario(BigDecimal costoVariableUnitario) {
		this.costoVariableUnitario = costoVariableUnitario;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public BigDecimal getParticipante() {
		return participante;
	}
	public void setParticipante(BigDecimal participante) {
		this.participante = participante;
	}
	public BigDecimal getVerifIngresos() {
		return verifIngresos;
	}
	public void setVerifIngresos(BigDecimal verifIngresos) {
		this.verifIngresos = verifIngresos;
	}
	public BigDecimal getVerifCostoVariable() {
		return verifCostoVariable;
	}
	public void setVerifCostoVariable(BigDecimal verifCostoVariable) {
		this.verifCostoVariable = verifCostoVariable;
	}
	public BigDecimal getVerifCostoFijo() {
		return verifCostoFijo;
	}
	public void setVerifCostoFijo(BigDecimal verifCostoFijo) {
		this.verifCostoFijo = verifCostoFijo;
	}
	public BigDecimal getVerifUtilidad() {
		return verifUtilidad;
	}
	public void setVerifUtilidad(BigDecimal verifUtilidad) {
		this.verifUtilidad = verifUtilidad;
	}	
}