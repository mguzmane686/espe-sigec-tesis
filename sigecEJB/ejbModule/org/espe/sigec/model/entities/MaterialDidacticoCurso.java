/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author roberto
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sgct_ifr_mat_did_cur")
@NamedQueries({
    @NamedQuery(name = "MaterialDidacticoCurso.findAll", query = "SELECT m FROM MaterialDidacticoCurso m")})
public class MaterialDidacticoCurso implements Serializable {
	    @EmbeddedId
	    protected MaterialDidacticoCursoPK materialDidacticoCursoPK;
	    @Basic(optional = false)
	    @Column(name = "mtdt_cantidad")
	    private int mtdtCantidad;
	    @Column(name = "mtdt_estado")
	    private String estado;
	    
	    @Transient
	    private boolean selected;
	    
	    @JoinColumn(name = "mtdt_id_material", referencedColumnName = "mtdt_id_material", insertable = false, updatable = false)
	    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	    private MaterialDidacticoCatalogo materialDidacticoCatalogo;
	    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)
	    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	    private CursoPeriodo cursoPeriodo;

	    public MaterialDidacticoCurso() {
	    }

	    public MaterialDidacticoCurso(MaterialDidacticoCursoPK sgctIfrMatDidCurPK) {
	        this.materialDidacticoCursoPK = sgctIfrMatDidCurPK;
	    }

	    public MaterialDidacticoCurso(MaterialDidacticoCursoPK sgctIfrMatDidCurPK, int mtdtCantidad) {
	        this.materialDidacticoCursoPK = sgctIfrMatDidCurPK;
	        this.mtdtCantidad = mtdtCantidad;
	    }

	    public MaterialDidacticoCurso(int mtdtIdMaterial, BigDecimal idCursoPeriodo) {
	        this.materialDidacticoCursoPK = new MaterialDidacticoCursoPK(mtdtIdMaterial, idCursoPeriodo);
	    }

	   

	    public MaterialDidacticoCursoPK getMaterialDidacticoCursoPK() {
			return materialDidacticoCursoPK;
		}

		public void setMaterialDidacticoCursoPK(
				MaterialDidacticoCursoPK materialDidacticoCursoPK) {
			this.materialDidacticoCursoPK = materialDidacticoCursoPK;
		}

		public int getMtdtCantidad() {
	        return mtdtCantidad;
	    }

	    public void setMtdtCantidad(int mtdtCantidad) {
	        this.mtdtCantidad = mtdtCantidad;
	    }
	    
	    
	    public MaterialDidacticoCatalogo getMaterialDidacticoCatalogo() {
			return materialDidacticoCatalogo;
		}

		public void setMaterialDidacticoCatalogo(
				MaterialDidacticoCatalogo materialDidacticoCatalogo) {
			this.materialDidacticoCatalogo = materialDidacticoCatalogo;
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
	        hash += (materialDidacticoCursoPK != null ? materialDidacticoCursoPK.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof MaterialDidacticoCurso)) {
	            return false;
	        }
	        MaterialDidacticoCurso other = (MaterialDidacticoCurso) object;
	        if ((this.materialDidacticoCursoPK == null && other.materialDidacticoCursoPK != null) || (this.materialDidacticoCursoPK != null && !this.materialDidacticoCursoPK.equals(other.materialDidacticoCursoPK))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "org.espe.sigec.model.entities.MaterialDidacticoCurso[ sgctIfrMatDidCurPK=" + materialDidacticoCursoPK + " ]";
	    }

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}
    
}
