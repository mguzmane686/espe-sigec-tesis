package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.HorarioCursoPeriodo;

@Local
public interface HorarioCursoPeriodoFacadeLocal {
	void create(HorarioCursoPeriodo horarioCursoPeriodo) throws Exception;

    void edit(HorarioCursoPeriodo horarioCursoPeriodo) throws Exception;

    void remove(HorarioCursoPeriodo horarioCursoPeriodo) throws Exception;

    HorarioCursoPeriodo find(Object id);

    List<HorarioCursoPeriodo> findAll();

    List<HorarioCursoPeriodo> findRange(int[] range);

    int count();
}
