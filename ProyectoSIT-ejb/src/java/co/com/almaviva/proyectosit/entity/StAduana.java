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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_ADUANA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StAduana.findByMaxFechaLevante", query = "SELECT MAX(s.aduFeclevreal) FROM StAduana s WHERE s.carConsec = :carConsec"),
    @NamedQuery(name = "StAduana.findAll", query = "SELECT s FROM StAduana s"),
    @NamedQuery(name = "StAduana.findByAduConsec", query = "SELECT s FROM StAduana s WHERE s.aduConsec = :aduConsec"),
    @NamedQuery(name = "StAduana.findAduBycarConsec", query = "SELECT s FROM StAduana s WHERE s.carConsec = :carConsec"),
    @NamedQuery(name = "StAduana.findByAduFeclevest", query = "SELECT s FROM StAduana s WHERE s.aduFeclevest = :aduFeclevest"),
    @NamedQuery(name = "StAduana.findByAduFeclevreal", query = "SELECT s FROM StAduana s WHERE s.aduFeclevreal = :aduFeclevreal"),
    @NamedQuery(name = "StAduana.findByAduValfob", query = "SELECT s FROM StAduana s WHERE s.aduValfob = :aduValfob"),
    @NamedQuery(name = "StAduana.findByAduCosfle", query = "SELECT s FROM StAduana s WHERE s.aduCosfle = :aduCosfle"),
    @NamedQuery(name = "StAduana.findByAduSeguro", query = "SELECT s FROM StAduana s WHERE s.aduSeguro = :aduSeguro"),
    @NamedQuery(name = "StAduana.findByAduOtrgas", query = "SELECT s FROM StAduana s WHERE s.aduOtrgas = :aduOtrgas"),
    @NamedQuery(name = "StAduana.findByAduCif", query = "SELECT s FROM StAduana s WHERE s.aduCif = :aduCif"),
    @NamedQuery(name = "StAduana.findByAduNumfac", query = "SELECT s FROM StAduana s WHERE s.aduNumfac = :aduNumfac"),
    @NamedQuery(name = "StAduana.findByAduValfac", query = "SELECT s FROM StAduana s WHERE s.aduValfac = :aduValfac"),
    @NamedQuery(name = "StAduana.findByAduNumdec", query = "SELECT s FROM StAduana s WHERE s.aduNumdec = :aduNumdec"),
    @NamedQuery(name = "StAduana.findByAduFecsollic", query = "SELECT s FROM StAduana s WHERE s.aduFecsollic = :aduFecsollic"),
    @NamedQuery(name = "StAduana.findByAduFecprelic", query = "SELECT s FROM StAduana s WHERE s.aduFecprelic = :aduFecprelic"),
    @NamedQuery(name = "StAduana.findByAduFecaprlic", query = "SELECT s FROM StAduana s WHERE s.aduFecaprlic = :aduFecaprlic"),
    @NamedQuery(name = "StAduana.findByAduFecrecultdoc", query = "SELECT s FROM StAduana s WHERE s.aduFecrecultdoc = :aduFecrecultdoc"),
    @NamedQuery(name = "StAduana.findByAduFecreccerori", query = "SELECT s FROM StAduana s WHERE s.aduFecreccerori = :aduFecreccerori"),
    @NamedQuery(name = "StAduana.findByAduFeclib", query = "SELECT s FROM StAduana s WHERE s.aduFeclib = :aduFeclib")})
public class StAduana implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADU_CONSEC")
    @GeneratedValue(generator = "seq_st_aduana", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_st_aduana", sequenceName = "SEQ_ST_ADUANA", initialValue = 1, allocationSize = 1)
    private Long aduConsec;
    @Column(name = "ADU_FECLEVEST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aduFeclevest;
    @Column(name = "ADU_FECLEVREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aduFeclevreal;
    @Column(name = "ADU_VALFOB")
    private BigInteger aduValfob;
    @Column(name = "ADU_COSFLE")
    private BigInteger aduCosfle;
    @Column(name = "ADU_SEGURO")
    private BigInteger aduSeguro;
    @Column(name = "ADU_OTRGAS")
    private BigInteger aduOtrgas;
    @Column(name = "ADU_CIF")
    private BigInteger aduCif;
    @Size(max = 30)
    @Column(name = "ADU_NUMFAC")
    private String aduNumfac;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ADU_VALFAC")
    private BigDecimal aduValfac;
    @Size(max = 30)
    @Column(name = "ADU_NUMDEC")
    private String aduNumdec;
    @Column(name = "ADU_FECSOLLIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aduFecsollic;
    @Column(name = "ADU_FECPRELIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aduFecprelic;
    @Column(name = "ADU_FECAPRLIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aduFecaprlic;
    @Column(name = "ADU_FECRECULTDOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aduFecrecultdoc;
    @Column(name = "ADU_FECRECCERORI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aduFecreccerori;
    @Column(name = "ADU_FECLIB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aduFeclib;
    @JoinColumn(name = "CAR_CONSEC", referencedColumnName = "CAR_CONSEC")
    @ManyToOne(optional = false)
    private StCarga carConsec;

    public StAduana() {
    }

    public StAduana(Long aduConsec) {
        this.aduConsec = aduConsec;
    }

    public Long getAduConsec() {
        return aduConsec;
    }

    public void setAduConsec(Long aduConsec) {
        this.aduConsec = aduConsec;
    }

    public Date getAduFeclevest() {
        return aduFeclevest;
    }

    public void setAduFeclevest(Date aduFeclevest) {
        this.aduFeclevest = aduFeclevest;
    }

    public Date getAduFeclevreal() {
        return aduFeclevreal;
    }

    public void setAduFeclevreal(Date aduFeclevreal) {
        this.aduFeclevreal = aduFeclevreal;
    }

    public BigInteger getAduValfob() {
        return aduValfob;
    }

    public void setAduValfob(BigInteger aduValfob) {
        this.aduValfob = aduValfob;
    }

    public BigInteger getAduCosfle() {
        return aduCosfle;
    }

    public void setAduCosfle(BigInteger aduCosfle) {
        this.aduCosfle = aduCosfle;
    }

    public BigInteger getAduSeguro() {
        return aduSeguro;
    }

    public void setAduSeguro(BigInteger aduSeguro) {
        this.aduSeguro = aduSeguro;
    }

    public BigInteger getAduOtrgas() {
        return aduOtrgas;
    }

    public void setAduOtrgas(BigInteger aduOtrgas) {
        this.aduOtrgas = aduOtrgas;
    }

    public BigInteger getAduCif() {
        return aduCif;
    }

    public void setAduCif(BigInteger aduCif) {
        this.aduCif = aduCif;
    }

    public String getAduNumfac() {
        return aduNumfac;
    }

    public void setAduNumfac(String aduNumfac) {
        this.aduNumfac = aduNumfac;
    }

    public BigDecimal getAduValfac() {
        return aduValfac;
    }

    public void setAduValfac(BigDecimal aduValfac) {
        this.aduValfac = aduValfac;
    }

    public String getAduNumdec() {
        return aduNumdec;
    }

    public void setAduNumdec(String aduNumdec) {
        this.aduNumdec = aduNumdec;
    }

    public Date getAduFecsollic() {
        return aduFecsollic;
    }

    public void setAduFecsollic(Date aduFecsollic) {
        this.aduFecsollic = aduFecsollic;
    }

    public Date getAduFecprelic() {
        return aduFecprelic;
    }

    public void setAduFecprelic(Date aduFecprelic) {
        this.aduFecprelic = aduFecprelic;
    }

    public Date getAduFecaprlic() {
        return aduFecaprlic;
    }

    public void setAduFecaprlic(Date aduFecaprlic) {
        this.aduFecaprlic = aduFecaprlic;
    }

    public Date getAduFecrecultdoc() {
        return aduFecrecultdoc;
    }

    public void setAduFecrecultdoc(Date aduFecrecultdoc) {
        this.aduFecrecultdoc = aduFecrecultdoc;
    }

    public Date getAduFecreccerori() {
        return aduFecreccerori;
    }

    public void setAduFecreccerori(Date aduFecreccerori) {
        this.aduFecreccerori = aduFecreccerori;
    }

    public Date getAduFeclib() {
        return aduFeclib;
    }

    public void setAduFeclib(Date aduFeclib) {
        this.aduFeclib = aduFeclib;
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
        hash += (aduConsec != null ? aduConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StAduana)) {
            return false;
        }
        StAduana other = (StAduana) object;
        if ((this.aduConsec == null && other.aduConsec != null) || (this.aduConsec != null && !this.aduConsec.equals(other.aduConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StAduana[ aduConsec=" + aduConsec + " ]";
    }

}
