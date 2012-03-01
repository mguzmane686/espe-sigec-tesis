/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "presupuesto")
@NamedQueries({
    @NamedQuery(name = "Presupuesto.findAll", query = "SELECT p FROM Presupuesto p")})
public class Presupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_presupuesto")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="presupuesto_seq")
    @SequenceGenerator(name="presupuesto_seq", sequenceName="presupuesto_seq", allocationSize = 1)
    private Integer idPresupuesto;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "recurso_inicial")
    private BigDecimal recursoInicial;
    @Column(name = "recurso_actual")
    private BigDecimal recursoActual;
    
    @Column(name = "codigo_anio")
    private String codigoAnio;
    
    @Column(name = "id_prefijo_presupuesto")
    private String idPrefijoPresupuesto;
    
    @OneToMany(mappedBy = "presupuesto", fetch = FetchType.LAZY)
    private Collection<PresupuestoCurso> presupuestoCursoCollection;

    public Presupuesto() {
    }

    public Presupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Integer getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
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

    
    public BigDecimal getRecursoInicial() {
		return recursoInicial;
	}

	public void setRecursoInicial(BigDecimal recursoInicial) {
		this.recursoInicial = recursoInicial;
	}

	public BigDecimal getRecursoActual() {
		return recursoActual;
	}

	public void setRecursoActual(BigDecimal recursoActual) {
		this.recursoActual = recursoActual;
	}

	public Collection<PresupuestoCurso> getPresupuestoCursoCollection() {
        return presupuestoCursoCollection;
    }

    public void setPresupuestoCursoCollection(Collection<PresupuestoCurso> presupuestoCursoCollection) {
        this.presupuestoCursoCollection = presupuestoCursoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPresupuesto != null ? idPresupuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        Presupuesto other = (Presupuesto) object;
        if ((this.idPresupuesto == null && other.idPresupuesto != null) || (this.idPresupuesto != null && !this.idPresupuesto.equals(other.idPresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.Presupuesto[ idPresupuesto=" + idPresupuesto + " ]";
    }

	public String getCodigoAnio() {
		return codigoAnio;
	}

	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
	}

	public String getIdPrefijoPresupuesto() {
		return idPrefijoPresupuesto;
	}

	public void setIdPrefijoPresupuesto(String idPrefijoPresupuesto) {
		this.idPrefijoPresupuesto = idPrefijoPresupuesto;
	}
    
}
