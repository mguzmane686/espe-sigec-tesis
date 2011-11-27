/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author roberto
 */

public abstract class AbstractFacade<T> {
    private Class<T> entityClass;
    
    @Resource 
    private SessionContext context;


    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    
    public void create(T entity) throws Exception{
        try {
        	getEntityManager().persist(entity);
		} catch (Exception e) {
//			getEntityManager().getTransaction().rollback();
//			context.setRollbackOnly();
			throw new Exception(e);
		}
    	
//        getEntityManager().flush();
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

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
