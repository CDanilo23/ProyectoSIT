/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_CONTENE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StContene.findAll", query = "SELECT s FROM StContene s"),
    @NamedQuery(name = "StContene.findByConConsec", query = "SELECT s FROM StContene s WHERE s.conConsec = :conConsec"),
    @NamedQuery(name = "StContene.findByConNumero", query = "SELECT s FROM StContene s WHERE s.conNumero = :conNumero"),
    @NamedQuery(name = "StContene.findByConVolumen", query = "SELECT s FROM StContene s WHERE s.conVolumen = :conVolumen")})
public class StContene implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "SEQ_ST_CONTENE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_ST_CONTENE", sequenceName = "SEQ_ST_CONTENE", allocationSize = 1)
    @Column(name = "CON_CONSEC")
    private Long conConsec;
    @Column(name = "CON_NUMERO")
    private String conNumero;
    @Column(name = "CON_VOLUMEN")
    private BigInteger conVolumen;
    @JoinColumn(name = "UNI_CONSEC", referencedColumnName = "UNI_CONSEC")
    @ManyToOne(optional = false)
    private StUnicarg uniConsec;
    @JoinColumn(name = "CAR_CONSEC", referencedColumnName = "CAR_CONSEC")
    @ManyToOne(optional = false)
    private StCarga carConsec;

    public StContene() {
    }

    public StContene(Long conConsec) {
        this.conConsec = conConsec;
    }

    public Long getConConsec() {
        return conConsec;
    }

    public void setConConsec(Long conConsec) {
        this.conConsec = conConsec;
    }

    public String getConNumero() {
        return conNumero;
    }

    public void setConNumero(String conNumero) {
        this.conNumero = conNumero;
    }

    public BigInteger getConVolumen() {
        return conVolumen;
    }

    public void setConVolumen(BigInteger conVolumen) {
        this.conVolumen = conVolumen;
    }

    public StUnicarg getUniConsec() {
        return uniConsec;
    }

    public void setUniConsec(StUnicarg uniConsec) {
        this.uniConsec = uniConsec;
    }

    public StCarga getCarConsec() {
        return carConsec;
    }

    public void setCarConsec(StCarga carConsec) {
        this.carConsec = carConsec;
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
        if (!(object instanceof StContene)) {
            return false;
        }
        StContene other = (StContene) object;
        if ((this.conConsec == null && other.conConsec != null) || (this.conConsec != null && !this.conConsec.equals(other.conConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StContene[ conConsec=" + conConsec + " ]";
    }
    
}
