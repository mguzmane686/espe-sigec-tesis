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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "pensum_academico")
@NamedQueries({
    @NamedQuery(name = "PensumAcademico.findAll", query = "SELECT p FROM PensumAcademico p")})
public class PensumAcademico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pensum")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pensum_seq")
    @SequenceGenerator(name="pensum_seq", sequenceName="pensum_seq", allocationSize = 1)
    private Integer idPensum;
    @Column(name = "pensum_parent")
    private Integer pensumParent;
    @Size(max = 250)
    @Column(name = "tema")
    private String tema;
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;
    @Transient
    private boolean existInBase;
    
    public PensumAcademico() {
    }

    public PensumAcademico(Integer idPensum) {
        this.idPensum = idPensum;
    }

    public Integer getIdPensum() {
        return idPensum;
    }

    public void setIdPensum(Integer idPensum) {
        this.idPensum = idPensum;
    }

    public Integer getPensumParent() {
        return pensumParent;
    }

    public void setPensumParent(Integer pensumParent) {
        this.pensumParent = pensumParent;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPensum != null ? idPensum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PensumAcademico)) {
            return false;
        }
        PensumAcademico other = (PensumAcademico) object;
        if ((this.idPensum == null && other.idPensum != null) || (this.idPensum != null && !this.idPensum.equals(other.idPensum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.PensumAcademico[ idPensum=" + idPensum + " ]";
    }

	public boolean isExistInBase() {
		return existInBase;
	}

	public void setExistInBase(boolean existInBase) {
		this.existInBase = existInBase;
	}
    
}
