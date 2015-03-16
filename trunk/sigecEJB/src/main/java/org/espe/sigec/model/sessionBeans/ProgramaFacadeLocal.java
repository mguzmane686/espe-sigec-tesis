package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.Programa;
@Local
public interface ProgramaFacadeLocal {
	void create(Programa programa) throws Exception;

    void edit(Programa programa) throws Exception;

    void remove(Programa programa) throws Exception;

    Programa find(Object id);

    List<Programa> findAll();

    List<Programa> findRange(int[] range);

    int count();
    
	List<Programa> cargarProgramaActivoPortal();
}
