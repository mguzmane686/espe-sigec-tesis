/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author roberto
 */

public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    
    public void create(T entity) throws Exception{
        try {
        	getEntityManager().persist(entity);
		} catch (Exception e) {
			throw new Exception(e);
		}
    }

    public void edit(T entity) throws Exception{
        getEntityManager().merge(entity);
    }

    public void remove(T entity) throws Exception{
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
	public List<T> findAll() {
    	Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(entityClass);
    	return crit.list();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
