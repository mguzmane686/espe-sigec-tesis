/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.PensumAcademico;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class PensumAcademicoFacade extends AbstractFacade<PensumAcademico> implements PensumAcademicoFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PensumAcademicoFacade() {
        super(PensumAcademico.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public Collection<PensumAcademico> findTemasCurso(Integer idCurso) {
		Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(PensumAcademico.class);
    	crit.add(Restrictions.eq("curso.idCurso", idCurso));
    	return crit.list();
	}
    
}
