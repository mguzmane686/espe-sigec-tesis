package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.utils.SigecConstantes;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
/**
 * @author Roberto
 *
 */
@Stateless
public class InvitacionDocenteFacade extends AbstractFacade<InvitacionDocente> implements InvitacionDocenteFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public InvitacionDocenteFacade() {
        super(InvitacionDocente.class);
    }

    
	@Override
	public Collection<InvitacionDocente> verificarInivtacionDocente(Integer idProfesor) throws Exception {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(InvitacionDocente.class);
		criteria.createAlias("profesor", "profesorA");
		criteria.setFetchMode("profesorA", FetchMode.JOIN);
		
		criteria.createAlias("profesorA.persona", "perProfA");
		criteria.setFetchMode("perProfA", FetchMode.JOIN);
		
		criteria.createAlias("perProfA.usuario", "perUsrA");
		criteria.setFetchMode("perUsrA", FetchMode.JOIN);
		
		criteria.createAlias("cursoPeriodo", "cursoPeriodoA");
		criteria.setFetchMode("cursoPeriodoA", FetchMode.JOIN);
		
		criteria.createAlias("cursoPeriodoA.curso", "cursoA");
		criteria.setFetchMode("cursoA", FetchMode.JOIN);
		
		
		criteria.createAlias("cursoPeriodoA.periodoAcademico", "peridoA");
		criteria.setFetchMode("peridoA", FetchMode.JOIN);
		
		criteria.add(Restrictions.eq("perUsrA.idUsuario", idProfesor));
		criteria.add(Restrictions.eq("estado", SigecConstantes.INVITACION_EMITIDA));
		Collection<InvitacionDocente> lst = criteria.list(); 
		return lst;
	}

	@Override
	public Collection<InvitacionDocente> verificarInivtacionAceptada()
			throws Exception {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(InvitacionDocente.class);
		criteria.createAlias("profesor", "profesorA");
		criteria.setFetchMode("profesorA", FetchMode.JOIN);
		
		criteria.createAlias("profesorA.persona", "perProfA");
		criteria.setFetchMode("perProfA", FetchMode.JOIN);
		
		criteria.createAlias("perProfA.usuario", "perUsrA");
		criteria.setFetchMode("perUsrA", FetchMode.JOIN);
		
		criteria.createAlias("cursoPeriodo", "cursoPeriodoA");
		criteria.setFetchMode("cursoPeriodoA", FetchMode.JOIN);
		
		criteria.createAlias("cursoPeriodoA.curso", "cursoA");
		criteria.setFetchMode("cursoA", FetchMode.JOIN);
		
		criteria.createAlias("cursoPeriodoA.periodoAcademico", "peridoA");
		criteria.setFetchMode("peridoA", FetchMode.JOIN);
		
		criteria.add(Restrictions.eq("estado", SigecConstantes.INVITACION_ACEPTADA));
		criteria.add(Restrictions.isNull("contratoGenerado"));
		Collection<InvitacionDocente> lst = criteria.list(); 
		return lst;
	}
}
