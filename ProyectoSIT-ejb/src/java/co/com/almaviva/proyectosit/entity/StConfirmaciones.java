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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ST_CONFIRMACIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StConfirmaciones.findAll", query = "SELECT s FROM StConfirmaciones s"),
    @NamedQuery(name = "StConfirmaciones.findByConConsec", query = "SELECT s FROM StConfirmaciones s WHERE s.conConsec = :conConsec"),
    @NamedQuery(name = "StConfirmaciones.findByConFeccon", query = "SELECT s FROM StConfirmaciones s WHERE s.conFeccon = :conFeccon"),
    @NamedQuery(name = "StConfirmaciones.findByConFecsalreal", query = "SELECT s FROM StConfirmaciones s WHERE s.conFecsalreal = :conFecsalreal"),
    @NamedQuery(name = "StConfirmaciones.findByConFecllegreal", query = "SELECT s FROM StConfirmaciones s WHERE s.conFecllegreal = :conFecllegreal"),
    @NamedQuery(name = "StConfirmaciones.findByConObserv", query = "SELECT s FROM StConfirmaciones s WHERE s.conObserv = :conObserv")})
public class StConfirmaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CON_CONSEC")
    private Long conConsec;
    @Column(name = "CON_FECCON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date conFeccon;
    @Column(name = "CON_FECSALREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date conFecsalreal;
    @Column(name = "CON_FECLLEGREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date conFecllegreal;
    @Size(max = 4000)
    @Column(name = "CON_OBSERV")
    private String conObserv;
    @JoinColumn(name = "DET_CONSEC", referencedColumnName = "DET_CONSEC")
    @ManyToOne(optional = false)
    private StDetcarg detConsec;

    public StConfirmaciones() {
    }

    public StConfirmaciones(Long conConsec) {
        this.conConsec = conConsec;
    }

    public Long getConConsec() {
        return conConsec;
    }

    public void setConConsec(Long conConsec) {
        this.conConsec = conConsec;
    }

    public Date getConFeccon() {
        return conFeccon;
    }

    public void setConFeccon(Date conFeccon) {
        this.conFeccon = conFeccon;
    }

    public Date getConFecsalreal() {
        return conFecsalreal;
    }

    public void setConFecsalreal(Date conFecsalreal) {
        this.conFecsalreal = conFecsalreal;
    }

    public Date getConFecllegreal() {
        return conFecllegreal;
    }

    public void setConFecllegreal(Date conFecllegreal) {
        this.conFecllegreal = conFecllegreal;
    }

    public String getConObserv() {
        return conObserv;
    }

    public void setConObserv(String conObserv) {
        this.conObserv = conObserv;
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
        hash += (conConsec != null ? conConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StConfirmaciones)) {
            return false;
        }
        StConfirmaciones other = (StConfirmaciones) object;
        if ((this.conConsec == null && other.conConsec != null) || (this.conConsec != null && !this.conConsec.equals(other.conConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StConfirmaciones[ conConsec=" + conConsec + " ]";
    }
    
}
