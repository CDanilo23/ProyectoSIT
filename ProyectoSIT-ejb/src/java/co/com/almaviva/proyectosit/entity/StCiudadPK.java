/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author cordonez
 */
@Embeddable
public class StCiudadPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAI_ID")
    private long paiId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEP_ID")
    private long depId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CIU_ID")
    private long ciuId;

    public StCiudadPK() {
    }

    public StCiudadPK(long paiId, long depId, long ciuId) {
        this.paiId = paiId;
        this.depId = depId;
        this.ciuId = ciuId;
    }

    public long getPaiId() {
        return paiId;
    }

    public void setPaiId(long paiId) {
        this.paiId = paiId;
    }

    public long getDepId() {
        return depId;
    }

    public void setDepId(long depId) {
        this.depId = depId;
    }

    public long getCiuId() {
        return ciuId;
    }

    public void setCiuId(long ciuId) {
        this.ciuId = ciuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) paiId;
        hash += (int) depId;
        hash += (int) ciuId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StCiudadPK)) {
            return false;
        }
        StCiudadPK other = (StCiudadPK) object;
        if (this.paiId != other.paiId) {
            return false;
        }
        if (this.depId != other.depId) {
            return false;
        }
        if (this.ciuId != other.ciuId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StCiudadPK[ paiId=" + paiId + ", depId=" + depId + ", ciuId=" + ciuId + " ]";
    }
    
}
