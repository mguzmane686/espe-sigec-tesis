/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.DetallePresupuestoCurso;

/**
 *
 * @author roberto
 */
@Local
public interface DetallePresupuestoCursoFacadeLocal {

    void create(DetallePresupuestoCurso detallePresupuestoCurso) throws Exception;

    void edit(DetallePresupuestoCurso detallePresupuestoCurso) throws Exception;

    void remove(DetallePresupuestoCurso detallePresupuestoCurso) throws Exception;

    DetallePresupuestoCurso find(Object id);

    List<DetallePresupuestoCurso> findAll();

    List<DetallePresupuestoCurso> findRange(int[] range);

    int count();
    
    Collection<DetallePresupuestoCurso> findDetallePresupuestoCurso(BigDecimal idCursoPeriodo);
}
