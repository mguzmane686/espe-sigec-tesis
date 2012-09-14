package org.espe.sigec.servicio.documentos;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.model.entities.ContratoProfesor;
import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.model.entities.Plantilla;
import org.espe.sigec.model.sessionBeans.ContratoProfesorFacadeLocal;
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
	
	@EJB
	private ContratoProfesorFacadeLocal contratoProfesorFacadeLocal;
	
	@Resource
	private UserTransaction userTransaction;
	@Override
	public void crearInivtacionDocente(InvitacionDocente invitacionDocente) throws Exception{
		try {
			userTransaction.begin();
			int secuencial = invitacionDocenteFacadeLocal.count();
			invitacionDocente.getInvitacionDocentePK().setDocNumInvit("SGCINV"+secuencial);
			invitacionDocenteFacadeLocal.create(invitacionDocente);
			userTransaction.commit();
		} catch (Exception e) {
			invitacionDocente.getInvitacionDocentePK().setDocNumInvit(null);
			e.printStackTrace();
			userTransaction.rollback();
		}
	}
	@Override
	public Plantilla obtenerPlantillaDocumento(Integer idPlantilla) {
		return plantillaFacadeLocal.find(idPlantilla);
	}
	@Override
	public Collection<InvitacionDocente> verificarInivtacionDocente(Integer idProfesor) throws Exception {
		return invitacionDocenteFacadeLocal.verificarInivtacionDocente(idProfesor);
	}
	@Override
	public void actualizarInvitacion(InvitacionDocente invitacionDocente)
			throws Exception {
		invitacionDocenteFacadeLocal.edit(invitacionDocente);
	}
	@Override
	public Collection<InvitacionDocente> verificarInivtacionAceptada()
			throws Exception {
		return invitacionDocenteFacadeLocal.verificarInivtacionAceptada();
	}
	@Override
	public void crearContratoDocente(ContratoProfesor contratoProfesor)
			throws Exception {
		contratoProfesorFacadeLocal.create(contratoProfesor);
	}
}
