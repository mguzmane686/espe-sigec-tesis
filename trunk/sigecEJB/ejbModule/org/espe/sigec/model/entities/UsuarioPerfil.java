/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "usuario_perfil")
@NamedQueries({
    @NamedQuery(name = "UsuarioPerfil.findAll", query = "SELECT u FROM UsuarioPerfil u")})
public class UsuarioPerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioPerfilPK usuarioPerfilPK;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Perfil perfil;

    @Transient
    private Persona persona;
    @OneToMany(mappedBy = "usuarioPerfil", fetch = FetchType.LAZY)
    private Collection<Modulo> moduloCollection;
    
    public UsuarioPerfil() {
    }

    public UsuarioPerfil(UsuarioPerfilPK usuarioPerfilPK) {
        this.usuarioPerfilPK = usuarioPerfilPK;
    }

    public UsuarioPerfil(int idUsuario, String idPerfil) {
        this.usuarioPerfilPK = new UsuarioPerfilPK(idUsuario, idPerfil);
    }

    public UsuarioPerfilPK getUsuarioPerfilPK() {
        return usuarioPerfilPK;
    }

    public void setUsuarioPerfilPK(UsuarioPerfilPK usuarioPerfilPK) {
        this.usuarioPerfilPK = usuarioPerfilPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioPerfilPK != null ? usuarioPerfilPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPerfil)) {
            return false;
        }
        UsuarioPerfil other = (UsuarioPerfil) object;
        if ((this.usuarioPerfilPK == null && other.usuarioPerfilPK != null) || (this.usuarioPerfilPK != null && !this.usuarioPerfilPK.equals(other.usuarioPerfilPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.UsuarioPerfil[ usuarioPerfilPK=" + usuarioPerfilPK + " ]";
    }

	public Collection<Modulo> getModuloCollection() {
		return moduloCollection;
	}

	public void setModuloCollection(Collection<Modulo> moduloCollection) {
		this.moduloCollection = moduloCollection;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
    
}
