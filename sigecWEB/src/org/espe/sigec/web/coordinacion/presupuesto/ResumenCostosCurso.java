package org.espe.sigec.web.coordinacion.presupuesto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
public class ResumenCostosCurso implements Serializable{
	
	private BigDecimal suministrosMatOfi;
	private BigDecimal costoTotal;
	private BigDecimal precioParicipante;
	private BigDecimal utilidadEspe;
	private BigDecimal precioVariable;
	private BigDecimal ingresoTotal;
	private BigDecimal ingresoReferencial;
	private BigDecimal valorHoraClase;
	private BigDecimal utilidad;
	private BigDecimal utilidadReferencial;
	private BigDecimal margenUtilidad;
	private BigDecimal margenUtilidadReferencial;
	
	public ResumenCostosCurso() {
	}

	public BigDecimal getSuministrosMatOfi() {
		return suministrosMatOfi;
	}

	public void setSuministrosMatOfi(BigDecimal suministrosMatOfi) {
		this.suministrosMatOfi = suministrosMatOfi;
	}

	public BigDecimal getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}

	public BigDecimal getPrecioParicipante() {
		return precioParicipante;
	}

	public void setPrecioParicipante(BigDecimal precioParicipante) {
		this.precioParicipante = precioParicipante;
	}

	public BigDecimal getUtilidadEspe() {
		return utilidadEspe;
	}

	public void setUtilidadEspe(BigDecimal utilidadEspe) {
		this.utilidadEspe = utilidadEspe;
	}

	public BigDecimal getPrecioVariable() {
		return precioVariable;
	}

	public void setPrecioVariable(BigDecimal precioVariable) {
		this.precioVariable = precioVariable;
	}

	public BigDecimal getIngresoTotal() {
		return ingresoTotal;
	}

	public void setIngresoTotal(BigDecimal ingresoTotal) {
		this.ingresoTotal = ingresoTotal;
	}

	public BigDecimal getIngresoReferencial() {
		return ingresoReferencial;
	}

	public void setIngresoReferencial(BigDecimal ingresoReferencial) {
		this.ingresoReferencial = ingresoReferencial;
	}

	public BigDecimal getValorHoraClase() {
		return valorHoraClase;
	}

	public void setValorHoraClase(BigDecimal valorHoraClase) {
		this.valorHoraClase = valorHoraClase;
	}

	public BigDecimal getUtilidad() {
		return utilidad;
	}

	public void setUtilidad(BigDecimal utilidad) {
		this.utilidad = utilidad;
	}

	public BigDecimal getUtilidadReferencial() {
		return utilidadReferencial;
	}

	public void setUtilidadReferencial(BigDecimal utilidadReferencial) {
		this.utilidadReferencial = utilidadReferencial;
	}

	public BigDecimal getMargenUtilidad() {
		return margenUtilidad;
	}

	public void setMargenUtilidad(BigDecimal margenUtilidad) {
		this.margenUtilidad = margenUtilidad;
	}

	public BigDecimal getMargenUtilidadReferencial() {
		return margenUtilidadReferencial;
	}

	public void setMargenUtilidadReferencial(BigDecimal margenUtilidadReferencial) {
		this.margenUtilidadReferencial = margenUtilidadReferencial;
	}
}
