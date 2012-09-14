package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sgct_doc_contrato_prof")
public class ContratoProfesor implements Serializable{
	private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContratoProfesorPK sgctDocContratoProfPK;
    @Column(name = "ctr_fecha_certificacion")
    @Temporal(TemporalType.DATE)
    private Date ctrFechaCertificacion;
    @Size(max = 20)
    @Column(name = "ctr_num_cert_presu")
    private String ctrNumCertPresu;
    @Size(max = 120)
    @Column(name = "ctr_nom_part_pre")
    private String ctrNomPartPre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ctr_valor_part_pre")
    private BigDecimal ctrValorPartPre;
    @Size(max = 256)
    @Column(name = "ctr_lugar_dictarse")
    private String ctrLugarDictarse;
    @JoinColumns({
        @JoinColumn(name = "doc_num_invit", referencedColumnName = "doc_num_invit", insertable = false, updatable = false),
        @JoinColumn(name = "prf_id_profesor", referencedColumnName = "prf_id_profesor", insertable = false, updatable = false),
        @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InvitacionDocente sgctDocInvitacionPorf;
    

    public ContratoProfesor() {
    }

    public ContratoProfesor(ContratoProfesorPK sgctDocContratoProfPK) {
        this.sgctDocContratoProfPK = sgctDocContratoProfPK;
    }

    public ContratoProfesor(int ctrNumContrato, String docNumInvit) {
        this.sgctDocContratoProfPK = new ContratoProfesorPK(ctrNumContrato, docNumInvit);
    }

    public ContratoProfesorPK getContratoProfesorPK() {
        return sgctDocContratoProfPK;
    }

    public void setContratoProfesorPK(ContratoProfesorPK sgctDocContratoProfPK) {
        this.sgctDocContratoProfPK = sgctDocContratoProfPK;
    }

    public Date getCtrFechaCertificacion() {
        return ctrFechaCertificacion;
    }

    public void setCtrFechaCertificacion(Date ctrFechaCertificacion) {
        this.ctrFechaCertificacion = ctrFechaCertificacion;
    }

    public String getCtrNumCertPresu() {
        return ctrNumCertPresu;
    }

    public void setCtrNumCertPresu(String ctrNumCertPresu) {
        this.ctrNumCertPresu = ctrNumCertPresu;
    }

    public String getCtrNomPartPre() {
        return ctrNomPartPre;
    }

    public void setCtrNomPartPre(String ctrNomPartPre) {
        this.ctrNomPartPre = ctrNomPartPre;
    }

    public BigDecimal getCtrValorPartPre() {
        return ctrValorPartPre;
    }

    public void setCtrValorPartPre(BigDecimal ctrValorPartPre) {
        this.ctrValorPartPre = ctrValorPartPre;
    }

    public String getCtrLugarDictarse() {
        return ctrLugarDictarse;
    }

    public void setCtrLugarDictarse(String ctrLugarDictarse) {
        this.ctrLugarDictarse = ctrLugarDictarse;
    }

    public InvitacionDocente getInvitacionDocente() {
        return sgctDocInvitacionPorf;
    }

    public void setInvitacionDocente(InvitacionDocente sgctDocInvitacionPorf) {
        this.sgctDocInvitacionPorf = sgctDocInvitacionPorf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sgctDocContratoProfPK != null ? sgctDocContratoProfPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoProfesor)) {
            return false;
        }
        ContratoProfesor other = (ContratoProfesor) object;
        if ((this.sgctDocContratoProfPK == null && other.sgctDocContratoProfPK != null) || (this.sgctDocContratoProfPK != null && !this.sgctDocContratoProfPK.equals(other.sgctDocContratoProfPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.ContratoProfesor[ sgctDocContratoProfPK=" + sgctDocContratoProfPK + " ]";
    }
}
