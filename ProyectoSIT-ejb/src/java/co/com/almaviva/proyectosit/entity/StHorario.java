/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cordonez
 */
@Entity
@Table(name = "ST_HORARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StHorario.findAll", query = "SELECT s FROM StHorario s"),
    @NamedQuery(name = "StHorario.findByHorConsec", query = "SELECT s FROM StHorario s WHERE s.horConsec = :horConsec"),
    @NamedQuery(name = "StHorario.findByHorInicio", query = "SELECT s FROM StHorario s WHERE s.horInicio = :horInicio"),
    @NamedQuery(name = "StHorario.findByHorFin", query = "SELECT s FROM StHorario s WHERE s.horFin = :horFin")})
public class StHorario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "HOR_CONSEC")
    @GeneratedValue(generator = "seq_st_horario", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_st_horario", sequenceName = "SEQ_ST_HORARIO", initialValue = 1, allocationSize = 1)
    private Long horConsec;
    @Column(name = "HOR_INICIO")
    private Long horInicio;
    @Column(name = "HOR_FIN")
    private Long horFin;
    @JoinColumn(name = "DIA_CONSEC", referencedColumnName = "DIA_CONSEC")
    @ManyToOne(optional = false)
    private StDiasema diaConsec;
    @JoinColumn(name = "CLI_TPCCONSEC", referencedColumnName = "CLI_TPCCONSEC")
    @ManyToOne(optional = false)
    private StCliprov cliTpcconsec;
    
    
    @Transient
    private String horMinsIni;
    
    @Transient
    private String horMinsfin;
    
    

    public StHorario() {
    }

    public StHorario(Long horConsec) {
        this.horConsec = horConsec;
    }

    public Long getHorConsec() {
        return horConsec;
    }

    public void setHorConsec(Long horConsec) {
        this.horConsec = horConsec;
    }

    public Long getHorInicio() {
        return horInicio;
    }

    public void setHorInicio(Long horInicio) {
        this.horInicio = horInicio;
    }

    public Long getHorFin() {
        return horFin;
    }

    public void setHorFin(Long horFin) {
        this.horFin = horFin;
    }

    public StDiasema getDiaConsec() {
        return diaConsec;
    }

    public void setDiaConsec(StDiasema diaConsec) {
        this.diaConsec = diaConsec;
    }

    public StCliprov getCliTpcconsec() {
        return cliTpcconsec;
    }

    public void setCliTpcconsec(StCliprov cliTpcconsec) {
        this.cliTpcconsec = cliTpcconsec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horConsec != null ? horConsec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StHorario)) {
            return false;
        }
        StHorario other = (StHorario) object;
        if ((this.horConsec == null && other.horConsec != null) || (this.horConsec != null && !this.horConsec.equals(other.horConsec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.almaviva.proyectosit.entity.StHorario[ horConsec=" + horConsec + " ]";
    }

    public String getHorMinsIni() {
        return horMinsIni;
    }

    public void setHorMinsIni(String horMinsIni) {
        this.horMinsIni = horMinsIni;
    }

    public String getHorMinsfin() {
        return horMinsfin;
    }

    public void setHorMinsfin(String horMinsfin) {
        this.horMinsfin = horMinsfin;
    }

   
}
