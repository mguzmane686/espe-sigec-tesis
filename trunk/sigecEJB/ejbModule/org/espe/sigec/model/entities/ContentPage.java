/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@Entity
@Table(name = "content_page")
@NamedQueries({
    @NamedQuery(name = "ContentPage.findAll", query = "SELECT c FROM ContentPage c")})
public class ContentPage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "id_content")
    private String idContent;
    @Size(max = 4096)
    @Column(name = "label_page")
    private String labelPage;
    @Size(max = 8192)
    @Column(name = "content_page")
    private String contentPage;
    @JoinColumn(name = "id_pagina", referencedColumnName = "id_pagina")
    @ManyToOne(fetch = FetchType.LAZY)
    private TemplatePage templatePage;

    public ContentPage() {
    }

    public ContentPage(String idContent) {
        this.idContent = idContent;
    }

    public String getIdContent() {
        return idContent;
    }

    public void setIdContent(String idContent) {
        this.idContent = idContent;
    }

    public String getLabelPage() {
        return labelPage;
    }

    public void setLabelPage(String labelPage) {
        this.labelPage = labelPage;
    }

    public String getContentPage() {
        return contentPage;
    }

    public void setContentPage(String contentPage) {
        this.contentPage = contentPage;
    }

    public TemplatePage getTemplatePage() {
        return templatePage;
    }

    public void setTemplatePage(TemplatePage templatePage) {
        this.templatePage = templatePage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContent != null ? idContent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContentPage)) {
            return false;
        }
        ContentPage other = (ContentPage) object;
        if ((this.idContent == null && other.idContent != null) || (this.idContent != null && !this.idContent.equals(other.idContent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.ContentPage[ idContent=" + idContent + " ]";
    }
    
}
