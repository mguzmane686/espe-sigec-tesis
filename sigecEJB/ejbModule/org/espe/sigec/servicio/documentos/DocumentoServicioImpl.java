package org.espe.sigec.servicio.documentos;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.model.entities.Plantilla;
import org.espe.sigec.model.sessionBeans.InvitacionDocenteFacadeLocal;
import org.espe.sigec.model.sessionBeans.PlantillaFacadeLocal;
import org.espe.sigec.model.sessionBeans.SgctSgSecuenciaFacadeLocal;

public class DocumentoServicioImpl implements DocumentoServicio{
	@EJB
	private InvitacionDocenteFacadeLocal invitacionDocenteFacadeLocal;
	@EJB
	private SgctSgSecuenciaFacadeLocal secuenciaFacadeLocal;
	
	@EJB
	private PlantillaFacadeLocal plantillaFacadeLocal;
	
	@Resource
	private UserTransaction userTransaction;
	@Override
	public void crearInivtacionDocente(InvitacionDocente invitacionDocente) throws Exception{
		try {
			userTransaction.begin();
			invitacionDocenteFacadeLocal.create(invitacionDocente);
		} catch (Exception e) {
			userTransaction.rollback();
		}
	}
	@Override
	public Plantilla obtenerPlantillaDocumento(Integer idPlantilla) {
		return plantillaFacadeLocal.find(idPlantilla);
	}
}