/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.PresupuestoDetalle;

/**
 *
 * @author roberto
 */
@Local
public interface PresupuestoDetalleFacadeLocal {

    void create(PresupuestoDetalle presupuestoDetalle) throws Exception;

    void edit(PresupuestoDetalle presupuestoDetalle) throws Exception;

    void remove(PresupuestoDetalle presupuestoDetalle) throws Exception;

    PresupuestoDetalle find(Object id);

    List<PresupuestoDetalle> findAll();

    List<PresupuestoDetalle> findRange(int[] range);
    
    Collection<PresupuestoDetalle> findDetallesByRestrictionIN(Collection<String> idCuentas);

}
