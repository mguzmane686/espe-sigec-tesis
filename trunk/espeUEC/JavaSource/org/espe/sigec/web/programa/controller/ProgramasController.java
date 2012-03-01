package org.espe.sigec.web.programa.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@SuppressWarnings("serial")
@ManagedBean(name="programasController")
@ViewScoped
public class ProgramasController implements Serializable{
	String hola;

	public ProgramasController() {
		setHola("zczxczxc");
	}

	public String getHola() {
		return hola;
	}

	public void setHola(String hola) {
		this.hola = hola;
	}
	
	
}
