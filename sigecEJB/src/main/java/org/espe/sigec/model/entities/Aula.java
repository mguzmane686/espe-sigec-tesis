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
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "sgct_ifr_aula")
@NamedQueries({
    @NamedQuery(name = "Aula.findAll", query = "SELECT a FROM Aula a")})
@Audited
public class Aula implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "aul_id")
    private String idAula;
    @Size(max = 250)
    @Column(name = "aul_nombre")
    private String nombreAula;
    @Size(max = 250)
    @Column(name = "aul_descripcion")
    private String descripcionAula;
    @Column(name = "aul_capacidad")
    private Integer capacidad;
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "edf_id_edificio", referencedColumnName = "edf_id_edificio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Edificio edificio;
//    @OneToMany(mappedBy = "aula", fetch = FetchType.LAZY)
//    private Collection<ModuloCursoPeriodo> moduloCursoPeriodoCollection;

    public Aula() {
    }

    public Aula(String idAula) {
        this.idAula = idAula;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }
    
    public String getNombreAula() {
        return nombreAula;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public String getDescripcionAula() {
        return descripcionAula;
    }

    public void setDescripcionAula(String descripcionAula) {
        this.descripcionAula = descripcionAula;
    }
    
    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
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
        return "org.espe.sigec.model.entities.Aula[ idAula=" + idAula + " ]";
    }

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

//	public Collection<ModuloCursoPeriodo> getModuloCursoPeriodoCollection() {
//		return moduloCursoPeriodoCollection;
//	}
//
//	public void setModuloCursoPeriodoCollection(
//			Collection<ModuloCursoPeriodo> moduloCursoPeriodoCollection) {
//		this.moduloCursoPeriodoCollection = moduloCursoPeriodoCollection;
//	}
    
}
