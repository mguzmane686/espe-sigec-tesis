/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "desempenio_profesor")
@NamedQueries({
    @NamedQuery(name = "DesempenioProfesor.findAll", query = "SELECT d FROM DesempenioProfesor d")})
public class DesempenioProfesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DesempenioProfesorPK desempenioProfesorPK;
    @Column(name = "puntualidad")
    private Integer puntualidad;
    @Column(name = "conocimientos")
    private Integer conocimientos;
    @Column(name = "desempenio")
    private Integer desempenio;
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profesor profesor;
    @JoinColumn(name = "id_per_academico", referencedColumnName = "id_per_academico", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeriodoAcademico periodoAcademico;

    public DesempenioProfesor() {
    }

    public DesempenioProfesor(DesempenioProfesorPK desempenioProfesorPK) {
        this.desempenioProfesorPK = desempenioProfesorPK;
    }

    public DesempenioProfesor(BigInteger idProfesor, int idPerAcademico) {
        this.desempenioProfesorPK = new DesempenioProfesorPK(idProfesor, idPerAcademico);
    }

    public DesempenioProfesorPK getDesempenioProfesorPK() {
        return desempenioProfesorPK;
    }

    public void setDesempenioProfesorPK(DesempenioProfesorPK desempenioProfesorPK) {
        this.desempenioProfesorPK = desempenioProfesorPK;
    }

    public Integer getPuntualidad() {
        return puntualidad;
    }

    public void setPuntualidad(Integer puntualidad) {
        this.puntualidad = puntualidad;
    }

    public Integer getConocimientos() {
        return conocimientos;
    }

    public void setConocimientos(Integer conocimientos) {
        this.conocimientos = conocimientos;
    }

    public Integer getDesempenio() {
        return desempenio;
    }

    public void setDesempenio(Integer desempenio) {
        this.desempenio = desempenio;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public PeriodoAcademico getPeriodoAcademico() {
        return periodoAcademico;
    }

    public void setPeriodoAcademico(PeriodoAcademico periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (desempenioProfesorPK != null ? desempenioProfesorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DesempenioProfesor)) {
            return false;
        }
        DesempenioProfesor other = (DesempenioProfesor) object;
        if ((this.desempenioProfesorPK == null && other.desempenioProfesorPK != null) || (this.desempenioProfesorPK != null && !this.desempenioProfesorPK.equals(other.desempenioProfesorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.DesempenioProfesor[ desempenioProfesorPK=" + desempenioProfesorPK + " ]";
    }
    
}
