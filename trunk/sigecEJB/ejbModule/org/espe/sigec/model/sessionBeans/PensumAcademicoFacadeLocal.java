/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.PensumAcademico;

/**
 *
 * @author roberto
 */
@Local
public interface PensumAcademicoFacadeLocal {

    void create(PensumAcademico pensumAcademico) throws Exception;

    void edit(PensumAcademico pensumAcademico) throws Exception;

    void remove(PensumAcademico pensumAcademico) throws Exception;

    PensumAcademico find(Object id);

    List<PensumAcademico> findAll();

    List<PensumAcademico> findRange(int[] range);

    int count();
    
}
