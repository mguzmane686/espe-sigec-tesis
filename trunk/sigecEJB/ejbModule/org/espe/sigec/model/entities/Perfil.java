/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "perfil")
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p")})
public class Perfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id_perfil")
    private String idPerfil;
    
    @Column(name = "nombre", length = 250)
    private String nombre;
    @Column(name = "descripcion", length = 250)
    private String descripcion;
    
//    @JoinTable(name = "usuario_perfil", joinColumns = {
//        @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")}, inverseJoinColumns = {
//        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")})
//    @ManyToMany(fetch = FetchType.LAZY)
//    private Collection<Usuario> usuarioCollection;
    
    @OneToMany( mappedBy = "perfil", fetch = FetchType.LAZY)
    private Collection<UsuarioPerfil> usuarioPerfilCollection;
    
    @OneToMany(mappedBy = "idPerfil", fetch = FetchType.LAZY)
    private Collection<Modulo> moduloCollection;
    
    @Transient
    private boolean selected;
    
    public Perfil() {
    }

    public Perfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }
    
    public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

//	public Collection<Usuario> getUsuarioCollection() {
//        return usuarioCollection;
//    }
//
//    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
//        this.usuarioCollection = usuarioCollection;
//    }

	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    public Collection<UsuarioPerfil> getUsuarioPerfilCollection() {
		return usuarioPerfilCollection;
	}

	public void setUsuarioPerfilCollection(
			Collection<UsuarioPerfil> usuarioPerfilCollection) {
		this.usuarioPerfilCollection = usuarioPerfilCollection;
	}

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.Perfil[ idPerfil=" + idPerfil + " ]";
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

	public Collection<Modulo> getModuloCollection() {
		return moduloCollection;
	}

	public void setModuloCollection(Collection<Modulo> moduloCollection) {
		this.moduloCollection = moduloCollection;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
    
}
