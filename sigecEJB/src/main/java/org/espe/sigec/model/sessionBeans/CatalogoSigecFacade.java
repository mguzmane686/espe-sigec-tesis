/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.espe.sigec.model.entities.CatalogoSigec;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class CatalogoSigecFacade extends AbstractFacade<CatalogoSigec> implements CatalogoSigecFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CatalogoSigecFacade() {
        super(CatalogoSigec.class);
    }

	@Override
	public Collection<CatalogoSigec> findCatalogo(String parentId) {
		Criteria crit =((Session) getEntityManager().getDelegate()).createCriteria(CatalogoSigec.class);
		crit.add(Restrictions.eq("catalogoSigec", parentId));
		crit.addOrder(Order.asc("ordenLista"));
//		crit.createAlias("catalogoSigec", "catalogoSigec_chil");
//		crit.add(Restrictions.eq("catalogoSigec_chil", parentId));
		@SuppressWarnings("unchecked")
		Collection<CatalogoSigec> lst = crit.list();
		return lst;
	}
    
}
