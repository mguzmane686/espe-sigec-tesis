package org.espe.sigec.servicio.portal;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.model.sessionBeans.ProgramaCursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.ProgramaFacadeLocal;

public class PortalServicioImpl implements PortalServicio{
	@EJB
	private ProgramaCursoFacadeLocal programaCursoFacadeLocal;
	
	@EJB
	private ProgramaFacadeLocal programaFacadeLocal;
	
	@Override
	public Collection<ProgramaCurso> buscarPrograma() {
		return programaCursoFacadeLocal.cargarProgramaPortal();
	}

	@Override
	public Collection<Programa> buscarProgramaActivo() {
		return programaFacadeLocal.cargarProgramaActivoPortal();
	}

}
