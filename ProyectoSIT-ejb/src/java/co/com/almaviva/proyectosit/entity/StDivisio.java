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
@Table(name = "ST_DIVISIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StDivisio.findAll", query = "SELECT s FROM StDivisio s"),
    @NamedQuery(name = "StDivisio.findByDivConsec", query = "SELECT s FROM StDivisio s WHERE s.divConsec = :divConsec"),
    @NamedQuery(name = "StDivisio.findByDivCodigo", query = "SELECT s FROM StDivisio s WHERE s.divCodigo = :divCodigo")})
public class StDivisio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIV_CONSEC")
    private Long divConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DIV_CODIGO")
    private String divCodigo;
    @OneToMany(mappedBy = "divConsec")
    private List<StProduct> stProductList;
    @JoinColumn(name = "CLI_CONSEC", referencedColumnName = "CLI_CONSEC")
    @ManyToOne(optional = false)
    private StCliente cliConsec;

    public StDivisio() {
    }

    public StDivisio(Long divConsec) {
        this.divConsec = divConsec;
    }

    public StDivisio(Long divConsec, String divCodigo) {
        this.divConsec = divConsec;
        this.divCodigo = divCodigo;
    }

    public Long getDivConsec() {
        return divConsec;
    }

    public void setDivConsec(Long divConsec) {
        this.divConsec = divConsec;
    }

    public String getDivCodigo() {
        return divCodigo;
    }

    public void setDivCodigo(String divCodigo) {
        this.divCodigo = divCodigo;
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
        hash += (divConsec != null ? divConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StDivisio)) {
            return false;
        }
        StDivisio other = (StDivisio) object;
        if ((this.divConsec == null && other.divConsec != null) || (this.divConsec != null && !this.divConsec.equals(other.divConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StDivisio[ divConsec=" + divConsec + " ]";
    }
    
}
