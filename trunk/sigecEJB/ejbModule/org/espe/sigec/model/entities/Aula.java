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
@Table(name = "aula")
@NamedQueries({
    @NamedQuery(name = "Aula.findAll", query = "SELECT a FROM Aula a")})
public class Aula implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_aula")
    private Integer idAula;
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo")
    @ManyToOne(fetch = FetchType.LAZY)
    private CursoPeriodo cursoPeriodo;

    public Aula() {
    }

    public Aula(Integer idAula) {
        this.idAula = idAula;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
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
        hash += (idAula != null ? idAula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aula)) {
            return false;
        }
        Aula other = (Aula) object;
        if ((this.idAula == null && other.idAula != null) || (this.idAula != null && !this.idAula.equals(other.idAula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.Aula[ idAula=" + idAula + " ]";
    }
    
}
