/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "sgct_fnc_presupuesto_curso")
@NamedQueries({
    @NamedQuery(name = "PresupuestoCurso.findAll", query = "SELECT p FROM PresupuestoCurso p")})
public class PresupuestoCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso_periodo")
    private BigDecimal idCursoPeriodo;
    @Column(name = "dinero_asignado")
    private Double dineroAsignado;
    @Column(name = "porcentage_mat_ofi")
    private Integer porcentageMatOfi;
    @Column(name = "porcentage_uti_espe")
    private Integer porcentageUtiEspe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "presupuestoCurso", fetch = FetchType.LAZY)
    private Collection<DetallePresupuestoCurso> detallePresupuestoCursoCollection;
    @JoinColumn(name = "pre_id", referencedColumnName = "pre_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Presupuesto presupuesto;
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private CursoPeriodo cursoPeriodo;

    public PresupuestoCurso() {
    }

    public PresupuestoCurso(BigDecimal idCursoPeriodo) {
        this.idCursoPeriodo = idCursoPeriodo;
    }

    public BigDecimal getIdCursoPeriodo() {
        return idCursoPeriodo;
    }

    public void setIdCursoPeriodo(BigDecimal idCursoPeriodo) {
        this.idCursoPeriodo = idCursoPeriodo;
    }

    public Double getDineroAsignado() {
        return dineroAsignado;
    }

    public void setDineroAsignado(Double dineroAsignado) {
        this.dineroAsignado = dineroAsignado;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
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
        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PresupuestoCurso)) {
            return false;
        }
        PresupuestoCurso other = (PresupuestoCurso) object;
        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.PresupuestoCurso[ idCursoPeriodo=" + idCursoPeriodo + " ]";
    }

	public Collection<DetallePresupuestoCurso> getDetallePresupuestoCursoCollection() {
		return detallePresupuestoCursoCollection;
	}

	public void setDetallePresupuestoCursoCollection(
			Collection<DetallePresupuestoCurso> detallePresupuestoCursoCollection) {
		this.detallePresupuestoCursoCollection = detallePresupuestoCursoCollection;
	}

	public Integer getPorcentageMatOfi() {
		return porcentageMatOfi;
	}

	public void setPorcentageMatOfi(Integer porcentageMatOfi) {
		this.porcentageMatOfi = porcentageMatOfi;
	}

	public Integer getPorcentageUtiEspe() {
		return porcentageUtiEspe;
	}

	public void setPorcentageUtiEspe(Integer porcentageUtiEspe) {
		this.porcentageUtiEspe = porcentageUtiEspe;
	}
	
}
