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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "ST_PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StProduct.findAll", query = "SELECT s FROM StProduct s"),
    @NamedQuery(name = "StTipprod.findByProductoXcliente", query = "SELECT s FROM StProduct s WHERE s.cliConsec = :cliConsec"),
    @NamedQuery(name = "StProduct.findByProConsec", query = "SELECT s FROM StProduct s WHERE s.proConsec = :proConsec"),
    @NamedQuery(name = "StProduct.findByProCodigo", query = "SELECT s FROM StProduct s WHERE s.proCodigo = :proCodigo"),
    @NamedQuery(name = "StProduct.findByProDescri", query = "SELECT s FROM StProduct s WHERE s.proDescri = :proDescri")})
public class StProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_CONSEC")
    @GeneratedValue(generator = "seq_st_product", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_st_product", sequenceName = "SEQ_ST_PRODUCT", initialValue = 1, allocationSize = 1)
    private Long proConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRO_CODIGO")
    private String proCodigo;
    @Size(max = 100)
    @Column(name = "PRO_DESCRI")
    private String proDescri;
    @JoinColumn(name = "TIP_CONSEC", referencedColumnName = "TIP_CONSEC")
    @ManyToOne
    private StTipprod tipConsec;
    @JoinColumn(name = "MOD_CONSEC", referencedColumnName = "MOD_CONSEC")
    @ManyToOne(optional = false)
    private StModelo modConsec;
    @JoinColumn(name = "DIV_CONSEC", referencedColumnName = "DIV_CONSEC")
    @ManyToOne
    private StDivisio divConsec;
    @JoinColumn(name = "CLI_CONSEC", referencedColumnName = "CLI_CONSEC")
    @ManyToOne(optional = false)
    private StCliente cliConsec;
    @JoinColumn(name = "CAT_CONSEC", referencedColumnName = "CAT_CONSEC")
    @ManyToOne(optional = false)
    private StCategor catConsec;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "proConsec")
    private List<StDetfact> stDetfactList;

    public StProduct() {
    }

    public StProduct(Long proConsec) {
        this.proConsec = proConsec;
    }

    public StProduct(Long proConsec, String proCodigo) {
        this.proConsec = proConsec;
        this.proCodigo = proCodigo;
    }

    public Long getProConsec() {
        return proConsec;
    }

    public void setProConsec(Long proConsec) {
        this.proConsec = proConsec;
    }

    public String getProCodigo() {
        return proCodigo;
    }

    public void setProCodigo(String proCodigo) {
        this.proCodigo = proCodigo;
    }

    public String getProDescri() {
        return proDescri;
    }

    public void setProDescri(String proDescri) {
        this.proDescri = proDescri;
    }

    public StTipprod getTipConsec() {
        return tipConsec;
    }

    public void setTipConsec(StTipprod tipConsec) {
        this.tipConsec = tipConsec;
    }

    public StModelo getModConsec() {
        return modConsec;
    }

    public void setModConsec(StModelo modConsec) {
        this.modConsec = modConsec;
    }

    public StDivisio getDivConsec() {
        return divConsec;
    }

    public void setDivConsec(StDivisio divConsec) {
        this.divConsec = divConsec;
    }

    public StCliente getCliConsec() {
        return cliConsec;
    }

    public void setCliConsec(StCliente cliConsec) {
        this.cliConsec = cliConsec;
    }

    public StCategor getCatConsec() {
        return catConsec;
    }

    public void setCatConsec(StCategor catConsec) {
        this.catConsec = catConsec;
    }

    @XmlTransient
    public List<StDetfact> getStDetfactList() {
        return stDetfactList;
    }

    public void setStDetfactList(List<StDetfact> stDetfactList) {
        this.stDetfactList = stDetfactList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proConsec != null ? proConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StProduct)) {
            return false;
        }
        StProduct other = (StProduct) object;
        if ((this.proConsec == null && other.proConsec != null) || (this.proConsec != null && !this.proConsec.equals(other.proConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StProduct[ proConsec=" + proConsec + " ]";
    }

}
