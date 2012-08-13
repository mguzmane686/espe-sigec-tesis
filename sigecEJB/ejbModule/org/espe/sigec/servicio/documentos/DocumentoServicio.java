package org.espe.sigec.servicio.documentos;

import java.util.Collection;

import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.model.entities.Plantilla;

@QDocumento
public interface DocumentoServicio {
	void crearInivtacionDocente(InvitacionDocente invitacionDocente) throws Exception;
	Plantilla obtenerPlantillaDocumento(Integer idPlantilla);
	Collection<InvitacionDocente> verificarInivtacionDocente(Integer idProfesor) throws Exception;
	void actualizarInvitacion(InvitacionDocente invitacionDocente) throws Exception;
}
