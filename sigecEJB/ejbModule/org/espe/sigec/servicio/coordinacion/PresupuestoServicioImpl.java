package org.espe.sigec.servicio.coordinacion;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.sessionBeans.ProfesorFacadeLocal;

public class PresupuestoServicioImpl implements PresupuestoServicio{
	@EJB
	ProfesorFacadeLocal facadeLocal;
	@Override
	public void guardarPresupuesto() throws Exception{
		Profesor profesor = new Profesor();
		profesor.setTitulo("asdasd");
		System.out.println("aki lo que tenga ke haver el metodo");
		try {
			facadeLocal.create(profesor);
		} catch (Exception e) {
			throw new Exception("Ha ocuttido algun erro y m se grabo");
		}
		
	}

}
