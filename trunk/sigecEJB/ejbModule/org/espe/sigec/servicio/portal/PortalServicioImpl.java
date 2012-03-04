package org.espe.sigec.servicio.portal;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.model.sessionBeans.ProgramaCursoFacadeLocal;

public class PortalServicioImpl implements PortalServicio{
	@EJB
	private ProgramaCursoFacadeLocal programaCursoFacadeLocal;
	@Override
	public Collection<ProgramaCurso> buscarPrograma() {
		return programaCursoFacadeLocal.findAll();
	}

}
