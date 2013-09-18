/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sgct_acd_hor_cur_per")
@NamedQueries({
    @NamedQuery(name = "HorarioCursoPeriodo.findAll", query = "SELECT h FROM HorarioCursoPeriodo h")})
public class HorarioCursoPeriodo implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "id_curso_periodo")
    private BigDecimal idCursoPeriodo;
    @Size(max = 30)
    @Column(name = "lunes_desde")
    private String lunesDesde;
    @Size(max = 30)
    @Column(name = "martes_desde")
    private String martesDesde;
    @Size(max = 30)
    @Column(name = "miercoles_desde")
    private String miercolesDesde;
    @Size(max = 30)
    @Column(name = "jueves_desde")
    private String juevesDesde;
    @Size(max = 30)
    @Column(name = "viernes_desde")
    private String viernesDesde;
    @Size(max = 30)
    @Column(name = "sabado_desde")
    private String sabadoDesde;
    @Size(max = 30)
    @Column(name = "domingo_desde")
    private String domingoDesde;
    
    
    @Size(max = 30)
    @Column(name = "lunes_hasta")
    private String lunesHasta;
    @Size(max = 30)
    @Column(name = "martes_hasta")
    private String martesHasta;
    @Size(max = 30)
    @Column(name = "miercoles_hasta")
    private String miercolesHasta;
    @Size(max = 30)
    @Column(name = "jueves_hasta")
    private String juevesHasta;
    @Size(max = 30)
    @Column(name = "viernes_hasta")
    private String viernesHasta;
    @Size(max = 30)
    @Column(name = "sabado_hasta")
    private String sabadoHasta;
    @Size(max = 30)
    @Column(name = "domingo_hasta")
    private String domingoHasta;
    
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private CursoPeriodo cursoPeriodo;

    public HorarioCursoPeriodo() {
    }
    

	public String getLunesDesde() {
		return lunesDesde;
	}


	public void setLunesDesde(String lunesDesde) {
		this.lunesDesde = lunesDesde;
	}


	public String getMartesDesde() {
		return martesDesde;
	}


	public void setMartesDesde(String martesDesde) {
		this.martesDesde = martesDesde;
	}


	public String getMiercolesDesde() {
		return miercolesDesde;
	}


	public void setMiercolesDesde(String miercolesDesde) {
		this.miercolesDesde = miercolesDesde;
	}


	public String getJuevesDesde() {
		return juevesDesde;
	}


	public void setJuevesDesde(String juevesDesde) {
		this.juevesDesde = juevesDesde;
	}


	public String getViernesDesde() {
		return viernesDesde;
	}


	public void setViernesDesde(String viernesDesde) {
		this.viernesDesde = viernesDesde;
	}


	public String getSabadoDesde() {
		return sabadoDesde;
	}


	public void setSabadoDesde(String sabadoDesde) {
		this.sabadoDesde = sabadoDesde;
	}


	public String getDomingoDesde() {
		return domingoDesde;
	}


	public void setDomingoDesde(String domingoDesde) {
		this.domingoDesde = domingoDesde;
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public CursoPeriodo getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
	}

	public BigDecimal getIdCursoPeriodo() {
		return idCursoPeriodo;
	}

	public void setIdCursoPeriodo(BigDecimal idCursoPeriodo) {
		this.idCursoPeriodo = idCursoPeriodo;
	}



	public String getLunesHasta() {
		return lunesHasta;
	}



	public void setLunesHasta(String lunesHasta) {
		this.lunesHasta = lunesHasta;
	}



	public String getMartesHasta() {
		return martesHasta;
	}



	public void setMartesHasta(String martesHasta) {
		this.martesHasta = martesHasta;
	}



	public String getMiercolesHasta() {
		return miercolesHasta;
	}



	public void setMiercolesHasta(String miercolesHasta) {
		this.miercolesHasta = miercolesHasta;
	}



	public String getJuevesHasta() {
		return juevesHasta;
	}



	public void setJuevesHasta(String juevesHasta) {
		this.juevesHasta = juevesHasta;
	}



	public String getViernesHasta() {
		return viernesHasta;
	}



	public void setViernesHasta(String viernesHasta) {
		this.viernesHasta = viernesHasta;
	}



	public String getSabadoHasta() {
		return sabadoHasta;
	}



	public void setSabadoHasta(String sabadoHasta) {
		this.sabadoHasta = sabadoHasta;
	}



	public String getDomingoHasta() {
		return domingoHasta;
	}



	public void setDomingoHasta(String domingoHasta) {
		this.domingoHasta = domingoHasta;
	}
    
}
