package org.espe.sigec.model.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Embeddable
public class ContratoProfesorPK implements Serializable{
	@Basic(optional = false)
    @NotNull
    @Column(name = "ctr_num_contrato")
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invprof_seq")
    @SequenceGenerator(name="num_contrato_sec", sequenceName="num_contrato_sec", allocationSize = 1)
    private int ctrNumContrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "doc_num_invit")
    private String docNumInvit;

    public ContratoProfesorPK() {
    }

    public ContratoProfesorPK(int ctrNumContrato, String docNumInvit) {
        this.ctrNumContrato = ctrNumContrato;
        this.docNumInvit = docNumInvit;
    }

    public int getCtrNumContrato() {
        return ctrNumContrato;
    }

    public void setCtrNumContrato(int ctrNumContrato) {
        this.ctrNumContrato = ctrNumContrato;
    }

    public String getDocNumInvit() {
        return docNumInvit;
    }

    public void setDocNumInvit(String docNumInvit) {
        this.docNumInvit = docNumInvit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ctrNumContrato;
        hash += (docNumInvit != null ? docNumInvit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoProfesorPK)) {
            return false;
        }
        ContratoProfesorPK other = (ContratoProfesorPK) object;
        if (this.ctrNumContrato != other.ctrNumContrato) {
            return false;
        }
        if ((this.docNumInvit == null && other.docNumInvit != null) || (this.docNumInvit != null && !this.docNumInvit.equals(other.docNumInvit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.ContratoProfesorPK[ ctrNumContrato=" + ctrNumContrato + ", docNumInvit=" + docNumInvit + " ]";
    }
}
