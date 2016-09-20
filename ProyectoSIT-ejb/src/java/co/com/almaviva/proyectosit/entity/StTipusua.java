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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "ST_TIPUSUA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StTipusua.findAll", query = "SELECT s FROM StTipusua s"),
    @NamedQuery(name = "StTipusua.findByTipConsec", query = "SELECT s FROM StTipusua s WHERE s.tipConsec = :tipConsec"),
    @NamedQuery(name = "StTipusua.findByTipNombre", query = "SELECT s FROM StTipusua s WHERE s.tipNombre = :tipNombre")})
public class StTipusua implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIP_CONSEC")
    private Long tipConsec;
    @Size(max = 100)
    @Column(name = "TIP_NOMBRE")
    private String tipNombre;
    @JoinTable(name = "ST_USUTIPO", joinColumns = {
        @JoinColumn(name = "TIP_CONSEC", referencedColumnName = "TIP_CONSEC")}, inverseJoinColumns = {
        @JoinColumn(name = "USU_CONSEC", referencedColumnName = "USU_CONSEC")})
    @ManyToMany
    private List<StUsuario> stUsuarioList;

    public StTipusua() {
    }

    public StTipusua(Long tipConsec) {
        this.tipConsec = tipConsec;
    }

    public Long getTipConsec() {
        return tipConsec;
    }

    public void setTipConsec(Long tipConsec) {
        this.tipConsec = tipConsec;
    }

    public String getTipNombre() {
        return tipNombre;
    }

    public void setTipNombre(String tipNombre) {
        this.tipNombre = tipNombre;
    }

    @XmlTransient
    public List<StUsuario> getStUsuarioList() {
        return stUsuarioList;
    }

    public void setStUsuarioList(List<StUsuario> stUsuarioList) {
        this.stUsuarioList = stUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipConsec != null ? tipConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StTipusua)) {
            return false;
        }
        StTipusua other = (StTipusua) object;
        if ((this.tipConsec == null && other.tipConsec != null) || (this.tipConsec != null && !this.tipConsec.equals(other.tipConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StTipusua[ tipConsec=" + tipConsec + " ]";
    }
    
}
