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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_CLIPROV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StCliprov.findAll", query = "SELECT s FROM StCliprov s"),
    @NamedQuery(name = "StCliprov.findproveeXcliOnlyGeneral", query = "SELECT s FROM StCliprov s WHERE s.cliConsec = :cliConsec and s.tipServic.tipConsec = 5"),
    @NamedQuery(name = "StCliprov.findproveeXcli", query = "SELECT s FROM StCliprov s WHERE s.cliConsec = :cliConsec and s.tipProveed.tipConsec = 2"),
    @NamedQuery(name = "StCliprov.findproveeXServicGeneral", query = "SELECT s FROM StCliprov s WHERE s.cliConsec = :cliConsec and s.tipServic.tipConsec = 5 and s.tipProveed.tipConsec = 2 and not exists (select t from StTiempos t where t.cliTpcconsec.cliTpcconsec = s.cliTpcconsec)"),
    @NamedQuery(name = "StCliprov.findproveeXcliANDservic", query = "SELECT s FROM StCliprov s WHERE s.cliConsec = :cliConsec and s.tipProveed.tipConsec = 2 and s.tipServic.tipConsec = :tipServic"),
    @NamedQuery(name = "StCliprov.findproveeProXcli", query = "SELECT s FROM StCliprov s WHERE s.cliConsec = :cliConsec and s.tipProveed.tipConsec = 1"),
    @NamedQuery(name = "StCliprov.findproveePro", query = "SELECT s FROM StCliprov s WHERE s.tipProveed.tipConsec = 1"),
    @NamedQuery(name = "StCliprov.findByproConsecANDtipServic", query = "SELECT s FROM StCliprov s WHERE s.proConsec.proConsec = :proConsec and s.tipServic.tipConsec = :tipConsec"),
    @NamedQuery(name = "StCliprov.findByCliTpcconsec", query = "SELECT s FROM StCliprov s WHERE s.cliTpcconsec = :cliTpcconsec")})
public class StCliprov implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLI_TPCCONSEC")
    @GeneratedValue(generator = "SEQ_ST_ClIPROV", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_ST_ClIPROV", sequenceName = "SEQ_ST_ClIPROV", initialValue = 1, allocationSize = 1)
    private Long cliTpcconsec;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "cliTpcconsec")
    private List<StFactura> stFacturaList;
    @OneToMany(mappedBy = "cliTpcconsec")
    private List<StCarga> stCargaList;
    @OneToMany(mappedBy = "cliTpcconsec")
    private List<StDetcarg> stDetcargList;
    @JoinColumn(name = "TIP_SERVIC", referencedColumnName = "TIP_CONSEC")
    @ManyToOne
    private StTipserv tipServic;
    @JoinColumn(name = "TIP_PRODUC", referencedColumnName = "TIP_CONSEC")
    @ManyToOne
    private StTipprod tipProduc;
    @JoinColumn(name = "TIP_PROVEED", referencedColumnName = "TIP_CONSEC")
    @ManyToOne(optional = false)
    private StTippro tipProveed;
    @JoinColumn(name = "PRO_CONSEC", referencedColumnName = "PRO_CONSEC")
    @ManyToOne(optional = false)
    private StProveed proConsec;
    @JoinColumn(name = "CLI_CONSEC", referencedColumnName = "CLI_CONSEC")
    @ManyToOne(optional = false)
    private StCliente cliConsec;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "cliTpcconsec")
    private List<StTiempos> stTiemposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliTpcconsec")
    private List<StHorario> stHorarioList;

    public StCliprov() {
    }

    public StCliprov(Long cliTpcconsec) {
        this.cliTpcconsec = cliTpcconsec;
    }

    public Long getCliTpcconsec() {
        return cliTpcconsec;
    }

    public void setCliTpcconsec(Long cliTpcconsec) {
        this.cliTpcconsec = cliTpcconsec;
    }

    @XmlTransient
    public List<StFactura> getStFacturaList() {
        return stFacturaList;
    }

    public void setStFacturaList(List<StFactura> stFacturaList) {
        this.stFacturaList = stFacturaList;
    }

    @XmlTransient
    public List<StCarga> getStCargaList() {
        return stCargaList;
    }

    public void setStCargaList(List<StCarga> stCargaList) {
        this.stCargaList = stCargaList;
    }

    @XmlTransient
    public List<StDetcarg> getStDetcargList() {
        return stDetcargList;
    }

    public void setStDetcargList(List<StDetcarg> stDetcargList) {
        this.stDetcargList = stDetcargList;
    }

    public StTipserv getTipServic() {
        return tipServic;
    }

    public void setTipServic(StTipserv tipServic) {
        this.tipServic = tipServic;
    }

    public StTipprod getTipProduc() {
        return tipProduc;
    }

    public void setTipProduc(StTipprod tipProduc) {
        this.tipProduc = tipProduc;
    }

    public StTippro getTipProveed() {
        return tipProveed;
    }

    public void setTipProveed(StTippro tipProveed) {
        this.tipProveed = tipProveed;
    }

    public StProveed getProConsec() {
        return proConsec;
    }

    public void setProConsec(StProveed proConsec) {
        this.proConsec = proConsec;
    }

    public StCliente getCliConsec() {
        return cliConsec;
    }

    public void setCliConsec(StCliente cliConsec) {
        this.cliConsec = cliConsec;
    }

    @XmlTransient
    public List<StTiempos> getStTiemposList() {
        return stTiemposList;
    }

    public void setStTiemposList(List<StTiempos> stTiemposList) {
        this.stTiemposList = stTiemposList;
    }

    @XmlTransient
    public List<StHorario> getStHorarioList() {
        return stHorarioList;
    }

    public void setStHorarioList(List<StHorario> stHorarioList) {
        this.stHorarioList = stHorarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliTpcconsec != null ? cliTpcconsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StCliprov)) {
            return false;
        }
        StCliprov other = (StCliprov) object;
        if ((this.cliTpcconsec == null && other.cliTpcconsec != null) || (this.cliTpcconsec != null && !this.cliTpcconsec.equals(other.cliTpcconsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StCliprov[ cliTpcconsec=" + cliTpcconsec + " ]";
    }

}
