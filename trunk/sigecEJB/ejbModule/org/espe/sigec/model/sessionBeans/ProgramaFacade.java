package org.espe.sigec.model.sessionBeans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
@Stateless
public class ProgramaFacade extends AbstractFacade<Programa>  implements ProgramaFacadeLocal{
	
	@PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ProgramaFacade() {
        super(Programa.class);
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public List<Programa> cargarProgramaActivoPortal() {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Programa.class, "programaA");
		
		criteria.add(Restrictions.eq("programaA.estado", "1"));
//		criteria.createAlias("programaA.programaCursoCollection", "programaCursoCollectionB");
		
		Collection<Programa> listPrograma = criteria.list();
		
		for(Programa programaTMP: listPrograma){
			
			criteria = null;
			criteria = ((Session)getEntityManager().getDelegate()).createCriteria(ProgramaCurso.class, "programaCursoA");
			
			criteria.add(Restrictions.eq("programaCursoA.programaCursoPK.idPrograma", programaTMP.getIdPrograma()));
			criteria.add(Restrictions.eq("programaCursoA.estado", "1"));
			criteria.createAlias("cursoPeriodo.curso", "cursoA");
			criteria.setFetchMode("cursoPeriodo", FetchMode.JOIN);
			criteria.setFetchMode("cursoA", FetchMode.JOIN);
			
			Collection<ProgramaCurso> lstProgramaCurso = criteria.list();
			if(lstProgramaCurso==null){
				lstProgramaCurso = new ArrayList<ProgramaCurso>();
			}
			programaTMP.setProgramaCursoCollection(lstProgramaCurso);
		}
		
		return (List<Programa>) listPrograma;
	}
}
