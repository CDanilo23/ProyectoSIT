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
@Table(name = "ST_DIASEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StDiasema.findAll", query = "SELECT s FROM StDiasema s"),
    @NamedQuery(name = "StDiasema.findByDiaConsec", query = "SELECT s FROM StDiasema s WHERE s.diaConsec = :diaConsec"),
    @NamedQuery(name = "StDiasema.findByDiaNombre", query = "SELECT s FROM StDiasema s WHERE s.diaNombre = :diaNombre")})
public class StDiasema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_CONSEC")
    private Long diaConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DIA_NOMBRE")
    private String diaNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diaConsec")
    private List<StHorario> stHorarioList;

    public StDiasema() {
    }

    public StDiasema(Long diaConsec) {
        this.diaConsec = diaConsec;
    }

    public StDiasema(Long diaConsec, String diaNombre) {
        this.diaConsec = diaConsec;
        this.diaNombre = diaNombre;
    }

    public Long getDiaConsec() {
        return diaConsec;
    }

    public void setDiaConsec(Long diaConsec) {
        this.diaConsec = diaConsec;
    }

    public String getDiaNombre() {
        return diaNombre;
    }

    public void setDiaNombre(String diaNombre) {
        this.diaNombre = diaNombre;
    }

    @XmlTransient
    public List<StHorario> getStHorarioList() {
        return stHorarioList;
    }

    public void setStHorarioList(List<StHorario> stHorarioList) {
        this.stHorarioList = stHorarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diaConsec != null ? diaConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StDiasema)) {
            return false;
        }
        StDiasema other = (StDiasema) object;
        if ((this.diaConsec == null && other.diaConsec != null) || (this.diaConsec != null && !this.diaConsec.equals(other.diaConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StDiasema[ diaConsec=" + diaConsec + " ]";
    }
    
}
