/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.Presupuesto;
import org.espe.sigec.model.entities.PresupuestoDetalle;

/**
 *
 * @author roberto
 */
@Local
public interface PresupuestoFacadeLocal {

    void create(Presupuesto presupuesto) throws Exception;

    void edit(Presupuesto presupuesto) throws Exception;

    void remove(Presupuesto presupuesto) throws Exception;

    Presupuesto find(Object id);
    
    Presupuesto findByCodAnio(String id);

    List<Presupuesto> findAll();

    List<Presupuesto> findRange(int[] range);

    int count();

	Collection<Presupuesto> findPresupuesto(String codigoAnio);
	List<PresupuestoDetalle> findPresupuestoDetalles(int presupuestoId);
}
