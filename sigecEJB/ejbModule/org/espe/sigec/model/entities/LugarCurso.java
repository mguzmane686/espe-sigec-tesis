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
@Table(name = "lugar_curso")
@NamedQueries({
    @NamedQuery(name = "LugarCurso.findAll", query = "SELECT l FROM LugarCurso l")})
public class LugarCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id_lugar")
    private String idLugar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "direccion")
    private String direccion;
    @OneToMany(mappedBy = "lugarCurso", fetch = FetchType.LAZY)
    private Collection<Edificio> edificioCollection;

    public LugarCurso() {
    }

    public LugarCurso(String idLugar) {
        this.idLugar = idLugar;
    }

    public LugarCurso(String idLugar, String nombre, String direccion) {
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(String idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Collection<Edificio> getEdificioCollection() {
        return edificioCollection;
    }

    public void setEdificioCollection(Collection<Edificio> edificioCollection) {
        this.edificioCollection = edificioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLugar != null ? idLugar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LugarCurso)) {
            return false;
        }
        LugarCurso other = (LugarCurso) object;
        if ((this.idLugar == null && other.idLugar != null) || (this.idLugar != null && !this.idLugar.equals(other.idLugar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.LugarCurso[ idLugar=" + idLugar + " ]";
    }
    
}
