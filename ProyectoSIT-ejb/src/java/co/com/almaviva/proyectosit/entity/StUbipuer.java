/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_UBIPUER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StUbipuer.findAll", query = "SELECT s FROM StUbipuer s"),
    @NamedQuery(name = "StUbipuer.findByUbiConsec", query = "SELECT s FROM StUbipuer s WHERE s.ubiConsec = :ubiConsec"),
    @NamedQuery(name = "StUbipuer.findByUbiCodigo", query = "SELECT s FROM StUbipuer s WHERE s.ubiCodigo = :ubiCodigo"),
    @NamedQuery(name = "StUbipuer.findByUbiNombre", query = "SELECT s FROM StUbipuer s WHERE s.ubiNombre = :ubiNombre")})
public class StUbipuer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UBI_CONSEC")
    private Long ubiConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "UBI_CODIGO")
    private String ubiCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UBI_NOMBRE")
    private String ubiNombre;
    @OneToMany(mappedBy = "ubiConsec")
    private List<StPuerto> stPuertoList;

    public StUbipuer() {
    }

    public StUbipuer(Long ubiConsec) {
        this.ubiConsec = ubiConsec;
    }

    public StUbipuer(Long ubiConsec, String ubiCodigo, String ubiNombre) {
        this.ubiConsec = ubiConsec;
        this.ubiCodigo = ubiCodigo;
        this.ubiNombre = ubiNombre;
    }

    public Long getUbiConsec() {
        return ubiConsec;
    }

    public void setUbiConsec(Long ubiConsec) {
        this.ubiConsec = ubiConsec;
    }

    public String getUbiCodigo() {
        return ubiCodigo;
    }

    public void setUbiCodigo(String ubiCodigo) {
        this.ubiCodigo = ubiCodigo;
    }

    public String getUbiNombre() {
        return ubiNombre;
    }

    public void setUbiNombre(String ubiNombre) {
        this.ubiNombre = ubiNombre;
    }

    @XmlTransient
    public List<StPuerto> getStPuertoList() {
        return stPuertoList;
    }

    public void setStPuertoList(List<StPuerto> stPuertoList) {
        this.stPuertoList = stPuertoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ubiConsec != null ? ubiConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StUbipuer)) {
            return false;
        }
        StUbipuer other = (StUbipuer) object;
        if ((this.ubiConsec == null && other.ubiConsec != null) || (this.ubiConsec != null && !this.ubiConsec.equals(other.ubiConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StUbipuer[ ubiConsec=" + ubiConsec + " ]";
    }
    
}
