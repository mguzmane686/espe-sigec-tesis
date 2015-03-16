/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.PeriodoAcademico;

/**
 *
 * @author roberto
 */
@Local
public interface PeriodoAcademicoFacadeLocal {

    void create(PeriodoAcademico periodoAcademico) throws Exception;

    void edit(PeriodoAcademico periodoAcademico) throws Exception;

    void remove(PeriodoAcademico periodoAcademico) throws Exception;

    PeriodoAcademico find(Object id);

    List<PeriodoAcademico> findAll();

    List<PeriodoAcademico> findRange(int[] range);

    int count();
    
}
