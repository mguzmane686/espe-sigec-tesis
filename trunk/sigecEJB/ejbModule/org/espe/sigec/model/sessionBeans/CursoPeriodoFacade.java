/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.CursoPeriodo;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class CursoPeriodoFacade extends AbstractFacade<CursoPeriodo> implements CursoPeriodoFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoPeriodoFacade() {
        super(CursoPeriodo.class);
    }

	/* (non-Javadoc)
	 * @see org.espe.sigec.model.sessionBeans.CursoPeriodoFacadeLocal#findCursoAbierto()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<CursoPeriodo> findCursoAbierto() {
		Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(CursoPeriodo.class);
    	crit.createAlias("curso", "cursoA");
    	crit.setFetchMode("cursoA", FetchMode.JOIN);
    	crit.addOrder(Order.asc("cursoA.nombreCurso"));
    	crit.setFetchMode("periodoAcademico", FetchMode.JOIN);
    	crit.setFetchMode("aula", FetchMode.JOIN);
    	crit.setFetchMode("historicoCursoEstadoCollection", FetchMode.JOIN);
    	return crit.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<CursoPeriodo> cargarCursoLanzado(){
    	Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(CursoPeriodo.class);
    	crit.createAlias("historicoCursoEstadoCollection", "estados");
    	crit.setFetchMode("curso", FetchMode.JOIN);
//    	crit.setFetchMode("periodoAcademico", FetchMode.JOIN);
//    	crit.setFetchMode("historicoCursoEstadoCollection", FetchMode.JOIN);
    	crit.add(Restrictions.eq("estados.estado", "1"));
    	crit.add(Restrictions.eq("estados.etapaLanzado", "1")).
//    	add(Restrictions.not(Restrictions.eq("estados.etapaEjecutado", "1"))). se mapea como and not estados1_.etapa_finalizado='1'
    	add(Restrictions.ne("estados.etapaEjecutado", "1")).
    	add(Restrictions.ne("estados.etapaFinalizado", "1"));
    	return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<CursoPeriodo> findCursoAbiertoByUser(Integer idPersona) {
		Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(CursoPeriodo.class);
    	crit.createAlias("curso", "cursoA");
    	crit.setFetchMode("cursoA", FetchMode.JOIN);
    	
    	crit.createAlias("cursoA.especialidad", "especialidadA");
    	crit.setFetchMode("especialidadA", FetchMode.JOIN);
    	
    	crit.createAlias("persona", "personaA");
    	crit.setFetchMode("personaA", FetchMode.JOIN);
    	crit.add(Restrictions.eq("personaA.idPersona", idPersona));
    	
    	crit.addOrder(Order.asc("cursoA.nombreCurso"));
    	crit.setFetchMode("periodoAcademico", FetchMode.JOIN);
    	crit.setFetchMode("aula", FetchMode.JOIN);
    	crit.setFetchMode("historicoCursoEstadoCollection", FetchMode.JOIN);
    	return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<CursoPeriodo> cargarCursosParametros(Date fechaInicio,Date fechaFin, String estado){
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(CursoPeriodo.class);
		criteria.setFetchMode("curso",FetchMode.JOIN);
		criteria.createAlias("periodoAcademico","periodo");
		criteria.setFetchMode("periodo",FetchMode.JOIN);
		criteria.createAlias("historicoCursoEstadoCollection","estado");
		criteria.setFetchMode("estado",FetchMode.JOIN);
		criteria.add(Restrictions.between("periodo.fechaInicio", fechaInicio, fechaFin));
		criteria.add(Restrictions.between("periodo.fechaFin", fechaInicio, fechaFin));
		if (estado.equals("A")) {
			criteria.add(Restrictions.eq("estado.etapaLanzado","1"));
		}
		if (estado.equals("E")) {
			criteria.add(Restrictions.eq("estado.etapaEjecutado","1"));
		}
		if (estado.equals("F")) {
			criteria.add(Restrictions.eq("estado.etapaFinalizado","1"));
		}
		return criteria.list();
	}
	
}
