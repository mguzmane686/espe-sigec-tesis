/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "edificio")
@NamedQueries({
    @NamedQuery(name = "Edificio.findAll", query = "SELECT e FROM Edificio e")})
public class Edificio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id_edificio")
    private String idEdificio;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_lugar", referencedColumnName = "id_lugar")
    @ManyToOne(fetch = FetchType.LAZY)
    private LugarCurso lugarCurso;
    @OneToMany(mappedBy = "edificio", fetch = FetchType.LAZY)
    private Collection<Aula> aulaCollection;

    public Edificio() {
    }

    public Edificio(String idEdificio) {
        this.idEdificio = idEdificio;
    }

    public String getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(String idEdificio) {
        this.idEdificio = idEdificio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LugarCurso getLugarCurso() {
        return lugarCurso;
    }

    public void setLugarCurso(LugarCurso lugarCurso) {
        this.lugarCurso = lugarCurso;
    }

    public Collection<Aula> getAulaCollection() {
        return aulaCollection;
    }

    public void setAulaCollection(Collection<Aula> aulaCollection) {
        this.aulaCollection = aulaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEdificio != null ? idEdificio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Edificio)) {
            return false;
        }
        Edificio other = (Edificio) object;
        if ((this.idEdificio == null && other.idEdificio != null) || (this.idEdificio != null && !this.idEdificio.equals(other.idEdificio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.Edificio[ idEdificio=" + idEdificio + " ]";
    }
    
}
