package org.espe.sigec.servicio.planificacion;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.model.entities.ProgramaCursoPK;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoPeriodoFacadeLocal;
import org.espe.sigec.model.sessionBeans.EspecialidadFacadeLocal;
import org.espe.sigec.model.sessionBeans.PensumAcademicoFacadeLocal;
import org.espe.sigec.model.sessionBeans.ProgramaCursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.ProgramaFacadeLocal;

/**
 * @author roberto
 *
 */
public class PlanificacionServicioImpl implements PlanificacionServicio{
	@EJB
	private PensumAcademicoFacadeLocal pensumAcademicoFacadeLocal;
	@EJB
	private CursoFacadeLocal cursoFacadeLocal;
//	@EJB
//	private ModuloCursoFacadeLocal moduloCursoFacadeLocal;
	@EJB
	private EspecialidadFacadeLocal especialidadFacadeLocal;
	@EJB
	private ProgramaFacadeLocal programaFacadeLocal;
	@EJB
	private ProgramaCursoFacadeLocal programaCursoFacadeLocal;
	@EJB
	private CursoPeriodoFacadeLocal cursoPeriodoFacadeLocal;
	@Resource
	private UserTransaction userTransaction;
	
	@Override
	public void crearNuevoCurso(Curso curso, Collection<PensumAcademico> lstPensumAcademicos) throws Exception{
		userTransaction.begin();
		try {
			cursoFacadeLocal.create(curso);
			for(PensumAcademico pensumAcademicoTMP: lstPensumAcademicos){
				pensumAcademicoTMP.setCurso(curso);
				pensumAcademicoFacadeLocal.create(pensumAcademicoTMP);
			}
			
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			throw new Exception("Ocurrio un error al guardar el curso");
		}
		
		
	}
	
//	@Override
//	public void crearNuevoCursoModulo(Curso curso, Collection<ContenidoCurso> lstModuloCursos) throws Exception{
//		userTransaction.begin();
//		try {
//			cursoFacadeLocal.create(curso);
//			for(ContenidoCurso moduloCursoTMP: lstModuloCursos){
//				moduloCursoTMP.setCurso(curso);
//				moduloCursoFacadeLocal.create(moduloCursoTMP);
//			}
//			userTransaction.commit();
//		} catch (Exception e) {
//			userTransaction.rollback();
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public Collection<Especialidad> findEspecialidades(){
		return especialidadFacadeLocal.findAll();
	}
	@Override
	public void editarCurso(Curso curso,
			Collection<PensumAcademico> lstPensumAcademicos) throws Exception {
		userTransaction.begin();
		try {
			cursoFacadeLocal.edit(curso);
			
			for(PensumAcademico pensumAcademicoTMP: lstPensumAcademicos){
				if (pensumAcademicoTMP.isExistInBase()) {
//					pensumAcademicoTMP.setCurso(curso);
					pensumAcademicoFacadeLocal.edit(pensumAcademicoTMP);
				}
//				else{
//					pensumAcademicoTMP.setCurso(curso);
//					pensumAcademicoFacadeLocal.create(pensumAcademicoTMP);
//				}
			}
		} catch (Exception e) {
			userTransaction.rollback();
		}
		
		userTransaction.commit();
	}
	
//	@Override
//	public void editarCursoModulo(Curso curso,
//			Collection<ContenidoCurso> lstModuloCursos) throws Exception {
//		userTransaction.begin();
//		try {
//			cursoFacadeLocal.edit(curso);
//			
//			for(ContenidoCurso moduloCursoTMP: lstModuloCursos){
//				if (moduloCursoTMP.isExistInBase()) {
//					moduloCursoFacadeLocal.edit(moduloCursoTMP);
//				}else{
//					moduloCursoTMP.setCurso(curso);
//					moduloCursoFacadeLocal.create(moduloCursoTMP);
//				}
//			}
//		} catch (Exception e) {
//			userTransaction.rollback();
//		}
//		
//		userTransaction.commit();
//	}
	
	@Override
	public void crearPrograma(Programa programa, Collection<ProgramaCurso> lstProgramaCurso) throws Exception {
		userTransaction.begin();
		try {
			programa.setEstado("1");
			programa.setFinalizacion(programa.getInicio());
			programaFacadeLocal.create(programa);
			for(ProgramaCurso programaCurso: lstProgramaCurso){
				programaCurso.getProgramaCursoPK().setIdPrograma(programa.getIdPrograma());
				programaCurso.setEstado("1");
				programaCursoFacadeLocal.create(programaCurso);
			}
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			throw new Exception(e);
		}
		
	}
	
	@Override
	public void editarPrograma(Programa programa, Collection<ProgramaCurso> lstProgramaCursoActivar, Collection<ProgramaCurso> lstProgramaCursoRemover) throws Exception {
		userTransaction.begin();
		try {
			programaFacadeLocal.edit(programa);
			ProgramaCurso programaCursoTMP=null;
			for(ProgramaCurso programaCurso: lstProgramaCursoActivar){
				ProgramaCursoPK programaCursoPK = programaCurso.getProgramaCursoPK();
				programaCursoTMP = programaCursoFacadeLocal.find(programaCursoPK);
				
				if(programaCursoTMP==null){
					programaCurso.setEstado("1");
					programaCursoFacadeLocal.create(programaCurso);
				}else{
					programaCursoTMP.setEstado("1");
					programaCursoFacadeLocal.edit(programaCursoTMP);
				}
			}
			
			for(ProgramaCurso programaCurso: lstProgramaCursoRemover){
				ProgramaCursoPK programaCursoPK = programaCurso.getProgramaCursoPK();
				programaCursoTMP = programaCursoFacadeLocal.find(programaCursoPK);
				programaCursoTMP.setEstado("0");
				programaCursoFacadeLocal.edit(programaCursoTMP);
			}
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			throw new Exception(e);
		}
		
	}
	
	@Override
	public Collection<Programa> buscarPrograma() {
		return programaFacadeLocal.findAll();
	}

	@Override
	public Collection<ProgramaCurso> buscarCursosAsignadosPrograma(Programa programa) {
		return programaCursoFacadeLocal.buscarCursosAsignadosPrograma(programa);
	}

	@Override
	public Collection<CursoPeriodo> cargarCursoPerdiodoPorAsignar(Integer[] listaIdCursosAsignados) {
		return cursoPeriodoFacadeLocal.cargarCursosPeriodoPorasignarPrograma(listaIdCursosAsignados);
	}
}
