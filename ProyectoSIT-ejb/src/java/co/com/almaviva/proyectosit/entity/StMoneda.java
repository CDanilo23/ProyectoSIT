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
@Table(name = "ST_MONEDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StMoneda.findAll", query = "SELECT s FROM StMoneda s"),
    @NamedQuery(name = "StMoneda.findByMonConsec", query = "SELECT s FROM StMoneda s WHERE s.monConsec = :monConsec"),
    @NamedQuery(name = "StMoneda.findByMonNombre", query = "SELECT s FROM StMoneda s WHERE s.monNombre = :monNombre"),
    @NamedQuery(name = "StMoneda.findByMonCodiso", query = "SELECT s FROM StMoneda s WHERE s.monCodiso = :monCodiso")})
public class StMoneda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MON_CONSEC")
    private Long monConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MON_NOMBRE")
    private String monNombre;
    @Size(max = 50)
    @Column(name = "MON_CODISO")
    private String monCodiso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monConsec")
    private List<StFactura> stFacturaList;

    public StMoneda() {
    }

    public StMoneda(Long monConsec) {
        this.monConsec = monConsec;
    }

    public StMoneda(Long monConsec, String monNombre) {
        this.monConsec = monConsec;
        this.monNombre = monNombre;
    }

    public Long getMonConsec() {
        return monConsec;
    }

    public void setMonConsec(Long monConsec) {
        this.monConsec = monConsec;
    }

    public String getMonNombre() {
        return monNombre;
    }

    public void setMonNombre(String monNombre) {
        this.monNombre = monNombre;
    }

    public String getMonCodiso() {
        return monCodiso;
    }

    public void setMonCodiso(String monCodiso) {
        this.monCodiso = monCodiso;
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
        hash += (monConsec != null ? monConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StMoneda)) {
            return false;
        }
        StMoneda other = (StMoneda) object;
        if ((this.monConsec == null && other.monConsec != null) || (this.monConsec != null && !this.monConsec.equals(other.monConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StMoneda[ monConsec=" + monConsec + " ]";
    }
    
}
