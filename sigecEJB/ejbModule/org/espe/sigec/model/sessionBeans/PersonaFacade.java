/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.espe.sigec.model.entities.Persona;

/**
 *
 * @author roberto
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> implements PersonaFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
    
}
