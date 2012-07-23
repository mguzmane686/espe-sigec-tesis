/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
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
@Table(name = "sgct_acd_per_aca")
@NamedQueries({
    @NamedQuery(name = "PeriodoAcademico.findAll", query = "SELECT p FROM PeriodoAcademico p")})
public class PeriodoAcademico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "paca_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="peraca_seq")
    @SequenceGenerator(name="peraca_seq", sequenceName="peraca_seq", allocationSize = 1)
    private Integer idPerAcademico;
    @Column(name = "paca_fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "paca_fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(mappedBy = "periodoAcademico", fetch = FetchType.LAZY)
    private Collection<CursoPeriodo> cursoPeriodoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoAcademico", fetch = FetchType.LAZY)
//    private Collection<DesempenioProfesor> desempenioProfesorCollection;

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

//    public Collection<DesempenioProfesor> getDesempenioProfesorCollection() {
//        return desempenioProfesorCollection;
//    }
//
//    public void setDesempenioProfesorCollection(Collection<DesempenioProfesor> desempenioProfesorCollection) {
//        this.desempenioProfesorCollection = desempenioProfesorCollection;
//    }

    

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.PeriodoAcademico[ idPerAcademico=" + idPerAcademico + " ]";
    }

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
    
}
