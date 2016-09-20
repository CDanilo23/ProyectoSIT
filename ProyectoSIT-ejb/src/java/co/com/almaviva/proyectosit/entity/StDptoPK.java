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
public class StDptoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEP_ID")
    private long depId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAI_ID")
    private long paiId;

    public StDptoPK() {
    }

    public StDptoPK(long depId, long paiId) {
        this.depId = depId;
        this.paiId = paiId;
    }

    public long getDepId() {
        return depId;
    }

    public void setDepId(long depId) {
        this.depId = depId;
    }

    public long getPaiId() {
        return paiId;
    }

    public void setPaiId(long paiId) {
        this.paiId = paiId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) depId;
        hash += (int) paiId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StDptoPK)) {
            return false;
        }
        StDptoPK other = (StDptoPK) object;
        if (this.depId != other.depId) {
            return false;
        }
        if (this.paiId != other.paiId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StDptoPK[ depId=" + depId + ", paiId=" + paiId + " ]";
    }
    
}
