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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_RUTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StRuta.findAll", query = "SELECT s FROM StRuta s"),
    @NamedQuery(name = "StRuta.findByRutConsec", query = "SELECT s FROM StRuta s WHERE s.rutConsec = :rutConsec"),
    @NamedQuery(name = "StRuta.findByRutCodigo", query = "SELECT s FROM StRuta s WHERE s.rutCodigo = :rutCodigo"),
    @NamedQuery(name = "StRuta.findByParams", query = "SELECT s FROM StRuta s WHERE s.rutCodigo LIKE :rutCodigo OR s.rutNombre LIKE :rutNombre OR s.pueDestino.pueNombre LIKE :pueNombreDestino OR s.pueDestino.stCiudad.stDpto.stPais.paiNom LIKE :paiNomDestino OR s.pueDestino.stCiudad.ciuNom LIKE :ciuNomDestino OR s.pueOrigen.pueNombre LIKE :pueNombreOrigen OR s.pueOrigen.stCiudad.stDpto.stPais.paiNom LIKE :paiNomOrigen OR s.pueOrigen.stCiudad.ciuNom LIKE :ciuNomOrigen"),
    @NamedQuery(name = "StRuta.findByRutNombre", query = "SELECT s FROM StRuta s WHERE s.rutNombre = :rutNombre")})
public class StRuta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUT_CONSEC")
    private Long rutConsec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RUT_CODIGO")
    private String rutCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RUT_NOMBRE")
    private String rutNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutConsec")
    private List<StRutprov> stRutprovList;
    @JoinColumn(name = "PUE_ORIGEN", referencedColumnName = "PUE_CONSEC")
    @ManyToOne(optional = false)
    private StPuerto pueOrigen;
    @JoinColumn(name = "PUE_DESTINO", referencedColumnName = "PUE_CONSEC")
    @ManyToOne(optional = false)
    private StPuerto pueDestino;
    @JoinColumn(name = "MOD_CONSEC", referencedColumnName = "MOD_CONSEC")
    @ManyToOne(optional = false)
    private StModtran modConsec;

    public StRuta() {
    }

    public StRuta(Long rutConsec) {
        this.rutConsec = rutConsec;
    }

    public StRuta(Long rutConsec, String rutCodigo, String rutNombre) {
        this.rutConsec = rutConsec;
        this.rutCodigo = rutCodigo;
        this.rutNombre = rutNombre;
    }

    public Long getRutConsec() {
        return rutConsec;
    }

    public void setRutConsec(Long rutConsec) {
        this.rutConsec = rutConsec;
    }

    public String getRutCodigo() {
        return rutCodigo;
    }

    public void setRutCodigo(String rutCodigo) {
        this.rutCodigo = rutCodigo;
    }

    public String getRutNombre() {
        return rutNombre;
    }

    public void setRutNombre(String rutNombre) {
        this.rutNombre = rutNombre;
    }

    @XmlTransient
    public List<StRutprov> getStRutprovList() {
        return stRutprovList;
    }

    public void setStRutprovList(List<StRutprov> stRutprovList) {
        this.stRutprovList = stRutprovList;
    }

    public StPuerto getPueOrigen() {
        return pueOrigen;
    }

    public void setPueOrigen(StPuerto pueOrigen) {
        this.pueOrigen = pueOrigen;
    }

    public StPuerto getPueDestino() {
        return pueDestino;
    }

    public void setPueDestino(StPuerto pueDestino) {
        this.pueDestino = pueDestino;
    }

    public StModtran getModConsec() {
        return modConsec;
    }

    public void setModConsec(StModtran modConsec) {
        this.modConsec = modConsec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutConsec != null ? rutConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StRuta)) {
            return false;
        }
        StRuta other = (StRuta) object;
        if ((this.rutConsec == null && other.rutConsec != null) || (this.rutConsec != null && !this.rutConsec.equals(other.rutConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StRuta[ rutConsec=" + rutConsec + " ]";
    }
    
}
