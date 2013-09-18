package org.espe.sigec.model.sessionBeans;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections.CollectionUtils;
import org.espe.sigec.model.entities.ContratoProfesor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

@Stateless
public class ContratoProfesorFacade extends AbstractFacade<ContratoProfesor> implements ContratoProfesorFacadeLocal{
	@PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ContratoProfesorFacade() {
        super(ContratoProfesor.class);
    }

	@Override
	public ContratoProfesor obtenerContratoDocente(BigDecimal idCursoPeriodo, int idProfesor) throws Exception {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(ContratoProfesor.class);
		criteria.add(Restrictions.eq("idCursoPeriodo", idCursoPeriodo));
		criteria.add(Restrictions.eq("idProfesor", idProfesor));
		criteria.addOrder(Order.desc("sgctDocContratoProfPK.docNumInvit"));
		@SuppressWarnings("unchecked")
		Collection<ContratoProfesor> lstContratoProfesors = criteria.list();
		if(CollectionUtils.isNotEmpty(lstContratoProfesors)){
			return lstContratoProfesors.iterator().next();
		}
		return null;
		
	}
}
