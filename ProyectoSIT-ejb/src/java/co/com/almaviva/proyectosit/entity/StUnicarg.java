/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "ST_UNICARG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StUnicarg.findAll", query = "SELECT s FROM StUnicarg s"),
    @NamedQuery(name = "StUnicarg.findByUniConsec", query = "SELECT s FROM StUnicarg s WHERE s.uniConsec = :uniConsec"),
    @NamedQuery(name = "StUnicarg.findByUniCodigo", query = "SELECT s FROM StUnicarg s WHERE s.uniCodigo = :uniCodigo"),
    @NamedQuery(name = "StUnicarg.findByUniNombre", query = "SELECT s FROM StUnicarg s WHERE s.uniNombre = :uniNombre")})
public class StUnicarg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UNI_CONSEC")
    private Long uniConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UNI_CODIGO")
    private String uniCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UNI_NOMBRE")
    private String uniNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uniConsec")
    private List<StContene> stConteneList;

    public StUnicarg() {
    }

    public StUnicarg(Long uniConsec) {
        this.uniConsec = uniConsec;
    }

    public StUnicarg(Long uniConsec, String uniCodigo, String uniNombre) {
        this.uniConsec = uniConsec;
        this.uniCodigo = uniCodigo;
        this.uniNombre = uniNombre;
    }

    public Long getUniConsec() {
        return uniConsec;
    }

    public void setUniConsec(Long uniConsec) {
        this.uniConsec = uniConsec;
    }

    public String getUniCodigo() {
        return uniCodigo;
    }

    public void setUniCodigo(String uniCodigo) {
        this.uniCodigo = uniCodigo;
    }

    public String getUniNombre() {
        return uniNombre;
    }

    public void setUniNombre(String uniNombre) {
        this.uniNombre = uniNombre;
    }

    @XmlTransient
    public List<StContene> getStConteneList() {
        return stConteneList;
    }

    public void setStConteneList(List<StContene> stConteneList) {
        this.stConteneList = stConteneList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uniConsec != null ? uniConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StUnicarg)) {
            return false;
        }
        StUnicarg other = (StUnicarg) object;
        if ((this.uniConsec == null && other.uniConsec != null) || (this.uniConsec != null && !this.uniConsec.equals(other.uniConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StUnicarg[ uniConsec=" + uniConsec + " ]";
    }
    
}
