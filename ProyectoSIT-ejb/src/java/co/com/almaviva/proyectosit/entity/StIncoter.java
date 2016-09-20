/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "ST_INCOTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StIncoter.findAll", query = "SELECT s FROM StIncoter s"),
    @NamedQuery(name = "StIncoter.findByIncConsec", query = "SELECT s FROM StIncoter s WHERE s.incConsec = :incConsec"),
    @NamedQuery(name = "StIncoter.findByIncCodigo", query = "SELECT s FROM StIncoter s WHERE s.incCodigo = :incCodigo"),
    @NamedQuery(name = "StIncoter.findByIncNombre", query = "SELECT s FROM StIncoter s WHERE s.incNombre = :incNombre"),
    @NamedQuery(name = "StIncoter.findByIncDescri", query = "SELECT s FROM StIncoter s WHERE s.incDescri = :incDescri")})
public class StIncoter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "INC_CONSEC")
    private Long incConsec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INC_CODIGO")
    private BigInteger incCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "INC_NOMBRE")
    private String incNombre;
    @Size(max = 55)
    @Column(name = "INC_DESCRI")
    private String incDescri;
    @OneToMany(mappedBy = "incConsec")
    private List<StFactura> stFacturaList;

    public StIncoter() {
    }

    public StIncoter(Long incConsec) {
        this.incConsec = incConsec;
    }

    public StIncoter(Long incConsec, BigInteger incCodigo, String incNombre) {
        this.incConsec = incConsec;
        this.incCodigo = incCodigo;
        this.incNombre = incNombre;
    }

    public Long getIncConsec() {
        return incConsec;
    }

    public void setIncConsec(Long incConsec) {
        this.incConsec = incConsec;
    }

    public BigInteger getIncCodigo() {
        return incCodigo;
    }

    public void setIncCodigo(BigInteger incCodigo) {
        this.incCodigo = incCodigo;
    }

    public String getIncNombre() {
        return incNombre;
    }

    public void setIncNombre(String incNombre) {
        this.incNombre = incNombre;
    }

    public String getIncDescri() {
        return incDescri;
    }

    public void setIncDescri(String incDescri) {
        this.incDescri = incDescri;
    }

    @XmlTransient
    public List<StFactura> getStFacturaList() {
        return stFacturaList;
    }

    public void setStFacturaList(List<StFactura> stFacturaList) {
        this.stFacturaList = stFacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (incConsec != null ? incConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StIncoter)) {
            return false;
        }
        StIncoter other = (StIncoter) object;
        if ((this.incConsec == null && other.incConsec != null) || (this.incConsec != null && !this.incConsec.equals(other.incConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StIncoter[ incConsec=" + incConsec + " ]";
    }
    
}
