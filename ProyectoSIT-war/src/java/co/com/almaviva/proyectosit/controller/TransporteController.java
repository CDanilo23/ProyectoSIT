/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.controller;

import co.com.almaviva.proyectosit.business.StCargaServiceBeanLocal;
import co.com.almaviva.proyectosit.controller.util.CeUtil;
import co.com.almaviva.proyectosit.controller.util.JsfUtil;
import co.com.almaviva.proyectosit.entity.StCarga;
import co.com.almaviva.proyectosit.entity.StCliente;
import co.com.almaviva.proyectosit.entity.StCliprov;
import co.com.almaviva.proyectosit.entity.StDetcarg;
import co.com.almaviva.proyectosit.entity.StFestivo;
import co.com.almaviva.proyectosit.entity.StRutprov;
import co.com.almaviva.proyectosit.entity.StTipcarg;
import co.com.almaviva.proyectosit.entity.StTipdia;
import co.com.almaviva.proyectosit.entity.StUsuario;
import co.com.almaviva.proyectosit.entity.StLogdetcarg;
import co.com.almaviva.proyectosit.entity.StOrdcomp;
import co.com.almaviva.proyectosit.entity.StPuerto;
import co.com.almaviva.proyectosit.entity.StTipserv;
import co.com.almaviva.proyectosit.entity.StTransbo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author egonzalm
 */
@ManagedBean(name = "transporteController")
@SessionScoped
public class TransporteController {

    @EJB
    private StCargaServiceBeanLocal stCargaService;

    //Variable de carga seleccionada 
    StCarga stCargaCurrent;
    //Cliente
    private StCliente clienteSeleccionado;
    //Variable equivalente a un dia en milisegundos
    final private Long daymillseconds = 86400000L;

    //Panel de transporte
    //Carga Maritima
    private final Long modTransportemaritimo = 1L;
    private final Long servicioProveedorMaritimo = 2L;
    private List<StDetcarg> stDetcargsMaritimas = new ArrayList<StDetcarg>();

    private StDetcarg stDetcargMaritimaCurrent = new StDetcarg();
    private Long proveedorMaritimo;
    private StRutprov rutaMaritima = new StRutprov();
    private Long rutaXprovvedorMaritimo;
    private String rutaXprovvedorMaritimoNombre;

    private Boolean flagPanelCargMaritima = false;
    private Boolean flagCargMaritima = false;
    private Boolean AnadCargMaritima = false;

    //Validación de trazas maritimo    
    private Boolean flagbtnfecestsalmaritimo = false;
    private Boolean flagbtnfecrealsalmaritimo = false;
    private Boolean flagbtnfecestllegmaritimo = true;
    private Boolean flagbtnfecrealllegmaritimo = false;

    //Carga Aereo
    private final Long modTransporteAereo = 2L;
    private final Long servicioProveedorAereo = 1L;
    private List<StDetcarg> stDetcargsAereas = new ArrayList<StDetcarg>();

    private StDetcarg stDetcargAereaCurrent = new StDetcarg();
    private Long proveedorAerea;
    private StRutprov rutaAerea = new StRutprov();
    private Long rutaXprovvedorAerea;
    private String rutaXprovvedorAereaNombre;

    private Boolean flagPanelCargAerea = false;
    private Boolean flagCargAerea = false;
    private Boolean AnadCargAerea = false;

    //Validación de trazas maritimo    
    private Boolean flagbtnfecestsalAerea = false;
    private Boolean flagbtnfecrealsalAerea = false;
    private Boolean flagbtnfecestllegAerea = true;
    private Boolean flagbtnfecrealllegAerea = false;

    //Carga Destino
    private Long modTransporteDestino;
    private Long servicioProveedorDestino;
    private List<StDetcarg> stDetcargsDestino = new ArrayList<StDetcarg>();

    private StDetcarg stDetcargDestinoCurrent = new StDetcarg();
    private Long proveedorDestino;
    private StRutprov rutaDestino = new StRutprov();
    private Long rutaXprovvedorDestino;
    private String rutaXprovvedorDestinoNombre;

    private Boolean flagPanelCargDestino = false;
    private Boolean flagCargDestino = false;
    private Boolean AnadCargDestino = false;

    //Validación de trazas destino    
    private Boolean flagbtnfecestsalDestino = false;
    private Boolean flagbtnfecrealsalDestino = false;
    private Boolean flagbtnfecestllegDestino = true;
    private Boolean flagbtnfecrealllegDestino = false;

    //Variables de trazas
    private List<StLogdetcarg> stLogdetcargs = new ArrayList<StLogdetcarg>();
    private StLogdetcarg stLogdetcargCurrent = new StLogdetcarg();

    //Variables de transbordos
    private List<StTransbo> stTransbos = new ArrayList<StTransbo>();
    private StTransbo stTransboCurrent = new StTransbo();
    private Boolean flagPanelTransbordo = false;
    private Boolean flagTransbordo = false;
    private Long puertoTransbordo = null;
    private String ruta = null;
    private StDetcarg cargaTransbordo = new StDetcarg();

    public TransporteController() {
    }

    public void initVarTransbordo() {
        //Inicializa variables de transbordo
        this.stTransbos = new ArrayList<StTransbo>();
        this.stTransboCurrent = new StTransbo();
        this.flagPanelTransbordo = false;
        this.flagTransbordo = false;
        this.puertoTransbordo = null;
        this.ruta = null;
        this.cargaTransbordo = new StDetcarg();
    }

    public void init() {
        /**
         * *****************TRANSPORTEMARITIMO*****************************************
         */
        stDetcargsMaritimas = new ArrayList<StDetcarg>();
        stDetcargMaritimaCurrent = new StDetcarg();

        //Variables del autocomplete ruta
        this.rutaMaritima = new StRutprov();

        //Variables de la carga maritima current
        this.rutaXprovvedorMaritimo = null;
        this.rutaXprovvedorMaritimoNombre = null;
        this.proveedorMaritimo = null;

        this.flagPanelCargMaritima = false;
        this.flagCargMaritima = false;
        this.AnadCargMaritima = false;

        //Validación de trazas maritimo    
        this.flagbtnfecestsalmaritimo = false;
        this.flagbtnfecrealsalmaritimo = false;
        this.flagbtnfecestllegmaritimo = true;
        this.flagbtnfecrealllegmaritimo = false;
        /**
         * **********************************************************
         */

        /**
         * *****************TRANSPORTEAEREO*****************************************
         */
        stDetcargsAereas = new ArrayList<StDetcarg>();
        stDetcargAereaCurrent = new StDetcarg();

        //Variables del autocomplete ruta
        this.rutaAerea = new StRutprov();

        //Variables de la carga maritima current
        this.rutaXprovvedorAerea = null;
        this.rutaXprovvedorAereaNombre = null;
        this.proveedorAerea = null;

        this.flagPanelCargAerea = false;
        this.flagCargAerea = false;
        this.AnadCargAerea = false;

        //Validación de trazas aerea    
        this.flagbtnfecestsalAerea = false;
        this.flagbtnfecrealsalAerea = false;
        this.flagbtnfecestllegAerea = true;
        this.flagbtnfecrealllegAerea = false;
        /**
         * **********************************************************
         */
        /**
         * **********************TRANSPORTEDESTINO********************
         */
        this.modTransporteDestino = null;
        this.servicioProveedorDestino = null;
        this.stDetcargsDestino = new ArrayList<StDetcarg>();

        this.stDetcargDestinoCurrent = new StDetcarg();
        this.proveedorDestino = null;
        this.rutaDestino = new StRutprov();
        this.rutaXprovvedorDestino = null;
        this.rutaXprovvedorDestinoNombre = null;

        this.flagPanelCargDestino = false;
        this.flagCargDestino = false;
        this.AnadCargDestino = false;

        //Validación de trazas destino    
        this.flagbtnfecestsalDestino = false;
        this.flagbtnfecrealsalDestino = false;
        this.flagbtnfecestllegDestino = true;
        this.flagbtnfecrealllegDestino = false;
        /**
         * *******************************************************
         */

        //Variable de traza
        this.stLogdetcargs = new ArrayList<StLogdetcarg>();
        this.stLogdetcargCurrent = null;

        //Variables de transbordo
        initVarTransbordo();
    }

    public void consultaCargasClientehandleSelect() {
        /**
         * *****************TRANSPORTEMARITIMO*****************************************
         */
        stDetcargsMaritimas = new ArrayList<StDetcarg>();
        stDetcargMaritimaCurrent = new StDetcarg();

        //Variables del autocomplete ruta
        this.rutaMaritima = new StRutprov();

        //Variables de la carga maritima current
        this.rutaXprovvedorMaritimo = null;
        this.rutaXprovvedorMaritimoNombre = null;
        this.proveedorMaritimo = null;

        this.flagPanelCargMaritima = false;
        this.flagCargMaritima = false;
        this.AnadCargMaritima = false;

        //Validación de trazas maritimo    
        this.flagbtnfecestsalmaritimo = false;
        this.flagbtnfecrealsalmaritimo = false;
        this.flagbtnfecestllegmaritimo = true;
        this.flagbtnfecrealllegmaritimo = false;

        /**
         * **********************************************************
         */
        /**
         * *****************TRANSPORTEAEREO*****************************************
         */
        stDetcargsAereas = new ArrayList<StDetcarg>();
        stDetcargAereaCurrent = new StDetcarg();

        //Variables del autocomplete ruta
        this.rutaAerea = new StRutprov();

        //Variables de la carga maritima current
        this.rutaXprovvedorAerea = null;
        this.rutaXprovvedorAereaNombre = null;
        this.proveedorAerea = null;

        this.flagPanelCargAerea = false;
        this.flagCargAerea = false;
        this.AnadCargAerea = false;

        //Validación de trazas aerea    
        this.flagbtnfecestsalAerea = false;
        this.flagbtnfecrealsalAerea = false;
        this.flagbtnfecestllegAerea = true;
        this.flagbtnfecrealllegAerea = false;
        /**
         * **********************************************************
         */
        /**
         * **********************TRANSPORTEDESTINO********************
         */
        this.modTransporteDestino = null;
        this.servicioProveedorDestino = null;
        this.stDetcargsDestino = new ArrayList<StDetcarg>();

        this.stDetcargDestinoCurrent = new StDetcarg();
        this.proveedorDestino = null;
        this.rutaDestino = new StRutprov();
        this.rutaXprovvedorDestino = null;
        this.rutaXprovvedorDestinoNombre = null;

        this.flagPanelCargDestino = false;
        this.flagCargDestino = false;
        this.AnadCargDestino = false;

        //Validación de trazas destino    
        this.flagbtnfecestsalDestino = false;
        this.flagbtnfecrealsalDestino = false;
        this.flagbtnfecestllegDestino = true;
        this.flagbtnfecrealllegDestino = false;
        /**
         * *******************************************************
         */

        //Variable de traza
        this.stLogdetcargs = new ArrayList<StLogdetcarg>();
        this.stLogdetcargCurrent = null;

        //Variables de transbordo
        initVarTransbordo();
    }

    public void consultaTransportesCarga(StCarga stCarga, StCliente stCliente) {
        this.stCargaCurrent = stCarga;
        this.clienteSeleccionado = stCliente;

        /**
         * *****************TRANSPORTEMARITIMO*****************************************
         */
        stDetcargsMaritimas = stCargaService.getStDetcargMaritima(stCargaCurrent.getCarConsec());
        stDetcargMaritimaCurrent = new StDetcarg();

        //Variables del autocomplete ruta
        this.rutaMaritima = new StRutprov();

        //Variables de la carga maritima current
        this.rutaXprovvedorMaritimo = null;
        this.rutaXprovvedorMaritimoNombre = null;
        this.proveedorMaritimo = null;

        this.flagPanelCargMaritima = this.stCargaCurrent.getModConsec().getModConsec() == 1;
        this.flagCargMaritima = false;
        this.AnadCargMaritima = this.stDetcargsMaritimas.isEmpty();

        //Validación de trazas maritimo    
        this.flagbtnfecestsalmaritimo = false;
        this.flagbtnfecrealsalmaritimo = false;
        this.flagbtnfecestllegmaritimo = true;
        this.flagbtnfecrealllegmaritimo = false;
        /**
         * **********************************************************
         */
        /**
         * *****************TRANSPORTEAEREO*****************************************
         */
        stDetcargsAereas = stCargaService.getStDetcargAerea(stCargaCurrent.getCarConsec());
        stDetcargAereaCurrent = new StDetcarg();

        //Variables del autocomplete ruta
        this.rutaAerea = new StRutprov();

        //Variables de la carga maritima current
        this.rutaXprovvedorAerea = null;
        this.rutaXprovvedorAereaNombre = null;
        this.proveedorAerea = null;

        this.flagPanelCargAerea = this.stCargaCurrent.getModConsec().getModConsec() == 2;
        this.flagCargAerea = false;
        this.AnadCargAerea = this.stDetcargsAereas.isEmpty();

        //Validación de trazas aerea    
        this.flagbtnfecestsalAerea = false;
        this.flagbtnfecrealsalAerea = false;
        this.flagbtnfecestllegAerea = true;
        this.flagbtnfecrealllegAerea = false;
        /**
         * **********************************************************
         */
        /**
         * **********************TRANSPORTEDESTINO********************
         */
        this.modTransporteDestino = null;
        this.servicioProveedorDestino = null;

        stDetcargsDestino = stCargaService.getStDetcargDestino(stCargaCurrent.getCarConsec());
        stDetcargDestinoCurrent = new StDetcarg();

        //Variables del autocomplete ruta
        this.rutaDestino = new StRutprov();

        //Variables de la carga destino current
        this.rutaXprovvedorDestino = null;
        this.rutaXprovvedorDestinoNombre = null;
        this.proveedorDestino = null;

        this.flagPanelCargDestino = true;
        this.flagCargDestino = false;
        this.AnadCargDestino = this.stDetcargsDestino.isEmpty();

        //Validación de trazas aerea    
        this.flagbtnfecestsalDestino = false;
        this.flagbtnfecrealsalDestino = false;
        this.flagbtnfecestllegDestino = true;
        this.flagbtnfecrealllegDestino = false;
        /**
         * *******************************************************
         */

        //Variable de traza
        this.stLogdetcargs = new ArrayList<StLogdetcarg>();
        this.stLogdetcargCurrent = null;

        //Variables de transbordo
        initVarTransbordo();
    }

    public void consultaCargMaritimaEdit(StDetcarg stDetcarg) {
        try {
            stDetcargMaritimaCurrent = stDetcarg;

            //Variables del autocomplete ruta
            this.rutaMaritima = new StRutprov();

            //Variables de la ruta
            if (stDetcarg.getRutRpconsec() != null) {
                this.rutaXprovvedorMaritimo = stDetcarg.getRutRpconsec().getRutRpconsec();
                this.rutaXprovvedorMaritimoNombre = stDetcarg.getRutRpconsec().getRutConsec().getRutNombre();
            } else {
                this.rutaXprovvedorMaritimo = null;
                this.rutaXprovvedorMaritimoNombre = null;
            }

            //Variable del proveedor
            if (stDetcarg.getCliTpcconsec() != null) {
                this.proveedorMaritimo = stDetcarg.getCliTpcconsec().getProConsec().getProConsec();
            } else {
                this.proveedorMaritimo = null;
            }

            this.flagPanelCargMaritima = true;
            this.flagCargMaritima = true;

            //Validación de trazas maritimo    
            this.flagbtnfecestsalmaritimo = this.stDetcargMaritimaCurrent.getDetFecsalest() != null;
            this.flagbtnfecrealsalmaritimo = this.stDetcargMaritimaCurrent.getDetFecrsalreal() != null;
            this.flagbtnfecestllegmaritimo = true;
            this.flagbtnfecrealllegmaritimo = this.stDetcargMaritimaCurrent.getDetFecllegreal() != null;

            this.stDetcargDestinoCurrent = null;
            this.flagCargDestino = false;
        } catch (Exception e) {
            System.err.println("Exception consultaFacturaEdit= " + e);
        }
    }

    public void consultaCargDestinoEdit(StDetcarg stDetcarg) {
        try {
            stDetcargDestinoCurrent = stDetcarg;

            //Variables del autocomplete ruta
            this.rutaDestino = new StRutprov();

            //Variables de la ruta
            if (stDetcarg.getRutRpconsec() != null) {
                //Asigna el modo de transporte destino
                this.modTransporteDestino = stDetcarg.getRutRpconsec().getRutConsec().getModConsec().getModConsec();

                this.rutaXprovvedorDestino = stDetcarg.getRutRpconsec().getRutRpconsec();
                this.rutaXprovvedorDestinoNombre = stDetcarg.getRutRpconsec().getRutConsec().getRutNombre();
            } else {
                this.rutaXprovvedorDestino = null;
                this.rutaXprovvedorDestinoNombre = null;
            }

            //Variable del proveedor
            if (stDetcarg.getCliTpcconsec() != null) {
                //Servicio del proveedor
                this.servicioProveedorDestino = stDetcarg.getCliTpcconsec().getTipServic().getTipConsec();

                this.proveedorDestino = stDetcarg.getCliTpcconsec().getProConsec().getProConsec();
            } else {
                this.proveedorDestino = null;
            }

            this.flagPanelCargDestino = true;
            this.flagCargDestino = true;

            //Validación de trazas maritimo    
            this.flagbtnfecestsalDestino = this.stDetcargDestinoCurrent.getDetFecsalest() != null;
            this.flagbtnfecrealsalDestino = this.stDetcargDestinoCurrent.getDetFecrsalreal() != null;
            this.flagbtnfecestllegDestino = true;
            this.flagbtnfecrealllegDestino = this.stDetcargDestinoCurrent.getDetFecllegreal() != null;

            this.stDetcargMaritimaCurrent = null;
            this.flagCargMaritima = false;
            this.stDetcargAereaCurrent = null;
            this.flagCargAerea = false;
        } catch (Exception e) {
            System.err.println("Exception consultaFacturaEdit= " + e);
        }
    }

    public void consultaCargAereaEdit(StDetcarg stDetcarg) {
        try {
            stDetcargAereaCurrent = stDetcarg;

            //Variables del autocomplete ruta
            this.rutaAerea = new StRutprov();

            //Variables de la ruta
            if (stDetcarg.getRutRpconsec() != null) {
                this.rutaXprovvedorAerea = stDetcarg.getRutRpconsec().getRutRpconsec();
                this.rutaXprovvedorAereaNombre = stDetcarg.getRutRpconsec().getRutConsec().getRutNombre();
            } else {
                this.rutaXprovvedorAerea = null;
                this.rutaXprovvedorAereaNombre = null;
            }

            //Variable del proveedor
            if (stDetcarg.getCliTpcconsec() != null) {
                this.proveedorAerea = stDetcarg.getCliTpcconsec().getProConsec().getProConsec();
            } else {
                this.proveedorAerea = null;
            }

            this.flagPanelCargAerea = true;
            this.flagCargAerea = true;

            //Validación de trazas aereas    
            this.flagbtnfecestsalAerea = this.stDetcargAereaCurrent.getDetFecsalest() != null;
            this.flagbtnfecrealsalAerea = this.stDetcargAereaCurrent.getDetFecrsalreal() != null;
            this.flagbtnfecestllegAerea = true;
            this.flagbtnfecrealllegAerea = this.stDetcargAereaCurrent.getDetFecllegreal() != null;

            this.stDetcargDestinoCurrent = null;
            this.flagCargDestino = false;
        } catch (Exception e) {
            System.err.println("Exception consultaFacturaEdit= " + e);
        }
    }

    public void consultaCargMaritima() {
        stDetcargMaritimaCurrent = new StDetcarg();

        //Variables del autocomplete ruta
        this.rutaMaritima = new StRutprov();

        //Variables de la ruta
        this.rutaXprovvedorMaritimo = null;
        this.rutaXprovvedorMaritimoNombre = null;

        //Variable del proveedor
        this.proveedorMaritimo = null;

        this.flagPanelCargMaritima = true;
        this.flagCargMaritima = true;

        //Validación de trazas maritimo    
        this.flagbtnfecestsalmaritimo = false;
        this.flagbtnfecrealsalmaritimo = false;
        this.flagbtnfecestllegmaritimo = true;
        this.flagbtnfecrealllegmaritimo = false;

        this.stDetcargDestinoCurrent = null;
        this.flagCargDestino = false;
    }

    public void consultaTransbordo() {
        //Inicializa variables de transbordo
        this.stTransboCurrent = new StTransbo();
        this.flagPanelTransbordo = false;
        this.flagTransbordo = true;
        this.puertoTransbordo = null;
    }

    public void consultaCargDestino() {
        this.servicioProveedorDestino = null;
        stDetcargDestinoCurrent = new StDetcarg();

        //Variables del autocomplete ruta
        this.rutaDestino = new StRutprov();

        //Variables de la ruta
        this.rutaXprovvedorDestino = null;
        this.rutaXprovvedorDestinoNombre = null;

        //Variable del proveedor
        this.proveedorDestino = null;

        this.flagPanelCargDestino = true;
        this.flagCargDestino = true;

        //Validación de trazas maritimo    
        this.flagbtnfecestsalDestino = false;
        this.flagbtnfecrealsalDestino = false;
        this.flagbtnfecestllegDestino = true;
        this.flagbtnfecrealllegDestino = false;

        this.stDetcargMaritimaCurrent = null;
        this.flagCargMaritima = false;
        this.stDetcargAereaCurrent = null;
        this.flagCargAerea = false;
    }

    public void consultaCargAerea() {
        stDetcargAereaCurrent = new StDetcarg();

        //Variables del autocomplete ruta
        this.rutaAerea = new StRutprov();

        //Variables de la ruta
        this.rutaXprovvedorAerea = null;
        this.rutaXprovvedorAereaNombre = null;

        //Variable del proveedor
        this.proveedorAerea = null;

        this.flagPanelCargAerea = true;
        this.flagCargAerea = true;

        //Validación de trazas maritimo    
        this.flagbtnfecestsalAerea = false;
        this.flagbtnfecrealsalAerea = false;
        this.flagbtnfecestllegAerea = true;
        this.flagbtnfecrealllegAerea = false;

        this.stDetcargDestinoCurrent = null;
        this.flagCargDestino = false;
    }

    public void updateCargaMaritima() {
        if (stDetcargMaritimaCurrent != null) {

            if (this.rutaXprovvedorMaritimo == null) {
                JsfUtil.addErrorMessage("La ruta es obligatoria");
                return;
            }

            //Variable de la carga general
            stDetcargMaritimaCurrent.setCarConsec(stCargaCurrent);

            //Variable del proveedor de la ruta
            stDetcargMaritimaCurrent.setCliTpcconsec(new StCliprov(stCargaService.getProveedorXServicio(this.clienteSeleccionado.getCliConsec(), this.servicioProveedorMaritimo, this.proveedorMaritimo)));

            //Variable del proveedor de la ruta
            stDetcargMaritimaCurrent.setRutRpconsec(new StRutprov(this.rutaXprovvedorMaritimo));

            //Identifica que el tipo de carga es maritimo
            stDetcargMaritimaCurrent.setTipConsec(new StTipcarg(2L));

            //Nuevo Registro
            if (stDetcargMaritimaCurrent.getDetConsec() == null) {

                try {
                    //Creación de la carga maritima
                    stDetcargMaritimaCurrent.setDetEstado(1L);
                    stCargaService.createStDetcarg(stDetcargMaritimaCurrent);
                    JsfUtil.addSuccessMessage("La carga maritima ha sido creada.");
                    stDetcargMaritimaCurrent = stCargaService.findStDetCarga(stDetcargMaritimaCurrent.getDetConsec());

                    try {

                        //Log de fechas
                        agregaLog(stDetcargMaritimaCurrent);
                    } catch (Exception e) {
                        JsfUtil.addErrorMessage("Error al crear el log de la carga.");
                        System.err.println("Error= " + e);
                    }

                    stDetcargsMaritimas = stCargaService.getStDetcargMaritima(stCargaCurrent.getCarConsec());
                    flagCargMaritima = false;
                    AnadCargMaritima = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al crear la carga maritima.");
                    System.err.println("Error= " + e);
                }
            } else {//Actualizar registro
                try {
                    Boolean cambios = stCargaService.verificaCambiosLog(stDetcargMaritimaCurrent);
                    stCargaService.editStDetcarg(stDetcargMaritimaCurrent);
                    JsfUtil.addSuccessMessage("La carga maritima ha sido actualizada.");
                    stDetcargMaritimaCurrent = stCargaService.findStDetCarga(stDetcargMaritimaCurrent.getDetConsec());

                    try {
                        if (cambios) {
                            //Log de fechas
                            agregaLog(stDetcargMaritimaCurrent);
                        }
                    } catch (Exception e) {
                        JsfUtil.addErrorMessage("Error al crear el log de la carga.");
                        System.err.println("Error= " + e);
                    }

                    stDetcargsMaritimas = stCargaService.getStDetcargMaritima(stCargaCurrent.getCarConsec());
                    flagCargMaritima = false;
                    AnadCargMaritima = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al actualizar la carga maritima.");
                    System.err.println("Error= " + e);
                }
            }
        }
    }

    public void updateCargaAerea() {
        if (stDetcargAereaCurrent != null) {

            if (this.rutaXprovvedorAerea == null) {
                JsfUtil.addErrorMessage("La ruta es obligatoria");
                return;
            }

            //Variable de la carga general
            stDetcargAereaCurrent.setCarConsec(stCargaCurrent);

            //Variable del proveedor de la ruta
            stDetcargAereaCurrent.setCliTpcconsec(new StCliprov(stCargaService.getProveedorXServicio(this.clienteSeleccionado.getCliConsec(), this.servicioProveedorAereo, this.proveedorAerea)));

            //Variable del proveedor de la ruta
            stDetcargAereaCurrent.setRutRpconsec(new StRutprov(this.rutaXprovvedorAerea));

            //Identifica que el tipo de carga es aerea
            stDetcargAereaCurrent.setTipConsec(new StTipcarg(3L));

            //Nuevo Registro
            if (stDetcargAereaCurrent.getDetConsec() == null) {

                try {
                    //Creación de la carga aerea
                    //La carga se declara como activa
                    stDetcargAereaCurrent.setDetEstado(1L);
                    stCargaService.createStDetcarg(stDetcargAereaCurrent);
                    JsfUtil.addSuccessMessage("La carga aérea ha sido creada.");

                    stDetcargAereaCurrent = stCargaService.findStDetCarga(stDetcargAereaCurrent.getDetConsec());

                    try {
                        //Log de fechas
                        agregaLog(stDetcargAereaCurrent);
                    } catch (Exception e) {
                        JsfUtil.addErrorMessage("Error al crear el log de la carga.");
                        System.err.println("Error= " + e);
                    }

                    stDetcargsAereas = stCargaService.getStDetcargAerea(stCargaCurrent.getCarConsec());
                    flagCargAerea = false;
                    AnadCargAerea = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al crear la carga aérea.");
                    System.err.println("Error= " + e);
                }
            } else {//Actualizar registro
                try {
                    System.err.println("paso1");
                    Boolean cambios = stCargaService.verificaCambiosLog(stDetcargAereaCurrent);
                    System.err.println("paso2");
                    stCargaService.editStDetcarg(stDetcargAereaCurrent);

                    stDetcargAereaCurrent = stCargaService.findStDetCarga(stDetcargAereaCurrent.getDetConsec());
                    System.err.println("paso3");
                    JsfUtil.addSuccessMessage("La carga aérea ha sido actualizada.");

                    try {
                        if (cambios) {
                            System.err.println("paso4");
                            //Log de fechas
                            agregaLog(stDetcargAereaCurrent);
                            System.err.println("paso5");
                        }
                    } catch (Exception e) {
                        JsfUtil.addErrorMessage("Error al crear el log de la carga.");
                        System.err.println("Error= " + e);
                    }

                    stDetcargsAereas = stCargaService.getStDetcargAerea(stCargaCurrent.getCarConsec());
                    flagCargAerea = false;
                    AnadCargAerea = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al actualizar la carga aérea.");
                    System.err.println("Error= " + e);
                }
            }
        }
    }

    public void updateCargaDestino() {
        if (stDetcargDestinoCurrent != null) {

            if (this.rutaXprovvedorDestino == null) {
                JsfUtil.addErrorMessage("La ruta es obligatoria");
                return;
            }

            //Variable de la carga general
            stDetcargDestinoCurrent.setCarConsec(stCargaCurrent);

            //Variable del proveedor de la ruta
            stDetcargDestinoCurrent.setCliTpcconsec(new StCliprov(stCargaService.getProveedorXServicio(this.clienteSeleccionado.getCliConsec(), this.servicioProveedorDestino, this.proveedorDestino)));

            //Variable del proveedor de la ruta
            stDetcargDestinoCurrent.setRutRpconsec(new StRutprov(this.rutaXprovvedorDestino));

            //Identifica que el tipo de carga es maritimo
            stDetcargDestinoCurrent.setTipConsec(new StTipcarg(4L));

            //Nuevo Registro
            if (stDetcargDestinoCurrent.getDetConsec() == null) {

                try {
                    //Creación de la carga maritima
                    stDetcargDestinoCurrent.setDetEstado(1L);
                    stCargaService.createStDetcarg(stDetcargDestinoCurrent);
                    JsfUtil.addSuccessMessage("La carga destino ha sido creada.");
                    stDetcargDestinoCurrent = stCargaService.findStDetCarga(stDetcargDestinoCurrent.getDetConsec());

                    try {

                        //Log de fechas
                        agregaLog(stDetcargDestinoCurrent);
                    } catch (Exception e) {
                        JsfUtil.addErrorMessage("Error al crear el log de la carga.");
                        System.err.println("Error= " + e);
                    }

                    stDetcargsDestino = stCargaService.getStDetcargDestino(stCargaCurrent.getCarConsec());
                    flagCargDestino = false;
                    AnadCargDestino = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al crear la carga destino.");
                    System.err.println("Error= " + e);
                }
            } else {//Actualizar registro
                try {
                    Boolean cambios = stCargaService.verificaCambiosLog(stDetcargDestinoCurrent);
                    stCargaService.editStDetcarg(stDetcargDestinoCurrent);
                    JsfUtil.addSuccessMessage("La carga destino ha sido actualizada.");
                    stDetcargDestinoCurrent = stCargaService.findStDetCarga(stDetcargDestinoCurrent.getDetConsec());

                    try {
                        if (cambios) {
                            //Log de fechas
                            agregaLog(stDetcargDestinoCurrent);
                        }
                    } catch (Exception e) {
                        JsfUtil.addErrorMessage("Error al crear el log de la carga.");
                        System.err.println("Error= " + e);
                    }

                    stDetcargsDestino = stCargaService.getStDetcargDestino(stCargaCurrent.getCarConsec());
                    flagCargDestino = false;
                    AnadCargDestino = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al actualizar la carga destino.");
                    System.err.println("Error= " + e);
                }
            }
        }
    }

    public void updatetransbordo() {
        if (cargaTransbordo != null && stTransboCurrent != null) {
            if (puertoTransbordo == null) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "El puerto de transbordo es obligatorio."));
                return;
            }

            stTransboCurrent.setDetConsec(cargaTransbordo);
            stTransboCurrent.setPueConsec(new StPuerto(puertoTransbordo));
            try {
                stCargaService.createStTransbo(stTransboCurrent);
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Validación", "El transbordo ha sido creado exitosamente."));

                stTransbos = stCargaService.getStTransbo(cargaTransbordo.getDetConsec());
                flagTransbordo = false;
            } catch (Exception e) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "Error al crear el trensbordo."));
            }
        }
    }

    public void updateFechaEstimadaMaritima() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (stLogdetcargCurrent != null) {

            if (stLogdetcargCurrent.getLogFecnue() == null) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "La Fecha nueva de estimación es obligatoria."));
                return;
            }

            if (stLogdetcargCurrent.getLogObserv() == null || stLogdetcargCurrent.getLogObserv().length() == 0) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "El campo observaciones es obligatorio."));
                return;
            }

            String observaciones;
            if (stLogdetcargCurrent.getIndFecha() == 1) {
                observaciones = "Nueva Fecha Salida Estimada : " + dateFormat.format(stLogdetcargCurrent.getLogFecnue()) + ". " + stLogdetcargCurrent.getLogObserv();
            } else {
                observaciones = "Nueva Fecha Llegada Estimada : " + dateFormat.format(stLogdetcargCurrent.getLogFecnue()) + ". " + stLogdetcargCurrent.getLogObserv();
            }

            stLogdetcargCurrent.setLogTraza(new Date());
            stLogdetcargCurrent.setLogObserv(observaciones);
            stLogdetcargCurrent.setDetConsec(stDetcargMaritimaCurrent);

            try {
                //Nueva Fecha Estimada
                stCargaService.createStLogdetcarg(stLogdetcargCurrent);

                //Edita la nueva Fecha Estimada al transporte
                try {
                    if (stLogdetcargCurrent.getIndFecha() == 1) {
                        stDetcargMaritimaCurrent.setDetFecsalest(stLogdetcargCurrent.getLogFecnue());
                        recalculaFechasMaritimas();
                    } else {
                        stDetcargMaritimaCurrent.setDetFecllegest(stLogdetcargCurrent.getLogFecnue());
                    }

                    Boolean cambios = stCargaService.verificaCambiosLog(stDetcargMaritimaCurrent);
                    stCargaService.editStDetcarg(stDetcargMaritimaCurrent);

                    if (cambios) {
                        //Log de fechas
                        agregaLog(stDetcargMaritimaCurrent);
                    }
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("DialogFecEstimadaVar.hide();");
                    RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Validación", "Fecha Estimada cambiada exitosamente."));

                } catch (Exception e) {
                    RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "Error al modificar la nueva fecha estimada"));
                }

                stDetcargsMaritimas = stCargaService.getStDetcargMaritima(stCargaCurrent.getCarConsec());
                flagCargMaritima = false;
                AnadCargMaritima = false;
            } catch (Exception e) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "Error al modificar la nueva fecha estimada."));
                System.err.println("Error= " + e);
            }
        }
    }

    public void updateFechaEstimadaAerea() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (stLogdetcargCurrent != null) {

            if (stLogdetcargCurrent.getLogFecnue() == null) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "La Fecha nueva de estimación es obligatoria."));
                return;
            }

            if (stLogdetcargCurrent.getLogObserv() == null || stLogdetcargCurrent.getLogObserv().length() == 0) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "El campo observaciones es obligatorio."));
                return;
            }

            String observaciones;
            if (stLogdetcargCurrent.getIndFecha() == 1) {
                observaciones = "Nueva Fecha Salida Estimada : " + dateFormat.format(stLogdetcargCurrent.getLogFecnue()) + ". " + stLogdetcargCurrent.getLogObserv();
            } else {
                observaciones = "Nueva Fecha Llegada Estimada : " + dateFormat.format(stLogdetcargCurrent.getLogFecnue()) + ". " + stLogdetcargCurrent.getLogObserv();
            }

            stLogdetcargCurrent.setLogTraza(new Date());
            stLogdetcargCurrent.setLogObserv(observaciones);
            stLogdetcargCurrent.setDetConsec(stDetcargAereaCurrent);

            try {
                //Nueva Fecha Estimada
                stCargaService.createStLogdetcarg(stLogdetcargCurrent);

                //Edita la nueva Fecha Estimada al transporte
                try {
                    if (stLogdetcargCurrent.getIndFecha() == 1) {
                        stDetcargAereaCurrent.setDetFecsalest(stLogdetcargCurrent.getLogFecnue());
                        recalculaFechasMaritimas();
                    } else {
                        stDetcargAereaCurrent.setDetFecllegest(stLogdetcargCurrent.getLogFecnue());
                    }

                    Boolean cambios = stCargaService.verificaCambiosLog(stDetcargAereaCurrent);
                    stCargaService.editStDetcarg(stDetcargAereaCurrent);

                    if (cambios) {
                        //Log de fechas
                        agregaLog(stDetcargAereaCurrent);
                    }
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("DialogFecEstimadaVar.hide();");
                    RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Validación", "Fecha Estimada cambiada exitosamente."));

                } catch (Exception e) {
                    RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "Error al modificar la nueva fecha estimada"));
                }

                stDetcargsAereas = stCargaService.getStDetcargAerea(stCargaCurrent.getCarConsec());
                flagCargAerea = false;
                AnadCargAerea = false;
            } catch (Exception e) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "Error al modificar la nueva fecha estimada."));
                System.err.println("Error= " + e);
            }
        }
    }

    public void updateFechaEstimadaDestino() {
        System.err.println("updateFechaEstimadaDestino");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (stLogdetcargCurrent != null) {

            if (stLogdetcargCurrent.getLogFecnue() == null) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "La Fecha nueva de estimación es obligatoria."));
                return;
            }

            if (stLogdetcargCurrent.getLogObserv() == null || stLogdetcargCurrent.getLogObserv().length() == 0) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "El campo observaciones es obligatorio."));
                return;
            }

            String observaciones;
            if (stLogdetcargCurrent.getIndFecha() == 1) {
                observaciones = "Nueva Fecha Salida Estimada : " + dateFormat.format(stLogdetcargCurrent.getLogFecnue()) + ". " + stLogdetcargCurrent.getLogObserv();
            } else {
                observaciones = "Nueva Fecha Llegada Estimada : " + dateFormat.format(stLogdetcargCurrent.getLogFecnue()) + ". " + stLogdetcargCurrent.getLogObserv();
            }

            stLogdetcargCurrent.setLogTraza(new Date());
            stLogdetcargCurrent.setLogObserv(observaciones);
            stLogdetcargCurrent.setDetConsec(stDetcargDestinoCurrent);

            try {
                //Nueva Fecha Estimada
                stCargaService.createStLogdetcarg(stLogdetcargCurrent);

                //Edita la nueva Fecha Estimada al transporte
                try {
                    if (stLogdetcargCurrent.getIndFecha() == 1) {
                        stDetcargDestinoCurrent.setDetFecsalest(stLogdetcargCurrent.getLogFecnue());
                        recalculaFechasDestino();
                    } else {
                        stDetcargDestinoCurrent.setDetFecllegest(stLogdetcargCurrent.getLogFecnue());
                    }

                    Boolean cambios = stCargaService.verificaCambiosLog(stDetcargDestinoCurrent);
                    stCargaService.editStDetcarg(stDetcargDestinoCurrent);

                    if (cambios) {
                        //Log de fechas
                        agregaLog(stDetcargDestinoCurrent);
                    }
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("DialogFecEstimadaVar.hide();");
                    RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Validación", "Fecha Estimada cambiada exitosamente."));

                } catch (Exception e) {
                    RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "Error al modificar la nueva fecha estimada"));
                }

                stDetcargsDestino = stCargaService.getStDetcargDestino(stCargaCurrent.getCarConsec());
                flagCargDestino = false;
                AnadCargDestino = false;
            } catch (Exception e) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "Error al modificar la nueva fecha estimada."));
                System.err.println("Error= " + e);
            }
        }
    }

    public void inactivarCargMaritima() {
        try {
            stDetcargMaritimaCurrent.setDetEstado(0L);
            stCargaService.editStDetcarg(stDetcargMaritimaCurrent);

            this.stDetcargsMaritimas = stCargaService.getStDetcargMaritima(stCargaCurrent.getCarConsec());
            this.stDetcargMaritimaCurrent = new StDetcarg();

            this.flagCargMaritima = false;
            this.proveedorMaritimo = null;
            this.rutaMaritima = null;
            this.AnadCargMaritima = true;
            JsfUtil.addSuccessMessage("La carga maritima ha sido inactivada");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error al inactivar la carga maritima.");
            System.err.println("Error al inactivar la carga maritima. = " + ex);
        }
    }

    public void inactivarCargDestino() {
        try {
            stDetcargDestinoCurrent.setDetEstado(0L);
            stCargaService.editStDetcarg(stDetcargDestinoCurrent);

            this.stDetcargsDestino = stCargaService.getStDetcargDestino(stCargaCurrent.getCarConsec());
            this.stDetcargDestinoCurrent = new StDetcarg();

            this.flagCargDestino = false;
            this.proveedorDestino = null;
            this.rutaDestino = null;
            this.AnadCargDestino = true;
            JsfUtil.addSuccessMessage("La carga destino ha sido inactivada");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error al inactivar la carga destino.");
            System.err.println("Error al inactivar la carga destino. = " + ex);
        }
    }

    public void inactivarCargAerea() {
        try {
            stDetcargAereaCurrent.setDetEstado(0L);
            stCargaService.editStDetcarg(stDetcargAereaCurrent);

            this.stDetcargsAereas = stCargaService.getStDetcargAerea(stCargaCurrent.getCarConsec());
            this.stDetcargAereaCurrent = new StDetcarg();

            this.flagCargAerea = false;
            this.proveedorAerea = null;
            this.rutaAerea = null;
            this.AnadCargAerea = true;
            JsfUtil.addSuccessMessage("La carga aérea ha sido inactivada");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error al inactivar la carga aérea.");
            System.err.println("Error al inactivar la carga aérea. = " + ex);
        }
    }

    public void eliminarCargAerea(StDetcarg stDetcarg) {
        try {
            stCargaService.removeStDetcarg(stDetcarg);
            this.stDetcargsAereas = stCargaService.getStDetcargAerea(stCargaCurrent.getCarConsec());
            this.stDetcargAereaCurrent = new StDetcarg();

            this.flagCargAerea = false;
            this.proveedorAerea = null;
            this.rutaAerea = null;
            this.AnadCargAerea = true;
            JsfUtil.addSuccessMessage("La carga aérea ha sido eliminada exitosamente");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error al eliminar la carga aérea.");
            System.err.println("Error al eliminar la carga aerea. = " + ex);
        }
    }

    public void eliminarTransbordo(StTransbo stTransbo) {
        try {
            stCargaService.removeStTransbo(stTransbo);
            this.stTransbos = stCargaService.getStTransbo(cargaTransbordo.getDetConsec());
            this.stTransboCurrent = new StTransbo();
            this.flagTransbordo = false;
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Validación", "El transbordo ha sido eliminado exitosamente"));

        } catch (Exception ex) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", "Error al eliminar el transbordo."));
            System.err.println("Error al eliminar el transbordo. = " + ex);
        }
    }

    public void consultaTraza(StDetcarg stDetcarg) {
        this.stLogdetcargs = stCargaService.getStLogdetcarg(stDetcarg.getDetConsec());
    }

    public void consultaTransbordos(StDetcarg stDetcarg) {
        if (stDetcarg.getRutRpconsec() == null && stDetcarg.getRutRpconsec().getRutRpconsec() == null) {
            JsfUtil.addErrorMessage("La carga no tiene ruta definida.");
            return;
        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("DialogTransbordosVar.show();");
        initVarTransbordo();
        this.ruta = stDetcarg.getRutRpconsec().getRutConsec().getRutNombre();
        this.stTransbos = stCargaService.getStTransbo(stDetcarg.getDetConsec());
        this.cargaTransbordo = stDetcarg;
    }

    public void edicionFechaEstSalida(StDetcarg stDetcarg) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("DialogFecEstimadaVar.show();");
        this.stLogdetcargCurrent = new StLogdetcarg();
        this.stLogdetcargCurrent.setIndFecha(new Short("1"));
        StUsuario stUsuario = CeUtil.getUsuarioSesion();
        this.stLogdetcargCurrent.setLogUsu(stUsuario.getUsuNomred());
    }

    public void edicionFechaEstLlegada(StDetcarg stDetcarg) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("DialogFecEstimadaVar.show();");
        this.stLogdetcargCurrent = new StLogdetcarg();
        this.stLogdetcargCurrent.setIndFecha(new Short("2"));
        StUsuario stUsuario = CeUtil.getUsuarioSesion();
        this.stLogdetcargCurrent.setLogUsu(stUsuario.getUsuNomred());
    }

    public void cancelarFechaEstimada() {
        this.stLogdetcargCurrent = null;
    }

    public void cancelarTransbordo() {
        initVarTransbordo();
    }

    public void cancelarCargaMaritima() {
        this.stDetcargMaritimaCurrent = new StDetcarg();

        this.flagCargMaritima = false;
        this.proveedorMaritimo = null;
        this.rutaMaritima = null;
        this.AnadCargMaritima = stDetcargsMaritimas.isEmpty();
    }

    public void cancelarCargaDestino() {
        this.stDetcargDestinoCurrent = new StDetcarg();

        this.flagCargDestino = false;
        this.proveedorDestino = null;
        this.rutaDestino = null;
        this.AnadCargDestino = stDetcargsDestino.isEmpty();
    }

    public void cancelarCargaAerea() {
        this.stDetcargAereaCurrent = new StDetcarg();

        this.flagCargAerea = false;
        this.proveedorAerea = null;
        this.rutaAerea = null;
        this.AnadCargAerea = stDetcargsAereas.isEmpty();
    }

    public void cambiarProveedor(ValueChangeEvent event) {
        this.rutaMaritima = new StRutprov();
        this.rutaXprovvedorMaritimo = null;
        this.rutaXprovvedorMaritimoNombre = null;
        this.stDetcargMaritimaCurrent.setTie_transporte(null);
        this.stDetcargMaritimaCurrent.setTipDia(null);
        this.stDetcargMaritimaCurrent.setDetFecllegest(null);
    }

    public void cambiarServiciosProveedorDestino(ValueChangeEvent event) {
        this.proveedorDestino = null;
        this.rutaDestino = new StRutprov();
        this.rutaXprovvedorDestino = null;
        this.rutaXprovvedorDestinoNombre = null;
        this.stDetcargDestinoCurrent.setTie_transporte(null);
        this.stDetcargDestinoCurrent.setTipDia(null);
        this.stDetcargDestinoCurrent.setDetFecllegest(null);
    }

    public void cambiarProveedorDestino(ValueChangeEvent event) {
        this.rutaDestino = new StRutprov();
        this.rutaXprovvedorDestino = null;
        this.rutaXprovvedorDestinoNombre = null;
        this.stDetcargDestinoCurrent.setTie_transporte(null);
        this.stDetcargDestinoCurrent.setTipDia(null);
        this.stDetcargDestinoCurrent.setDetFecllegest(null);
    }

    public void cambiarFechaestimadaDestino(SelectEvent event) {
        System.err.println("cambiarFechaestimadaDestino");
        if (event.getObject() != null) {
            stDetcargDestinoCurrent.setDetFecsalest((Date) event.getObject());
            recalculaFechasDestino();
        }
    }

    public void limpiaFechaestimadaDestino() {
        stDetcargDestinoCurrent.setDetFecsalest(null);
        recalculaFechasDestino();
    }

    public void cambiarFechaRealDestino(SelectEvent event) {
        if (event.getObject() != null) {
            stDetcargDestinoCurrent.setDetFecrsalreal((Date) event.getObject());
            recalculaFechasDestino();
        }
    }

    public void limpiaFechaRealDestino() {
        stDetcargDestinoCurrent.setDetFecrsalreal(null);
        recalculaFechasDestino();
    }

    public void cambiarFechaestimadaAerea(SelectEvent event) {
        if (event.getObject() != null) {
            stDetcargAereaCurrent.setDetFecsalest((Date) event.getObject());
            recalculaFechasAereas();
        }
    }

    public void limpiaFechaestimadaAerea() {
        stDetcargAereaCurrent.setDetFecsalest(null);
        recalculaFechasAereas();
    }

    public void cambiarFechaRealAerea(SelectEvent event) {
        if (event.getObject() != null) {
            stDetcargAereaCurrent.setDetFecrsalreal((Date) event.getObject());
            recalculaFechasAereas();
        }
    }

    public void limpiaFechaRealAerea() {
        stDetcargAereaCurrent.setDetFecrsalreal(null);
        recalculaFechasAereas();
    }

    public void cambiarFechaRealMaritima(SelectEvent event) {
        if (event.getObject() != null) {
            stDetcargMaritimaCurrent.setDetFecrsalreal((Date) event.getObject());
            recalculaFechasMaritimas();
        }
    }

    public void cambiarFechaestimadaMaritima(SelectEvent event) {
        if (event.getObject() != null) {
            stDetcargMaritimaCurrent.setDetFecsalest((Date) event.getObject());
            recalculaFechasMaritimas();
        }
    }

    public void limpiaFechaestimadaMaritima() {
        stDetcargMaritimaCurrent.setDetFecsalest(null);
        recalculaFechasMaritimas();
    }

    public void limpiaFechaRealMaritima() {
        stDetcargMaritimaCurrent.setDetFecrsalreal(null);
        recalculaFechasMaritimas();
    }

    public void recalculaFechasMaritimas() {
        Date fecInicioMaritimo = null;
        Long tiempoTransporteMaritimo = null;
        Long tipoDiaMaritimo = null;

        if (stDetcargMaritimaCurrent.getDetFecrsalreal() != null) {
            fecInicioMaritimo = stDetcargMaritimaCurrent.getDetFecrsalreal();
        }

        if (stDetcargMaritimaCurrent.getDetFecrsalreal() == null && stDetcargMaritimaCurrent.getDetFecsalest() != null) {
            fecInicioMaritimo = stDetcargMaritimaCurrent.getDetFecsalest();
        }

        if (stDetcargMaritimaCurrent.getTie_transporte() != null) {
            tiempoTransporteMaritimo = stDetcargMaritimaCurrent.getTie_transporte();
        }

        if (stDetcargMaritimaCurrent.getTipDia() != null && stDetcargMaritimaCurrent.getTipDia().getTipConsec() != null) {
            tipoDiaMaritimo = stDetcargMaritimaCurrent.getTipDia().getTipConsec();
        }

        if (fecInicioMaritimo != null && tiempoTransporteMaritimo != null && tipoDiaMaritimo != null) {
            stDetcargMaritimaCurrent.setDetFecllegest(calculaFechaLlegada(fecInicioMaritimo, tiempoTransporteMaritimo, tipoDiaMaritimo));
        } else {
            stDetcargMaritimaCurrent.setDetFecllegest(null);
        }
    }

    public void recalculaFechasAereas() {
        Date fecInicioAerea = null;
        Long tiempoTransporteAerea = null;
        Long tipoDiaAerea = null;

        if (stDetcargAereaCurrent.getDetFecrsalreal() != null) {
            fecInicioAerea = stDetcargAereaCurrent.getDetFecrsalreal();
        }

        if (stDetcargAereaCurrent.getDetFecrsalreal() == null && stDetcargAereaCurrent.getDetFecsalest() != null) {
            fecInicioAerea = stDetcargAereaCurrent.getDetFecsalest();
        }

        if (stDetcargAereaCurrent.getTie_transporte() != null) {
            tiempoTransporteAerea = stDetcargAereaCurrent.getTie_transporte();
        }

        if (stDetcargAereaCurrent.getTipDia() != null && stDetcargAereaCurrent.getTipDia().getTipConsec() != null) {
            tipoDiaAerea = stDetcargAereaCurrent.getTipDia().getTipConsec();
        }

        System.err.println("fecInicioAerea= " + fecInicioAerea + "----" + tiempoTransporteAerea + "----" + tipoDiaAerea);
        if (fecInicioAerea != null && tiempoTransporteAerea != null && tipoDiaAerea != null) {
            stDetcargAereaCurrent.setDetFecllegest(calculaFechaLlegada(fecInicioAerea, tiempoTransporteAerea, tipoDiaAerea));
        } else {
            stDetcargAereaCurrent.setDetFecllegest(null);
        }
    }

    public void recalculaFechasDestino() {
        Date fecInicioDestino = null;
        Long tiempoTransporteDestino = null;
        Long tipoDiaDestino = null;

        if (stDetcargDestinoCurrent.getDetFecrsalreal() != null) {
            fecInicioDestino = stDetcargDestinoCurrent.getDetFecrsalreal();
        }

        if (stDetcargDestinoCurrent.getDetFecrsalreal() == null && stDetcargDestinoCurrent.getDetFecsalest() != null) {
            fecInicioDestino = stDetcargDestinoCurrent.getDetFecsalest();
        }

        if (stDetcargDestinoCurrent.getTie_transporte() != null) {
            tiempoTransporteDestino = stDetcargDestinoCurrent.getTie_transporte();
        }

        if (stDetcargDestinoCurrent.getTipDia() != null && stDetcargDestinoCurrent.getTipDia().getTipConsec() != null) {
            tipoDiaDestino = stDetcargDestinoCurrent.getTipDia().getTipConsec();
        }

        if (fecInicioDestino != null && tiempoTransporteDestino != null && tipoDiaDestino != null) {
            stDetcargDestinoCurrent.setDetFecllegest(calculaFechaLlegada(fecInicioDestino, tiempoTransporteDestino, tipoDiaDestino));
        } else {
            stDetcargDestinoCurrent.setDetFecllegest(null);
        }
    }

    public Date calculaFechaLlegada(Date fecInicio, Long tiempoTransporte, Long tipoDia) {
        Date fechaLlegada;
        if (tipoDia == 2) {//Calcula sin festivos

            fechaLlegada = new Date(fecInicio.getTime() + (daymillseconds * tiempoTransporte));
            Long festivos = stCargaService.getNumDiasFestivo(fecInicio, fechaLlegada);
            tiempoTransporte = tiempoTransporte + festivos;
            fechaLlegada = new Date(fecInicio.getTime() + (daymillseconds * tiempoTransporte));
        } else {//Calcula con festivos
            fechaLlegada = new Date(fecInicio.getTime() + (daymillseconds * tiempoTransporte));
        }
        return fechaLlegada;
    }

    public void cambiarProveedorAereo(ValueChangeEvent event) {
        this.rutaAerea = new StRutprov();
        this.rutaXprovvedorAerea = null;
        this.rutaXprovvedorAereaNombre = null;
        this.stDetcargAereaCurrent.setTie_transporte(null);
        this.stDetcargAereaCurrent.setTipDia(null);
        this.stDetcargAereaCurrent.setDetFecllegest(null);
    }

    public void cambioRutahandleSelect(ValueChangeEvent valueChangeEvent) {
        StRutprov rutprov = (StRutprov) valueChangeEvent.getNewValue();
        if (rutprov != null) {
            this.rutaXprovvedorMaritimo = rutprov.getIdPrincipal();
            this.rutaXprovvedorMaritimoNombre = rutprov.getRutConsec().getRutNombre();

            if (rutprov.getIndTiempos()) {
                this.stDetcargMaritimaCurrent.setTie_transporte((long) rutprov.getTieTransporte());
                this.stDetcargMaritimaCurrent.setTipDia(new StTipdia(rutprov.getTipDia(), rutprov.getTipDiaNombre()));

            } else {
                this.stDetcargMaritimaCurrent.setTie_transporte(null);
                this.stDetcargMaritimaCurrent.setTipDia(null);
                JsfUtil.addErrorMessage("La ruta no tiene tiempos definidos");
            }
            recalculaFechasMaritimas();
        }
    }

    public void cambioRutaDestinohandleSelect(ValueChangeEvent valueChangeEvent) {
        StRutprov rutprov = (StRutprov) valueChangeEvent.getNewValue();
        if (rutprov != null) {
            this.rutaXprovvedorDestino = rutprov.getIdPrincipal();
            this.rutaXprovvedorDestinoNombre = rutprov.getRutConsec().getRutNombre();

            if (rutprov.getIndTiempos()) {
                this.stDetcargDestinoCurrent.setTie_transporte((long) rutprov.getTieTransporte());
                this.stDetcargDestinoCurrent.setTipDia(new StTipdia(rutprov.getTipDia(), rutprov.getTipDiaNombre()));
            } else {
                this.stDetcargDestinoCurrent.setTie_transporte(null);
                this.stDetcargDestinoCurrent.setTipDia(null);
                JsfUtil.addErrorMessage("La ruta no tiene tiempos definidos");
            }
            recalculaFechasDestino();
        }
    }

    public void cambioRutaAereahandleSelect(ValueChangeEvent valueChangeEvent) {
        StRutprov rutprov = (StRutprov) valueChangeEvent.getNewValue();
        if (rutprov != null) {
            this.rutaXprovvedorAerea = rutprov.getIdPrincipal();
            this.rutaXprovvedorAereaNombre = rutprov.getRutConsec().getRutNombre();

            if (rutprov.getIndTiempos()) {
                this.stDetcargAereaCurrent.setTie_transporte((long) rutprov.getTieTransporte());
                this.stDetcargAereaCurrent.setTipDia(new StTipdia(rutprov.getTipDia(), rutprov.getTipDiaNombre()));
            } else {
                this.stDetcargAereaCurrent.setTie_transporte(null);
                this.stDetcargAereaCurrent.setTipDia(null);
                JsfUtil.addErrorMessage("La ruta no tiene tiempos definidos");
            }
            recalculaFechasAereas();
        }
    }

    public List<StRutprov> getRutasXProveedorMaritimo(String description) {
        List<StRutprov> results = new ArrayList<StRutprov>();
        if (this.proveedorMaritimo != null && this.clienteSeleccionado != null) {
            results = (List<StRutprov>) (stCargaService.getStRutasXProveedor(proveedorMaritimo, description, this.clienteSeleccionado.getCliConsec(), modTransportemaritimo));
        }
        return results;
    }

    public List<StRutprov> getRutasXProveedorDestino(String description) {
        List<StRutprov> results = new ArrayList<StRutprov>();
        if (this.proveedorDestino != null && this.clienteSeleccionado != null) {
            results = (List<StRutprov>) (stCargaService.getStRutasXProveedorDestino(proveedorDestino, description, this.clienteSeleccionado.getCliConsec()));
        }
        return results;
    }

    public List<StRutprov> getRutasXProveedorAereo(String description) {
        List<StRutprov> results = new ArrayList<StRutprov>();
        if (this.proveedorAerea != null && this.clienteSeleccionado != null) {
            results = (List<StRutprov>) (stCargaService.getStRutasXProveedor(proveedorAerea, description, this.clienteSeleccionado.getCliConsec(), modTransporteAereo));
        }
        return results;
    }

    public SelectItem[] getServiciosProveedor() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StTipserv> entities = stCargaService.findAllStServicio();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {

                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StTipserv x : entities) {
                items[i++] = new SelectItem(x.getTipConsec(), x.getTipNombre());
            }
        }
        return items;
    }

    public SelectItem[] getPuertosTransbordo() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StPuerto> entities = stCargaService.findAllStPuerto();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {

                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StPuerto x : entities) {
                String detalle;
                if (x.getUbiConsec().getUbiConsec() != 4) {
                    detalle = x.getPueNombre() + "-" + x.getUbiConsec().getUbiNombre();
                } else {
                    detalle = x.getPueNombre();
                }
                items[i++] = new SelectItem(x.getPueConsec(), detalle);
            }
        }
        return items;
    }

    public SelectItem[] getProveedoresDestino() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null && this.servicioProveedorDestino != null) {
            List<StCliprov> entities = stCargaService.getProveedorXServicio(this.clienteSeleccionado.getCliConsec(), this.servicioProveedorDestino);

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {

                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StCliprov x : entities) {
                items[i++] = new SelectItem(x.getProConsec().getProConsec(), x.getProConsec().getProNombre());
            }
        }
        return items;
    }

    public SelectItem[] getProveedoresMaritimos() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StCliprov> entities = stCargaService.getProveedorXServicio(this.clienteSeleccionado.getCliConsec(), this.servicioProveedorMaritimo);

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {

                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StCliprov x : entities) {
                items[i++] = new SelectItem(x.getProConsec().getProConsec(), x.getProConsec().getProNombre());
            }
        }
        return items;
    }

    public SelectItem[] getProveedoresAereos() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StCliprov> entities = stCargaService.getProveedorXServicio(this.clienteSeleccionado.getCliConsec(), this.servicioProveedorAereo);

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {

                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StCliprov x : entities) {
                items[i++] = new SelectItem(x.getProConsec().getProConsec(), x.getProConsec().getProNombre());
            }
        }
        return items;
    }

    public StCarga getStCargaCurrent() {
        return stCargaCurrent;
    }

    public void setStCargaCurrent(StCarga stCargaCurrent) {
        this.stCargaCurrent = stCargaCurrent;
    }

    public List<StDetcarg> getStDetcargsMaritimas() {
        return stDetcargsMaritimas;
    }

    public void setStDetcargsMaritimas(List<StDetcarg> stDetcargsMaritimas) {
        this.stDetcargsMaritimas = stDetcargsMaritimas;
    }

    public StDetcarg getStDetcargMaritimaCurrent() {
        return stDetcargMaritimaCurrent;
    }

    public void setStDetcargMaritimaCurrent(StDetcarg stDetcargMaritimaCurrent) {
        this.stDetcargMaritimaCurrent = stDetcargMaritimaCurrent;
    }

    public Boolean getFlagCargMaritima() {
        return flagCargMaritima;
    }

    public void setFlagCargMaritima(Boolean flagCargMaritima) {
        this.flagCargMaritima = flagCargMaritima;
    }

    public Long getProveedorMaritimo() {
        return proveedorMaritimo;
    }

    public void setProveedorMaritimo(Long proveedorMaritimo) {
        this.proveedorMaritimo = proveedorMaritimo;
    }

    public StRutprov getRutaMaritima() {
        return rutaMaritima;
    }

    public void setRutaMaritima(StRutprov rutaMaritima) {
        this.rutaMaritima = rutaMaritima;
    }

    public Boolean getFlagbtnfecestsalmaritimo() {
        return flagbtnfecestsalmaritimo;
    }

    public void setFlagbtnfecestsalmaritimo(Boolean flagbtnfecestsalmaritimo) {
        this.flagbtnfecestsalmaritimo = flagbtnfecestsalmaritimo;
    }

    public Boolean getFlagbtnfecrealsalmaritimo() {
        return flagbtnfecrealsalmaritimo;
    }

    public void setFlagbtnfecrealsalmaritimo(Boolean flagbtnfecrealsalmaritimo) {
        this.flagbtnfecrealsalmaritimo = flagbtnfecrealsalmaritimo;
    }

    public Boolean getFlagbtnfecestllegmaritimo() {
        return flagbtnfecestllegmaritimo;
    }

    public void setFlagbtnfecestllegmaritimo(Boolean flagbtnfecestllegmaritimo) {
        this.flagbtnfecestllegmaritimo = flagbtnfecestllegmaritimo;
    }

    public Boolean getFlagbtnfecrealllegmaritimo() {
        return flagbtnfecrealllegmaritimo;
    }

    public void setFlagbtnfecrealllegmaritimo(Boolean flagbtnfecrealllegmaritimo) {
        this.flagbtnfecrealllegmaritimo = flagbtnfecrealllegmaritimo;
    }

    public Boolean getFlagPanelCargMaritima() {
        return flagPanelCargMaritima;
    }

    public void setFlagPanelCargMaritima(Boolean flagPanelCargMaritima) {
        this.flagPanelCargMaritima = flagPanelCargMaritima;
    }

    public StCliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(StCliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public Long getRutaXprovvedorMaritimo() {
        return rutaXprovvedorMaritimo;
    }

    public void setRutaXprovvedorMaritimo(Long rutaXprovvedorMaritimo) {
        this.rutaXprovvedorMaritimo = rutaXprovvedorMaritimo;
    }

    public String getRutaXprovvedorMaritimoNombre() {
        return rutaXprovvedorMaritimoNombre;
    }

    public void setRutaXprovvedorMaritimoNombre(String rutaXprovvedorMaritimoNombre) {
        this.rutaXprovvedorMaritimoNombre = rutaXprovvedorMaritimoNombre;
    }

    public Boolean getAnadCargMaritima() {
        return AnadCargMaritima;
    }

    public void setAnadCargMaritima(Boolean AnadCargMaritima) {
        this.AnadCargMaritima = AnadCargMaritima;
    }

    /**
     * *****************CARGAAEREA**********************************
     */
    public List<StDetcarg> getStDetcargsAereas() {
        return stDetcargsAereas;
    }

    public void setStDetcargsAereas(List<StDetcarg> stDetcargsAereas) {
        this.stDetcargsAereas = stDetcargsAereas;
    }

    public StDetcarg getStDetcargAereaCurrent() {
        return stDetcargAereaCurrent;
    }

    public void setStDetcargAereaCurrent(StDetcarg stDetcargAereaCurrent) {
        this.stDetcargAereaCurrent = stDetcargAereaCurrent;
    }

    public Long getProveedorAerea() {
        return proveedorAerea;
    }

    public void setProveedorAerea(Long proveedorAerea) {
        this.proveedorAerea = proveedorAerea;
    }

    public StRutprov getRutaAerea() {
        return rutaAerea;
    }

    public void setRutaAerea(StRutprov rutaAerea) {
        this.rutaAerea = rutaAerea;
    }

    public Long getRutaXprovvedorAerea() {
        return rutaXprovvedorAerea;
    }

    public void setRutaXprovvedorAerea(Long rutaXprovvedorAerea) {
        this.rutaXprovvedorAerea = rutaXprovvedorAerea;
    }

    public String getRutaXprovvedorAereaNombre() {
        return rutaXprovvedorAereaNombre;
    }

    public void setRutaXprovvedorAereaNombre(String rutaXprovvedorAereaNombre) {
        this.rutaXprovvedorAereaNombre = rutaXprovvedorAereaNombre;
    }

    public Boolean getFlagPanelCargAerea() {
        return flagPanelCargAerea;
    }

    public void setFlagPanelCargAerea(Boolean flagPanelCargAerea) {
        this.flagPanelCargAerea = flagPanelCargAerea;
    }

    public Boolean getFlagCargAerea() {
        return flagCargAerea;
    }

    public void setFlagCargAerea(Boolean flagCargAerea) {
        this.flagCargAerea = flagCargAerea;
    }

    public Boolean getAnadCargAerea() {
        return AnadCargAerea;
    }

    public void setAnadCargAerea(Boolean AnadCargAerea) {
        this.AnadCargAerea = AnadCargAerea;
    }

    public Boolean getFlagbtnfecestsalAerea() {
        return flagbtnfecestsalAerea;
    }

    public void setFlagbtnfecestsalAerea(Boolean flagbtnfecestsalAerea) {
        this.flagbtnfecestsalAerea = flagbtnfecestsalAerea;
    }

    public Boolean getFlagbtnfecrealsalAerea() {
        return flagbtnfecrealsalAerea;
    }

    public void setFlagbtnfecrealsalAerea(Boolean flagbtnfecrealsalAerea) {
        this.flagbtnfecrealsalAerea = flagbtnfecrealsalAerea;
    }

    public Boolean getFlagbtnfecestllegAerea() {
        return flagbtnfecestllegAerea;
    }

    public void setFlagbtnfecestllegAerea(Boolean flagbtnfecestllegAerea) {
        this.flagbtnfecestllegAerea = flagbtnfecestllegAerea;
    }

    public Boolean getFlagbtnfecrealllegAerea() {
        return flagbtnfecrealllegAerea;
    }

    public void setFlagbtnfecrealllegAerea(Boolean flagbtnfecrealllegAerea) {
        this.flagbtnfecrealllegAerea = flagbtnfecrealllegAerea;
    }

    public List<StLogdetcarg> getStLogdetcargs() {
        return stLogdetcargs;
    }

    public void setStLogdetcargs(List<StLogdetcarg> stLogdetcargs) {
        this.stLogdetcargs = stLogdetcargs;
    }

    public StLogdetcarg getStLogdetcargCurrent() {
        return stLogdetcargCurrent;
    }

    public void setStLogdetcargCurrent(StLogdetcarg stLogdetcargCurrent) {
        this.stLogdetcargCurrent = stLogdetcargCurrent;
    }

    public List<StDetcarg> getStDetcargsDestino() {
        return stDetcargsDestino;
    }

    public void setStDetcargsDestino(List<StDetcarg> stDetcargsDestino) {
        this.stDetcargsDestino = stDetcargsDestino;
    }

    public StDetcarg getStDetcargDestinoCurrent() {
        return stDetcargDestinoCurrent;
    }

    public void setStDetcargDestinoCurrent(StDetcarg stDetcargDestinoCurrent) {
        this.stDetcargDestinoCurrent = stDetcargDestinoCurrent;
    }

    public Long getProveedorDestino() {
        return proveedorDestino;
    }

    public void setProveedorDestino(Long proveedorDestino) {
        this.proveedorDestino = proveedorDestino;
    }

    public StRutprov getRutaDestino() {
        return rutaDestino;
    }

    public void setRutaDestino(StRutprov rutaDestino) {
        this.rutaDestino = rutaDestino;
    }

    public Long getRutaXprovvedorDestino() {
        return rutaXprovvedorDestino;
    }

    public void setRutaXprovvedorDestino(Long rutaXprovvedorDestino) {
        this.rutaXprovvedorDestino = rutaXprovvedorDestino;
    }

    public String getRutaXprovvedorDestinoNombre() {
        return rutaXprovvedorDestinoNombre;
    }

    public void setRutaXprovvedorDestinoNombre(String rutaXprovvedorDestinoNombre) {
        this.rutaXprovvedorDestinoNombre = rutaXprovvedorDestinoNombre;
    }

    public Boolean getFlagPanelCargDestino() {
        return flagPanelCargDestino;
    }

    public void setFlagPanelCargDestino(Boolean flagPanelCargDestino) {
        this.flagPanelCargDestino = flagPanelCargDestino;
    }

    public Boolean getFlagCargDestino() {
        return flagCargDestino;
    }

    public void setFlagCargDestino(Boolean flagCargDestino) {
        this.flagCargDestino = flagCargDestino;
    }

    public Boolean getAnadCargDestino() {
        return AnadCargDestino;
    }

    public void setAnadCargDestino(Boolean AnadCargDestino) {
        this.AnadCargDestino = AnadCargDestino;
    }

    public Boolean getFlagbtnfecestsalDestino() {
        return flagbtnfecestsalDestino;
    }

    public void setFlagbtnfecestsalDestino(Boolean flagbtnfecestsalDestino) {
        this.flagbtnfecestsalDestino = flagbtnfecestsalDestino;
    }

    public Boolean getFlagbtnfecrealsalDestino() {
        return flagbtnfecrealsalDestino;
    }

    public void setFlagbtnfecrealsalDestino(Boolean flagbtnfecrealsalDestino) {
        this.flagbtnfecrealsalDestino = flagbtnfecrealsalDestino;
    }

    public Boolean getFlagbtnfecestllegDestino() {
        return flagbtnfecestllegDestino;
    }

    public void setFlagbtnfecestllegDestino(Boolean flagbtnfecestllegDestino) {
        this.flagbtnfecestllegDestino = flagbtnfecestllegDestino;
    }

    public Boolean getFlagbtnfecrealllegDestino() {
        return flagbtnfecrealllegDestino;
    }

    public void setFlagbtnfecrealllegDestino(Boolean flagbtnfecrealllegDestino) {
        this.flagbtnfecrealllegDestino = flagbtnfecrealllegDestino;

    }

    public Long getModTransporteDestino() {
        return modTransporteDestino;
    }

    public void setModTransporteDestino(Long modTransporteDestino) {
        this.modTransporteDestino = modTransporteDestino;
    }

    public Long getServicioProveedorDestino() {
        return servicioProveedorDestino;
    }

    public void setServicioProveedorDestino(Long servicioProveedorDestino) {
        this.servicioProveedorDestino = servicioProveedorDestino;
    }

    @FacesConverter(value = "stRutProvCargaConverter")
    public static class StRutProvCargaConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            try {
                if (value == null || value.length() == 0) {
                    return null;
                } else {
                    if (value != null && !value.equals("null")) {
                        TransporteController controller = (TransporteController) facesContext.getApplication().getELResolver().
                                getValue(facesContext.getELContext(), null, "transporteController");
                        StRutprov rutprov = controller.stCargaService.findStRutprovConverter(getKey(value), controller.proveedorMaritimo, "", controller.clienteSeleccionado.getCliConsec(), controller.modTransportemaritimo, controller.servicioProveedorMaritimo);
                        return rutprov;
                    } else {
                        return null;
                    }

                }
            } catch (Exception e) {
                System.err.println("error= " + e);
                return null;
            }
        }

        java.lang.Long getKey(String value) {
            try {
                if (value == null || value.length() == 0) {
                    return null;
                } else {
                    if (value != null) {
                        java.lang.Long key;
                        key = Long.valueOf(value);
                        return key;
                    } else {
                        return null;
                    }

                }
            } catch (Exception e) {
                System.err.println("error= " + e);
                return 1L;
            }
        }

        String getStringKey(java.lang.Long value) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(value);
                return sb.toString();
            } catch (Exception e) {
                System.err.println("error= " + e);
                return "";
            }
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            try {
                if (object == null) {
                    return null;
                }
                if (object instanceof StRutprov) {
                    StRutprov o = (StRutprov) object;
                    if (o.getRutRpconsec() == null) {
                        return null;
                    }

                    return getStringKey(Long.valueOf(o.getRutRpconsec().toString()));
                } else {
                    throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + StRutprov.class.getName());
                }
            } catch (Exception e) {
                System.err.println("error= " + e);
                return "";
            }
        }
    }

    @FacesConverter(value = "stRutProvCargaAereaConverter")
    public static class StRutProvCargaAereaConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            try {
                if (value == null || value.length() == 0) {
                    return null;
                } else {
                    if (value != null && !value.equals("null")) {
                        TransporteController controller = (TransporteController) facesContext.getApplication().getELResolver().
                                getValue(facesContext.getELContext(), null, "transporteController");

                        StRutprov rutprov = controller.stCargaService.findStRutprovConverter(getKey(value), controller.proveedorAerea, "", controller.clienteSeleccionado.getCliConsec(), controller.modTransporteAereo, controller.servicioProveedorAereo);
                        return rutprov;
                    } else {
                        return null;
                    }

                }
            } catch (Exception e) {
                System.err.println("error= " + e);
                return null;
            }
        }

        java.lang.Long getKey(String value) {
            try {
                if (value == null || value.length() == 0) {
                    return null;
                } else {
                    if (value != null) {
                        java.lang.Long key;
                        key = Long.valueOf(value);
                        return key;
                    } else {
                        return null;
                    }

                }
            } catch (Exception e) {
                System.err.println("error= " + e);
                return 1L;
            }
        }

        String getStringKey(java.lang.Long value) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(value);
                return sb.toString();
            } catch (Exception e) {
                System.err.println("error= " + e);
                return "";
            }
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            try {
                if (object == null) {
                    return null;
                }
                if (object instanceof StRutprov) {
                    StRutprov o = (StRutprov) object;
                    if (o.getRutRpconsec() == null) {
                        return null;
                    }

                    return getStringKey(Long.valueOf(o.getRutRpconsec().toString()));
                } else {
                    throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + StRutprov.class.getName());
                }
            } catch (Exception e) {
                System.err.println("error= " + e);
                return "";
            }
        }
    }

    @FacesConverter(value = "stRutProvCargaDestinoConverter")
    public static class StRutProvCargaDestinoConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            try {
                if (value == null || value.length() == 0) {
                    return null;
                } else {
                    if (value != null && !value.equals("null")) {
                        TransporteController controller = (TransporteController) facesContext.getApplication().getELResolver().
                                getValue(facesContext.getELContext(), null, "transporteController");
                        StRutprov rutprov = controller.stCargaService.findStRutprovDestinoConverter(getKey(value), controller.proveedorDestino, "", controller.clienteSeleccionado.getCliConsec(), controller.servicioProveedorDestino);
                        return rutprov;
                    } else {
                        return null;
                    }

                }
            } catch (Exception e) {
                System.err.println("error= " + e);
                return null;
            }
        }

        java.lang.Long getKey(String value) {
            try {
                if (value == null || value.length() == 0) {
                    return null;
                } else {
                    if (value != null) {
                        java.lang.Long key;
                        key = Long.valueOf(value);
                        return key;
                    } else {
                        return null;
                    }

                }
            } catch (Exception e) {
                System.err.println("error= " + e);
                return 1L;
            }
        }

        String getStringKey(java.lang.Long value) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(value);
                return sb.toString();
            } catch (Exception e) {
                System.err.println("error= " + e);
                return "";
            }
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            try {
                if (object == null) {
                    return null;
                }
                if (object instanceof StRutprov) {
                    StRutprov o = (StRutprov) object;
                    if (o.getRutRpconsec() == null) {
                        return null;
                    }

                    return getStringKey(Long.valueOf(o.getRutRpconsec().toString()));
                } else {
                    throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + StRutprov.class.getName());
                }
            } catch (Exception e) {
                System.err.println("error= " + e);
                return "";
            }
        }
    }

    public void agregaLog(StDetcarg stDetcarg) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String observaciones = "";
        StLogdetcarg stLogdetcarg = new StLogdetcarg();
        stLogdetcarg.setLogTraza(new Date());
        stLogdetcarg.setIndFecha(new Short("0"));

        System.err.println("agregaLog paso1");
        if (stDetcarg.getDetFecsalest() != null) {
            observaciones = "Fecha de salida estimada: " + dateFormat.format(stDetcarg.getDetFecsalest()) + ". ";
        }

        System.err.println("agregaLog paso2");
        if (stDetcarg.getDetFecrsalreal() != null) {
            observaciones = observaciones + "Fecha de salida Real: " + dateFormat.format(stDetcarg.getDetFecrsalreal()) + ". ";
        }

        System.err.println("agregaLog paso3");
        if (stDetcarg.getDetFecllegest() != null) {
            observaciones = observaciones + "Fecha de llegada estimada: " + dateFormat.format(stDetcarg.getDetFecllegest()) + ". ";
        }

        System.err.println("agregaLog paso4");
        if (stDetcarg.getDetFecllegreal() != null) {
            observaciones = observaciones + "Fecha de llegada real: " + dateFormat.format(stDetcarg.getDetFecllegreal()) + ". ";
        }

        System.err.println("agregaLog paso5");
        if (stDetcarg.getRutRpconsec() != null && stDetcarg.getRutRpconsec().getRutConsec().getRutNombre() != null) {
            observaciones = observaciones + "Ruta: " + stDetcarg.getRutRpconsec().getRutConsec().getRutNombre() + ". ";

            if (stDetcarg.getTie_transporte() != null) {
                observaciones = stDetcarg.getTie_transporte() + " " + stDetcarg.getTipDia() != null && stDetcarg.getTipDia().getTipConsec() != null ? (stDetcarg.getTipDia().getTipConsec() == 1) ? observaciones + "Tiempo: " + stDetcarg.getTie_transporte() + " Días Calendario. " : observaciones + "Tiempo: " + stDetcarg.getTie_transporte() + " Días Hábiles. " : "" + ". ";
            } else {
                observaciones = observaciones + "Tiempos no definidos.";
            }
        }

        System.err.println("agregaLog paso6");
        stLogdetcarg.setLogObserv("Actualización datos de transporte. " + observaciones);
        stLogdetcarg.setDetConsec(stDetcarg);
        System.err.println("agregaLog paso7");
        StUsuario stUsuario = CeUtil.getUsuarioSesion();
        stLogdetcarg.setLogUsu(stUsuario.getUsuNomred());

        System.err.println("agregaLog paso8");
        if (observaciones.length() > 0) {
            stCargaService.createStLogdetcarg(stLogdetcarg);
        }
    }

    public List<StTransbo> getStTransbos() {
        return stTransbos;
    }

    public void setStTransbos(List<StTransbo> stTransbos) {
        this.stTransbos = stTransbos;
    }

    public StTransbo getStTransboCurrent() {
        return stTransboCurrent;
    }

    public void setStTransboCurrent(StTransbo stTransboCurrent) {
        this.stTransboCurrent = stTransboCurrent;
    }

    public Boolean getFlagPanelTransbordo() {
        return flagPanelTransbordo;
    }

    public void setFlagPanelTransbordo(Boolean flagPanelTransbordo) {
        this.flagPanelTransbordo = flagPanelTransbordo;
    }

    public Boolean getFlagTransbordo() {
        return flagTransbordo;
    }

    public void setFlagTransbordo(Boolean flagTransbordo) {
        this.flagTransbordo = flagTransbordo;
    }

    public Long getPuertoTransbordo() {
        return puertoTransbordo;
    }

    public void setPuertoTransbordo(Long puertoTransbordo) {
        this.puertoTransbordo = puertoTransbordo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
