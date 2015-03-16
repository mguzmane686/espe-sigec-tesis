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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@Entity
@Table(name = "sgct_doc_plantilla", catalog = "sigec", schema = "public")
public class Plantilla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "doc_id_plantilla")
    private Integer idPlantilla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "doc_nombre")
    private String docNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "doc_contenido")
    private String docContenido;
    @OneToMany(mappedBy = "plantilla", fetch = FetchType.LAZY)
    private Collection<InvitacionDocente> sgctDocInvitacionPorfCollection;

    public Plantilla() {
    }

    public Plantilla(Integer idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public Plantilla(Integer idPlantilla, String docNombre, String docContenido) {
        this.idPlantilla = idPlantilla;
        this.docNombre = docNombre;
        this.docContenido = docContenido;
    }

    public Integer getDocIdPlantilla() {
        return idPlantilla;
    }

    public void setDocIdPlantilla(Integer idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public String getDocNombre() {
        return docNombre;
    }

    public void setDocNombre(String docNombre) {
        this.docNombre = docNombre;
    }

    public String getDocContenido() {
        return docContenido;
    }

    public void setDocContenido(String docContenido) {
        this.docContenido = docContenido;
    }

    public Collection<InvitacionDocente> getSgctDocInvitacionPorfCollection() {
        return sgctDocInvitacionPorfCollection;
    }

    public void setSgctDocInvitacionPorfCollection(Collection<InvitacionDocente> sgctDocInvitacionPorfCollection) {
        this.sgctDocInvitacionPorfCollection = sgctDocInvitacionPorfCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlantilla != null ? idPlantilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantilla)) {
            return false;
        }
        Plantilla other = (Plantilla) object;
        if ((this.idPlantilla == null && other.idPlantilla != null) || (this.idPlantilla != null && !this.idPlantilla.equals(other.idPlantilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.Plantilla[ idPlantilla=" + idPlantilla + " ]";
    }
    
}
