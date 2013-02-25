/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.model.entities.Profesor;

/**
 *
 * @author roberto
 */
@Local
public interface ProfesorFacadeLocal {

    void create(Profesor profesor) throws Exception;

    void edit(Profesor profesor) throws Exception;

    void remove(Profesor profesor) throws Exception;

    Profesor find(Object id);

    List<Profesor> findAll();

    List<Profesor> findRange(int[] range);

    int count();
    
    Collection<Profesor> findProfesores();
    Collection<Profesor> findProfesoresSeleccionadosPorEspecialidad();
    Collection<InvitacionDocente> findProfesoresSeleccionados(BigDecimal idCursoPeriodo);
    
    Collection<Profesor> cargarProfesores();
    
}
