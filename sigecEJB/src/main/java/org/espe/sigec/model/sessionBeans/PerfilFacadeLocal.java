/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.Perfil;

/**
 *
 * @author roberto
 */
@Local
public interface PerfilFacadeLocal {

    void create(Perfil perfil) throws Exception;

    void edit(Perfil perfil) throws Exception;

    void remove(Perfil perfil) throws Exception;

    Perfil find(Object id) throws Exception;

    List<Perfil> findAll();

    List<Perfil> findRange(int[] range);
    
    Collection<Perfil> findPerfiles();
    
    int count();
    
}
