/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.model.entities.Profesor;
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
    	crit.createAlias("persona", "personaA");
    	crit.addOrder(Order.asc("personaA.primerApellido"));
    	crit.setFetchMode("personaA", FetchMode.JOIN);
    	crit.setFetchMode("personaA.educacionFormacion", FetchMode.JOIN);
    	crit.setFetchMode("especialidad", FetchMode.JOIN);
		return crit.list();
	}

	@Override
	public Collection<Profesor> findProfesoresSeleccionadosPorEspecialidad() {
		return null;
	}

	@Override
	public Collection<InvitacionDocente> findProfesoresSeleccionados(BigDecimal idCursoPeriodo) {
		
		Criteria crit = ((Session)getEntityManager().getDelegate()).createCriteria(InvitacionDocente.class);
		crit.createAlias("cursoPeriodo", "cursoPeriodoA");
		crit.setFetchMode("cursoPeriodoA", FetchMode.JOIN);
		crit.add(Restrictions.eq("cursoPeriodoA.idCursoPeriodo", idCursoPeriodo));
		
		crit.createAlias("profesor", "profesorA");
		crit.setFetchMode("profesorA", FetchMode.JOIN);
		
		crit.createAlias("profesorA.persona", "personaA");
    	crit.setFetchMode("personaA", FetchMode.JOIN);
    	@SuppressWarnings("unchecked")
		Collection<InvitacionDocente> lst = crit.list();
    			 
		return lst;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Profesor> cargarProfesores() {
		Criteria crit = ((Session)getEntityManager().getDelegate()).createCriteria(Profesor.class);
		crit.setFetchMode("persona", FetchMode.JOIN);
    	    	
		return crit.list();
	}
    
}
