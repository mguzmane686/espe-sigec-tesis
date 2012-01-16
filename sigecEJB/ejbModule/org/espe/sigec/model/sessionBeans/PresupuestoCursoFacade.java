/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.PresupuestoCurso;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class PresupuestoCursoFacade extends AbstractFacade<PresupuestoCurso> implements PresupuestoCursoFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PresupuestoCursoFacade() {
        super(PresupuestoCurso.class);
    }
    
	@Override
	public PresupuestoCurso findPresupuestoCurso(BigDecimal idCursoPeriodo) {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(PresupuestoCurso.class);
		criteria.add(Restrictions.eq("idCursoPeriodo", idCursoPeriodo));
		criteria.setFetchMode("detallePresupuestoCursoCollection", FetchMode.JOIN);
		return (PresupuestoCurso) criteria.uniqueResult();
	}
	
}
