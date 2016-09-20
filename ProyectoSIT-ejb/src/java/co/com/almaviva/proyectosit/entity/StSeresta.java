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
@Table(name = "ST_SERESTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StSeresta.findAll", query = "SELECT s FROM StSeresta s"),
    @NamedQuery(name = "StSeresta.findBySerConsec", query = "SELECT s FROM StSeresta s WHERE s.serConsec = :serConsec"),
    @NamedQuery(name = "StSeresta.findBySerCodigo", query = "SELECT s FROM StSeresta s WHERE s.serCodigo = :serCodigo"),
    @NamedQuery(name = "StSeresta.findBySerNombre", query = "SELECT s FROM StSeresta s WHERE s.serNombre = :serNombre")})
public class StSeresta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SER_CONSEC")
    private Long serConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SER_CODIGO")
    private String serCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SER_NOMBRE")
    private String serNombre;
    @OneToMany(mappedBy = "serConsec")
    private List<StCarga> stCargaList;

    public StSeresta() {
    }

    public StSeresta(Long serConsec) {
        this.serConsec = serConsec;
    }

    public StSeresta(Long serConsec, String serCodigo, String serNombre) {
        this.serConsec = serConsec;
        this.serCodigo = serCodigo;
        this.serNombre = serNombre;
    }

    public Long getSerConsec() {
        return serConsec;
    }

    public void setSerConsec(Long serConsec) {
        this.serConsec = serConsec;
    }

    public String getSerCodigo() {
        return serCodigo;
    }

    public void setSerCodigo(String serCodigo) {
        this.serCodigo = serCodigo;
    }

    public String getSerNombre() {
        return serNombre;
    }

    public void setSerNombre(String serNombre) {
        this.serNombre = serNombre;
    }

    @XmlTransient
    public List<StCarga> getStCargaList() {
        return stCargaList;
    }

    public void setStCargaList(List<StCarga> stCargaList) {
        this.stCargaList = stCargaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serConsec != null ? serConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StSeresta)) {
            return false;
        }
        StSeresta other = (StSeresta) object;
        if ((this.serConsec == null && other.serConsec != null) || (this.serConsec != null && !this.serConsec.equals(other.serConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StSeresta[ serConsec=" + serConsec + " ]";
    }
    
}
