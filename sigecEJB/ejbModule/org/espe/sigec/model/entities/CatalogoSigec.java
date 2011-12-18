/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "catalogo_sigec")
@NamedQueries({
    @NamedQuery(name = "CatalogoSigec.findAll", query = "SELECT c FROM CatalogoSigec c")})
public class CatalogoSigec implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
//    @OneToMany(mappedBy = "catalogoSigec", fetch = FetchType.LAZY)
//    private Collection<CatalogoSigec> catalogoSigecCollection;
//    @JoinColumn(name = "parent_codigo", referencedColumnName = "codigo")
//    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "parent_codigo")
    private String catalogoSigec;

    public CatalogoSigec() {
    }

    public CatalogoSigec(String codigo) {
        this.codigo = codigo;
    }

    public CatalogoSigec(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    public Collection<CatalogoSigec> getCatalogoSigecCollection() {
//        return catalogoSigecCollection;
//    }
//
//    public void setCatalogoSigecCollection(Collection<CatalogoSigec> catalogoSigecCollection) {
//        this.catalogoSigecCollection = catalogoSigecCollection;
//    }

//    public CatalogoSigec getCatalogoSigec() {
//        return catalogoSigec;
//    }
//
//    public void setCatalogoSigec(CatalogoSigec catalogoSigec) {
//        this.catalogoSigec = catalogoSigec;
//    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    public String getCatalogoSigec() {
		return catalogoSigec;
	}

	public void setCatalogoSigec(String catalogoSigec) {
		this.catalogoSigec = catalogoSigec;
	}

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoSigec)) {
            return false;
        }
        CatalogoSigec other = (CatalogoSigec) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.CatalogoSigec[ codigo=" + codigo + " ]";
    }
    
}
