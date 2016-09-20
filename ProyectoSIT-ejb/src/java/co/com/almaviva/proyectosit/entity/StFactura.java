/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_FACTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StFactura.findAll", query = "SELECT s FROM StFactura s"),
    @NamedQuery(name = "StFactura.findByFacConsec", query = "SELECT s FROM StFactura s WHERE s.facConsec = :facConsec"),
    @NamedQuery(name = "StFactura.findByFacNumero", query = "SELECT s FROM StFactura s WHERE s.facNumero = :facNumero"),
    @NamedQuery(name = "StFactura.findByFacFecha", query = "SELECT s FROM StFactura s WHERE s.facFecha = :facFecha"),
    @NamedQuery(name = "StFactura.findByValFactura", query = "SELECT s FROM StFactura s WHERE s.valFactura = :valFactura")})
public class StFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "SEQ_ST_FACTURA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_ST_FACTURA", sequenceName = "SEQ_ST_FACTURA", allocationSize = 1)
    @Column(name = "FAC_CONSEC")
    private Long facConsec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_NUMERO")
    private BigInteger facNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date facFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VAL_FACTURA")
    private BigDecimal valFactura;
    @JoinColumn(name = "ORD_CONSEC", referencedColumnName = "ORD_CONSEC")
    @ManyToOne
    private StOrdcomp ordConsec;
    @JoinColumn(name = "MON_CONSEC", referencedColumnName = "MON_CONSEC")
    @ManyToOne(optional = false)
    private StMoneda monConsec;
    @JoinColumn(name = "INC_CONSEC", referencedColumnName = "INC_CONSEC")
    @ManyToOne
    private StIncoter incConsec;
    @JoinColumn(name = "CLI_TPCCONSEC", referencedColumnName = "CLI_TPCCONSEC")
    @ManyToOne(optional = false)
    private StCliprov cliTpcconsec;
    @JoinColumn(name = "CAR_CONSEC", referencedColumnName = "CAR_CONSEC")
    @ManyToOne(optional = false)
    private StCarga carConsec;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facConsec")
    private List<StDetfact> stDetfactList;

    public StFactura() {
    }

    public StFactura(Long facConsec) {
        this.facConsec = facConsec;
    }

    public StFactura(Long facConsec, BigInteger facNumero, Date facFecha, BigDecimal valFactura) {
        this.facConsec = facConsec;
        this.facNumero = facNumero;
        this.facFecha = facFecha;
        this.valFactura = valFactura;
    }

    public Long getFacConsec() {
        return facConsec;
    }

    public void setFacConsec(Long facConsec) {
        this.facConsec = facConsec;
    }

    public BigInteger getFacNumero() {
        return facNumero;
    }

    public void setFacNumero(BigInteger facNumero) {
        this.facNumero = facNumero;
    }

    public Date getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(Date facFecha) {
        this.facFecha = facFecha;
    }

    public BigDecimal getValFactura() {
        return valFactura;
    }

    public void setValFactura(BigDecimal valFactura) {
        this.valFactura = valFactura;
    }

    public StOrdcomp getOrdConsec() {
        return ordConsec;
    }

    public void setOrdConsec(StOrdcomp ordConsec) {
        this.ordConsec = ordConsec;
    }

    public StMoneda getMonConsec() {
        return monConsec;
    }

    public void setMonConsec(StMoneda monConsec) {
        this.monConsec = monConsec;
    }

    public StIncoter getIncConsec() {
        return incConsec;
    }

    public void setIncConsec(StIncoter incConsec) {
        this.incConsec = incConsec;
    }

    public StCliprov getCliTpcconsec() {
        return cliTpcconsec;
    }

    public void setCliTpcconsec(StCliprov cliTpcconsec) {
        this.cliTpcconsec = cliTpcconsec;
    }

    public StCarga getCarConsec() {
        return carConsec;
    }

    public void setCarConsec(StCarga carConsec) {
        this.carConsec = carConsec;
    }

    @XmlTransient
    public List<StDetfact> getStDetfactList() {
        return stDetfactList;
    }

    public void setStDetfactList(List<StDetfact> stDetfactList) {
        this.stDetfactList = stDetfactList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facConsec != null ? facConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StFactura)) {
            return false;
        }
        StFactura other = (StFactura) object;
        if ((this.facConsec == null && other.facConsec != null) || (this.facConsec != null && !this.facConsec.equals(other.facConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StFactura[ facConsec=" + facConsec + " ]";
    }

}
