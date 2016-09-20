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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_TIEMPOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StTiempos.findAll", query = "SELECT s FROM StTiempos s"),
    @NamedQuery(name = "StTiempos.findByTieConsec", query = "SELECT s FROM StTiempos s WHERE s.tieConsec = :tieConsec"),
    @NamedQuery(name = "StTiempos.findByCliTpcConsec", query = "SELECT s FROM StTiempos s WHERE s.cliTpcconsec.tipServic.tipConsec = 5"),
    @NamedQuery(name = "StTiempos.findByCliTpcConsecServicNotGeneral", query = "SELECT s FROM StTiempos s WHERE s.cliTpcconsec.tipServic.tipConsec != 5"),
    @NamedQuery(name = "StTiempos.findByTieTransporte", query = "SELECT s FROM StTiempos s WHERE s.tieTransporte = :tieTransporte"),
    @NamedQuery(name = "StTiempos.findByNumHorHabPreinspeccion", query = "SELECT s FROM StTiempos s WHERE s.numHorHabPreinspeccion = :numHorHabPreinspeccion"),
    @NamedQuery(name = "StTiempos.findByNumHorHabAduana", query = "SELECT s FROM StTiempos s WHERE s.numHorHabAduana = :numHorHabAduana"),
    @NamedQuery(name = "StTiempos.findByNumHorHabOnhand", query = "SELECT s FROM StTiempos s WHERE s.numHorHabOnhand = :numHorHabOnhand")})
public class StTiempos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIE_CONSEC")
    @GeneratedValue(generator = "seq_st_tiempos", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_st_tiempos", sequenceName = "SEQ_ST_TIEMPOS", initialValue = 1, allocationSize = 1)
    private Long tieConsec;
    @Column(name = "TIE_TRANSPORTE")
    private Short tieTransporte;
    @Column(name = "NUM_HOR_HAB_PREINSPECCION")
    private Short numHorHabPreinspeccion;
    @Column(name = "NUM_HOR_HAB_ADUANA")
    private Short numHorHabAduana;
    @Column(name = "NUM_HOR_HAB_ONHAND")
    private Short numHorHabOnhand;
    @JoinColumn(name = "TIP_CONSEC", referencedColumnName = "TIP_CONSEC")
    @ManyToOne
    private StTipdia tipConsec;
    @JoinColumn(name = "RUT_RPCONSEC", referencedColumnName = "RUT_RPCONSEC")
    @ManyToOne
    private StRutprov rutRpconsec;
    @JoinColumn(name = "CLI_TPCCONSEC", referencedColumnName = "CLI_TPCCONSEC")
    @ManyToOne(optional = false)
    private StCliprov cliTpcconsec;

    public StTiempos() {
    }

    public StTiempos(Long tieConsec) {
        this.tieConsec = tieConsec;
    }

    public Long getTieConsec() {
        return tieConsec;
    }

    public void setTieConsec(Long tieConsec) {
        this.tieConsec = tieConsec;
    }

    public Short getTieTransporte() {
        return tieTransporte;
    }

    public void setTieTransporte(Short tieTransporte) {
        this.tieTransporte = tieTransporte;
    }

    public Short getNumHorHabPreinspeccion() {
        return numHorHabPreinspeccion;
    }

    public void setNumHorHabPreinspeccion(Short numHorHabPreinspeccion) {
        this.numHorHabPreinspeccion = numHorHabPreinspeccion;
    }

    public Short getNumHorHabAduana() {
        return numHorHabAduana;
    }

    public void setNumHorHabAduana(Short numHorHabAduana) {
        this.numHorHabAduana = numHorHabAduana;
    }

    public Short getNumHorHabOnhand() {
        return numHorHabOnhand;
    }

    public void setNumHorHabOnhand(Short numHorHabOnhand) {
        this.numHorHabOnhand = numHorHabOnhand;
    }

    public StTipdia getTipConsec() {
        return tipConsec;
    }

    public void setTipConsec(StTipdia tipConsec) {
        this.tipConsec = tipConsec;
    }

    public StRutprov getRutRpconsec() {
        return rutRpconsec;
    }

    public void setRutRpconsec(StRutprov rutRpconsec) {
        this.rutRpconsec = rutRpconsec;
    }

    public StCliprov getCliTpcconsec() {
        return cliTpcconsec;
    }

    public void setCliTpcconsec(StCliprov cliTpcconsec) {
        this.cliTpcconsec = cliTpcconsec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tieConsec != null ? tieConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StTiempos)) {
            return false;
        }
        StTiempos other = (StTiempos) object;
        if ((this.tieConsec == null && other.tieConsec != null) || (this.tieConsec != null && !this.tieConsec.equals(other.tieConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StTiempos[ tieConsec=" + tieConsec + " ]";
    }
    
}
