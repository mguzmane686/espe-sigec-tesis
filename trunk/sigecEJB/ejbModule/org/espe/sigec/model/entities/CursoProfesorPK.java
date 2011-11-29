/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author roberto
 */
@SuppressWarnings("serial")
@Embeddable
public class CursoProfesorPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso")
    private int idCurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_profesor")
    private BigInteger idProfesor;

    public CursoProfesorPK() {
    }

    public CursoProfesorPK(int idCurso, BigInteger idProfesor) {
        this.idCurso = idCurso;
        this.idProfesor = idProfesor;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public BigInteger getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(BigInteger idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCurso;
        hash += (idProfesor != null ? idProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoProfesorPK)) {
            return false;
        }
        CursoProfesorPK other = (CursoProfesorPK) object;
        if (this.idCurso != other.idCurso) {
            return false;
        }
        if ((this.idProfesor == null && other.idProfesor != null) || (this.idProfesor != null && !this.idProfesor.equals(other.idProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.CursoProfesorPK[ idCurso=" + idCurso + ", idProfesor=" + idProfesor + " ]";
    }
    
}
