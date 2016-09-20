/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_FESTIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StFestivo.findAll", query = "SELECT s FROM StFestivo s"),
    @NamedQuery(name = "StFestivo.findByFesConsec", query = "SELECT s FROM StFestivo s WHERE s.fesConsec = :fesConsec"),
    @NamedQuery(name = "StFestivo.findByFesAnio", query = "SELECT s FROM StFestivo s WHERE s.fesAnio = :fesAnio"),
    @NamedQuery(name = "StFestivo.findByPaiFecha", query = "SELECT s FROM StFestivo s WHERE s.fesFecha = :fesFecha")})
public class StFestivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FES_CONSEC")
    private Long fesConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "FES_ANIO")
    private String fesAnio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FES_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fesFecha;

    public StFestivo() {
    }

    public StFestivo(Long fesConsec) {
        this.fesConsec = fesConsec;
    }

    public StFestivo(Long fesConsec, String fesAnio, Date fesFecha) {
        this.fesConsec = fesConsec;
        this.fesAnio = fesAnio;
        this.fesFecha = fesFecha;
    }

    public Long getFesConsec() {
        return fesConsec;
    }

    public void setFesConsec(Long fesConsec) {
        this.fesConsec = fesConsec;
    }

    public String getFesAnio() {
        return fesAnio;
    }

    public void setFesAnio(String fesAnio) {
        this.fesAnio = fesAnio;
    }

    public Date getFesFecha() {
        return fesFecha;
    }

    public void setFesFecha(Date fesFecha) {
        this.fesFecha = fesFecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fesConsec != null ? fesConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StFestivo)) {
            return false;
        }
        StFestivo other = (StFestivo) object;
        if ((this.fesConsec == null && other.fesConsec != null) || (this.fesConsec != null && !this.fesConsec.equals(other.fesConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StFestivo[ fesConsec=" + fesConsec + " ]";
    }
    
}
