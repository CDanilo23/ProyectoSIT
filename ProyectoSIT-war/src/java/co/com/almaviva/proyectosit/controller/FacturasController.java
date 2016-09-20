/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.controller;

import co.com.almaviva.proyectosit.business.StCargaServiceBeanLocal;
import co.com.almaviva.proyectosit.controller.util.JsfUtil;
import co.com.almaviva.proyectosit.entity.StCarga;
import co.com.almaviva.proyectosit.entity.StCliprov;
import co.com.almaviva.proyectosit.entity.StFactura;
import co.com.almaviva.proyectosit.entity.StIncoter;
import co.com.almaviva.proyectosit.entity.StMoneda;
import co.com.almaviva.proyectosit.entity.StOrdcomp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author egonzalm
 */
@ManagedBean(name = "facturasController")
@SessionScoped
public class FacturasController {

    @EJB
    private StCargaServiceBeanLocal stCargaService;

    //Identificación de la carga
    private StCarga stCargaCurrent;
    //Panel de facturas
    private List<StFactura> stFacturas = new ArrayList<StFactura>();
    private StFactura stFacturaCurrent;
    private Boolean flagFacturas = false;
    private Long monedaFactura;
    private Long provFactura;
    private Long ordenCompraFactura;
    private Long inconFactura;

    private List<StOrdcomp> stOrdcomps = new ArrayList<StOrdcomp>();
    private StOrdcomp stOrdcompCurrent;
    private Boolean flagOrdCompras = false;

    public FacturasController() {
    }

    public void init() {
        //Panel de facturas
        this.stFacturas = new ArrayList<StFactura>();
        this.stFacturaCurrent = new StFactura();
        this.flagFacturas = false;
        this.monedaFactura = null;
        this.provFactura = null;
        this.ordenCompraFactura = null;
        this.inconFactura = null;

        //Panel de ordenes de compra
        this.stOrdcomps = new ArrayList<StOrdcomp>();
        this.stOrdcompCurrent = new StOrdcomp();
        this.flagOrdCompras = false;
    }

    public void consultaCargasClientehandleSelect() {
        //Panel de facturas
        this.stFacturas = new ArrayList<StFactura>();
        this.stFacturaCurrent = new StFactura();
        this.flagFacturas = false;
        this.monedaFactura = null;
        this.provFactura = null;
        this.inconFactura = null;
        this.ordenCompraFactura = null;

        this.stOrdcomps = new ArrayList<StOrdcomp>();
        this.stOrdcompCurrent = new StOrdcomp();
        this.flagOrdCompras = false;
    }

    public void consultaFacturasCarga(StCarga stCarga) {
        this.stCargaCurrent = stCarga;
        this.stFacturas = stCargaService.getStFacturaCarga(stCarga.getCarConsec());
        this.stFacturaCurrent = new StFactura();
        this.flagFacturas = false;
        this.monedaFactura = null;
        this.provFactura = null;
        this.ordenCompraFactura = null;
        this.inconFactura = null;

    }

    public void consultaOrdenesCarga(StCarga stCarga) {
        this.stOrdcomps = stCargaService.getStOrdCompraCarga(stCarga.getCarConsec());
        this.stOrdcompCurrent = new StOrdcomp();
        this.flagOrdCompras = false;
    }

    public void consultaFactura() {
        this.flagFacturas = true;
        this.monedaFactura = null;
        this.provFactura = null;
        this.ordenCompraFactura = null;
        this.stFacturaCurrent = new StFactura();
        this.flagOrdCompras = false;
        this.stOrdcompCurrent = null;
    }

    public void consultaFacturaEdit(StFactura stFactura) {
        try {
            this.flagFacturas = true;
            this.stFacturaCurrent = new StFactura();
            this.stFacturaCurrent = stFactura;
            this.monedaFactura = stFacturaCurrent.getMonConsec().getMonConsec();
            this.provFactura = stFacturaCurrent.getCliTpcconsec().getProConsec().getProConsec();
            if (stFacturaCurrent.getOrdConsec() != null) {
                this.ordenCompraFactura = stFacturaCurrent.getOrdConsec().getOrdConsec();
            }

            this.inconFactura = stFacturaCurrent.getIncConsec().getIncConsec();
            this.flagOrdCompras = false;
            this.stOrdcompCurrent = null;
        } catch (Exception e) {
            System.err.println("Exception consultaFacturaEdit= " + e);
        }
    }

    public void consultaOrdenCompra() {
        this.flagOrdCompras = true;
        this.stOrdcompCurrent = new StOrdcomp();

        this.flagFacturas = false;
        this.stFacturaCurrent = null;
    }

    public void consultaOrdenCompraEdit(StOrdcomp stOrdcomp) {
        try {
            System.err.println("stOrdcomp= " + stOrdcomp);
            this.flagOrdCompras = true;
            this.stOrdcompCurrent = new StOrdcomp();
            this.stOrdcompCurrent = stOrdcomp;
            this.flagFacturas = false;
            this.stFacturaCurrent = null;
        } catch (Exception e) {
            System.err.println("Exception consultaFacturaEdit= " + e);
        }
    }

    public void updateOrdenCompra() {
        if (stOrdcompCurrent != null) {

            //Nuevo Registro
            if (stOrdcompCurrent.getOrdConsec() == null) {
                stOrdcompCurrent.setCarConsec(stCargaCurrent);

                try {
                    //Creación de la orden de compra
                    stCargaService.createStOrdcomp(stOrdcompCurrent);
                    JsfUtil.addSuccessMessage("La orden de compra ha sido creada.");

                    stOrdcomps = stCargaService.getStOrdCompraCarga(stCargaCurrent.getCarConsec());
                    flagOrdCompras = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al crear la orden de compra");
                    System.err.println("Error= " + e);
                }
            } else {//Actualizar registro
                try {
                    stCargaService.editStOrdcomp(stOrdcompCurrent);
                    JsfUtil.addSuccessMessage("La orden de compra ha sido actualizada.");

                    stOrdcomps = stCargaService.getStOrdCompraCarga(stCargaCurrent.getCarConsec());
                    flagOrdCompras = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al actualizar la orden de compra.");
                    System.err.println("Error= " + e);
                }
            }
        }
    }

    public void updateFactura() {
        if (stFacturaCurrent != null) {
            if (ordenCompraFactura != null) {
                stFacturaCurrent.setOrdConsec(new StOrdcomp(ordenCompraFactura));
            } else {
                stFacturaCurrent.setOrdConsec(null);
            }
            stFacturaCurrent.setMonConsec(new StMoneda(monedaFactura));
            stFacturaCurrent.setCliTpcconsec(new StCliprov(provFactura));
            stFacturaCurrent.setIncConsec(new StIncoter(inconFactura));

            //Nuevo Registro
            if (stFacturaCurrent.getFacConsec() == null) {
                stFacturaCurrent.setCarConsec(stCargaCurrent);

                try {
                    //Creación de la Factura
                    stCargaService.createStFactura(stFacturaCurrent);
                    JsfUtil.addSuccessMessage("La factura ha sido creada.");

                    stFacturas = stCargaService.getStFacturaCarga(stCargaCurrent.getCarConsec());
                    flagFacturas = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al crear la factura");
                    System.err.println("Error= " + e);
                }
            } else {//Actualizar registro
                try {
                    stCargaService.editStFactura(stFacturaCurrent);
                    JsfUtil.addSuccessMessage("La factura ha sido actualizada.");

                    stFacturas = stCargaService.getStFacturaCarga(stCargaCurrent.getCarConsec());
                    flagFacturas = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al actualizar la factura");
                    System.err.println("Error= " + e);
                }
            }

        }
    }

    public void eliminarOrdenCompra(StOrdcomp stOrdcomp) {

        try {
            stCargaService.removeStOrdcomp(stOrdcomp);
            this.stOrdcomps = stCargaService.getStOrdCompraCarga(stCargaCurrent.getCarConsec());
            this.stOrdcompCurrent = new StOrdcomp();
            this.flagOrdCompras = false;

            JsfUtil.addSuccessMessage("La orden de compra ha sido eliminada exitosamente");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error al eliminar la orden de compra. Podría tener facturas asociadas.");
            System.err.println("Error al eliminar la orden de compra. = " + ex);
        }
    }

    public void eliminarFactura(StFactura stFactura) {

        try {
            stCargaService.removeStFactura(stFactura);
            this.stFacturas = stCargaService.getStFacturaCarga(stCargaCurrent.getCarConsec());
            this.stFacturaCurrent = new StFactura();
            this.flagFacturas = false;
            this.monedaFactura = null;
            this.provFactura = null;
            this.ordenCompraFactura = null;

            JsfUtil.addSuccessMessage("La factura ha sido eliminada exitosamente");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error al eliminar la factura.");
            System.err.println("Error al eliminar la factura. = " + ex);
        }
    }

    public void cancelarOrdenCompra() {
        this.stOrdcompCurrent = null;
        this.flagOrdCompras = false;
    }
    
    public void cancelarFactura() {        
        this.stFacturaCurrent = null;
        this.flagFacturas = false;
    }

    public Long getInconFactura() {
        return inconFactura;
    }

    public void setInconFactura(Long inconFactura) {
        this.inconFactura = inconFactura;
    }

    public List<StFactura> getStFacturas() {
        return stFacturas;
    }

    public void setStFacturas(List<StFactura> stFacturas) {
        this.stFacturas = stFacturas;
    }

    public StFactura getStFacturaCurrent() {
        return stFacturaCurrent;
    }

    public void setStFacturaCurrent(StFactura stFacturaCurrent) {
        this.stFacturaCurrent = stFacturaCurrent;
    }

    public Boolean getFlagFacturas() {
        return flagFacturas;
    }

    public void setFlagFacturas(Boolean flagFacturas) {
        this.flagFacturas = flagFacturas;
    }

    public Long getMonedaFactura() {
        return monedaFactura;
    }

    public void setMonedaFactura(Long monedaFactura) {
        this.monedaFactura = monedaFactura;
    }

    public Long getProvFactura() {
        return provFactura;
    }

    public void setProvFactura(Long provFactura) {
        this.provFactura = provFactura;
    }

    public Long getOrdenCompraFactura() {
        return ordenCompraFactura;
    }

    public void setOrdenCompraFactura(Long ordenCompraFactura) {
        this.ordenCompraFactura = ordenCompraFactura;
    }

    public List<StOrdcomp> getStOrdcomps() {
        return stOrdcomps;
    }

    public void setStOrdcomps(List<StOrdcomp> stOrdcomps) {
        this.stOrdcomps = stOrdcomps;
    }

    public StOrdcomp getStOrdcompCurrent() {
        return stOrdcompCurrent;
    }

    public void setStOrdcompCurrent(StOrdcomp stOrdcompCurrent) {
        this.stOrdcompCurrent = stOrdcompCurrent;
    }

    public Boolean getFlagOrdCompras() {
        return flagOrdCompras;
    }

    public void setFlagOrdCompras(Boolean flagOrdCompras) {
        this.flagOrdCompras = flagOrdCompras;
    }

    public StCarga getStCargaCurrent() {
        return stCargaCurrent;
    }

    public void setStCargaCurrent(StCarga stCargaCurrent) {
        this.stCargaCurrent = stCargaCurrent;
    }
}
