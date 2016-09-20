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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_RUTPROV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StRutprov.findAll", query = "SELECT s FROM StRutprov s"),
    @NamedQuery(name = "StRutprov.findByParams", query = "SELECT s FROM StRutprov s WHERE s.proConsec.proConsec = :proConsec AND (TRIM(LOWER(s.rutConsec.rutCodigo)) like :rutCodigo or TRIM(LOWER(s.rutConsec.rutNombre)) LIKE :rutNombre OR TRIM(LOWER(s.rutConsec.pueDestino.pueNombre)) LIKE :pueNombreDestino OR TRIM(LOWER(s.rutConsec.pueDestino.stCiudad.stDpto.stPais.paiNom)) LIKE :paiNomDestino OR TRIM(LOWER(s.rutConsec.pueDestino.stCiudad.ciuNom)) LIKE :ciuNomDestino OR s.rutConsec.pueOrigen.pueNombre LIKE :pueNombreOrigen OR TRIM(LOWER(s.rutConsec.pueOrigen.stCiudad.stDpto.stPais.paiNom)) LIKE :paiNomOrigen OR TRIM(LOWER(s.rutConsec.pueOrigen.stCiudad.ciuNom)) LIKE :ciuNomOrigen OR TRIM(LOWER(s.rutConsec.pueDestino.ubiConsec.ubiNombre)) like :ubiNombre)"),
    @NamedQuery(name = "StRutprov.findByRutRpconsec", query = "SELECT s FROM StRutprov s WHERE s.rutRpconsec = :rutRpconsec")})
public class StRutprov implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUT_RPCONSEC")
    private Long rutRpconsec;
    @OneToMany(mappedBy = "rutRpconsec")
    private List<StDetcarg> stDetcargList;
    @JoinColumn(name = "RUT_CONSEC", referencedColumnName = "RUT_CONSEC")
    @ManyToOne(optional = false)
    private StRuta rutConsec;
    @JoinColumn(name = "PRO_CONSEC", referencedColumnName = "PRO_CONSEC")
    @ManyToOne(optional = false)
    private StProveed proConsec;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutRpconsec")
    private List<StTiempos> stTiemposList;
    @Transient
    boolean indTiempos = false;
    @Transient
    Short tieTransporte;
    @Transient
    Long tipDia;
    @Transient
    String tipDiaNombre;
    @Transient
    Long idPrincipal;

    public StRutprov() {
    }

    public StRutprov(Long rutRpconsec) {
        this.rutRpconsec = rutRpconsec;
    }

    public Long getRutRpconsec() {
        return rutRpconsec;
    }

    public void setRutRpconsec(Long rutRpconsec) {
        this.rutRpconsec = rutRpconsec;
    }

    @XmlTransient
    public List<StDetcarg> getStDetcargList() {
        return stDetcargList;
    }

    public void setStDetcargList(List<StDetcarg> stDetcargList) {
        this.stDetcargList = stDetcargList;
    }

    public StRuta getRutConsec() {
        return rutConsec;
    }

    public void setRutConsec(StRuta rutConsec) {
        this.rutConsec = rutConsec;
    }

    public StProveed getProConsec() {
        return proConsec;
    }

    public void setProConsec(StProveed proConsec) {
        this.proConsec = proConsec;
    }

    @XmlTransient
    public List<StTiempos> getStTiemposList() {
        return stTiemposList;
    }

    public void setStTiemposList(List<StTiempos> stTiemposList) {
        this.stTiemposList = stTiemposList;
    }

    public boolean getIndTiempos() {
        return indTiempos;
    }

    public void setIndTiempos(boolean indTiempos) {
        this.indTiempos = indTiempos;
    }

    public Short getTieTransporte() {
        return tieTransporte;
    }

    public void setTieTransporte(Short tieTransporte) {
        this.tieTransporte = tieTransporte;
    }

    public Long getTipDia() {
        return tipDia;
    }

    public void setTipDia(Long tipDia) {
        this.tipDia = tipDia;
    }

    public String getTipDiaNombre() {
        return tipDiaNombre;
    }

    public void setTipDiaNombre(String tipDiaNombre) {
        this.tipDiaNombre = tipDiaNombre;
    }

    public Long getIdPrincipal() {
        return idPrincipal;
    }

    public void setIdPrincipal(Long idPrincipal) {
        this.idPrincipal = idPrincipal;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutRpconsec != null ? rutRpconsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StRutprov)) {
            return false;
        }
        StRutprov other = (StRutprov) object;
        if ((this.rutRpconsec == null && other.rutRpconsec != null) || (this.rutRpconsec != null && !this.rutRpconsec.equals(other.rutRpconsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StRutprov[ rutRpconsec=" + rutRpconsec + " ]";
    }
    
}
