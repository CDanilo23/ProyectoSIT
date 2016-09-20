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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "ST_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StUsuario.findAll", query = "SELECT s FROM StUsuario s"),
    @NamedQuery(name = "StUsuario.findByUsuConsec", query = "SELECT s FROM StUsuario s WHERE s.usuConsec = :usuConsec"),
    @NamedQuery(name = "StUsuario.findByUsuNombre", query = "SELECT s FROM StUsuario s WHERE s.usuNombre = :usuNombre"),
    @NamedQuery(name = "StUsuario.findByUsuNomred", query = "SELECT s FROM StUsuario s WHERE s.usuNomred = :usuNomred"),
    @NamedQuery(name = "StUsuario.findByUsuCorreo", query = "SELECT s FROM StUsuario s WHERE s.usuCorreo = :usuCorreo"),
    @NamedQuery(name = "StUsuario.findByOfiId", query = "SELECT s FROM StUsuario s WHERE s.ofiId = :ofiId")})
public class StUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_CONSEC")
    private Long usuConsec;
    @Size(max = 100)
    @Column(name = "USU_NOMBRE")
    private String usuNombre;
    @Size(max = 20)
    @Column(name = "USU_NOMRED")
    private String usuNomred;
    @Size(max = 40)
    @Column(name = "USU_CORREO")
    private String usuCorreo;
    @Column(name = "OFI_ID")
    private Long ofiId;
    @ManyToMany(mappedBy = "stUsuarioList")
    private List<StTipusua> stTipusuaList;
    @ManyToMany(mappedBy = "stUsuarioList")
    private List<StCliente> stClienteList;

    public StUsuario() {
    }

    public StUsuario(Long usuConsec) {
        this.usuConsec = usuConsec;
    }

    public Long getUsuConsec() {
        return usuConsec;
    }

    public void setUsuConsec(Long usuConsec) {
        this.usuConsec = usuConsec;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuNomred() {
        return usuNomred;
    }

    public void setUsuNomred(String usuNomred) {
        this.usuNomred = usuNomred;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public Long getOfiId() {
        return ofiId;
    }

    public void setOfiId(Long ofiId) {
        this.ofiId = ofiId;
    }

    @XmlTransient
    public List<StTipusua> getStTipusuaList() {
        return stTipusuaList;
    }

    public void setStTipusuaList(List<StTipusua> stTipusuaList) {
        this.stTipusuaList = stTipusuaList;
    }

    @XmlTransient
    public List<StCliente> getStClienteList() {
        return stClienteList;
    }

    public void setStClienteList(List<StCliente> stClienteList) {
        this.stClienteList = stClienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuConsec != null ? usuConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StUsuario)) {
            return false;
        }
        StUsuario other = (StUsuario) object;
        if ((this.usuConsec == null && other.usuConsec != null) || (this.usuConsec != null && !this.usuConsec.equals(other.usuConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StUsuario[ usuConsec=" + usuConsec + " ]";
    }
    
}
