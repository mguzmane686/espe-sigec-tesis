/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "sgct_ifr_mat_did")
@NamedQueries({
    @NamedQuery(name = "MaterialDidactico.findAll", query = "SELECT m FROM MaterialDidactico m")})
public class MaterialDidactico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "mtdt_id_material")
    private Integer idMaterial;
    @Column(name = "mtdt_cantidad")
    private Integer cantidad;
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo")
    @ManyToOne(fetch = FetchType.LAZY)
    private CursoPeriodo cursoPeriodo;

    public MaterialDidactico() {
    }

    public MaterialDidactico(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public CursoPeriodo getCursoPeriodo() {
        return cursoPeriodo;
    }

    public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
        this.cursoPeriodo = cursoPeriodo;
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
        if (!(object instanceof MaterialDidactico)) {
            return false;
        }
        MaterialDidactico other = (MaterialDidactico) object;
        if ((this.idMaterial == null && other.idMaterial != null) || (this.idMaterial != null && !this.idMaterial.equals(other.idMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.MaterialDidactico[ idMaterial=" + idMaterial + " ]";
    }
    
}
