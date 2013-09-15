package org.espe.sigec.model.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sgct_ifr_material")
@NamedQueries({
    @NamedQuery(name = "MaterialDidacticoCatalogo.findAll", query = "SELECT s FROM MaterialDidacticoCatalogo s")})
public class MaterialDidacticoCatalogo implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "id_material")
    private Integer idMaterial;
    @Column(name = "mat_nombre")
    private String matNombre;
    @Column(name = "mat_descripcion")
    private String matDescripcion;
    @Column(name = "mat_estado")
    private String matEstado;

    public MaterialDidacticoCatalogo() {
    }

    public MaterialDidacticoCatalogo(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getMatNombre() {
        return matNombre;
    }

    public void setMatNombre(String matNombre) {
        this.matNombre = matNombre;
    }

    public String getMatDescripcion() {
        return matDescripcion;
    }

    public void setMatDescripcion(String matDescripcion) {
        this.matDescripcion = matDescripcion;
    }

    public String getMatEstado() {
        return matEstado;
    }

    public void setMatEstado(String matEstado) {
        this.matEstado = matEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaterial != null ? idMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialDidacticoCatalogo)) {
            return false;
        }
        MaterialDidacticoCatalogo other = (MaterialDidacticoCatalogo) object;
        if ((this.idMaterial == null && other.idMaterial != null) || (this.idMaterial != null && !this.idMaterial.equals(other.idMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.SgctIfrMaterial[ idMaterial=" + idMaterial + " ]";
    }
}
