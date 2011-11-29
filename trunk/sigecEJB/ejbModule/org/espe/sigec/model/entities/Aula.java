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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "aula")
@NamedQueries({
    @NamedQuery(name = "Aula.findAll", query = "SELECT a FROM Aula a")})
public class Aula implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_aula")
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="aula_seq")
//    @SequenceGenerator(name="aula_seq", sequenceName="aula_seq", allocationSize = 1)
    @Size(min = 1, max = 20)
    private String idAula;
    @Size(max = 50)
    @Column(name = "cod_aula")
    private String codAula;
    @Size(max = 250)
    @Column(name = "nombre_aula")
    private String nombreAula;
    @Size(max = 250)
    @Column(name = "descripcion_aula")
    private String descripcionAula;
    @OneToMany(mappedBy = "aula", fetch = FetchType.LAZY)
    private Collection<CursoPeriodo> cursoPeriodoCollection;
    @JoinColumn(name = "id_edificio", referencedColumnName = "id_edificio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Edificio edificio;
    
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
        return "org.espe.sigec.model.entites.Aula[ idAula=" + idAula + " ]";
    }

	public String getCodAula() {
		return codAula;
	}

	public void setCodAula(String codAula) {
		this.codAula = codAula;
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

	public Collection<CursoPeriodo> getCursoPeriodoCollection() {
		return cursoPeriodoCollection;
	}

	public void setCursoPeriodoCollection(
			Collection<CursoPeriodo> cursoPeriodoCollection) {
		this.cursoPeriodoCollection = cursoPeriodoCollection;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}
    
}
