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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "ST_CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StCliente.findAll", query = "SELECT s FROM StCliente s"),
    @NamedQuery(name = "StCliente.findByCliConsec", query = "SELECT s FROM StCliente s WHERE s.cliConsec = :cliConsec"),
    @NamedQuery(name = "StCliente.findByCliNit", query = "SELECT s FROM StCliente s WHERE s.cliNit = :cliNit"),
    @NamedQuery(name = "StCliente.findByCliDigver", query = "SELECT s FROM StCliente s WHERE s.cliDigver = :cliDigver"),
    @NamedQuery(name = "StCliente.findByCliNombre", query = "SELECT s FROM StCliente s WHERE s.cliNombre = :cliNombre"),
    @NamedQuery(name = "StCliente.findByCliLikeNombre", query = "SELECT s FROM StCliente s WHERE s.cliNombre LIKE :cliNombre"),
    @NamedQuery(name = "StCliente.findByCliNomcom", query = "SELECT s FROM StCliente s WHERE s.cliNomcom = :cliNomcom"),
    @NamedQuery(name = "StCliente.findByCliDirecc", query = "SELECT s FROM StCliente s WHERE s.cliDirecc = :cliDirecc"),
    @NamedQuery(name = "StCliente.findByCliTelefo", query = "SELECT s FROM StCliente s WHERE s.cliTelefo = :cliTelefo"),
    @NamedQuery(name = "StCliente.findByCliNombreLike", query = "SELECT s FROM StCliente s WHERE lower(s.cliNombre) LIKE :cliNombre")})
public class StCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLI_CONSEC")
    private Long cliConsec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLI_NIT")
    private BigInteger cliNit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLI_DIGVER")
    private short cliDigver;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CLI_NOMBRE")
    private String cliNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CLI_NOMCOM")
    private String cliNomcom;
    @Size(max = 100)
    @Column(name = "CLI_DIRECC")
    private String cliDirecc;
    @Size(max = 100)
    @Column(name = "CLI_TELEFO")
    private String cliTelefo;
    @JoinTable(name = "ST_USUCLIE", joinColumns = {
        @JoinColumn(name = "CLI_CONSEC", referencedColumnName = "CLI_CONSEC")}, inverseJoinColumns = {
        @JoinColumn(name = "USU_CONSEC", referencedColumnName = "USU_CONSEC")})
    @ManyToMany
    private List<StUsuario> stUsuarioList;
    @PrimaryKeyJoinColumn(name = "PAI_ID", referencedColumnName = "PAI_ID")
    @ManyToOne(optional = false)
    private StPais paiId;
    @JoinColumns({
        @JoinColumn(name = "PAI_ID", referencedColumnName = "PAI_ID"),
        @JoinColumn(name = "DEP_ID", referencedColumnName = "DEP_ID"),
        @JoinColumn(name = "CIU_ID", referencedColumnName = "CIU_ID")})
    @ManyToOne(optional = false)
    private StCiudad stCiudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliConsec")
    private List<StModelo> stModeloList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliConsec")
    private List<StProduct> stProductList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliConsec")
    private List<StPedido> stPedidoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliConsec")
    private List<StCliprov> stCliprovList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stCliente")
    private List<StDepclie> stDepclieList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliConsec")
    private List<StDivisio> stDivisioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliConsec")
    private List<StCategor> stCategorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliConsec")
    private List<StTipprod> stTipprodList;

    public StCliente() {
    }

    public StCliente(Long cliConsec) {
        this.cliConsec = cliConsec;
    }

    public StCliente(Long cliConsec, BigInteger cliNit, short cliDigver, String cliNombre, String cliNomcom) {
        this.cliConsec = cliConsec;
        this.cliNit = cliNit;
        this.cliDigver = cliDigver;
        this.cliNombre = cliNombre;
        this.cliNomcom = cliNomcom;
    }

    public Long getCliConsec() {
        return cliConsec;
    }

    public void setCliConsec(Long cliConsec) {
        this.cliConsec = cliConsec;
    }

    public BigInteger getCliNit() {
        return cliNit;
    }

    public void setCliNit(BigInteger cliNit) {
        this.cliNit = cliNit;
    }

    public short getCliDigver() {
        return cliDigver;
    }

    public void setCliDigver(short cliDigver) {
        this.cliDigver = cliDigver;
    }

    public String getCliNombre() {
        return cliNombre;
    }

    public void setCliNombre(String cliNombre) {
        this.cliNombre = cliNombre;
    }

    public String getCliNomcom() {
        return cliNomcom;
    }

    public void setCliNomcom(String cliNomcom) {
        this.cliNomcom = cliNomcom;
    }

    public String getCliDirecc() {
        return cliDirecc;
    }

    public void setCliDirecc(String cliDirecc) {
        this.cliDirecc = cliDirecc;
    }

    public String getCliTelefo() {
        return cliTelefo;
    }

    public void setCliTelefo(String cliTelefo) {
        this.cliTelefo = cliTelefo;
    }

    @XmlTransient
    public List<StUsuario> getStUsuarioList() {
        return stUsuarioList;
    }

    public void setStUsuarioList(List<StUsuario> stUsuarioList) {
        this.stUsuarioList = stUsuarioList;
    }

    public StPais getPaiId() {
        return paiId;
    }

    public void setPaiId(StPais paiId) {
        this.paiId = paiId;
    }

    public StCiudad getStCiudad() {
        return stCiudad;
    }

    public void setStCiudad(StCiudad stCiudad) {
        this.stCiudad = stCiudad;
    }

    @XmlTransient
    public List<StModelo> getStModeloList() {
        return stModeloList;
    }

    public void setStModeloList(List<StModelo> stModeloList) {
        this.stModeloList = stModeloList;
    }

    @XmlTransient
    public List<StProduct> getStProductList() {
        return stProductList;
    }

    public void setStProductList(List<StProduct> stProductList) {
        this.stProductList = stProductList;
    }

    @XmlTransient
    public List<StPedido> getStPedidoList() {
        return stPedidoList;
    }

    public void setStPedidoList(List<StPedido> stPedidoList) {
        this.stPedidoList = stPedidoList;
    }

    @XmlTransient
    public List<StCliprov> getStCliprovList() {
        return stCliprovList;
    }

    public void setStCliprovList(List<StCliprov> stCliprovList) {
        this.stCliprovList = stCliprovList;
    }

    @XmlTransient
    public List<StDepclie> getStDepclieList() {
        return stDepclieList;
    }

    public void setStDepclieList(List<StDepclie> stDepclieList) {
        this.stDepclieList = stDepclieList;
    }

    @XmlTransient
    public List<StDivisio> getStDivisioList() {
        return stDivisioList;
    }

    public void setStDivisioList(List<StDivisio> stDivisioList) {
        this.stDivisioList = stDivisioList;
    }

    @XmlTransient
    public List<StCategor> getStCategorList() {
        return stCategorList;
    }

    public void setStCategorList(List<StCategor> stCategorList) {
        this.stCategorList = stCategorList;
    }

    @XmlTransient
    public List<StTipprod> getStTipprodList() {
        return stTipprodList;
    }

    public void setStTipprodList(List<StTipprod> stTipprodList) {
        this.stTipprodList = stTipprodList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliConsec != null ? cliConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StCliente)) {
            return false;
        }
        StCliente other = (StCliente) object;
        if ((this.cliConsec == null && other.cliConsec != null) || (this.cliConsec != null && !this.cliConsec.equals(other.cliConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StCliente[ cliConsec=" + cliConsec + " ]";
    }
    
}
