package org.espe.sigec.servicio.documentos;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.model.sessionBeans.InvitacionDocenteFacade;
import org.espe.sigec.model.sessionBeans.SgctSgSecuenciaFacade;

public class DocumentoServicioImpl implements DocumentoServicio{
	@EJB
	private InvitacionDocenteFacade invitacionDocenteFacade;
	@EJB
	private SgctSgSecuenciaFacade secuenciaFacade;
	@Resource
	private UserTransaction userTransaction;
	@Override
	public void crearInivtacionDocente(InvitacionDocente invitacionDocente) throws Exception{
		try {
			userTransaction.begin();
			invitacionDocenteFacade.create(invitacionDocente);
		} catch (Exception e) {
			userTransaction.rollback();
		}
	}
}
