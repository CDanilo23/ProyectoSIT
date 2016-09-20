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
@Table(name = "ST_CATEGOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StCategor.findAll", query = "SELECT s FROM StCategor s"),
    @NamedQuery(name = "StCategor.findByCatXcliente", query = "SELECT s FROM StCategor s WHERE s.cliConsec = :cliConsec"),
    @NamedQuery(name = "StCategor.findByCatConsec", query = "SELECT s FROM StCategor s WHERE s.catConsec = :catConsec"),
    @NamedQuery(name = "StCategor.findByCatCodigo", query = "SELECT s FROM StCategor s WHERE s.catCodigo = :catCodigo")})
public class StCategor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAT_CONSEC")
    private Long catConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CAT_CODIGO")
    private String catCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catConsec")
    private List<StProduct> stProductList;
    @JoinColumn(name = "CLI_CONSEC", referencedColumnName = "CLI_CONSEC")
    @ManyToOne(optional = false)
    private StCliente cliConsec;

    public StCategor() {
    }

    public StCategor(Long catConsec) {
        this.catConsec = catConsec;
    }

    public StCategor(Long catConsec, String catCodigo) {
        this.catConsec = catConsec;
        this.catCodigo = catCodigo;
    }

    public Long getCatConsec() {
        return catConsec;
    }

    public void setCatConsec(Long catConsec) {
        this.catConsec = catConsec;
    }

    public String getCatCodigo() {
        return catCodigo;
    }

    public void setCatCodigo(String catCodigo) {
        this.catCodigo = catCodigo;
    }

    @XmlTransient
    public List<StProduct> getStProductList() {
        return stProductList;
    }

    public void setStProductList(List<StProduct> stProductList) {
        this.stProductList = stProductList;
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
        hash += (catConsec != null ? catConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StCategor)) {
            return false;
        }
        StCategor other = (StCategor) object;
        if ((this.catConsec == null && other.catConsec != null) || (this.catConsec != null && !this.catConsec.equals(other.catConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StCategor[ catConsec=" + catConsec + " ]";
    }
    
}
