/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_DETCARG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StDetcarg.findAll", query = "SELECT s FROM StDetcarg s"),
    @NamedQuery(name = "StDetcarg.findByDetConsec", query = "SELECT s FROM StDetcarg s WHERE s.detConsec = :detConsec"),
    @NamedQuery(name = "StDetcarg.findByDetFecsalest", query = "SELECT s FROM StDetcarg s WHERE s.detFecsalest = :detFecsalest"),
    @NamedQuery(name = "StDetcarg.findByDetFecrsalreal", query = "SELECT s FROM StDetcarg s WHERE s.detFecrsalreal = :detFecrsalreal"),
    @NamedQuery(name = "StDetcarg.findByDetFecllegest", query = "SELECT s FROM StDetcarg s WHERE s.detFecllegest = :detFecllegest"),
    @NamedQuery(name = "StDetcarg.findByDetFecllegreal", query = "SELECT s FROM StDetcarg s WHERE s.detFecllegreal = :detFecllegreal"),
    @NamedQuery(name = "StDetcarg.findByDetDesmed", query = "SELECT s FROM StDetcarg s WHERE s.detDesmed = :detDesmed"),
    @NamedQuery(name = "StDetcarg.findByDetFacnum", query = "SELECT s FROM StDetcarg s WHERE s.detFacnum = :detFacnum"),
    @NamedQuery(name = "StDetcarg.findByDetFacval", query = "SELECT s FROM StDetcarg s WHERE s.detFacval = :detFacval"),
    @NamedQuery(name = "StDetcarg.findByDetBultos", query = "SELECT s FROM StDetcarg s WHERE s.detBultos = :detBultos"),
    @NamedQuery(name = "StDetcarg.findByDetPesbruto", query = "SELECT s FROM StDetcarg s WHERE s.detPesbruto = :detPesbruto"),
    @NamedQuery(name = "StDetcarg.findByDetPesneto", query = "SELECT s FROM StDetcarg s WHERE s.detPesneto = :detPesneto"),
    @NamedQuery(name = "StDetcarg.findByDetVolumen", query = "SELECT s FROM StDetcarg s WHERE s.detVolumen = :detVolumen")})
public class StDetcarg implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "SEQ_ST_DETCARG", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_ST_DETCARG", sequenceName = "SEQ_ST_DETCARG", allocationSize = 1)
    @Column(name = "DET_CONSEC")
    private Long detConsec;
    @Column(name = "DET_FECSALEST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date detFecsalest;
    @Column(name = "DET_FECRSALREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date detFecrsalreal;
    @Column(name = "DET_FECLLEGEST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date detFecllegest;
    @Column(name = "DET_FECLLEGREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date detFecllegreal;
    @Size(max = 50)
    @Column(name = "DET_DESMED")
    private String detDesmed;
    @Size(max = 50)
    @Column(name = "DET_FACNUM")
    private String detFacnum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DET_FACVAL")
    private BigDecimal detFacval;
    @Column(name = "DET_BULTOS")
    private Long detBultos;
    @Column(name = "DET_PESBRUTO")
    private Long detPesbruto;
    @Column(name = "DET_PESNETO")
    private Long detPesneto;
    @Column(name = "DET_VOLUMEN")
    private Long detVolumen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detConsec")
    private List<StConfirmaciones> stConfirmacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detConsec")
    private List<StTransbo> stTransboList;
    @JoinColumn(name = "TIP_CONSEC", referencedColumnName = "TIP_CONSEC")
    @ManyToOne(optional = false)
    private StTipcarg tipConsec;
    @JoinColumn(name = "RUT_RPCONSEC", referencedColumnName = "RUT_RPCONSEC")
    @ManyToOne
    private StRutprov rutRpconsec;
    @JoinColumn(name = "CLI_TPCCONSEC", referencedColumnName = "CLI_TPCCONSEC")
    @ManyToOne
    private StCliprov cliTpcconsec;
    @JoinColumn(name = "CAR_CONSEC", referencedColumnName = "CAR_CONSEC")
    @ManyToOne(optional = false)
    private StCarga carConsec;
    @OneToMany(mappedBy = "detConsec")
    private List<StLogdetcarg> stLogdetcargList;
    @Column(name = "tie_transporte")
    private Long tie_transporte;
    @JoinColumn(name = "TIP_DIA", referencedColumnName = "TIP_CONSEC")
    @ManyToOne
    private StTipdia tipDia;
    @Column(name = "DET_ESTADO")
    private Long detEstado;

    public StDetcarg() {
    }

    public StDetcarg(Long detConsec) {
        this.detConsec = detConsec;
    }

    public Long getDetConsec() {
        return detConsec;
    }

    public void setDetConsec(Long detConsec) {
        this.detConsec = detConsec;
    }

    public Date getDetFecsalest() {
        return detFecsalest;
    }

    public void setDetFecsalest(Date detFecsalest) {
        this.detFecsalest = detFecsalest;
    }

    public Date getDetFecrsalreal() {
        return detFecrsalreal;
    }

    public void setDetFecrsalreal(Date detFecrsalreal) {
        this.detFecrsalreal = detFecrsalreal;
    }

    public Date getDetFecllegest() {
        return detFecllegest;
    }

    public void setDetFecllegest(Date detFecllegest) {
        this.detFecllegest = detFecllegest;
    }

    public Date getDetFecllegreal() {
        return detFecllegreal;
    }

    public void setDetFecllegreal(Date detFecllegreal) {
        this.detFecllegreal = detFecllegreal;
    }

    public String getDetDesmed() {
        return detDesmed;
    }

    public void setDetDesmed(String detDesmed) {
        this.detDesmed = detDesmed;
    }

    public String getDetFacnum() {
        return detFacnum;
    }

    public void setDetFacnum(String detFacnum) {
        this.detFacnum = detFacnum;
    }

    public BigDecimal getDetFacval() {
        return detFacval;
    }

    public void setDetFacval(BigDecimal detFacval) {
        this.detFacval = detFacval;
    }

    public Long getDetBultos() {
        return detBultos;
    }

    public void setDetBultos(Long detBultos) {
        this.detBultos = detBultos;
    }

    public Long getDetPesbruto() {
        return detPesbruto;
    }

    public void setDetPesbruto(Long detPesbruto) {
        this.detPesbruto = detPesbruto;
    }

    public Long getDetPesneto() {
        return detPesneto;
    }

    public void setDetPesneto(Long detPesneto) {
        this.detPesneto = detPesneto;
    }

    public Long getDetVolumen() {
        return detVolumen;
    }

    public void setDetVolumen(Long detVolumen) {
        this.detVolumen = detVolumen;
    }

    @XmlTransient
    public List<StConfirmaciones> getStConfirmacionesList() {
        return stConfirmacionesList;
    }

    public void setStConfirmacionesList(List<StConfirmaciones> stConfirmacionesList) {
        this.stConfirmacionesList = stConfirmacionesList;
    }

    @XmlTransient
    public List<StTransbo> getStTransboList() {
        return stTransboList;
    }

    public void setStTransboList(List<StTransbo> stTransboList) {
        this.stTransboList = stTransboList;
    }

    public StTipcarg getTipConsec() {
        return tipConsec;
    }

    public void setTipConsec(StTipcarg tipConsec) {
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

    public StCarga getCarConsec() {
        return carConsec;
    }

    public void setCarConsec(StCarga carConsec) {
        this.carConsec = carConsec;
    }

    public Long getTie_transporte() {
        return tie_transporte;
    }

    public void setTie_transporte(Long tie_transporte) {
        this.tie_transporte = tie_transporte;
    }

    public StTipdia getTipDia() {
        return tipDia;
    }

    public void setTipDia(StTipdia tipDia) {
        this.tipDia = tipDia;
    }

    public Long getDetEstado() {
        return detEstado;
    }

    public void setDetEstado(Long detEstado) {
        this.detEstado = detEstado;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detConsec != null ? detConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StDetcarg)) {
            return false;
        }
        StDetcarg other = (StDetcarg) object;
        if ((this.detConsec == null && other.detConsec != null) || (this.detConsec != null && !this.detConsec.equals(other.detConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StDetcarg[ detConsec=" + detConsec + " ]";
    }

    @XmlTransient
    public List<StLogdetcarg> getStLogdetcargList() {
        return stLogdetcargList;
    }

    public void setStLogdetcargList(List<StLogdetcarg> stLogdetcargList) {
        this.stLogdetcargList = stLogdetcargList;
    }
    
    
}
