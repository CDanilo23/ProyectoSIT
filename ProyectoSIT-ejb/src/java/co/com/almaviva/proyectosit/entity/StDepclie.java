/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_DEPCLIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StDepclie.findAll", query = "SELECT s FROM StDepclie s"),
    @NamedQuery(name = "StDepclie.findByDepConsec", query = "SELECT s FROM StDepclie s WHERE s.stDepcliePK.depConsec = :depConsec"),
    @NamedQuery(name = "StDepclie.findByCliConsec", query = "SELECT s FROM StDepclie s WHERE s.stDepcliePK.cliConsec = :cliConsec")})
public class StDepclie implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StDepcliePK stDepcliePK;
    @OneToMany(mappedBy = "stDepclie")
    private List<StCarga> stCargaList;
    @JoinColumn(name = "DEP_CONSEC", referencedColumnName = "DEP_CONSEC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private StDeposit stDeposit;
    @JoinColumn(name = "CLI_CONSEC", referencedColumnName = "CLI_CONSEC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private StCliente stCliente;

    public StDepclie() {
    }

    public StDepclie(StDepcliePK stDepcliePK) {
        this.stDepcliePK = stDepcliePK;
    }

    public StDepclie(long depConsec, long cliConsec) {
        this.stDepcliePK = new StDepcliePK(depConsec, cliConsec);
    }

    public StDepcliePK getStDepcliePK() {
        return stDepcliePK;
    }

    public void setStDepcliePK(StDepcliePK stDepcliePK) {
        this.stDepcliePK = stDepcliePK;
    }

    @XmlTransient
    public List<StCarga> getStCargaList() {
        return stCargaList;
    }

    public void setStCargaList(List<StCarga> stCargaList) {
        this.stCargaList = stCargaList;
    }

    public StDeposit getStDeposit() {
        return stDeposit;
    }

    public void setStDeposit(StDeposit stDeposit) {
        this.stDeposit = stDeposit;
    }

    public StCliente getStCliente() {
        return stCliente;
    }

    public void setStCliente(StCliente stCliente) {
        this.stCliente = stCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stDepcliePK != null ? stDepcliePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StDepclie)) {
            return false;
        }
        StDepclie other = (StDepclie) object;
        if ((this.stDepcliePK == null && other.stDepcliePK != null) || (this.stDepcliePK != null && !this.stDepcliePK.equals(other.stDepcliePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StDepclie[ stDepcliePK=" + stDepcliePK + " ]";
    }
    
}
