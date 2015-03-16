package org.espe.sigec.model.sessionBeans;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.MaterialDidacticoCatalogo;
import org.espe.sigec.model.entities.MaterialDidacticoCurso;
@Local
public interface MaterialDidacticoCatalogoFacadeLocal {
	void create(MaterialDidacticoCatalogo materialDidacticoCatalogo) throws Exception;

    void edit(MaterialDidacticoCatalogo materialDidacticoCatalogo) throws Exception;

    void remove(MaterialDidacticoCatalogo materialDidacticoCatalogo) throws Exception;

    MaterialDidacticoCatalogo find(Object id);

    List<MaterialDidacticoCatalogo> findAll();

    List<MaterialDidacticoCatalogo> findRange(int[] range);

    int count();
    
    Collection<MaterialDidacticoCatalogo> findMaterialDidacticoCatalogos(Integer[] listaIdCursosAsignados, String estado) throws Exception;
    Collection<MaterialDidacticoCurso> findMaterialDidacticoAsignado(BigDecimal idCursoPeriodo) throws Exception;
}
