/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.CursoPeriodo;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;

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

	@SuppressWarnings("unchecked")
	@Override
	public Collection<CursoPeriodo> findCursoAbierto() {
		Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(CursoPeriodo.class);
    	crit.setFetchMode("curso", FetchMode.JOIN);
    	crit.setFetchMode("periodoAcademico", FetchMode.JOIN);
    	crit.setFetchMode("aula", FetchMode.JOIN);
    	return crit.list();
	}
    
}
