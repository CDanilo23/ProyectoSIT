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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ST_TIPPROD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StTipprod.findAll", query = "SELECT s FROM StTipprod s"),
    @NamedQuery(name = "StTipprod.findByTipprodByClient", query = "SELECT s FROM StTipprod s WHERE s.cliConsec = :cliConsec"),
    @NamedQuery(name = "StTipprod.findByTipConsec", query = "SELECT s FROM StTipprod s WHERE s.tipConsec = :tipConsec"),
    @NamedQuery(name = "StTipprod.findByTipNombre", query = "SELECT s FROM StTipprod s WHERE s.tipNombre = :tipNombre")})
public class StTipprod implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIP_CONSEC")
    private Long tipConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TIP_NOMBRE")
    private String tipNombre;
    @OneToMany(mappedBy = "tipConsec")
    private List<StProduct> stProductList;
    @OneToMany(mappedBy = "tipProduc")
    private List<StCliprov> stCliprovList;
    @JoinColumn(name = "CLI_CONSEC", referencedColumnName = "CLI_CONSEC")
    @ManyToOne(optional = false)
    private StCliente cliConsec;

    public StTipprod() {
    }

    public StTipprod(Long tipConsec) {
        this.tipConsec = tipConsec;
    }

    public StTipprod(Long tipConsec, String tipNombre) {
        this.tipConsec = tipConsec;
        this.tipNombre = tipNombre;
    }

    public Long getTipConsec() {
        return tipConsec;
    }

    public void setTipConsec(Long tipConsec) {
        this.tipConsec = tipConsec;
    }

    public String getTipNombre() {
        return tipNombre;
    }

    public void setTipNombre(String tipNombre) {
        this.tipNombre = tipNombre;
    }

    @XmlTransient
    public List<StProduct> getStProductList() {
        return stProductList;
    }

    public void setStProductList(List<StProduct> stProductList) {
        this.stProductList = stProductList;
    }

    @XmlTransient
    public List<StCliprov> getStCliprovList() {
        return stCliprovList;
    }

    public void setStCliprovList(List<StCliprov> stCliprovList) {
        this.stCliprovList = stCliprovList;
    }

    public StCliente getCliConsec() {
        return cliConsec;
    }

    public void setCliConsec(StCliente cliConsec) {
        this.cliConsec = cliConsec;
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
        if (!(object instanceof StTipprod)) {
            return false;
        }
        StTipprod other = (StTipprod) object;
        if ((this.tipConsec == null && other.tipConsec != null) || (this.tipConsec != null && !this.tipConsec.equals(other.tipConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StTipprod[ tipConsec=" + tipConsec + " ]";
    }
    
}
