package org.espe.sigec.model.sessionBeans;

import java.math.BigDecimal;
import java.util.Collection;
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
    
    Collection<InvitacionDocente> verificarInivtacionDocente(Integer idProfesor) throws Exception;
    Collection<InvitacionDocente> verificarInivtacionAceptada() throws Exception;
    Collection<InvitacionDocente> findInvitacionesByEstado(String estadoInvitacion) throws Exception;
    
    InvitacionDocente verificarUltimaInivtacionDocente(BigDecimal idCursoPeriodo, Integer idProfesor) throws Exception;
    boolean validarUnicidadInivtacionDocente(BigDecimal idCursoPeriodo, Integer idProfesor) throws Exception;
}
