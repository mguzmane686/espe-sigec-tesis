/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.Curso;
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
public class CursoFacade extends AbstractFacade<Curso> implements CursoFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoFacade() {
        super(Curso.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Curso> findCursoByEspecialidad(Integer idEspecialidad) {
		
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Curso.class);
		
		criteria.add(Restrictions.eq("especialidad.idEspecialidad", idEspecialidad));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Curso> findCursoByEstado() {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Curso.class);
		criteria.createAlias("especialidad", "especialidadA");
		criteria.addOrder(Order.asc("especialidadA.nombre")).addOrder(Order.asc("nombreCurso"));
		criteria.setFetchMode("especialidadA", FetchMode.JOIN);
		return criteria.list();
	}
    
}
