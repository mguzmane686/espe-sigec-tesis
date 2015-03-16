package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.MaterialDidacticoCurso;

@Stateless
public class MaterialDidacticoCursoFacade extends AbstractFacade<MaterialDidacticoCurso> implements MaterialDidacticoCursoFacadeLocal {
	@PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public MaterialDidacticoCursoFacade() {
        super(MaterialDidacticoCurso.class);
    }
}
