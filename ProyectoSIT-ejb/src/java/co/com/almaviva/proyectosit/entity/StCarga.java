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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "ST_CARGA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StCarga.findAll", query = "SELECT s FROM StCarga s"),
    @NamedQuery(name = "StCarga.findByCarConsec", query = "SELECT s FROM StCarga s WHERE s.carConsec = :carConsec"),
    @NamedQuery(name = "StCarga.findByCarFecope", query = "SELECT s FROM StCarga s WHERE s.carFecope = :carFecope"),
    @NamedQuery(name = "StCarga.findByCarVolumen", query = "SELECT s FROM StCarga s WHERE s.carVolumen = :carVolumen"),
    @NamedQuery(name = "StCarga.findByCarBultos", query = "SELECT s FROM StCarga s WHERE s.carBultos = :carBultos"),
    @NamedQuery(name = "StCarga.findByCarPesbruto", query = "SELECT s FROM StCarga s WHERE s.carPesbruto = :carPesbruto"),
    @NamedQuery(name = "StCarga.findByCarPesneto", query = "SELECT s FROM StCarga s WHERE s.carPesneto = :carPesneto"),
    @NamedQuery(name = "StCarga.findByCarDo", query = "SELECT s FROM StCarga s WHERE s.carDo = :carDo"),
    @NamedQuery(name = "StCarga.findByCarDoalma", query = "SELECT s FROM StCarga s WHERE s.carDoalma = :carDoalma"),
    @NamedQuery(name = "StCarga.findByCarNumdoctra", query = "SELECT s FROM StCarga s WHERE s.carNumdoctra = :carNumdoctra"),
    @NamedQuery(name = "StCarga.findByCarFecdoctra", query = "SELECT s FROM StCarga s WHERE s.carFecdoctra = :carFecdoctra"),
    @NamedQuery(name = "StCarga.findByDepFecinsest", query = "SELECT s FROM StCarga s WHERE s.depFecinsest = :depFecinsest"),
    @NamedQuery(name = "StCarga.findByDepFecinsreal", query = "SELECT s FROM StCarga s WHERE s.depFecinsreal = :depFecinsreal"),
    @NamedQuery(name = "StCarga.findByDepFeconhandest", query = "SELECT s FROM StCarga s WHERE s.depFeconhandest = :depFeconhandest"),
    @NamedQuery(name = "StCarga.findByDepFeconhandreal", query = "SELECT s FROM StCarga s WHERE s.depFeconhandreal = :depFeconhandreal"),
    @NamedQuery(name = "StCarga.findByDepFecaduanaest", query = "SELECT s FROM StCarga s WHERE s.depFecaduanaest = :depFecaduanaest"),
    @NamedQuery(name = "StCarga.findByDepFecaduanareal", query = "SELECT s FROM StCarga s WHERE s.depFecaduanareal = :depFecaduanareal"),
    @NamedQuery(name = "StCarga.findByDepNumfacdep", query = "SELECT s FROM StCarga s WHERE s.depNumfacdep = :depNumfacdep"),
    @NamedQuery(name = "StCarga.findByDepValfacdep", query = "SELECT s FROM StCarga s WHERE s.depValfacdep = :depValfacdep"),
    @NamedQuery(name = "StCarga.findByDepInddoctra", query = "SELECT s FROM StCarga s WHERE s.depInddoctra = :depInddoctra"),
    @NamedQuery(name = "StCarga.findByDepIndfac", query = "SELECT s FROM StCarga s WHERE s.depIndfac = :depIndfac"),
    @NamedQuery(name = "StCarga.findByDepIndser", query = "SELECT s FROM StCarga s WHERE s.depIndser = :depIndser"),
    @NamedQuery(name = "StCarga.findByDepIndbl", query = "SELECT s FROM StCarga s WHERE s.depIndbl = :depIndbl"),
    @NamedQuery(name = "StCarga.findByDepIndfacori", query = "SELECT s FROM StCarga s WHERE s.depIndfacori = :depIndfacori"),
    @NamedQuery(name = "StCarga.findByDepIndcerori", query = "SELECT s FROM StCarga s WHERE s.depIndcerori = :depIndcerori"),
    @NamedQuery(name = "StCarga.findByDepIndfle", query = "SELECT s FROM StCarga s WHERE s.depIndfle = :depIndfle")})
public class StCarga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAR_CONSEC")
    private Long carConsec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAR_FECOPE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date carFecope;
    @Column(name = "CAR_VOLUMEN")
    private Long carVolumen;
    @Column(name = "CAR_BULTOS")
    private Long carBultos;
    @Column(name = "CAR_PESBRUTO")
    private Long carPesbruto;
    @Column(name = "CAR_PESNETO")
    private Long carPesneto;
    @Column(name = "CAR_DO")
    private Long carDo;
    @Column(name = "CAR_DOALMA")
    private Long carDoalma;
    @Size(max = 20)
    @Column(name = "CAR_NUMDOCTRA")
    private String carNumdoctra;
    @Column(name = "CAR_FECDOCTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date carFecdoctra;
    @Column(name = "CAR_FECINSEST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depFecinsest;
    @Column(name = "CAR_FECINSREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depFecinsreal;
    @Column(name = "CAR_FECONHANDEST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depFeconhandest;
    @Column(name = "CAR_FECONHANDREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depFeconhandreal;
    @Column(name = "CAR_FECADUANAEST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depFecaduanaest;
    @Column(name = "CAR_FECADUANAREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depFecaduanareal;
    @Size(max = 50)
    @Column(name = "CAR_NUMFACDEP")
    private String depNumfacdep;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CAR_VALFACDEP")
    private BigDecimal depValfacdep;
    @Column(name = "CAR_INDDOCTRA")
    private Short depInddoctra;
    @Column(name = "CAR_INDFAC")
    private Short depIndfac;
    @Column(name = "CAR_INDSER")
    private Short depIndser;
    @Column(name = "CAR_INDBL")
    private Short depIndbl;
    @Column(name = "CAR_INDFACORI")
    private Short depIndfacori;
    @Column(name = "CAR_INDCERORI")
    private Short depIndcerori;
    @Column(name = "CAR_INDFLE")
    private Short depIndfle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carConsec")
    private List<StAduana> stAduanaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carConsec")
    private List<StFactura> stFacturaList;
    @JoinColumn(name = "TIP_OPERA", referencedColumnName = "TIP_CONSEC")
    @ManyToOne(optional = false)
    private StTipoper tipOpera;
    @JoinColumn(name = "TIP_CONSEC", referencedColumnName = "TIP_CONSEC")
    @ManyToOne(optional = false)
    private StTipemba tipConsec;
    @JoinColumn(name = "SER_CONSEC", referencedColumnName = "SER_CONSEC")
    @ManyToOne
    private StSeresta serConsec;
    @JoinColumn(name = "PED_CONSEC", referencedColumnName = "PED_CONSEC")
    @ManyToOne(optional = false)
    private StPedido pedConsec;
    @JoinColumn(name = "MOD_CONSEC", referencedColumnName = "MOD_CONSEC")
    @ManyToOne(optional = false)
    private StModtran modConsec;
    @JoinColumns({
        @JoinColumn(name = "DEP_CONSEC", referencedColumnName = "DEP_CONSEC"),
        @JoinColumn(name = "CLI_CONSEC", referencedColumnName = "CLI_CONSEC")})
    @ManyToOne
    private StDepclie stDepclie;
    @JoinColumn(name = "CLI_TPCCONSEC", referencedColumnName = "CLI_TPCCONSEC")
    @ManyToOne
    private StCliprov cliTpcconsec;
    @JoinColumn(name = "ADU_CONSEC", referencedColumnName = "ADU_CONSEC")
    @ManyToOne
    private StAduesta aduConsec;
    @Column(name = "CAR_ESTADO")
    private BigDecimal carEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carConsec")
    private List<StDetcarg> stDetcargList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carConsec")
    private List<StContene> stConteneList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carConsec")
    private List<StOrdcomp> stOrdcompList;

    public StCarga() {
    }

    public StCarga(Long carConsec) {
        this.carConsec = carConsec;
    }

    public StCarga(Long carConsec, Date carFecope) {
        this.carConsec = carConsec;
        this.carFecope = carFecope;
    }

    public Long getCarConsec() {
        return carConsec;
    }

    public void setCarConsec(Long carConsec) {
        this.carConsec = carConsec;
    }

    public Date getCarFecope() {
        return carFecope;
    }

    public void setCarFecope(Date carFecope) {
        this.carFecope = carFecope;
    }

    public Long getCarVolumen() {
        return carVolumen;
    }

    public void setCarVolumen(Long carVolumen) {
        this.carVolumen = carVolumen;
    }

    public Long getCarBultos() {
        return carBultos;
    }

    public void setCarBultos(Long carBultos) {
        this.carBultos = carBultos;
    }

    public Long getCarPesbruto() {
        return carPesbruto;
    }

    public void setCarPesbruto(Long carPesbruto) {
        this.carPesbruto = carPesbruto;
    }

    public Long getCarPesneto() {
        return carPesneto;
    }

    public void setCarPesneto(Long carPesneto) {
        this.carPesneto = carPesneto;
    }

    public Long getCarDo() {
        return carDo;
    }

    public void setCarDo(Long carDo) {
        this.carDo = carDo;
    }

    public Long getCarDoalma() {
        return carDoalma;
    }

    public void setCarDoalma(Long carDoalma) {
        this.carDoalma = carDoalma;
    }

    public String getCarNumdoctra() {
        return carNumdoctra;
    }

    public void setCarNumdoctra(String carNumdoctra) {
        this.carNumdoctra = carNumdoctra;
    }

    public Date getCarFecdoctra() {
        return carFecdoctra;
    }

    public void setCarFecdoctra(Date carFecdoctra) {
        this.carFecdoctra = carFecdoctra;
    }

    public Date getDepFecinsest() {
        return depFecinsest;
    }

    public void setDepFecinsest(Date depFecinsest) {
        this.depFecinsest = depFecinsest;
    }

    public Date getDepFecinsreal() {
        return depFecinsreal;
    }

    public void setDepFecinsreal(Date depFecinsreal) {
        this.depFecinsreal = depFecinsreal;
    }

    public Date getDepFeconhandest() {
        return depFeconhandest;
    }

    public void setDepFeconhandest(Date depFeconhandest) {
        this.depFeconhandest = depFeconhandest;
    }

    public Date getDepFeconhandreal() {
        return depFeconhandreal;
    }

    public void setDepFeconhandreal(Date depFeconhandreal) {
        this.depFeconhandreal = depFeconhandreal;
    }

    public Date getDepFecaduanaest() {
        return depFecaduanaest;
    }

    public void setDepFecaduanaest(Date depFecaduanaest) {
        this.depFecaduanaest = depFecaduanaest;
    }

    public Date getDepFecaduanareal() {
        return depFecaduanareal;
    }

    public void setDepFecaduanareal(Date depFecaduanareal) {
        this.depFecaduanareal = depFecaduanareal;
    }

    public String getDepNumfacdep() {
        return depNumfacdep;
    }

    public void setDepNumfacdep(String depNumfacdep) {
        this.depNumfacdep = depNumfacdep;
    }

    public BigDecimal getDepValfacdep() {
        return depValfacdep;
    }

    public void setDepValfacdep(BigDecimal depValfacdep) {
        this.depValfacdep = depValfacdep;
    }

    public Short getDepInddoctra() {
        return depInddoctra;
    }

    public void setDepInddoctra(Short depInddoctra) {
        this.depInddoctra = depInddoctra;
    }

    public Short getDepIndfac() {
        return depIndfac;
    }

    public void setDepIndfac(Short depIndfac) {
        this.depIndfac = depIndfac;
    }

    public Short getDepIndser() {
        return depIndser;
    }

    public void setDepIndser(Short depIndser) {
        this.depIndser = depIndser;
    }

    public Short getDepIndbl() {
        return depIndbl;
    }

    public void setDepIndbl(Short depIndbl) {
        this.depIndbl = depIndbl;
    }

    public Short getDepIndfacori() {
        return depIndfacori;
    }

    public void setDepIndfacori(Short depIndfacori) {
        this.depIndfacori = depIndfacori;
    }

    public Short getDepIndcerori() {
        return depIndcerori;
    }

    public void setDepIndcerori(Short depIndcerori) {
        this.depIndcerori = depIndcerori;
    }

    public Short getDepIndfle() {
        return depIndfle;
    }

    public void setDepIndfle(Short depIndfle) {
        this.depIndfle = depIndfle;
    }

    @XmlTransient
    public List<StAduana> getStAduanaList() {
        return stAduanaList;
    }

    public void setStAduanaList(List<StAduana> stAduanaList) {
        this.stAduanaList = stAduanaList;
    }

    public BigDecimal getCarEstado() {
        return carEstado;
    }

    public void setCarEstado(BigDecimal carEstado) {
        this.carEstado = carEstado;
    }
    
    @XmlTransient
    public List<StFactura> getStFacturaList() {
        return stFacturaList;
    }

    public void setStFacturaList(List<StFactura> stFacturaList) {
        this.stFacturaList = stFacturaList;
    }

    public StTipoper getTipOpera() {
        return tipOpera;
    }

    public void setTipOpera(StTipoper tipOpera) {
        this.tipOpera = tipOpera;
    }

    public StTipemba getTipConsec() {
        return tipConsec;
    }

    public void setTipConsec(StTipemba tipConsec) {
        this.tipConsec = tipConsec;
    }

    public StSeresta getSerConsec() {
        return serConsec;
    }

    public void setSerConsec(StSeresta serConsec) {
        this.serConsec = serConsec;
    }

    public StPedido getPedConsec() {
        return pedConsec;
    }

    public void setPedConsec(StPedido pedConsec) {
        this.pedConsec = pedConsec;
    }

    public StModtran getModConsec() {
        return modConsec;
    }

    public void setModConsec(StModtran modConsec) {
        this.modConsec = modConsec;
    }

    public StDepclie getStDepclie() {
        return stDepclie;
    }

    public void setStDepclie(StDepclie stDepclie) {
        this.stDepclie = stDepclie;
    }

    public StCliprov getCliTpcconsec() {
        return cliTpcconsec;
    }

    public void setCliTpcconsec(StCliprov cliTpcconsec) {
        this.cliTpcconsec = cliTpcconsec;
    }

    public StAduesta getAduConsec() {
        return aduConsec;
    }

    public void setAduConsec(StAduesta aduConsec) {
        this.aduConsec = aduConsec;
    }

    @XmlTransient
    public List<StDetcarg> getStDetcargList() {
        return stDetcargList;
    }

    public void setStDetcargList(List<StDetcarg> stDetcargList) {
        this.stDetcargList = stDetcargList;
    }

    @XmlTransient
    public List<StContene> getStConteneList() {
        return stConteneList;
    }

    public void setStConteneList(List<StContene> stConteneList) {
        this.stConteneList = stConteneList;
    }

    @XmlTransient
    public List<StOrdcomp> getStOrdcompList() {
        return stOrdcompList;
    }

    public void setStOrdcompList(List<StOrdcomp> stOrdcompList) {
        this.stOrdcompList = stOrdcompList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carConsec != null ? carConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StCarga)) {
            return false;
        }
        StCarga other = (StCarga) object;
        if ((this.carConsec == null && other.carConsec != null) || (this.carConsec != null && !this.carConsec.equals(other.carConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StCarga[ carConsec=" + carConsec + " ]";
    }

}
