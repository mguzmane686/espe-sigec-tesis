package org.espe.sigec.model.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Embeddable
public class ContratoProfesorPK implements Serializable{
	@Basic(optional = false)
    @NotNull
    @Column(name = "ctr_num_contrato")
	
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invprof_seq")
//    @SequenceGenerator(name="num_contrato_sec", sequenceName="num_contrato_sec", allocationSize = 1)
    private String numeroContrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "doc_num_invit")
    private String docNumInvit;

    public ContratoProfesorPK() {
    }

    public ContratoProfesorPK(String numeroContrato, String docNumInvit) {
        this.numeroContrato = numeroContrato;
        this.docNumInvit = docNumInvit;
    }

    public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getDocNumInvit() {
        return docNumInvit;
    }

    public void setDocNumInvit(String docNumInvit) {
        this.docNumInvit = docNumInvit;
    }

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString();
	}

    
}
