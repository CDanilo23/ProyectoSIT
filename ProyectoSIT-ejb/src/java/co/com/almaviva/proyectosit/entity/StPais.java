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
@Table(name = "ST_PAIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StPais.findAll", query = "SELECT s FROM StPais s"),
    @NamedQuery(name = "StPais.findByPaiId", query = "SELECT s FROM StPais s WHERE s.paiId = :paiId"),
    @NamedQuery(name = "StPais.findByPaiNom", query = "SELECT s FROM StPais s WHERE s.paiNom = :paiNom"),
    @NamedQuery(name = "StPais.findByPaiCodLet", query = "SELECT s FROM StPais s WHERE s.paiCodLet = :paiCodLet")})
public class StPais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAI_ID")
    private Long paiId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PAI_NOM")
    private String paiNom;
    @Size(max = 5)
    @Column(name = "PAI_COD_LET")
    private String paiCodLet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stPais")
    private List<StDpto> stDptoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paiId")
    private List<StCliente> stClienteList;
    @OneToMany(mappedBy = "paiId")
    private List<StProveed> stProveedList;
    @OneToMany(mappedBy = "paiId")
    private List<StDeposit> stDepositList;

    public StPais() {
    }

    public StPais(Long paiId) {
        this.paiId = paiId;
    }

    public StPais(Long paiId, String paiNom) {
        this.paiId = paiId;
        this.paiNom = paiNom;
    }

    public Long getPaiId() {
        return paiId;
    }

    public void setPaiId(Long paiId) {
        this.paiId = paiId;
    }

    public String getPaiNom() {
        return paiNom;
    }

    public void setPaiNom(String paiNom) {
        this.paiNom = paiNom;
    }

    public String getPaiCodLet() {
        return paiCodLet;
    }

    public void setPaiCodLet(String paiCodLet) {
        this.paiCodLet = paiCodLet;
    }

    @XmlTransient
    public List<StDpto> getStDptoList() {
        return stDptoList;
    }

    public void setStDptoList(List<StDpto> stDptoList) {
        this.stDptoList = stDptoList;
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
    public List<StDeposit> getStDepositList() {
        return stDepositList;
    }

    public void setStDepositList(List<StDeposit> stDepositList) {
        this.stDepositList = stDepositList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paiId != null ? paiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StPais)) {
            return false;
        }
        StPais other = (StPais) object;
        if ((this.paiId == null && other.paiId != null) || (this.paiId != null && !this.paiId.equals(other.paiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StPais[ paiId=" + paiId + " ]";
    }
    
}
