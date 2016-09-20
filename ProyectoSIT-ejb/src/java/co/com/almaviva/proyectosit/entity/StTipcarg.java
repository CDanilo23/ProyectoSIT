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
@Table(name = "ST_TIPCARG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StTipcarg.findAll", query = "SELECT s FROM StTipcarg s"),
    @NamedQuery(name = "StTipcarg.findByTipConsec", query = "SELECT s FROM StTipcarg s WHERE s.tipConsec = :tipConsec"),
    @NamedQuery(name = "StTipcarg.findByTipCodigo", query = "SELECT s FROM StTipcarg s WHERE s.tipCodigo = :tipCodigo"),
    @NamedQuery(name = "StTipcarg.findByTipNombre", query = "SELECT s FROM StTipcarg s WHERE s.tipNombre = :tipNombre")})
public class StTipcarg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIP_CONSEC")
    private Long tipConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TIP_CODIGO")
    private String tipCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TIP_NOMBRE")
    private String tipNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipConsec")
    private List<StDetcarg> stDetcargList;

    public StTipcarg() {
    }

    public StTipcarg(Long tipConsec) {
        this.tipConsec = tipConsec;
    }

    public StTipcarg(Long tipConsec, String tipCodigo, String tipNombre) {
        this.tipConsec = tipConsec;
        this.tipCodigo = tipCodigo;
        this.tipNombre = tipNombre;
    }

    public Long getTipConsec() {
        return tipConsec;
    }

    public void setTipConsec(Long tipConsec) {
        this.tipConsec = tipConsec;
    }

    public String getTipCodigo() {
        return tipCodigo;
    }

    public void setTipCodigo(String tipCodigo) {
        this.tipCodigo = tipCodigo;
    }

    public String getTipNombre() {
        return tipNombre;
    }

    public void setTipNombre(String tipNombre) {
        this.tipNombre = tipNombre;
    }

    @XmlTransient
    public List<StDetcarg> getStDetcargList() {
        return stDetcargList;
    }

    public void setStDetcargList(List<StDetcarg> stDetcargList) {
        this.stDetcargList = stDetcargList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipConsec != null ? tipConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StTipcarg)) {
            return false;
        }
        StTipcarg other = (StTipcarg) object;
        if ((this.tipConsec == null && other.tipConsec != null) || (this.tipConsec != null && !this.tipConsec.equals(other.tipConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StTipcarg[ tipConsec=" + tipConsec + " ]";
    }
    
}
