/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.SgctSgSecuencia;

/**
 *
 * @author Roberto
 */
@Local
public interface SgctSgSecuenciaFacadeLocal {

    void create(SgctSgSecuencia sgctSgSecuencia) throws Exception;

    void edit(SgctSgSecuencia sgctSgSecuencia) throws Exception;

    void remove(SgctSgSecuencia sgctSgSecuencia) throws Exception;

    SgctSgSecuencia find(Object id);

    List<SgctSgSecuencia> findAll();

    List<SgctSgSecuencia> findRange(int[] range);

    int count();
    
}
