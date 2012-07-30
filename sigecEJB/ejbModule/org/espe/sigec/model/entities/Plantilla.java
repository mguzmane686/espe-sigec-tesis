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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = "Plantilla.findAll", query = "SELECT s FROM Plantilla s"),
    @NamedQuery(name = "Plantilla.findByDocIdPlantilla", query = "SELECT s FROM Plantilla s WHERE s.docIdPlantilla = :docIdPlantilla"),
    @NamedQuery(name = "Plantilla.findByDocNombre", query = "SELECT s FROM Plantilla s WHERE s.docNombre = :docNombre"),
    @NamedQuery(name = "Plantilla.findByDocContenido", query = "SELECT s FROM Plantilla s WHERE s.docContenido = :docContenido")})
public class Plantilla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "doc_id_plantilla")
    private Integer docIdPlantilla;
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

    public Plantilla(Integer docIdPlantilla) {
        this.docIdPlantilla = docIdPlantilla;
    }

    public Plantilla(Integer docIdPlantilla, String docNombre, String docContenido) {
        this.docIdPlantilla = docIdPlantilla;
        this.docNombre = docNombre;
        this.docContenido = docContenido;
    }

    public Integer getDocIdPlantilla() {
        return docIdPlantilla;
    }

    public void setDocIdPlantilla(Integer docIdPlantilla) {
        this.docIdPlantilla = docIdPlantilla;
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
        hash += (docIdPlantilla != null ? docIdPlantilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantilla)) {
            return false;
        }
        Plantilla other = (Plantilla) object;
        if ((this.docIdPlantilla == null && other.docIdPlantilla != null) || (this.docIdPlantilla != null && !this.docIdPlantilla.equals(other.docIdPlantilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.Plantilla[ docIdPlantilla=" + docIdPlantilla + " ]";
    }
    
}
