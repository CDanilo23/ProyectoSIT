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
@Table(name = "ST_ADUESTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StAduesta.findAll", query = "SELECT s FROM StAduesta s"),
    @NamedQuery(name = "StAduesta.findByAduConsec", query = "SELECT s FROM StAduesta s WHERE s.aduConsec = :aduConsec"),
    @NamedQuery(name = "StAduesta.findByAduCodigo", query = "SELECT s FROM StAduesta s WHERE s.aduCodigo = :aduCodigo"),
    @NamedQuery(name = "StAduesta.findByAduDescri", query = "SELECT s FROM StAduesta s WHERE s.aduDescri = :aduDescri")})
public class StAduesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADU_CONSEC")
    private Long aduConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ADU_CODIGO")
    private String aduCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ADU_DESCRI")
    private String aduDescri;
    @OneToMany(mappedBy = "aduConsec")
    private List<StCarga> stCargaList;

    public StAduesta() {
    }

    public StAduesta(Long aduConsec) {
        this.aduConsec = aduConsec;
    }

    public StAduesta(Long aduConsec, String aduCodigo, String aduDescri) {
        this.aduConsec = aduConsec;
        this.aduCodigo = aduCodigo;
        this.aduDescri = aduDescri;
    }

    public Long getAduConsec() {
        return aduConsec;
    }

    public void setAduConsec(Long aduConsec) {
        this.aduConsec = aduConsec;
    }

    public String getAduCodigo() {
        return aduCodigo;
    }

    public void setAduCodigo(String aduCodigo) {
        this.aduCodigo = aduCodigo;
    }

    public String getAduDescri() {
        return aduDescri;
    }

    public void setAduDescri(String aduDescri) {
        this.aduDescri = aduDescri;
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
        hash += (aduConsec != null ? aduConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StAduesta)) {
            return false;
        }
        StAduesta other = (StAduesta) object;
        if ((this.aduConsec == null && other.aduConsec != null) || (this.aduConsec != null && !this.aduConsec.equals(other.aduConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StAduesta[ aduConsec=" + aduConsec + " ]";
    }
    
}
