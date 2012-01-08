/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.CursoEstudiante;

/**
 *
 * @author roberto
 */
@Local
public interface CursoEstudianteFacadeLocal {

    void create(CursoEstudiante cursoEstudiante) throws Exception;

    void edit(CursoEstudiante cursoEstudiante) throws Exception;

    void remove(CursoEstudiante cursoEstudiante) throws Exception;

    CursoEstudiante find(Object id);

    List<CursoEstudiante> findAll();

    List<CursoEstudiante> findRange(int[] range);

    int count();
    
}