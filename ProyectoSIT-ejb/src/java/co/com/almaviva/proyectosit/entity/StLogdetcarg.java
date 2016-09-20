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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author egonzalm
 */
@Entity
@Table(name = "ST_LOGDETCARG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StLogdetcarg.findAll", query = "SELECT s FROM StLogdetcarg s"),
    @NamedQuery(name = "StLogdetcarg.findByLogConsec", query = "SELECT s FROM StLogdetcarg s WHERE s.logConsec = :logConsec"),
    @NamedQuery(name = "StLogdetcarg.findByLogTraza", query = "SELECT s FROM StLogdetcarg s WHERE s.logTraza = :logTraza"),
    @NamedQuery(name = "StLogdetcarg.findByLogFecnue", query = "SELECT s FROM StLogdetcarg s WHERE s.logFecnue = :logFecnue"),
    @NamedQuery(name = "StLogdetcarg.findByIndFecha", query = "SELECT s FROM StLogdetcarg s WHERE s.indFecha = :indFecha"),
    @NamedQuery(name = "StLogdetcarg.findByLogObserv", query = "SELECT s FROM StLogdetcarg s WHERE s.logObserv = :logObserv"),
    @NamedQuery(name = "StLogdetcarg.findByLogUsu", query = "SELECT s FROM StLogdetcarg s WHERE s.logUsu = :logUsu")})
public class StLogdetcarg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "SEQ_ST_LOG_DETCARG", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_ST_LOG_DETCARG", sequenceName = "SEQ_ST_LOG_DETCARG", allocationSize = 1)
    @Column(name = "LOG_CONSEC")
    private Long logConsec;
    @Column(name = "LOG_TRAZA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logTraza;
    @Column(name = "LOG_FECNUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logFecnue;
    @Column(name = "IND_FECHA")
    private Short indFecha;
    @Size(max = 4000)
    @Column(name = "LOG_OBSERV")
    private String logObserv;
    @Size(max = 10)
    @Column(name = "LOG_USU")
    private String logUsu;
    @JoinColumn(name = "DET_CONSEC", referencedColumnName = "DET_CONSEC")
    @ManyToOne
    private StDetcarg detConsec;
    @Transient
    String logTrazaFormato;

    public StLogdetcarg() {
    }

    public StLogdetcarg(Long logConsec) {
        this.logConsec = logConsec;
    }

    public Long getLogConsec() {
        return logConsec;
    }

    public void setLogConsec(Long logConsec) {
        this.logConsec = logConsec;
    }

    public Date getLogTraza() {
        return logTraza;
    }

    public void setLogTraza(Date logTraza) {
        this.logTraza = logTraza;
    }

    public Date getLogFecnue() {
        return logFecnue;
    }

    public void setLogFecnue(Date logFecnue) {
        this.logFecnue = logFecnue;
    }

    public Short getIndFecha() {
        return indFecha;
    }

    public void setIndFecha(Short indFecha) {
        this.indFecha = indFecha;
    }

    public String getLogObserv() {
        return logObserv;
    }

    public void setLogObserv(String logObserv) {
        this.logObserv = logObserv;
    }

    public String getLogUsu() {
        return logUsu;
    }

    public void setLogUsu(String logUsu) {
        this.logUsu = logUsu;
    }

    public StDetcarg getDetConsec() {
        return detConsec;
    }

    public void setDetConsec(StDetcarg detConsec) {
        this.detConsec = detConsec;
    }

    public String getLogTrazaFormato() {
        return logTrazaFormato;
    }

    public void setLogTrazaFormato(String logTrazaFormato) {
        this.logTrazaFormato = logTrazaFormato;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logConsec != null ? logConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StLogdetcarg)) {
            return false;
        }
        StLogdetcarg other = (StLogdetcarg) object;
        if ((this.logConsec == null && other.logConsec != null) || (this.logConsec != null && !this.logConsec.equals(other.logConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StLogdetcarg[ logConsec=" + logConsec + " ]";
    }
    
}
