package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.ProgramaCurso;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
/**
 * @author roberto
 *
 */
@Stateless
public class ProgramaCursoFacade extends AbstractFacade<ProgramaCurso>  implements ProgramaCursoFacadeLocal{
	@PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramaCursoFacade() {
        super(ProgramaCurso.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramaCurso> cargarProgramaPortal() {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(ProgramaCurso.class);
		criteria.createAlias("programa", "programaA");
		criteria.createAlias("cursoPeriodo", "cursoPeriodoA");
		criteria.createAlias("cursoPeriodoA.curso", "cursoA");
		
		criteria.setFetchMode("cursoPeriodo", FetchMode.JOIN);
		criteria.setFetchMode("programaA", FetchMode.JOIN);
		criteria.setFetchMode("cursoA", FetchMode.JOIN);
		return criteria.list();
	}
}