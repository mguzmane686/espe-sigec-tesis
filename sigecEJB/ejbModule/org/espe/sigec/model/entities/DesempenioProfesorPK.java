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
public class DesempenioProfesorPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_profesor")
    private BigInteger idProfesor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_per_academico")
    private int idPerAcademico;

    public DesempenioProfesorPK() {
    }

    public DesempenioProfesorPK(BigInteger idProfesor, int idPerAcademico) {
        this.idProfesor = idProfesor;
        this.idPerAcademico = idPerAcademico;
    }

    public BigInteger getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(BigInteger idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdPerAcademico() {
        return idPerAcademico;
    }

    public void setIdPerAcademico(int idPerAcademico) {
        this.idPerAcademico = idPerAcademico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfesor != null ? idProfesor.hashCode() : 0);
        hash += (int) idPerAcademico;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DesempenioProfesorPK)) {
            return false;
        }
        DesempenioProfesorPK other = (DesempenioProfesorPK) object;
        if ((this.idProfesor == null && other.idProfesor != null) || (this.idProfesor != null && !this.idProfesor.equals(other.idProfesor))) {
            return false;
        }
        if (this.idPerAcademico != other.idPerAcademico) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.DesempenioProfesorPK[ idProfesor=" + idProfesor + ", idPerAcademico=" + idPerAcademico + " ]";
    }
    
}
