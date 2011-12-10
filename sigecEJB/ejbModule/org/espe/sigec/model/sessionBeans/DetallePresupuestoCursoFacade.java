/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

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
    @PersistenceContext(unitName = "baseRamosPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallePresupuestoCursoFacade() {
        super(DetallePresupuestoCurso.class);
    }
    
}
