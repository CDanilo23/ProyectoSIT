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
@Table(name = "ST_TIPSERV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StTipserv.findAll", query = "SELECT s FROM StTipserv s"),
    @NamedQuery(name = "StTipserv.findAllexceptGeneral", query = "SELECT s FROM StTipserv s WHERE s.tipCodigo != 5"),
    @NamedQuery(name = "StTipserv.findByTipConsec", query = "SELECT s FROM StTipserv s WHERE s.tipConsec = :tipConsec"),
    @NamedQuery(name = "StTipserv.findByTipCodigo", query = "SELECT s FROM StTipserv s WHERE s.tipCodigo = :tipCodigo"),
    @NamedQuery(name = "StTipserv.findByTipNombre", query = "SELECT s FROM StTipserv s WHERE s.tipNombre = :tipNombre")})
public class StTipserv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIP_CONSEC")
    private Long tipConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TIP_CODIGO")
    private String tipCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TIP_NOMBRE")
    private String tipNombre;
    @OneToMany(mappedBy = "tipServic")
    private List<StCliprov> stCliprovList;

    public StTipserv() {
    }

    public StTipserv(Long tipConsec) {
        this.tipConsec = tipConsec;
    }

    public StTipserv(Long tipConsec, String tipCodigo, String tipNombre) {
        this.tipConsec = tipConsec;
        this.tipCodigo = tipCodigo;
        this.tipNombre = tipNombre;
    }

    public Long getTipConsec() {
        return tipConsec;
    }

    public void setTipConsec(Long tipConsec) {
        this.tipConsec = tipConsec;
    }

    public String getTipCodigo() {
        return tipCodigo;
    }

    public void setTipCodigo(String tipCodigo) {
        this.tipCodigo = tipCodigo;
    }

    public String getTipNombre() {
        return tipNombre;
    }

    public void setTipNombre(String tipNombre) {
        this.tipNombre = tipNombre;
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
        hash += (tipConsec != null ? tipConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StTipserv)) {
            return false;
        }
        StTipserv other = (StTipserv) object;
        if ((this.tipConsec == null && other.tipConsec != null) || (this.tipConsec != null && !this.tipConsec.equals(other.tipConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StTipserv[ tipConsec=" + tipConsec + " ]";
    }

}
