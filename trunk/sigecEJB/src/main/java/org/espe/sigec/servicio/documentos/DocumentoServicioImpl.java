package org.espe.sigec.servicio.documentos;

import java.math.BigDecimal;
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
import org.espe.sigec.utils.SigecConstantes;

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
			if(invitacionDocenteFacadeLocal.validarUnicidadInivtacionDocente(invitacionDocente.getInvitacionDocentePK().getIdCursoPeriodo(), invitacionDocente.getInvitacionDocentePK().getPrfIdProfesor())){
				int secuencial = invitacionDocenteFacadeLocal.count();
				invitacionDocente.getInvitacionDocentePK().setDocNumInvit("SGCINV"+secuencial);
				invitacionDocente.setEstado(SigecConstantes.INVITACION_EMITIDA);
				invitacionDocenteFacadeLocal.create(invitacionDocente);
			}else{
				throw new Exception("Existe una inivitacion ya emitida");
			}
			userTransaction.commit();
			
		} catch (Exception e) {
			invitacionDocente.getInvitacionDocentePK().setDocNumInvit(null);
			e.printStackTrace();
			userTransaction.rollback();
			throw new Exception("Existe una inivitacion ya emitida");
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
	public void crearContratoDocente(ContratoProfesor contratoProfesor) throws Exception {
		contratoProfesorFacadeLocal.create(contratoProfesor);
	}
	@Override
	public Collection<InvitacionDocente> findInvitacionesByEstado(
			String estadoInvitacion) throws Exception {
		return invitacionDocenteFacadeLocal.findInvitacionesByEstado(estadoInvitacion);
	}
	@Override
	public ContratoProfesor obtenerContratoDocente(BigDecimal idCursoPeriodo, int idProfesor) throws Exception {
		return contratoProfesorFacadeLocal.obtenerContratoDocente(idCursoPeriodo, idProfesor);
	}
}
