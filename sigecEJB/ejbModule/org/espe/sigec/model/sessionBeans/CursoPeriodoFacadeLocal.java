/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.CursoPeriodo;

/**
 *
 * @author roberto
 */
@Local
public interface CursoPeriodoFacadeLocal {

    void create(CursoPeriodo cursoPeriodo) throws Exception;

    void edit(CursoPeriodo cursoPeriodo) throws Exception;

    void remove(CursoPeriodo cursoPeriodo) throws Exception;

    CursoPeriodo find(Object id);

    List<CursoPeriodo> findAll();

    List<CursoPeriodo> findRange(int[] range);

    int count();
    
    /**
     * Se usa para la administracion de cursos
     * @return
     */
    Collection<CursoPeriodo> findCursoAbierto();
    
    Collection<CursoPeriodo> findCursoAbiertoByUser(Integer idPersona);
    
    /**
     * Carga la lista de cursos en los cuales se puede inscribir un estudiante
     * @return
     */
    Collection<CursoPeriodo> cargarCursoLanzado();
}
