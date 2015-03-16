package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.EducacionFormacion;
/**
 * @author Roberto
 *
 */
@Local
public interface EducacionFormacionFacadeLocal {

	void create(EducacionFormacion educacionFormacion) throws Exception;

    void edit(EducacionFormacion educacionFormacion) throws Exception;

    void remove(EducacionFormacion educacionFormacion) throws Exception;

    EducacionFormacion find(Object id);

    List<EducacionFormacion> findAll();

    List<EducacionFormacion> findRange(int[] range);

    int count();
}
