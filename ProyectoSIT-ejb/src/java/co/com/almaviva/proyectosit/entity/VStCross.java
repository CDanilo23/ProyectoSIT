/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "V_ST_CROSS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VStCross.findAll", query = "SELECT v FROM VStCross v"),
    @NamedQuery(name = "VStCross.findByConsecutivo", query = "SELECT v FROM VStCross v WHERE v.consecutivo = :consecutivo"),
    @NamedQuery(name = "VStCross.findByConseccliente", query = "SELECT v FROM VStCross v WHERE v.conseccliente = :conseccliente"),
    @NamedQuery(name = "VStCross.findByCliente", query = "SELECT v FROM VStCross v WHERE v.cliente = :cliente"),
    @NamedQuery(name = "VStCross.findByFechaOperacion", query = "SELECT v FROM VStCross v WHERE v.fechaOperacion = :fechaOperacion"),
    @NamedQuery(name = "VStCross.findByDocTransporte", query = "SELECT v FROM VStCross v WHERE v.docTransporte = :docTransporte"),
    @NamedQuery(name = "VStCross.findByNoContenedor", query = "SELECT v FROM VStCross v WHERE v.noContenedor = :noContenedor"),
    @NamedQuery(name = "VStCross.findByFechaArriboreal", query = "SELECT v FROM VStCross v WHERE v.fechaArriboreal = :fechaArriboreal"),
    @NamedQuery(name = "VStCross.findByFechaLevantereal", query = "SELECT v FROM VStCross v WHERE v.fechaLevantereal = :fechaLevantereal"),
    @NamedQuery(name = "VStCross.findByTipoOperacion", query = "SELECT v FROM VStCross v WHERE v.tipoOperacion = :tipoOperacion"),
    @NamedQuery(name = "VStCross.findByFacturaOtm", query = "SELECT v FROM VStCross v WHERE v.facturaOtm = :facturaOtm"),
    @NamedQuery(name = "VStCross.findByFechaOnhandreal", query = "SELECT v FROM VStCross v WHERE v.fechaOnhandreal = :fechaOnhandreal")})
public class VStCross implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONSECUTIVO")
    @Id
    private long consecutivo;
    @Column(name = "CONSECCLIENTE")
    private Long conseccliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CLIENTE")
    private String cliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_OPERACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOperacion;
    @Size(max = 20)
    @Column(name = "DOC_TRANSPORTE")
    private String docTransporte;
    @Size(max = 20)
    @Column(name = "NO_CONTENEDOR")
    private String noContenedor;
    @Column(name = "FECHA_ARRIBOREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaArriboreal;
    @Column(name = "FECHA_LEVANTEREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLevantereal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TIPO_OPERACION")
    private String tipoOperacion;
    @Size(max = 50)
    @Column(name = "FACTURA_OTM")
    private String facturaOtm;
    @Column(name = "FECHA_ONHANDREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOnhandreal;

    public VStCross() {
    }

    public long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getConseccliente() {
        return conseccliente;
    }

    public void setConseccliente(Long conseccliente) {
        this.conseccliente = conseccliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getDocTransporte() {
        return docTransporte;
    }

    public void setDocTransporte(String docTransporte) {
        this.docTransporte = docTransporte;
    }

    public String getNoContenedor() {
        return noContenedor;
    }

    public void setNoContenedor(String noContenedor) {
        this.noContenedor = noContenedor;
    }

    public Date getFechaArriboreal() {
        return fechaArriboreal;
    }

    public void setFechaArriboreal(Date fechaArriboreal) {
        this.fechaArriboreal = fechaArriboreal;
    }

    public Date getFechaLevantereal() {
        return fechaLevantereal;
    }

    public void setFechaLevantereal(Date fechaLevantereal) {
        this.fechaLevantereal = fechaLevantereal;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getFacturaOtm() {
        return facturaOtm;
    }

    public void setFacturaOtm(String facturaOtm) {
        this.facturaOtm = facturaOtm;
    }

    public Date getFechaOnhandreal() {
        return fechaOnhandreal;
    }

    public void setFechaOnhandreal(Date fechaOnhandreal) {
        this.fechaOnhandreal = fechaOnhandreal;
    }
    
}
