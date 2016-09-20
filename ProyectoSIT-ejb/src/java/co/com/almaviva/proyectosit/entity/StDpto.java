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
@Table(name = "ST_DPTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StDpto.findAll", query = "SELECT s FROM StDpto s"),
    @NamedQuery(name = "StDpto.findByDepId", query = "SELECT s FROM StDpto s WHERE s.stDptoPK.depId = :depId"),
    @NamedQuery(name = "StDpto.findByDepNom", query = "SELECT s FROM StDpto s WHERE s.depNom = :depNom"),
    @NamedQuery(name = "StDpto.findByPaiId", query = "SELECT s FROM StDpto s WHERE s.stDptoPK.paiId = :paiId")})
public class StDpto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StDptoPK stDptoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DEP_NOM")
    private String depNom;
    @JoinColumn(name = "PAI_ID", referencedColumnName = "PAI_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private StPais stPais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stDpto")
    private List<StCiudad> stCiudadList;

    public StDpto() {
    }

    public StDpto(StDptoPK stDptoPK) {
        this.stDptoPK = stDptoPK;
    }

    public StDpto(StDptoPK stDptoPK, String depNom) {
        this.stDptoPK = stDptoPK;
        this.depNom = depNom;
    }

    public StDpto(long depId, long paiId) {
        this.stDptoPK = new StDptoPK(depId, paiId);
    }

    public StDptoPK getStDptoPK() {
        return stDptoPK;
    }

    public void setStDptoPK(StDptoPK stDptoPK) {
        this.stDptoPK = stDptoPK;
    }

    public String getDepNom() {
        return depNom;
    }

    public void setDepNom(String depNom) {
        this.depNom = depNom;
    }

    public StPais getStPais() {
        return stPais;
    }

    public void setStPais(StPais stPais) {
        this.stPais = stPais;
    }

    @XmlTransient
    public List<StCiudad> getStCiudadList() {
        return stCiudadList;
    }

    public void setStCiudadList(List<StCiudad> stCiudadList) {
        this.stCiudadList = stCiudadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stDptoPK != null ? stDptoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StDpto)) {
            return false;
        }
        StDpto other = (StDpto) object;
        if ((this.stDptoPK == null && other.stDptoPK != null) || (this.stDptoPK != null && !this.stDptoPK.equals(other.stDptoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StDpto[ stDptoPK=" + stDptoPK + " ]";
    }
    
}
