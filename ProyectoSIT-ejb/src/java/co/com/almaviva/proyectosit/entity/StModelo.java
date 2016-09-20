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
@Table(name = "ST_MODELO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StModelo.findAll", query = "SELECT s FROM StModelo s"),
    @NamedQuery(name = "StModelo.findByModeloByClient", query = "SELECT s FROM StModelo s WHERE s.cliConsec = :cliConsec"),
    @NamedQuery(name = "StModelo.findByModConsec", query = "SELECT s FROM StModelo s WHERE s.modConsec = :modConsec"),
    @NamedQuery(name = "StModelo.findByModCodigo", query = "SELECT s FROM StModelo s WHERE s.modCodigo = :modCodigo")})
public class StModelo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOD_CONSEC")
    private Long modConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MOD_CODIGO")
    private String modCodigo;
    @JoinColumn(name = "CLI_CONSEC", referencedColumnName = "CLI_CONSEC")
    @ManyToOne(optional = false)
    private StCliente cliConsec;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modConsec")
    private List<StProduct> stProductList;

    public StModelo() {
    }

    public StModelo(Long modConsec) {
        this.modConsec = modConsec;
    }

    public StModelo(Long modConsec, String modCodigo) {
        this.modConsec = modConsec;
        this.modCodigo = modCodigo;
    }

    public Long getModConsec() {
        return modConsec;
    }

    public void setModConsec(Long modConsec) {
        this.modConsec = modConsec;
    }

    public String getModCodigo() {
        return modCodigo;
    }

    public void setModCodigo(String modCodigo) {
        this.modCodigo = modCodigo;
    }

    public StCliente getCliConsec() {
        return cliConsec;
    }

    public void setCliConsec(StCliente cliConsec) {
        this.cliConsec = cliConsec;
    }

    @XmlTransient
    public List<StProduct> getStProductList() {
        return stProductList;
    }

    public void setStProductList(List<StProduct> stProductList) {
        this.stProductList = stProductList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modConsec != null ? modConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StModelo)) {
            return false;
        }
        StModelo other = (StModelo) object;
        if ((this.modConsec == null && other.modConsec != null) || (this.modConsec != null && !this.modConsec.equals(other.modConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StModelo[ modConsec=" + modConsec + " ]";
    }
    
}
