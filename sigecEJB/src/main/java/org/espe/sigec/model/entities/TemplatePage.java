/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@Entity
@Table(name = "template_page")
@NamedQueries({
    @NamedQuery(name = "TemplatePage.findAll", query = "SELECT t FROM TemplatePage t")})
public class TemplatePage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "id_pagina")
    private String idPagina;
    @Size(max = 1024)
    @Column(name = "left_principal_cab")
    private String leftPrincipalCab;
    @Size(max = 4096)
    @Column(name = "left_principal_det")
    private String leftPrincipalDet;
    @Size(max = 2048)
    @Column(name = "bottom_left_principal_cab")
    private String bottomLeftPrincipalCab;
    @Size(max = 4096)
    @Column(name = "bottom_left_principal_det")
    private String bottomLeftPrincipalDet;
    @Size(max = 2048)
    @Column(name = "bottom_center_principal_cab")
    private String bottomCenterPrincipalCab;
    @Size(max = 4096)
    @Column(name = "bottom_center_principal_det")
    private String bottomCenterPrincipalDet;
    @Size(max = 2048)
    @Column(name = "bottom_right_principal_cab")
    private String bottomRightPrincipalCab;
    @Size(max = 4096)
    @Column(name = "bottom_right_principal_det")
    private String bottomRightPrincipalDet;
    @OneToMany(mappedBy = "templatePage", fetch = FetchType.LAZY)
    private Collection<ContentPage> contentPageCollection;

    public TemplatePage() {
    }

    public TemplatePage(String idPagina) {
        this.idPagina = idPagina;
    }

    public String getIdPagina() {
        return idPagina;
    }

    public void setIdPagina(String idPagina) {
        this.idPagina = idPagina;
    }

    public String getLeftPrincipalCab() {
        return leftPrincipalCab;
    }

    public void setLeftPrincipalCab(String leftPrincipalCab) {
        this.leftPrincipalCab = leftPrincipalCab;
    }

    public String getLeftPrincipalDet() {
        return leftPrincipalDet;
    }

    public void setLeftPrincipalDet(String leftPrincipalDet) {
        this.leftPrincipalDet = leftPrincipalDet;
    }

    public String getBottomLeftPrincipalCab() {
        return bottomLeftPrincipalCab;
    }

    public void setBottomLeftPrincipalCab(String bottomLeftPrincipalCab) {
        this.bottomLeftPrincipalCab = bottomLeftPrincipalCab;
    }

    public String getBottomLeftPrincipalDet() {
        return bottomLeftPrincipalDet;
    }

    public void setBottomLeftPrincipalDet(String bottomLeftPrincipalDet) {
        this.bottomLeftPrincipalDet = bottomLeftPrincipalDet;
    }

    public String getBottomCenterPrincipalCab() {
        return bottomCenterPrincipalCab;
    }

    public void setBottomCenterPrincipalCab(String bottomCenterPrincipalCab) {
        this.bottomCenterPrincipalCab = bottomCenterPrincipalCab;
    }

    public String getBottomCenterPrincipalDet() {
        return bottomCenterPrincipalDet;
    }

    public void setBottomCenterPrincipalDet(String bottomCenterPrincipalDet) {
        this.bottomCenterPrincipalDet = bottomCenterPrincipalDet;
    }

    public String getBottomRightPrincipalCab() {
        return bottomRightPrincipalCab;
    }

    public void setBottomRightPrincipalCab(String bottomRightPrincipalCab) {
        this.bottomRightPrincipalCab = bottomRightPrincipalCab;
    }

    public String getBottomRightPrincipalDet() {
        return bottomRightPrincipalDet;
    }

    public void setBottomRightPrincipalDet(String bottomRightPrincipalDet) {
        this.bottomRightPrincipalDet = bottomRightPrincipalDet;
    }

    public Collection<ContentPage> getContentPageCollection() {
        return contentPageCollection;
    }

    public void setContentPageCollection(Collection<ContentPage> contentPageCollection) {
        this.contentPageCollection = contentPageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPagina != null ? idPagina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TemplatePage)) {
            return false;
        }
        TemplatePage other = (TemplatePage) object;
        if ((this.idPagina == null && other.idPagina != null) || (this.idPagina != null && !this.idPagina.equals(other.idPagina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.TemplatePage[ idPagina=" + idPagina + " ]";
    }
    
}
