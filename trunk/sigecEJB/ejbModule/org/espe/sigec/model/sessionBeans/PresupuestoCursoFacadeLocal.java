/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.PresupuestoCurso;

/**
 *
 * @author roberto
 */
@Local
public interface PresupuestoCursoFacadeLocal {

    void create(PresupuestoCurso presupuestoCurso) throws Exception;

    void edit(PresupuestoCurso presupuestoCurso) throws Exception;

    void remove(PresupuestoCurso presupuestoCurso) throws Exception;

    PresupuestoCurso find(Object id);

    List<PresupuestoCurso> findAll();

    List<PresupuestoCurso> findRange(int[] range);

    int count();
    
    PresupuestoCurso findPresupuestoCurso(BigDecimal idCursoPeriodo);
    
}
