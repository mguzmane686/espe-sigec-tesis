/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.HistoricoCursoEstado;

/**
 *
 * @author roberto
 */
@Local
public interface HistoricoCursoEstadoFacadeLocal {

    void create(HistoricoCursoEstado historicoCursoEstado) throws Exception;

    void edit(HistoricoCursoEstado historicoCursoEstado) throws Exception;

    void remove(HistoricoCursoEstado historicoCursoEstado) throws Exception;

    HistoricoCursoEstado find(Object id);

    List<HistoricoCursoEstado> findAll();

    List<HistoricoCursoEstado> findRange(int[] range);

    int count();
    
}
