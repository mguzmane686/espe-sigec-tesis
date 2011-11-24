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
import org.espe.sigec.model.entities.Profesor;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;

/**
 *
 * @author roberto
 */
@Stateless
public class ProfesorFacade extends AbstractFacade<Profesor> implements ProfesorFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorFacade() {
        super(Profesor.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Profesor> findProfesores() {
		Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(Profesor.class);
    	crit.setFetchMode("persona", FetchMode.JOIN);
		return crit.list();
	}
    
}
