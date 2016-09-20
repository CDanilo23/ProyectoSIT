/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_ORDCOMP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StOrdcomp.findAll", query = "SELECT s FROM StOrdcomp s"),
    @NamedQuery(name = "StOrdcomp.findByOrdConsec", query = "SELECT s FROM StOrdcomp s WHERE s.ordConsec = :ordConsec"),
    @NamedQuery(name = "StOrdcomp.findByOrdFecgen", query = "SELECT s FROM StOrdcomp s WHERE s.ordFecgen = :ordFecgen"),
    @NamedQuery(name = "StOrdcomp.findByOrdNumero", query = "SELECT s FROM StOrdcomp s WHERE s.ordNumero = :ordNumero"),
    @NamedQuery(name = "StOrdcomp.findByOrdFecent", query = "SELECT s FROM StOrdcomp s WHERE s.ordFecent = :ordFecent")})
public class StOrdcomp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "SEQ_ST_ORDCOMP", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_ST_ORDCOMP", sequenceName = "SEQ_ST_ORDCOMP", allocationSize = 1)
    @Column(name = "ORD_CONSEC")
    private Long ordConsec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORD_FECGEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ordFecgen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ORD_NUMERO")
    private String ordNumero;
    @Column(name = "ORD_FECENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ordFecent;
    @OneToMany(mappedBy = "ordConsec")
    private List<StFactura> stFacturaList;
    @JoinColumn(name = "CAR_CONSEC", referencedColumnName = "CAR_CONSEC")
    @ManyToOne(optional = false)
    private StCarga carConsec;

    public StOrdcomp() {
    }

    public StOrdcomp(Long ordConsec) {
        this.ordConsec = ordConsec;
    }

    public StOrdcomp(Long ordConsec, Date ordFecgen, String ordNumero) {
        this.ordConsec = ordConsec;
        this.ordFecgen = ordFecgen;
        this.ordNumero = ordNumero;
    }

    public Long getOrdConsec() {
        return ordConsec;
    }

    public void setOrdConsec(Long ordConsec) {
        this.ordConsec = ordConsec;
    }

    public Date getOrdFecgen() {
        return ordFecgen;
    }

    public void setOrdFecgen(Date ordFecgen) {
        this.ordFecgen = ordFecgen;
    }

    public String getOrdNumero() {
        return ordNumero;
    }

    public void setOrdNumero(String ordNumero) {
        this.ordNumero = ordNumero;
    }

    public Date getOrdFecent() {
        return ordFecent;
    }

    public void setOrdFecent(Date ordFecent) {
        this.ordFecent = ordFecent;
    }

    @XmlTransient
    public List<StFactura> getStFacturaList() {
        return stFacturaList;
    }

    public void setStFacturaList(List<StFactura> stFacturaList) {
        this.stFacturaList = stFacturaList;
    }

    public StCarga getCarConsec() {
        return carConsec;
    }

    public void setCarConsec(StCarga carConsec) {
        this.carConsec = carConsec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordConsec != null ? ordConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StOrdcomp)) {
            return false;
        }
        StOrdcomp other = (StOrdcomp) object;
        if ((this.ordConsec == null && other.ordConsec != null) || (this.ordConsec != null && !this.ordConsec.equals(other.ordConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StOrdcomp[ ordConsec=" + ordConsec + " ]";
    }
    
}
