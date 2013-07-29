package org.espe.sigec.model.views;

import java.util.Date;

/**
 * @author Roberto
 *
 */
public class Memo {
	private Date fechaInivitacion;
	private String numeroInivitacion;
	private String nombreProveedor;
	private String nombreElaborador;
	private String nombreDirectora;
	private String cuerpoMemo;
	private String resource;
	
	public Memo() {
		super();
	}
	
	
	/**
	 * Constructor para la generacion de la invitacion
	 * @param fechaInivitacion
	 * @param numeroInivitacion
	 * @param nombreProveedor
	 * @param nombreElaborador
	 * @param nombreDirectora
	 * @param cuerpoMemo
	 */
	public Memo(Date fechaInivitacion, String numeroInivitacion,
			String nombreProveedor, String nombreElaborador,
			String nombreDirectora, String cuerpoMemo) {
		super();
		this.fechaInivitacion = fechaInivitacion;
		this.numeroInivitacion = numeroInivitacion;
		this.nombreProveedor = nombreProveedor;
		this.nombreElaborador = nombreElaborador;
		this.nombreDirectora = nombreDirectora;
		this.cuerpoMemo = cuerpoMemo;
	}


	public Date getFechaInivitacion() {
		return fechaInivitacion;
	}
	public void setFechaInivitacion(Date fechaInivitacion) {
		this.fechaInivitacion = fechaInivitacion;
	}
	public String getNumeroInivitacion() {
		return numeroInivitacion;
	}
	public void setNumeroInivitacion(String numeroInivitacion) {
		this.numeroInivitacion = numeroInivitacion;
	}
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	public String getNombreElaborador() {
		return nombreElaborador;
	}
	public void setNombreElaborador(String nombreElaborador) {
		this.nombreElaborador = nombreElaborador;
	}
	public String getNombreDirectora() {
		return nombreDirectora;
	}
	public void setNombreDirectora(String nombreDirectora) {
		this.nombreDirectora = nombreDirectora;
	}
	public String getCuerpoMemo() {
		return cuerpoMemo;
	}
	public void setCuerpoMemo(String cuerpoMemo) {
		this.cuerpoMemo = cuerpoMemo;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}	
}