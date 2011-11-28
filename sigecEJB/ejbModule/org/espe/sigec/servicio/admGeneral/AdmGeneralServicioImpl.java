package org.espe.sigec.servicio.admGeneral;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.sessionBeans.AulaFacadeLocal;

public class AdmGeneralServicioImpl implements AdmGeneralServicio{
	@EJB
	private AulaFacadeLocal aulaFacadeLocal;

	@Override
	public void createAula(Aula aula) throws Exception {
		aulaFacadeLocal.create(aula);
	}
	
	
}
