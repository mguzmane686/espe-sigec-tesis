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
    
}
