package org.espe.sigec.servicio.planificacion;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.EspecialidadFacadeLocal;
import org.espe.sigec.model.sessionBeans.PensumAcademicoFacadeLocal;

/**
 * @author roberto
 *
 */
public class PlanificacionServicioImpl implements PlanificacionServicio{
	@EJB
	private PensumAcademicoFacadeLocal pensumAcademicoFacadeLocal;
	@EJB
	private CursoFacadeLocal cursoFacadeLocal;
	@EJB
	private EspecialidadFacadeLocal especialidadFacadeLocal;
	@Override
	public void crearNuevoCurso(Curso curso, Collection<PensumAcademico> lstPensumAcademicos) throws Exception{
		cursoFacadeLocal.create(curso);
		for(PensumAcademico pensumAcademicoTMP: lstPensumAcademicos){
			pensumAcademicoTMP.setCurso(curso);
			pensumAcademicoFacadeLocal.create(pensumAcademicoTMP);
		}
	}
	@Override
	public Collection<Especialidad> findEspecialidades(){
		return especialidadFacadeLocal.findAll();
	}
	@Override
	public void editarCurso(Curso curso,
			Collection<PensumAcademico> lstPensumAcademicos) throws Exception {
		cursoFacadeLocal.edit(curso);
		
		for(PensumAcademico pensumAcademicoTMP: lstPensumAcademicos){
			if (pensumAcademicoTMP.isExistInBase()) {
				pensumAcademicoFacadeLocal.edit(pensumAcademicoTMP);
			}else{
				pensumAcademicoTMP.setCurso(curso);
				pensumAcademicoFacadeLocal.create(pensumAcademicoTMP);
			}
		}
		
	}
}
