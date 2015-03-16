package org.espe.sigec.servicio.portal;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.Encuesta;
import org.espe.sigec.model.entities.EncuestaPK;
import org.espe.sigec.model.entities.HorarioCursoPeriodo;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.model.sessionBeans.CursoEstudianteFacadeLocal;
import org.espe.sigec.model.sessionBeans.EncuestaFacadeLocal;
import org.espe.sigec.model.sessionBeans.PensumAcademicoFacadeLocal;
import org.espe.sigec.model.sessionBeans.ProgramaCursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.ProgramaFacadeLocal;

public class PortalServicioImpl implements PortalServicio{
	@EJB
	private ProgramaCursoFacadeLocal programaCursoFacadeLocal;
	
	@EJB
	private ProgramaFacadeLocal programaFacadeLocal;
	
	@EJB
	private EncuestaFacadeLocal encuestaFacadeLocal;
	
	@EJB
	private CursoEstudianteFacadeLocal cursoEstudianteFacadeLocal;
	
	@EJB
	private PensumAcademicoFacadeLocal pensumAcademicoFacadeLocal;
	
	
	@Override
	public Collection<ProgramaCurso> buscarPrograma() {
		return programaCursoFacadeLocal.cargarProgramaPortal();
	}

	@Override
	public Collection<Programa> buscarProgramaActivo() {
		return programaFacadeLocal.cargarProgramaActivoPortal();
	}

	@Override
	public void guardarEncuesta(Encuesta encuesta) throws Exception{
		try {
			encuestaFacadeLocal.create(encuesta);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public Encuesta buscarEncuesta(EncuestaPK encuestaPK) {
		return encuestaFacadeLocal.find(encuestaPK);
	}

	@Override
	public Collection<CursoEstudiante> buscarCursosEstudiante(int idEstudiante) {
		// TODO Auto-generated method stub
		return cursoEstudianteFacadeLocal.buscarCursosEstudiante(idEstudiante);
	}

	@Override
	public Collection<PensumAcademico> buscarPensumCurso(Integer idCurso) throws Exception {
		return pensumAcademicoFacadeLocal.findTemasModulo(idCurso);
	}

	@Override
	public HorarioCursoPeriodo buscarHorarioCurso(BigDecimal idCursoPeriodo) throws Exception {
		// TODO Auto-generated method stub
		return pensumAcademicoFacadeLocal.buscarHorarioCurso(idCursoPeriodo);
	}

	@Override
	public Collection<CursoEstudiante> buscarHistorialEstudiante(String cedula) throws Exception {
		// TODO Auto-generated method stub
		
		return cursoEstudianteFacadeLocal.buscarHistorialEstudiante(cedula);
	}

	
}
