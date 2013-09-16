package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sgct_ifr_mat_did_cat")
@NamedQueries({
    @NamedQuery(name = "MaterialDidacticoCatalogo.findAll", query = "SELECT s FROM MaterialDidacticoCatalogo s")})
public class MaterialDidacticoCatalogo implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "mtdt_id_material")
    private Integer idMaterial;
    @Column(name = "mtdt_nombre")
    private String nombre;
    @Column(name = "mtdt_descripcion")
    private String descripcion;
    @Column(name = "mtdt_estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialDidacticoCatalogo", fetch = FetchType.LAZY)
    private Collection<MaterialDidacticoCurso> lstMaterialDidacticoCursos;

    public MaterialDidacticoCatalogo() {
    }

    public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Collection<MaterialDidacticoCurso> getLstMaterialDidacticoCursos() {
		return lstMaterialDidacticoCursos;
	}

	public void setLstMaterialDidacticoCursos(
			Collection<MaterialDidacticoCurso> lstMaterialDidacticoCursos) {
		this.lstMaterialDidacticoCursos = lstMaterialDidacticoCursos;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaterial != null ? idMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialDidacticoCatalogo)) {
            return false;
        }
        MaterialDidacticoCatalogo other = (MaterialDidacticoCatalogo) object;
        if ((this.idMaterial == null && other.idMaterial != null) || (this.idMaterial != null && !this.idMaterial.equals(other.idMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.MaterialDidacticoCatalogo[ mtdtIdMaterial=" + idMaterial + " ]";
    }
}
