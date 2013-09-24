/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections.CollectionUtils;
import org.espe.sigec.model.entities.CursoEstudiante;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class CursoEstudianteFacade extends AbstractFacade<CursoEstudiante> implements CursoEstudianteFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoEstudianteFacade() {
        super(CursoEstudiante.class);
    }

	@Override
	public int numeroEstudiantesInscritos(BigDecimal idCursoPeriodo) {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(CursoEstudiante.class);
		criteria.add(Restrictions.eq("cursoEstudiantePK.idCursoPeriodo", idCursoPeriodo));
		@SuppressWarnings("unchecked")
		Collection<CursoEstudiante> lstUsuarios = criteria.list();
		int numeroInscritos = 0;
		if(lstUsuarios!=null && CollectionUtils.isNotEmpty(lstUsuarios)){
			numeroInscritos = lstUsuarios.size();
		}
		
		return numeroInscritos;
	}

	@Override
	public void editCursosEstudiante(CursoEstudiante cursoEstudiante) throws Exception{
		getEntityManager().merge(cursoEstudiante);
	}
	
	@Override
	public Collection<CursoEstudiante> buscarCursosEstudiante(int idEstudiante) {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(CursoEstudiante.class, "cursoEstudianteA");
		criteria.add(Restrictions.eq("cursoEstudianteA.cursoEstudiantePK.idEstudiante", idEstudiante));
		criteria.createAlias("cursoEstudianteA.programaCurso", "programaCursoA");
		criteria.setFetchMode("programaCursoA", FetchMode.JOIN);
		
		criteria.createAlias("programaCursoA.cursoPeriodo", "cursoPeriodoA");
		criteria.setFetchMode("cursoPeriodoA", FetchMode.JOIN);
		
		criteria.createAlias("cursoPeriodoA.curso", "cursoA");
		criteria.setFetchMode("cursoA", FetchMode.JOIN);
		
		@SuppressWarnings("unchecked")
		Collection<CursoEstudiante> lstCursosEstudiante = criteria.list();
		return lstCursosEstudiante;
	}

	@Override
	public Collection<CursoEstudiante> estudiantesInscritosCurso(BigDecimal idCursoPeriodo) {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(CursoEstudiante.class, "cursoEstudianteA");
		criteria.add(Restrictions.eq("cursoEstudianteA.cursoEstudiantePK.idCursoPeriodo", idCursoPeriodo));
		criteria.createAlias("cursoEstudianteA.estudiante", "estudianteA");
		criteria.setFetchMode("estudianteA", FetchMode.JOIN);
		criteria.createAlias("estudianteA.persona", "personaA");
		criteria.setFetchMode("personaA", FetchMode.JOIN);
		Collection<CursoEstudiante> lstUsuarios = criteria.list();
		return lstUsuarios;
	}

	@Override
	public Collection<CursoEstudiante> estudiantesInscritosCurso(
			BigDecimal idCursoPeriodo, String estadoCupo) {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(CursoEstudiante.class, "cursoEstudianteA");
		criteria.add(Restrictions.eq("cursoEstudianteA.cursoEstudiantePK.idCursoPeriodo", idCursoPeriodo));
		criteria.add(Restrictions.eq("estadoCupo", estadoCupo));
		criteria.createAlias("cursoEstudianteA.estudiante", "estudianteA");
		criteria.setFetchMode("estudianteA", FetchMode.JOIN);
		criteria.createAlias("estudianteA.persona", "personaA");
		criteria.setFetchMode("personaA", FetchMode.JOIN);
		Collection<CursoEstudiante> lstUsuarios = criteria.list();
		return lstUsuarios;
	}

	@Override
	public Collection<CursoEstudiante> estudiantesInscritosCurso(
			BigDecimal idCursoPeriodo, String estadoCupo, String estadoPago) {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(CursoEstudiante.class, "cursoEstudianteA");
		criteria.add(Restrictions.eq("cursoEstudianteA.cursoEstudiantePK.idCursoPeriodo", new BigInteger(String.valueOf(idCursoPeriodo))));
		criteria.add(Restrictions.eq("estadoPago", estadoPago));
		criteria.add(Restrictions.eq("estadoCupo", estadoCupo));
		criteria.createAlias("cursoEstudianteA.estudiante", "estudianteA");
		criteria.setFetchMode("estudianteA", FetchMode.JOIN);
		criteria.createAlias("estudianteA.persona", "personaA");
		criteria.setFetchMode("personaA", FetchMode.JOIN);
		Collection<CursoEstudiante> lstUsuarios = criteria.list();
		return lstUsuarios;
	}
}