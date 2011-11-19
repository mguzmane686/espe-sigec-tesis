/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "periodo_academico")
@NamedQueries({
    @NamedQuery(name = "PeriodoAcademico.findAll", query = "SELECT p FROM PeriodoAcademico p")})
public class PeriodoAcademico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_per_academico")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="peraca_sec")
    @SequenceGenerator(name="peraca_sec", sequenceName="peraca_sec", allocationSize = 1)
    private Integer idPerAcademico;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(mappedBy = "periodoAcademico", fetch = FetchType.LAZY)
    private Collection<CursoPeriodo> cursoPeriodoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoAcademico", fetch = FetchType.LAZY)
    private Collection<DesempenioProfesor> desempenioProfesorCollection;

    public PeriodoAcademico() {
    }

    public PeriodoAcademico(Integer idPerAcademico) {
        this.idPerAcademico = idPerAcademico;
    }

    public Integer getIdPerAcademico() {
        return idPerAcademico;
    }

    public void setIdPerAcademico(Integer idPerAcademico) {
        this.idPerAcademico = idPerAcademico;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Collection<CursoPeriodo> getCursoPeriodoCollection() {
        return cursoPeriodoCollection;
    }

    public void setCursoPeriodoCollection(Collection<CursoPeriodo> cursoPeriodoCollection) {
        this.cursoPeriodoCollection = cursoPeriodoCollection;
    }

    public Collection<DesempenioProfesor> getDesempenioProfesorCollection() {
        return desempenioProfesorCollection;
    }

    public void setDesempenioProfesorCollection(Collection<DesempenioProfesor> desempenioProfesorCollection) {
        this.desempenioProfesorCollection = desempenioProfesorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerAcademico != null ? idPerAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoAcademico)) {
            return false;
        }
        PeriodoAcademico other = (PeriodoAcademico) object;
        if ((this.idPerAcademico == null && other.idPerAcademico != null) || (this.idPerAcademico != null && !this.idPerAcademico.equals(other.idPerAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.PeriodoAcademico[ idPerAcademico=" + idPerAcademico + " ]";
    }
    
}
