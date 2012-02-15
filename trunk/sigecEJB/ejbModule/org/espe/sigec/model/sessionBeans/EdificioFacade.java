/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.Edificio;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class EdificioFacade extends AbstractFacade<Edificio> implements EdificioFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public EdificioFacade() {
        super(Edificio.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Edificio> findEdificioByLugarCurso(String idLugar) {
		Criteria crit = ((Session)getEntityManager().getDelegate()).createCriteria(Edificio.class);
    	crit.add(Restrictions.eq("lugarCurso.idLugar", idLugar));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Edificio> findEdificiosReporte() {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Edificio.class);
		criteria.setFetchMode("lugarCurso", FetchMode.JOIN);
		return criteria.list();
	}
    
}
