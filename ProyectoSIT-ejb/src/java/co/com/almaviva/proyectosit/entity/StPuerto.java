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
@Table(name = "ST_PUERTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StPuerto.findAll", query = "SELECT s FROM StPuerto s"),
    @NamedQuery(name = "StPuerto.findByPueConsec", query = "SELECT s FROM StPuerto s WHERE s.pueConsec = :pueConsec"),
    @NamedQuery(name = "StPuerto.findByPueCodigo", query = "SELECT s FROM StPuerto s WHERE s.pueCodigo = :pueCodigo"),
    @NamedQuery(name = "StPuerto.findByPueNombre", query = "SELECT s FROM StPuerto s WHERE s.pueNombre = :pueNombre")})
public class StPuerto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUE_CONSEC")
    private Long pueConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PUE_CODIGO")
    private String pueCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PUE_NOMBRE")
    private String pueNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pueConsec")
    private List<StTransbo> stTransboList;
    @JoinColumn(name = "UBI_CONSEC", referencedColumnName = "UBI_CONSEC")
    @ManyToOne
    private StUbipuer ubiConsec;
    @JoinColumns({
        @JoinColumn(name = "PAI_ID", referencedColumnName = "PAI_ID"),
        @JoinColumn(name = "DEP_ID", referencedColumnName = "DEP_ID"),
        @JoinColumn(name = "CIU_ID", referencedColumnName = "CIU_ID")})
    @ManyToOne(optional = false)
    private StCiudad stCiudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pueOrigen")
    private List<StRuta> stRutaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pueDestino")
    private List<StRuta> stRutaList1;

    public StPuerto() {
    }

    public StPuerto(Long pueConsec) {
        this.pueConsec = pueConsec;
    }

    public StPuerto(Long pueConsec, String pueCodigo, String pueNombre) {
        this.pueConsec = pueConsec;
        this.pueCodigo = pueCodigo;
        this.pueNombre = pueNombre;
    }

    public Long getPueConsec() {
        return pueConsec;
    }

    public void setPueConsec(Long pueConsec) {
        this.pueConsec = pueConsec;
    }

    public String getPueCodigo() {
        return pueCodigo;
    }

    public void setPueCodigo(String pueCodigo) {
        this.pueCodigo = pueCodigo;
    }

    public String getPueNombre() {
        return pueNombre;
    }

    public void setPueNombre(String pueNombre) {
        this.pueNombre = pueNombre;
    }

    @XmlTransient
    public List<StTransbo> getStTransboList() {
        return stTransboList;
    }

    public void setStTransboList(List<StTransbo> stTransboList) {
        this.stTransboList = stTransboList;
    }

    public StUbipuer getUbiConsec() {
        return ubiConsec;
    }

    public void setUbiConsec(StUbipuer ubiConsec) {
        this.ubiConsec = ubiConsec;
    }

    public StCiudad getStCiudad() {
        return stCiudad;
    }

    public void setStCiudad(StCiudad stCiudad) {
        this.stCiudad = stCiudad;
    }

    @XmlTransient
    public List<StRuta> getStRutaList() {
        return stRutaList;
    }

    public void setStRutaList(List<StRuta> stRutaList) {
        this.stRutaList = stRutaList;
    }

    @XmlTransient
    public List<StRuta> getStRutaList1() {
        return stRutaList1;
    }

    public void setStRutaList1(List<StRuta> stRutaList1) {
        this.stRutaList1 = stRutaList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pueConsec != null ? pueConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StPuerto)) {
            return false;
        }
        StPuerto other = (StPuerto) object;
        if ((this.pueConsec == null && other.pueConsec != null) || (this.pueConsec != null && !this.pueConsec.equals(other.pueConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StPuerto[ pueConsec=" + pueConsec + " ]";
    }
    
}
