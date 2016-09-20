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
public class StDepcliePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEP_CONSEC")
    private long depConsec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLI_CONSEC")
    private long cliConsec;

    public StDepcliePK() {
    }

    public StDepcliePK(long depConsec, long cliConsec) {
        this.depConsec = depConsec;
        this.cliConsec = cliConsec;
    }

    public long getDepConsec() {
        return depConsec;
    }

    public void setDepConsec(long depConsec) {
        this.depConsec = depConsec;
    }

    public long getCliConsec() {
        return cliConsec;
    }

    public void setCliConsec(long cliConsec) {
        this.cliConsec = cliConsec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) depConsec;
        hash += (int) cliConsec;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StDepcliePK)) {
            return false;
        }
        StDepcliePK other = (StDepcliePK) object;
        if (this.depConsec != other.depConsec) {
            return false;
        }
        if (this.cliConsec != other.cliConsec) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StDepcliePK[ depConsec=" + depConsec + ", cliConsec=" + cliConsec + " ]";
    }
    
}
