/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.AsistenciaEstudiante;

/**
 *
 * @author roberto
 */
@Local
public interface AsistenciaEstudianteFacadeLocal {

    void create(AsistenciaEstudiante asistenciaEstudiante) throws Exception;

    void edit(AsistenciaEstudiante asistenciaEstudiante) throws Exception;

    void remove(AsistenciaEstudiante asistenciaEstudiante) throws Exception;

    AsistenciaEstudiante find(Object id);

    List<AsistenciaEstudiante> findAll();

    List<AsistenciaEstudiante> findRange(int[] range);

    int count();
    
}
