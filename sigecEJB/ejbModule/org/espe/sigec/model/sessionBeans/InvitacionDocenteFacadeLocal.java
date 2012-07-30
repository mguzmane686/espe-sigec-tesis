package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.InvitacionDocente;
/**
 * @author Roberto
 *
 */
@Local
public interface InvitacionDocenteFacadeLocal {
	void create(InvitacionDocente invitacionDocente) throws Exception;

    void edit(InvitacionDocente invitacionDocente) throws Exception;

    void remove(InvitacionDocente invitacionDocente) throws Exception;

    InvitacionDocente find(Object id);

    List<InvitacionDocente> findAll();

    List<InvitacionDocente> findRange(int[] range);

    int count();
}
