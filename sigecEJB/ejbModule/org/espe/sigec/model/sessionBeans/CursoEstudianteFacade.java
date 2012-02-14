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
import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class CursoEstudianteFacade extends AbstractFacade<CursoEstudiante> implements CursoEstudianteFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoEstudianteFacade() {
        super(CursoEstudiante.class);
    }

	@Override
	public int numeroEstudiantesInscritos(BigDecimal idCursoPeriodo) {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(CursoEstudiante.class);
		criteria.add(Restrictions.eq("cursoEstudiantePK.idCursoPeriodo", idCursoPeriodo));
		
		@SuppressWarnings("unchecked")
		Collection<Usuario> lstUsuarios = criteria.list();
		return lstUsuarios.size();
	}
    
}
