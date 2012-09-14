package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.ContratoProfesor;

@Local
public interface ContratoProfesorFacadeLocal {
	void create(ContratoProfesor contratoProfesor) throws Exception;

    void edit(ContratoProfesor contratoProfesor) throws Exception;

    void remove(ContratoProfesor contratoProfesor) throws Exception;

    ContratoProfesor find(Object id);

    List<ContratoProfesor> findAll();

    List<ContratoProfesor> findRange(int[] range);
    
    int count();
}
