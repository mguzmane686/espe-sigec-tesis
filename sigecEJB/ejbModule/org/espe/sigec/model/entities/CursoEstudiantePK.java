/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author roberto
 */
@SuppressWarnings("serial")
@Embeddable
public class CursoEstudiantePK implements Serializable {
	
	 @Basic(optional = false)
	    @Column(name = "est_id_estudiante")
	    private int idEstudiante;
	    @Basic(optional = false)
	    @Column(name = "id_curso_periodo")
	    private BigInteger idCursoPeriodo;

	    public CursoEstudiantePK() {
	    }

	    public CursoEstudiantePK(int idEstudiante, BigInteger idCursoPeriodo) {
	        this.idEstudiante = idEstudiante;
	        this.idCursoPeriodo = idCursoPeriodo;
	    }
	    
	    
	    public int getIdEstudiante() {
			return idEstudiante;
		}

		public void setIdEstudiante(int idEstudiante) {
			this.idEstudiante = idEstudiante;
		}

		public BigInteger getIdCursoPeriodo() {
			return idCursoPeriodo;
		}

		public void setIdCursoPeriodo(BigInteger idCursoPeriodo) {
			this.idCursoPeriodo = idCursoPeriodo;
		}

		@Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (int) idEstudiante;
	        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof CursoEstudiantePK)) {
	            return false;
	        }
	        CursoEstudiantePK other = (CursoEstudiantePK) object;
	        if (this.idEstudiante != other.idEstudiante) {
	            return false;
	        }
	        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "org.espe.sigec.model.entities.CursoEstudiantePK[ idEstudiante=" + idEstudiante + ", idCursoPeriodo=" + idCursoPeriodo + " ]";
	    }
	    

}
