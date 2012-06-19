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

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "opcion")
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o")})
public class Opcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_opcion")
    private Integer idOpcion;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "target")
    private String targetUrl;
    
//    @Size(max = 250)
//    @Column(name = "URL")
//    private String url;
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")
    @ManyToOne(fetch = FetchType.EAGER)
    private Modulo modulo;

    public Opcion() {
    }

    public Opcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcion != null ? idOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.idOpcion == null && other.idOpcion != null) || (this.idOpcion != null && !this.idOpcion.equals(other.idOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.Opcion[ idOpcion=" + idOpcion + " ]";
    }

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
    
}
