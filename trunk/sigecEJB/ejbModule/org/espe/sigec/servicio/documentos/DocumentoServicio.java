package org.espe.sigec.servicio.documentos;

import org.espe.sigec.model.entities.InvitacionDocente;

@QDocumento
public interface DocumentoServicio {
	void crearInivtacionDocente(InvitacionDocente invitacionDocente) throws Exception;
}
