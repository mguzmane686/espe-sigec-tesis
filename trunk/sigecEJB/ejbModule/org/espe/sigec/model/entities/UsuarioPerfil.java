package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "usuario_perfil")
public class UsuarioPerfil implements Serializable{
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private Integer idUsuario;
    
//    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil")
    private Integer idPerfil;
    
    @OneToMany(mappedBy = "usuarioPerfil", fetch = FetchType.EAGER)
    private Collection<Modulo> moduloCollection;

	public UsuarioPerfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Collection<Modulo> getModuloCollection() {
		return moduloCollection;
	}

	public void setModuloCollection(Collection<Modulo> moduloCollection) {
		this.moduloCollection = moduloCollection;
	}
	
}
