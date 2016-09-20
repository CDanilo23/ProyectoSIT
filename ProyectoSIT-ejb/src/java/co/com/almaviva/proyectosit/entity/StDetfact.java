/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_DETFACT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StDetfact.findAll", query = "SELECT s FROM StDetfact s"),
    @NamedQuery(name = "StDetfact.findByDetFactXproduct", query = "SELECT s FROM StDetfact s WHERE s.proConsec = :proConsec"),
    @NamedQuery(name = "StDetfact.findByDetConsec", query = "SELECT s FROM StDetfact s WHERE s.detConsec = :detConsec"),
    @NamedQuery(name = "StDetfact.findByDetCantid", query = "SELECT s FROM StDetfact s WHERE s.detCantid = :detCantid"),
    @NamedQuery(name = "StDetfact.findByDetValuni", query = "SELECT s FROM StDetfact s WHERE s.detValuni = :detValuni")})
public class StDetfact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_CONSEC")
    private Long detConsec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_CANTID")
    private long detCantid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_VALUNI")
    private BigDecimal detValuni;
    @JoinColumn(name = "PRO_CONSEC", referencedColumnName = "PRO_CONSEC")
    @ManyToOne(optional = false)
    private StProduct proConsec;
    @JoinColumn(name = "FAC_CONSEC", referencedColumnName = "FAC_CONSEC")
    @ManyToOne(optional = false)
    private StFactura facConsec;

    public StDetfact() {
    }

    public StDetfact(Long detConsec) {
        this.detConsec = detConsec;
    }

    public StDetfact(Long detConsec, long detCantid, BigDecimal detValuni) {
        this.detConsec = detConsec;
        this.detCantid = detCantid;
        this.detValuni = detValuni;
    }

    public Long getDetConsec() {
        return detConsec;
    }

    public void setDetConsec(Long detConsec) {
        this.detConsec = detConsec;
    }

    public long getDetCantid() {
        return detCantid;
    }

    public void setDetCantid(long detCantid) {
        this.detCantid = detCantid;
    }

    public BigDecimal getDetValuni() {
        return detValuni;
    }

    public void setDetValuni(BigDecimal detValuni) {
        this.detValuni = detValuni;
    }

    public StProduct getProConsec() {
        return proConsec;
    }

    public void setProConsec(StProduct proConsec) {
        this.proConsec = proConsec;
    }

    public StFactura getFacConsec() {
        return facConsec;
    }

    public void setFacConsec(StFactura facConsec) {
        this.facConsec = facConsec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detConsec != null ? detConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StDetfact)) {
            return false;
        }
        StDetfact other = (StDetfact) object;
        if ((this.detConsec == null && other.detConsec != null) || (this.detConsec != null && !this.detConsec.equals(other.detConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StDetfact[ detConsec=" + detConsec + " ]";
    }
    
}
