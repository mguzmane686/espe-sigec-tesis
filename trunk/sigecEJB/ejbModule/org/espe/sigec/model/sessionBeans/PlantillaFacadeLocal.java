package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.Plantilla;

@Local
public interface PlantillaFacadeLocal {
	void create(Plantilla plantilla) throws Exception;

    void edit(Plantilla plantilla) throws Exception;

    void remove(Plantilla plantilla) throws Exception;

    Plantilla find(Object id);

    List<Plantilla> findAll();

    List<Plantilla> findRange(int[] range);

    int count();
}
