/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.exception.UserValidateException;
import org.espe.sigec.model.entities.Modulo;
import org.espe.sigec.model.entities.Usuario;

/**
 *
 * @author roberto
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario) throws Exception;

    void edit(Usuario usuario) throws Exception;

    void remove(Usuario usuario) throws Exception;

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    Usuario validateLogin(String identificador, String clave);
    Collection<Modulo> getMenuByProfile(Usuario usuario);
    boolean isIdentificadorvalida(String identificacdor) throws UserValidateException;
}
