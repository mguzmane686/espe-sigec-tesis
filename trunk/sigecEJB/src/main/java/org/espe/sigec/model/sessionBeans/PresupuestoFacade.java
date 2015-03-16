/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.Presupuesto;
import org.espe.sigec.model.entities.PresupuestoDetalle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class PresupuestoFacade extends AbstractFacade<Presupuesto> implements PresupuestoFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PresupuestoFacade() {
        super(Presupuesto.class);
    }

	@Override
	public Presupuesto findByCodAnio(String codAnio) {
		Criteria crit = ((Session)getEntityManager().getDelegate()).createCriteria(Presupuesto.class);
    	crit.add(Restrictions.eq("codigoAnio", codAnio));
    	Presupuesto  presupuesto = (Presupuesto) crit.uniqueResult();
		return presupuesto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Presupuesto> findPresupuesto(String codigoAnio) {
		Criteria crit = ((Session)getEntityManager().getDelegate()).createCriteria(Presupuesto.class);
    	crit.add(Restrictions.eq("codigoAnio", codigoAnio));
		return crit.list();
	}

	@Override
	public List<PresupuestoDetalle> findPresupuestoDetalles(int presupuestoId) {
		Criteria crit = ((Session)getEntityManager().getDelegate()).createCriteria(PresupuestoDetalle.class);
		crit.add(Restrictions.eq("presupuestoDetallePK.preId", presupuestoId));
		Collection<PresupuestoDetalle> lst = crit.list();
		return (List<PresupuestoDetalle>) lst;
	}
    
}