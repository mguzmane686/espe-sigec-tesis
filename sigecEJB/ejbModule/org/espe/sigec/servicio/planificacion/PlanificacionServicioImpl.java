package org.espe.sigec.servicio.planificacion;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.ModuloCurso;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.EspecialidadFacadeLocal;
import org.espe.sigec.model.sessionBeans.ModuloCursoFacadeLocal;
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
	private ModuloCursoFacadeLocal moduloCursoFacadeLocal;
	@EJB
	private EspecialidadFacadeLocal especialidadFacadeLocal;
	@Resource
	private UserTransaction userTransaction;
	@Override
	public void crearNuevoCurso(Curso curso, Collection<PensumAcademico> lstPensumAcademicos) throws Exception{
		userTransaction.begin();
		try {
			cursoFacadeLocal.create(curso);
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			e.printStackTrace();
		}
		
//		for(PensumAcademico pensumAcademicoTMP: lstPensumAcademicos){
//			pensumAcademicoTMP.setCurso(curso);
//			pensumAcademicoFacadeLocal.create(pensumAcademicoTMP);
//		}
	}
	
	@Override
	public void crearNuevoCursoModulo(Curso curso, Collection<ModuloCurso> lstModuloCursos) throws Exception{
		userTransaction.begin();
		try {
			cursoFacadeLocal.create(curso);
			for(ModuloCurso moduloCursoTMP: lstModuloCursos){
				moduloCursoTMP.setCurso(curso);
				moduloCursoFacadeLocal.create(moduloCursoTMP);
			}
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			e.printStackTrace();
		}
	}
	
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
					pensumAcademicoFacadeLocal.edit(pensumAcademicoTMP);
				}else{
//					pensumAcademicoTMP.setCurso(curso);
//					pensumAcademicoFacadeLocal.create(pensumAcademicoTMP);
				}
			}
		} catch (Exception e) {
			userTransaction.rollback();
		}
		
		userTransaction.commit();
	}
	
	@Override
	public void editarCursoModulo(Curso curso,
			Collection<ModuloCurso> lstModuloCursos) throws Exception {
		userTransaction.begin();
		try {
			cursoFacadeLocal.edit(curso);
			
			for(ModuloCurso moduloCursoTMP: lstModuloCursos){
				if (moduloCursoTMP.isExistInBase()) {
					moduloCursoFacadeLocal.edit(moduloCursoTMP);
				}else{
					moduloCursoTMP.setCurso(curso);
					moduloCursoFacadeLocal.create(moduloCursoTMP);
				}
			}
		} catch (Exception e) {
			userTransaction.rollback();
		}
		
		userTransaction.commit();
	}
}
