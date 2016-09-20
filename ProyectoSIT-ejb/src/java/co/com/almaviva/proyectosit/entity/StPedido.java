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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_PEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StPedido.findAll", query = "SELECT s FROM StPedido s"),
    @NamedQuery(name = "StPedido.findByPedConsec", query = "SELECT s FROM StPedido s WHERE s.pedConsec = :pedConsec")})
public class StPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PED_CONSEC")
    private Long pedConsec;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedConsec")
    private List<StCarga> stCargaList;
    @JoinColumn(name = "CLI_CONSEC", referencedColumnName = "CLI_CONSEC")
    @ManyToOne(optional = false)
    private StCliente cliConsec;

    public StPedido() {
    }

    public StPedido(Long pedConsec) {
        this.pedConsec = pedConsec;
    }

    public Long getPedConsec() {
        return pedConsec;
    }

    public void setPedConsec(Long pedConsec) {
        this.pedConsec = pedConsec;
    }

    @XmlTransient
    public List<StCarga> getStCargaList() {
        return stCargaList;
    }

    public void setStCargaList(List<StCarga> stCargaList) {
        this.stCargaList = stCargaList;
    }

    public StCliente getCliConsec() {
        return cliConsec;
    }

    public void setCliConsec(StCliente cliConsec) {
        this.cliConsec = cliConsec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedConsec != null ? pedConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StPedido)) {
            return false;
        }
        StPedido other = (StPedido) object;
        if ((this.pedConsec == null && other.pedConsec != null) || (this.pedConsec != null && !this.pedConsec.equals(other.pedConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StPedido[ pedConsec=" + pedConsec + " ]";
    }
    
}
