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

import org.espe.sigec.model.entities.DetallePresupuestoCurso;
import org.espe.sigec.model.entities.PresupuestoDetalle;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class DetallePresupuestoCursoFacade extends AbstractFacade<DetallePresupuestoCurso> implements DetallePresupuestoCursoFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallePresupuestoCursoFacade() {
        super(DetallePresupuestoCurso.class);
    }

	@Override
	public Collection<DetallePresupuestoCurso> findDetallePresupuestoCurso(
			BigDecimal idCursoPeriodo) {
		
		return null;
	}

	@Override
	public Collection<PresupuestoDetalle> findDetallePresupuestoActual(int anioActual) {
		Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(PresupuestoDetalle.class);
    	
    	crit.createAlias("presupuesto", "presupuestoA");
    	crit.add(Restrictions.eq("presupuestoA.codigoAnio", String.valueOf(anioActual)));
    	crit.setFetchMode("presupuesto", FetchMode.JOIN);
    	Collection<PresupuestoDetalle> lst = crit.list();
    	
    	return lst;
	}
    
}
