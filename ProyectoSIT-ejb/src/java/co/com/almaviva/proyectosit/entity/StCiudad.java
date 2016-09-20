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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "ST_CIUDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StCiudad.findAll", query = "SELECT s FROM StCiudad s"),
    @NamedQuery(name = "StCiudad.findByPaiId", query = "SELECT s FROM StCiudad s WHERE s.stCiudadPK.paiId = :paiId"),
    @NamedQuery(name = "StCiudad.findByDepId", query = "SELECT s FROM StCiudad s WHERE s.stCiudadPK.depId = :depId"),
    @NamedQuery(name = "StCiudad.findByCiuNom", query = "SELECT s FROM StCiudad s WHERE s.ciuNom = :ciuNom"),
    @NamedQuery(name = "StCiudad.findByCiuId", query = "SELECT s FROM StCiudad s WHERE s.stCiudadPK.ciuId = :ciuId"),
    @NamedQuery(name = "StCiudad.findByCiuCodmun", query = "SELECT s FROM StCiudad s WHERE s.ciuCodmun = :ciuCodmun")})
public class StCiudad implements Serializable {
    @OneToMany(mappedBy = "stCiudad")
    private List<StDeposit> stDepositList;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StCiudadPK stCiudadPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CIU_NOM")
    private String ciuNom;
    @Size(max = 5)
    @Column(name = "CIU_CODMUN")
    private String ciuCodmun;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stCiudad")
    private List<StCliente> stClienteList;
    @OneToMany(mappedBy = "stCiudad")
    private List<StProveed> stProveedList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stCiudad")
    private List<StPuerto> stPuertoList;
    @JoinColumns({
        @JoinColumn(name = "DEP_ID", referencedColumnName = "DEP_ID", insertable = false, updatable = false),
        @JoinColumn(name = "PAI_ID", referencedColumnName = "PAI_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private StDpto stDpto;

    public StCiudad() {
    }

    public StCiudad(StCiudadPK stCiudadPK) {
        this.stCiudadPK = stCiudadPK;
    }

    public StCiudad(StCiudadPK stCiudadPK, String ciuNom) {
        this.stCiudadPK = stCiudadPK;
        this.ciuNom = ciuNom;
    }

    public StCiudad(long paiId, long depId, long ciuId) {
        this.stCiudadPK = new StCiudadPK(paiId, depId, ciuId);
    }

    public StCiudadPK getStCiudadPK() {
        return stCiudadPK;
    }

    public void setStCiudadPK(StCiudadPK stCiudadPK) {
        this.stCiudadPK = stCiudadPK;
    }

    public String getCiuNom() {
        return ciuNom;
    }

    public void setCiuNom(String ciuNom) {
        this.ciuNom = ciuNom;
    }

    public String getCiuCodmun() {
        return ciuCodmun;
    }

    public void setCiuCodmun(String ciuCodmun) {
        this.ciuCodmun = ciuCodmun;
    }

    @XmlTransient
    public List<StCliente> getStClienteList() {
        return stClienteList;
    }

    public void setStClienteList(List<StCliente> stClienteList) {
        this.stClienteList = stClienteList;
    }

    @XmlTransient
    public List<StProveed> getStProveedList() {
        return stProveedList;
    }

    public void setStProveedList(List<StProveed> stProveedList) {
        this.stProveedList = stProveedList;
    }

    @XmlTransient
    public List<StPuerto> getStPuertoList() {
        return stPuertoList;
    }

    public void setStPuertoList(List<StPuerto> stPuertoList) {
        this.stPuertoList = stPuertoList;
    }

    public StDpto getStDpto() {
        return stDpto;
    }

    public void setStDpto(StDpto stDpto) {
        this.stDpto = stDpto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stCiudadPK != null ? stCiudadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StCiudad)) {
            return false;
        }
        StCiudad other = (StCiudad) object;
        if ((this.stCiudadPK == null && other.stCiudadPK != null) || (this.stCiudadPK != null && !this.stCiudadPK.equals(other.stCiudadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StCiudad[ stCiudadPK=" + stCiudadPK + " ]";
    }

    @XmlTransient
    public List<StDeposit> getStDepositList() {
        return stDepositList;
    }

    public void setStDepositList(List<StDeposit> stDepositList) {
        this.stDepositList = stDepositList;
    }
    
}
