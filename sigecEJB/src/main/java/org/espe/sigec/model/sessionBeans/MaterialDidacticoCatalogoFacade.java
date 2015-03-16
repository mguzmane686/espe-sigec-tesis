package org.espe.sigec.model.sessionBeans;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.MaterialDidacticoCatalogo;
import org.espe.sigec.model.entities.MaterialDidacticoCurso;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@Stateless
public class MaterialDidacticoCatalogoFacade extends AbstractFacade<MaterialDidacticoCatalogo> implements MaterialDidacticoCatalogoFacadeLocal {
	@PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public MaterialDidacticoCatalogoFacade() {
        super(MaterialDidacticoCatalogo.class);
    }

	@Override
	public Collection<MaterialDidacticoCatalogo> findMaterialDidacticoCatalogos(Integer[] listaIdCursosAsignados, String estado) throws Exception {
		Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(MaterialDidacticoCatalogo.class);
    	crit.add(Restrictions.eq("estado", estado));
    	if(listaIdCursosAsignados != null){
    		crit.add(Restrictions.not(Restrictions.in("idMaterial", listaIdCursosAsignados)));
    	}
    	Collection<MaterialDidacticoCatalogo> lstMaterialDidacticoCatalogos = crit.list();
		return lstMaterialDidacticoCatalogos;
	}

	@Override
	public Collection<MaterialDidacticoCurso> findMaterialDidacticoAsignado(BigDecimal idCursoPeriodo) throws Exception {
		Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(MaterialDidacticoCurso.class);
    	crit.add(Restrictions.eq("materialDidacticoCursoPK.idCursoPeriodo", idCursoPeriodo));
    	crit.setFetchMode("materialDidacticoCatalogo", FetchMode.JOIN);
    	crit.add(Restrictions.eq("estado", "ACT"));
    	Collection<MaterialDidacticoCurso> lstMaterialDidacticoCursos = crit.list();
		return lstMaterialDidacticoCursos;
	}
}
