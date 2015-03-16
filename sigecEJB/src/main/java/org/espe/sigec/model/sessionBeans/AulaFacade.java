/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.espe.sigec.model.entities.Aula;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class AulaFacade extends AbstractFacade<Aula> implements AulaFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AulaFacade() {
        super(Aula.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Aula> findCursoByEdificio(String idEdificio) {
		Criteria crit = ((Session)getEntityManager().getDelegate()).createCriteria(Aula.class);
    	crit.add(Restrictions.eq("edificio.idEdificio", idEdificio));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Aula> findAulas() {
		Criteria crit = ((Session)getEntityManager().getDelegate()).createCriteria(Aula.class);
		crit.setFetchMode("edificio", FetchMode.JOIN);
		crit.setFetchMode("edificio.lugarCurso", FetchMode.JOIN);
		
		return crit.list();
	}
    
}
