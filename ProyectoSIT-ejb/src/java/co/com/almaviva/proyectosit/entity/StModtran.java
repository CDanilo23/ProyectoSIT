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
@Table(name = "ST_MODTRAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StModtran.findAll", query = "SELECT s FROM StModtran s"),
    @NamedQuery(name = "StModtran.findByModConsec", query = "SELECT s FROM StModtran s WHERE s.modConsec = :modConsec"),
    @NamedQuery(name = "StModtran.findByModCodigo", query = "SELECT s FROM StModtran s WHERE s.modCodigo = :modCodigo"),
    @NamedQuery(name = "StModtran.findByModNombre", query = "SELECT s FROM StModtran s WHERE s.modNombre = :modNombre")})
public class StModtran implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOD_CONSEC")
    private Long modConsec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOD_CODIGO")
    private short modCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MOD_NOMBRE")
    private String modNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modConsec")
    private List<StCarga> stCargaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modConsec")
    private List<StRuta> stRutaList;

    public StModtran() {
    }

    public StModtran(Long modConsec) {
        this.modConsec = modConsec;
    }

    public StModtran(Long modConsec, short modCodigo, String modNombre) {
        this.modConsec = modConsec;
        this.modCodigo = modCodigo;
        this.modNombre = modNombre;
    }

    public Long getModConsec() {
        return modConsec;
    }

    public void setModConsec(Long modConsec) {
        this.modConsec = modConsec;
    }

    public short getModCodigo() {
        return modCodigo;
    }

    public void setModCodigo(short modCodigo) {
        this.modCodigo = modCodigo;
    }

    public String getModNombre() {
        return modNombre;
    }

    public void setModNombre(String modNombre) {
        this.modNombre = modNombre;
    }

    @XmlTransient
    public List<StCarga> getStCargaList() {
        return stCargaList;
    }

    public void setStCargaList(List<StCarga> stCargaList) {
        this.stCargaList = stCargaList;
    }

    @XmlTransient
    public List<StRuta> getStRutaList() {
        return stRutaList;
    }

    public void setStRutaList(List<StRuta> stRutaList) {
        this.stRutaList = stRutaList;
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
        if (!(object instanceof StModtran)) {
            return false;
        }
        StModtran other = (StModtran) object;
        if ((this.modConsec == null && other.modConsec != null) || (this.modConsec != null && !this.modConsec.equals(other.modConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StModtran[ modConsec=" + modConsec + " ]";
    }
    
}
