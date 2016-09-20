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
import javax.persistence.JoinColumns;
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
@Table(name = "ST_PROVEED")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StProveed.findAll", query = "SELECT s FROM StProveed s"),
    @NamedQuery(name = "StProveed.findByProConsec", query = "SELECT s FROM StProveed s WHERE s.proConsec = :proConsec"),
    @NamedQuery(name = "StProveed.findByProNombre", query = "SELECT s FROM StProveed s WHERE s.proNombre = :proNombre"),
    @NamedQuery(name = "StProveed.findByProTelefo", query = "SELECT s FROM StProveed s WHERE s.proTelefo = :proTelefo"),
    @NamedQuery(name = "StProveed.findByProDirecc", query = "SELECT s FROM StProveed s WHERE s.proDirecc = :proDirecc")})
public class StProveed implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_CONSEC")
    private Long proConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PRO_NOMBRE")
    private String proNombre;
    @Size(max = 20)
    @Column(name = "PRO_TELEFO")
    private String proTelefo;
    @Size(max = 4000)
    @Column(name = "PRO_DIRECC")
    private String proDirecc;
    @PrimaryKeyJoinColumn(name = "PAI_ID", referencedColumnName = "PAI_ID")
    @ManyToOne
    private StPais paiId;
    @JoinColumns({
        @JoinColumn(name = "PAI_ID", referencedColumnName = "PAI_ID"),
        @JoinColumn(name = "DEP_ID", referencedColumnName = "DEP_ID"),
        @JoinColumn(name = "CIU_ID", referencedColumnName = "CIU_ID")})
    @ManyToOne
    private StCiudad stCiudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proConsec")
    private List<StRutprov> stRutprovList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proConsec")
    private List<StCliprov> stCliprovList;

    public StProveed() {
    }

    public StProveed(Long proConsec) {
        this.proConsec = proConsec;
    }

    public StProveed(Long proConsec, String proNombre) {
        this.proConsec = proConsec;
        this.proNombre = proNombre;
    }

    public Long getProConsec() {
        return proConsec;
    }

    public void setProConsec(Long proConsec) {
        this.proConsec = proConsec;
    }

    public String getProNombre() {
        return proNombre;
    }

    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    public String getProTelefo() {
        return proTelefo;
    }

    public void setProTelefo(String proTelefo) {
        this.proTelefo = proTelefo;
    }

    public String getProDirecc() {
        return proDirecc;
    }

    public void setProDirecc(String proDirecc) {
        this.proDirecc = proDirecc;
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
    public List<StRutprov> getStRutprovList() {
        return stRutprovList;
    }

    public void setStRutprovList(List<StRutprov> stRutprovList) {
        this.stRutprovList = stRutprovList;
    }

    @XmlTransient
    public List<StCliprov> getStCliprovList() {
        return stCliprovList;
    }

    public void setStCliprovList(List<StCliprov> stCliprovList) {
        this.stCliprovList = stCliprovList;
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
        if (!(object instanceof StProveed)) {
            return false;
        }
        StProveed other = (StProveed) object;
        if ((this.proConsec == null && other.proConsec != null) || (this.proConsec != null && !this.proConsec.equals(other.proConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StProveed[ proConsec=" + proConsec + " ]";
    }
    
}
