package org.espe.sigec.servicio.documentos;

import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.model.entities.Plantilla;

@QDocumento
public interface DocumentoServicio {
	void crearInivtacionDocente(InvitacionDocente invitacionDocente) throws Exception;
	Plantilla obtenerPlantillaDocumento(Integer idPlantilla);
}
