package org.espe.sigec.web.home;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@SuppressWarnings("serial")
@ManagedBean(name="homeController")
@ViewScoped
public class HomeController implements Serializable{
	private String mensajeLeft;
	private String mensajeLeftHeader;
	
	
	private String leftTopFooter;
	private String centerTopFooter;
	private String rightTopFooter;
	
	private String leftFooter;
	private String centerFooter;
	private String rightFooter;
	
	public HomeController() {
		setMensajeLeft("Cursos de la uec");
		setMensajeLeftHeader("algun mensaje");
		
		setLeftTopFooter("cabecera");
		setLeftFooter("detalle");
		
		setCenterTopFooter("cabecera");
		setCenterFooter("detalle");
		
		setRightTopFooter("cabecera");
		setRightFooter("detalle");
		
		
		
		
	}

	public String getMensajeLeft() {
		return mensajeLeft;
	}

	public void setMensajeLeft(String mensajeLeft) {
		this.mensajeLeft = mensajeLeft;
	}

	public String getMensajeLeftHeader() {
		return mensajeLeftHeader;
	}

	public void setMensajeLeftHeader(String mensajeLeftHeader) {
		this.mensajeLeftHeader = mensajeLeftHeader;
	}

	public String getLeftFooter() {
		return leftFooter;
	}

	public void setLeftFooter(String leftFooter) {
		this.leftFooter = leftFooter;
	}

	public String getCenterFooter() {
		return centerFooter;
	}

	public void setCenterFooter(String centerFooter) {
		this.centerFooter = centerFooter;
	}

	public String getRightFooter() {
		return rightFooter;
	}

	public void setRightFooter(String rightFooter) {
		this.rightFooter = rightFooter;
	}

	public String getLeftTopFooter() {
		return leftTopFooter;
	}

	public void setLeftTopFooter(String leftTopFooter) {
		this.leftTopFooter = leftTopFooter;
	}

	public String getCenterTopFooter() {
		return centerTopFooter;
	}

	public void setCenterTopFooter(String centerTopFooter) {
		this.centerTopFooter = centerTopFooter;
	}

	public String getRightTopFooter() {
		return rightTopFooter;
	}

	public void setRightTopFooter(String rightTopFooter) {
		this.rightTopFooter = rightTopFooter;
	}
	
}
