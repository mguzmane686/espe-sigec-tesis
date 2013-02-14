package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.Encuesta;

@Local
public interface EncuestaFacadeLocal {
	void create(Encuesta encuesta) throws Exception;

    void edit(Encuesta encuesta) throws Exception;

    void remove(Encuesta encuesta) throws Exception;

    Encuesta find(Object id);

    List<Encuesta> findAll();

    List<Encuesta> findRange(int[] range);

    int count();
}
