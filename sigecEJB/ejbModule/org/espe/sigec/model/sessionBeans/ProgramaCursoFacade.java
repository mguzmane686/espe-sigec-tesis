package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.ProgramaCurso;
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
}
