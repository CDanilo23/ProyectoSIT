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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_TRANSBO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StTransbo.findAll", query = "SELECT s FROM StTransbo s"),
    @NamedQuery(name = "StTransbo.findByTraConsec", query = "SELECT s FROM StTransbo s WHERE s.traConsec = :traConsec"),
    @NamedQuery(name = "StTransbo.findByTraFecllegest", query = "SELECT s FROM StTransbo s WHERE s.traFecllegest = :traFecllegest"),
    @NamedQuery(name = "StTransbo.findByTraFecllegreal", query = "SELECT s FROM StTransbo s WHERE s.traFecllegreal = :traFecllegreal"),
    @NamedQuery(name = "StTransbo.findByTraNumdia", query = "SELECT s FROM StTransbo s WHERE s.traNumdia = :traNumdia")})
public class StTransbo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "SEQ_ST_TRANSBO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_ST_TRANSBO", sequenceName = "SEQ_ST_TRANSBO", allocationSize = 1)
    @Column(name = "TRA_CONSEC")
    private Long traConsec;
    @Column(name = "TRA_FECLLEGEST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date traFecllegest;
    @Column(name = "TRA_FECLLEGREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date traFecllegreal;
    @Column(name = "TRA_NUMDIA")
    private Long traNumdia;
    @JoinColumn(name = "PUE_CONSEC", referencedColumnName = "PUE_CONSEC")
    @ManyToOne(optional = false)
    private StPuerto pueConsec;
    @JoinColumn(name = "DET_CONSEC", referencedColumnName = "DET_CONSEC")
    @ManyToOne(optional = false)
    private StDetcarg detConsec;

    public StTransbo() {
    }

    public StTransbo(Long traConsec) {
        this.traConsec = traConsec;
    }

    public Long getTraConsec() {
        return traConsec;
    }

    public void setTraConsec(Long traConsec) {
        this.traConsec = traConsec;
    }

    public Date getTraFecllegest() {
        return traFecllegest;
    }

    public void setTraFecllegest(Date traFecllegest) {
        this.traFecllegest = traFecllegest;
    }

    public Date getTraFecllegreal() {
        return traFecllegreal;
    }

    public void setTraFecllegreal(Date traFecllegreal) {
        this.traFecllegreal = traFecllegreal;
    }

    public Long getTraNumdia() {
        return traNumdia;
    }

    public void setTraNumdia(Long traNumdia) {
        this.traNumdia = traNumdia;
    }

    public StPuerto getPueConsec() {
        return pueConsec;
    }

    public void setPueConsec(StPuerto pueConsec) {
        this.pueConsec = pueConsec;
    }

    public StDetcarg getDetConsec() {
        return detConsec;
    }

    public void setDetConsec(StDetcarg detConsec) {
        this.detConsec = detConsec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traConsec != null ? traConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StTransbo)) {
            return false;
        }
        StTransbo other = (StTransbo) object;
        if ((this.traConsec == null && other.traConsec != null) || (this.traConsec != null && !this.traConsec.equals(other.traConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StTransbo[ traConsec=" + traConsec + " ]";
    }
    
}
