/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.CalificacionEstudiante;

/**
 *
 * @author roberto
 */
@Local
public interface CalificacionEstudianteFacadeLocal {

    void create(CalificacionEstudiante calificacionEstudiante) throws Exception;

    void edit(CalificacionEstudiante calificacionEstudiante) throws Exception;

    void remove(CalificacionEstudiante calificacionEstudiante) throws Exception;

    CalificacionEstudiante find(Object id);

    List<CalificacionEstudiante> findAll();

    List<CalificacionEstudiante> findRange(int[] range);

    int count();
    
}
