/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_DEPOSIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StDeposit.findAll", query = "SELECT s FROM StDeposit s"),
    @NamedQuery(name = "StDeposit.findByDepConsec", query = "SELECT s FROM StDeposit s WHERE s.depConsec = :depConsec"),
    @NamedQuery(name = "StDeposit.findByStDepcliePK", query = "select d from StDeposit d, StDepclie dc where dc.stDepcliePK.depConsec = d.depConsec"),
    @NamedQuery(name = "StDeposit.findByDepNombre", query = "SELECT s FROM StDeposit s WHERE s.depNombre = :depNombre"),
    @NamedQuery(name = "StDeposit.findByDepDirecc", query = "SELECT s FROM StDeposit s WHERE s.depDirecc = :depDirecc")})
public class StDeposit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEP_CONSEC")
    private Long depConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DEP_NOMBRE")
    private String depNombre;
    @Size(max = 400)
    @Column(name = "DEP_DIRECC")
    private String depDirecc;
    @JoinColumn(name = "PAI_ID", referencedColumnName = "PAI_ID")
    @ManyToOne
    private StPais paiId;
    @JoinColumns({
        @JoinColumn(insertable = false, updatable = false, name = "PAI_ID", referencedColumnName = "PAI_ID"),
        @JoinColumn(insertable = false, updatable = false, name = "DEP_ID", referencedColumnName = "DEP_ID"),
        @JoinColumn(insertable = false, updatable = false, name = "CIU_ID", referencedColumnName = "CIU_ID")})
    @ManyToOne
    private StCiudad stCiudad;

    public StDeposit() {
    }

    public StDeposit(Long depConsec) {
        this.depConsec = depConsec;
    }

    public StDeposit(Long depConsec, String depNombre) {
        this.depConsec = depConsec;
        this.depNombre = depNombre;
    }

    public Long getDepConsec() {
        return depConsec;
    }

    public void setDepConsec(Long depConsec) {
        this.depConsec = depConsec;
    }

    public String getDepNombre() {
        return depNombre;
    }

    public void setDepNombre(String depNombre) {
        this.depNombre = depNombre;
    }

    public String getDepDirecc() {
        return depDirecc;
    }

    public void setDepDirecc(String depDirecc) {
        this.depDirecc = depDirecc;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depConsec != null ? depConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StDeposit)) {
            return false;
        }
        StDeposit other = (StDeposit) object;
        if ((this.depConsec == null && other.depConsec != null) || (this.depConsec != null && !this.depConsec.equals(other.depConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StDeposit[ depConsec=" + depConsec + " ]";
    }

}
