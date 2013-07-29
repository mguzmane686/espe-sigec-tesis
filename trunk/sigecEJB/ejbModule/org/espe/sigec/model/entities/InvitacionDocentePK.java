package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@SuppressWarnings("serial")
@Embeddable
public class InvitacionDocentePK implements Serializable{
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "doc_num_invit")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invprof_seq")
    @SequenceGenerator(name="invprof_seq", sequenceName="invprof_seq", allocationSize = 1)
    private String docNumInvit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prf_id_profesor")
    private int prfIdProfesor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso_periodo")
    private BigDecimal idCursoPeriodo;

    public InvitacionDocentePK() {
    }

    public InvitacionDocentePK(String docNumInvit, int prfIdProfesor, BigDecimal idCursoPeriodo) {
        this.docNumInvit = docNumInvit;
        this.prfIdProfesor = prfIdProfesor;
        this.idCursoPeriodo = idCursoPeriodo;
    }

    public String getDocNumInvit() {
        return docNumInvit;
    }

    public void setDocNumInvit(String docNumInvit) {
        this.docNumInvit = docNumInvit;
    }

    public int getPrfIdProfesor() {
        return prfIdProfesor;
    }

    public void setPrfIdProfesor(int prfIdProfesor) {
        this.prfIdProfesor = prfIdProfesor;
    }

    public BigDecimal getIdCursoPeriodo() {
        return idCursoPeriodo;
    }

    public void setIdCursoPeriodo(BigDecimal idCursoPeriodo) {
        this.idCursoPeriodo = idCursoPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docNumInvit != null ? docNumInvit.hashCode() : 0);
        hash += (int) prfIdProfesor;
        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvitacionDocentePK)) {
            return false;
        }
        InvitacionDocentePK other = (InvitacionDocentePK) object;
        if ((this.docNumInvit == null && other.docNumInvit != null) || (this.docNumInvit != null && !this.docNumInvit.equals(other.docNumInvit))) {
            return false;
        }
        if (this.prfIdProfesor != other.prfIdProfesor) {
            return false;
        }
        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.SgctDocInvitacionPorfPK[ docNumInvit=" + docNumInvit + ", prfIdProfesor=" + prfIdProfesor + ", idCursoPeriodo=" + idCursoPeriodo + " ]";
    }
}
