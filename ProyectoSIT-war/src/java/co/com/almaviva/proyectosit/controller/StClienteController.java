/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.controller;

import co.com.almaviva.proyectosit.business.StClienteServiceBeanLocal;
import co.com.almaviva.proyectosit.controller.util.JsfUtil;
import co.com.almaviva.proyectosit.controller.util.SitUtil;
import co.com.almaviva.proyectosit.entity.StCategor;
import co.com.almaviva.proyectosit.entity.StCliente;
import co.com.almaviva.proyectosit.entity.StCliprov;
import co.com.almaviva.proyectosit.entity.StDepclie;
import co.com.almaviva.proyectosit.entity.StDepcliePK;
import co.com.almaviva.proyectosit.entity.StDeposit;
import co.com.almaviva.proyectosit.entity.StDiasema;
import co.com.almaviva.proyectosit.entity.StHorario;
import co.com.almaviva.proyectosit.entity.StModelo;
import co.com.almaviva.proyectosit.entity.StProduct;
import co.com.almaviva.proyectosit.entity.StProveed;
import co.com.almaviva.proyectosit.entity.StRuta;
import co.com.almaviva.proyectosit.entity.StRutprov;
import co.com.almaviva.proyectosit.entity.StTiempos;
import co.com.almaviva.proyectosit.entity.StTipdia;
import co.com.almaviva.proyectosit.entity.StTippro;
import co.com.almaviva.proyectosit.entity.StTipprod;
import co.com.almaviva.proyectosit.entity.StTipserv;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
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
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author egonzalm
 */
@ManagedBean(name = "stClienteController")
@SessionScoped
public class StClienteController implements Serializable {

    private static final long serialVersionUID = -3142112831903388235L;

    @EJB
    private StClienteServiceBeanLocal PersistenciaClienteControllerEJB;

    //Variable de TabView
    private boolean flagPrepareEdit = false;
    //Panel de busqueda
    private StCliente clienteSeleccionado;

    //variables contexto proveedor logistico
    private Long lstProveed;
    private Long lstTipserv;
    private boolean activadorPanelAsociacionEdicionLog;
    private boolean activadorComponentesAsociarLog;
    private boolean activadorBotonActualizarLog;

    //variables contexto proveedor producto
    private Long lproveePro;
    private Long lproducto;
    private StCliprov stCliProvcurrent;
    private StCliprov proveedCurrent;
    private boolean activadorComponentesAsociarProducto;
    private boolean activadorPanelAsociacionEdicionProveePro;
    private boolean activadorBotonActualizarProducto;

    //variables contexto producto
    private StProduct currentProduct;
    private Long longProduct;
    private String codProducto;
    private String pdescripcion;
    private Long lcatConsec;
    private Long lmodConsec;
    private Long ltipprodConsec;
    private boolean activadorPanelCreacionEdicionProducto;

    //variables contexto tiempos normales
    private boolean activadorPanelEdicionCreacionTiempos;
    private StTiempos stTiempos;
    private Long longProveelog;
    private StRutprov stRutprov;
    private Short tiempoTransporte;
    private Long tipoDias;
    private Long tipoDiasGeneral;
    private Long ltipoServ;
    private boolean activadorItemsEdicionTiempos;
    private boolean activadorItemsCreacionTiempos;

    //variables contexto tiempos generales
    private StTiempos stTiemposGenerales;
    private Short horasPreinspeccion;
    private Short horasAduana;
    private Short horasOnHand;
    private boolean activadorPanelEdicionCreacionTiemposGenerales;
    private boolean activadorItemsCreacionTiemposGenerales;
    private Long longProveeGeneral;

    //variables de contexto horarios
    private boolean activadorPanelEdicionCreacionHorarios;
    private Long lproveeHorario;
    private boolean activadorProveedorHorario;
    private Long tipoDiaHora;
    private Date horaInicio;
    private Date horaFin;
    private StHorario stHorario;

    //variables de contexto deposito
    private boolean activadorPanelEdicionCreacionDepositos;
    private Long longDeposito;

    public StClienteController() {
        //variable cliente seleccionado
        this.clienteSeleccionado = new StCliente();
        //variables contexto proveedor logistico
        this.lstProveed = null;
        this.lstTipserv = null;
        this.activadorComponentesAsociarLog = false;
        this.activadorPanelAsociacionEdicionLog = false;
        this.activadorBotonActualizarLog = false;
        //variables contexto proveedor producto
        this.proveedCurrent = new StCliprov();
        this.stCliProvcurrent = new StCliprov();
        this.lproducto = null;
        this.lproveePro = null;
        this.flagPrepareEdit = false;
        this.activadorPanelAsociacionEdicionProveePro = false;
        this.activadorComponentesAsociarProducto = false;
        //variables contexto producto
        this.currentProduct = new StProduct();
        this.lcatConsec = null;
        this.lmodConsec = null;
        this.longProduct = null;
        this.ltipprodConsec = null;
        this.codProducto = new String();
        this.pdescripcion = new String();
        this.activadorPanelCreacionEdicionProducto = false;
        //variables contexto tiempos
        this.activadorPanelEdicionCreacionTiempos = false;
        this.stTiempos = new StTiempos();
        this.longProveelog = null;
        this.stRutprov = new StRutprov();
        this.tiempoTransporte = null;
        this.tipoDias = null;
        this.ltipoServ = null;
        this.activadorItemsEdicionTiempos = false;
        this.activadorItemsCreacionTiempos = false;
        //variables contexto tiempos generales
        this.stTiemposGenerales = new StTiempos();
        this.horasPreinspeccion = null;
        this.horasAduana = null;
        this.horasOnHand = null;
        this.activadorPanelEdicionCreacionTiemposGenerales = false;
        this.activadorItemsCreacionTiemposGenerales = false;
        this.longProveeGeneral = null;
        this.tipoDiasGeneral = null;
        //variables contexto horarios
        this.activadorPanelEdicionCreacionHorarios = false;
        this.lproveeHorario = null;
        this.activadorProveedorHorario = false;
        this.tipoDiaHora = null;
        this.horaInicio = null;
        this.stHorario = null;
        this.horaFin = null;
        //variables de contexto deposito
        this.activadorPanelEdicionCreacionDepositos = false;
        this.longDeposito = null;
    }

    public void init() {

    }

    public List<StCliente> findAllClienteDescription(String description) throws Exception {
        List<StCliente> results = (List<StCliente>) (PersistenciaClienteControllerEJB.findAllClienteDescription(description));
        return results;
    }

    public void cambioTab(TabChangeEvent e) {
        String tabActual = e.getTab().getId();
        if (tabActual.equals("tab1")) {

        } else if (tabActual.equals("tab2")) {
            this.activadorPanelAsociacionEdicionProveePro = false;
            this.activadorPanelAsociacionEdicionLog = false;
        } else if (tabActual.equals("tab3")) {
            this.activadorPanelCreacionEdicionProducto = false;

        } else if (tabActual.equals("tab4")) {
            //variables contexto tiempos
            this.stTiempos = new StTiempos();
            this.longProveelog = null;
            this.stRutprov = new StRutprov();
            this.tiempoTransporte = null;
            this.tipoDias = null;
            this.ltipoServ = null;
            this.activadorItemsEdicionTiempos = false;
            this.activadorItemsCreacionTiempos = false;
            this.activadorPanelEdicionCreacionTiempos = false;
            //variables contexto tiempos generales
            this.stTiemposGenerales = new StTiempos();
            this.horasPreinspeccion = null;
            this.horasAduana = null;
            this.activadorPanelEdicionCreacionTiemposGenerales = false;
            this.activadorItemsCreacionTiemposGenerales = false;
            this.longProveeGeneral = null;
            this.tipoDiasGeneral = null;
        } else if (tabActual.equals("tab5")) {
            this.stHorario = null;
            this.horaInicio = null;
            this.horaFin = null;
            this.tipoDiaHora = null;
            this.lproveeHorario = null;
            this.activadorPanelEdicionCreacionHorarios = false;
            this.activadorProveedorHorario = false;

        } else if (tabActual.equals("tab6")) {
            this.activadorPanelEdicionCreacionDepositos = false;
            this.longDeposito = null;
        }

    }

    public void handleSelect(ValueChangeEvent valueChangeEvent) {
        System.err.println("handleSelect");
//        this.flagPrepareEdit = false;
//        this.current = this.clienteSeleccionado;
//        this.visMesOk = "true";
//        StCliente ceTerceroTemp = new StCliente();
//        ceTerceroTemp = (StCliente) valueChangeEvent.getNewValue();
//        if (ceTerceroTemp != null && ceTerceroTemp.getTerNit() != null) {
//            this.flagPrepareEdit = true;
//        }
    }

    public void probando() {
//        this.flagPrepareEdit = false;
        if (this.clienteSeleccionado != null) {
            this.flagPrepareEdit = true;

//            this.clienteEncontrado = null;
//            this.clienteEncontrado = new StCliente();
//            this.clienteEncontrado = this.clienteSeleccionado;
//            prepareEdit(this.clienteSeleccionado);
            System.out.println("prueba commit");
        }
    }

    public void asociarProveedorLogistico() {

        if (clienteSeleccionado != null) {
            //cliente seleccionado del autocompletar
            stCliProvcurrent.setCliConsec(clienteSeleccionado);
            //long de tipo servicio seleccionado del combo
            stCliProvcurrent.setTipServic(new StTipserv(lstTipserv));
            //long de tipo proveedor logistico seleccionado del combo
            stCliProvcurrent.setProConsec(new StProveed(lstProveed));
            //objeto tipoProveedor con valor 2 (proveedor logistico)
            StTippro stTippro = new StTippro(new Long("2"), "2", "Proveedor Logistico");
            stCliProvcurrent.setTipProveed(stTippro);
            //se persiste el objeto cliprov
            try {
                PersistenciaClienteControllerEJB.create(stCliProvcurrent);
                JsfUtil.addSuccessMessage("El proveedor logistico se ha asociado correctamente al servicio");
            } catch (Exception e) {
                JsfUtil.addErrorMessage("Error al asociar el proveedor logistico al servicio");
            }
            this.lstTipserv = null;
            this.lstProveed = null;
            stCliProvcurrent = new StCliprov();
            this.activadorPanelAsociacionEdicionLog = false;
        }

    }

    public void asociarProveedorProducto() {
        if (clienteSeleccionado != null) {
            //cliente seleccionado del autocompletar
            stCliProvcurrent.setCliConsec(clienteSeleccionado);
            //long de tipo producto seleccionado del combo
            stCliProvcurrent.setTipProduc(new StTipprod(lproducto));
            //long de tipo proveedor de producto seleccionado del combo
            stCliProvcurrent.setProConsec(new StProveed(lproveePro));
            //objeto tipoProveedor con valor 1 (proveedor de producto)
            StTippro stTippro = new StTippro(new Long("1"), "1", "Proveedor de Producto");
            stCliProvcurrent.setTipProveed(stTippro);
            //se persiste el objeto cliprov
            try {
                PersistenciaClienteControllerEJB.create(stCliProvcurrent);
                JsfUtil.addSuccessMessage("El proveedor de producto se ha asociado correctamente al tipo de producto");
            } catch (Exception e) {
                JsfUtil.addErrorMessage("Error al asociar el proveedor de producto al tipo de producto");
            }
            this.lproducto = null;
            this.lproveePro = null;
            stCliProvcurrent = new StCliprov();
            this.activadorPanelAsociacionEdicionProveePro = false;
        }
    }

    public void creacionEdicionProducto() {

        if (clienteSeleccionado != null) {

            //editar producto
            if (currentProduct != null) {

                try {
                    if (SitUtil.validaCamposNoVaciosProducto(codProducto, lcatConsec, lmodConsec, ltipprodConsec)) {
                        currentProduct.setProCodigo(codProducto);
                        currentProduct.setProDescri(pdescripcion);
                        currentProduct.setCatConsec(new StCategor(lcatConsec));
                        currentProduct.setModConsec(new StModelo(lmodConsec));
                        currentProduct.setTipConsec(new StTipprod(ltipprodConsec));
                        PersistenciaClienteControllerEJB.updateProductCurrent(currentProduct);
                        JsfUtil.addSuccessMessage("Edición del producto " + currentProduct.getProDescri() + " exitosa");
                        this.currentProduct = new StProduct();
                        this.codProducto = null;
                        this.pdescripcion = null;
                        this.lcatConsec = null;
                        this.lmodConsec = null;
                        this.ltipprodConsec = null;
                        this.activadorPanelCreacionEdicionProducto = false;
                    }

                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al actualizar la informacion del producto " + currentProduct.getProDescri());
                } finally {
//                    this.currentProduct = new StProduct();
//                    this.codProducto = null;
//                    this.pdescripcion = null;
//                    this.lcatConsec = null;
//                    this.lmodConsec = null;
//                    this.ltipprodConsec = null;
//                    this.activadorPanelCreacionEdicionProducto = false;
                }
            } //crear producto
            else {

                try {
                    if (SitUtil.validaCamposNoVaciosProducto(codProducto, lcatConsec, lmodConsec, ltipprodConsec)) {
                        currentProduct = new StProduct();
                        currentProduct.setProCodigo(codProducto);
                        currentProduct.setProDescri(pdescripcion);
                        currentProduct.setCatConsec(new StCategor(lcatConsec));
                        currentProduct.setModConsec(new StModelo(lmodConsec));
                        currentProduct.setTipConsec(new StTipprod(ltipprodConsec));
                        currentProduct.setCliConsec(clienteSeleccionado);
                        PersistenciaClienteControllerEJB.create(currentProduct);
                        JsfUtil.addSuccessMessage("Registro del producto " + currentProduct.getProDescri() + " exitoso");
                        this.currentProduct = new StProduct();
                        this.codProducto = null;
                        this.pdescripcion = null;
                        this.lcatConsec = null;
                        this.lmodConsec = null;
                        this.ltipprodConsec = null;
                        this.activadorPanelCreacionEdicionProducto = false;

                    }
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al crear el producto " + currentProduct.getProDescri());
                } finally {
//                    this.currentProduct = new StProduct();
//                    this.codProducto = null;
//                    this.pdescripcion = null;
//                    this.lcatConsec = null;
//                    this.lmodConsec = null;
//                    this.ltipprodConsec = null;
//                    this.activadorPanelCreacionEdicionProducto = false;
                }
            }
        }
    }

    public void creacionEdicionTiempo() {
        //crear
        if (stTiempos.getTieConsec() == null) {
            try {
                //valor ingresado por front
                if (SitUtil.validaCamposNoVaciosTiempo("C", tiempoTransporte, tipoDias, stRutprov, ltipoServ, longProveelog)) {

                    stTiempos.setTieTransporte(tiempoTransporte);
                    //valor seleccionado del combo tipo dias
                    stTiempos.setTipConsec(new StTipdia(tipoDias));
                    //Ruta por proveedor logistico seleccionada del autocomplete
                    stTiempos.setRutRpconsec(stRutprov);
                    //valor seleccionado del combo proveedor logistico

                    stTiempos.setCliTpcconsec(PersistenciaClienteControllerEJB.findIdCliProveeXtiempo(ltipoServ, longProveelog));

                    PersistenciaClienteControllerEJB.create(stTiempos);
                    JsfUtil.addSuccessMessage("El tiempo para el proveedor " + stTiempos.getCliTpcconsec().getProConsec().getProNombre() + " registrado exitosamente");
                    stTiempos = new StTiempos();
                    tiempoTransporte = null;
                    tipoDias = null;
                    stRutprov = null;
                    ltipoServ = null;
                    longProveelog = null;
                    this.activadorPanelEdicionCreacionTiempos = false;

                }
            } catch (Exception e) {

                JsfUtil.addErrorMessage("Error al registrar el tiempo");
            } finally {
//                stTiempos = new StTiempos();
//                tiempoTransporte = null;
//                tipoDias = null;
//                stRutprov = null;
//                ltipoServ = null;
//                longProveelog = null;

            }
            //editar
        } else {
            try {
                if (SitUtil.validaCamposNoVaciosTiempo("E", tiempoTransporte, tipoDias, stRutprov, ltipoServ, longProveelog)) {
                    //valor ingresado por front
                    stTiempos.setTieTransporte(tiempoTransporte);
                    //valor seleccionado del combo tipo dias
                    stTiempos.setTipConsec(new StTipdia(tipoDias));

                    PersistenciaClienteControllerEJB.updateTimeCurrent(stTiempos);
                    JsfUtil.addSuccessMessage("El tiempo para el proveedor " + stTiempos.getCliTpcconsec().getProConsec().getProNombre() + " actualizado exitosamente");
                    stTiempos = new StTiempos();
                    tiempoTransporte = null;
                    tipoDias = null;
                    stRutprov = null;
                    ltipoServ = null;
                    longProveelog = null;
                    this.activadorPanelEdicionCreacionTiempos = false;

                }
            } catch (Exception e) {
                JsfUtil.addErrorMessage("Error al actualizar el tiempo");
            } finally {
//                stTiempos = new StTiempos();
//                tiempoTransporte = null;
//                tipoDias = null;
//                stRutprov = null;
//                ltipoServ = null;
//                longProveelog = null;

            }
        }

    }

    public void creacionEdicionTiempoGeneral() {
        if (stTiemposGenerales.getTieConsec() == null) {
            try {
                if (SitUtil.validaCamposNoVaciosTiempoGeneral("C", longProveeGeneral, horasPreinspeccion, horasAduana, tipoDiasGeneral)) {
                    stTiemposGenerales.setCliTpcconsec(new StCliprov(longProveeGeneral));
                    stTiemposGenerales.setNumHorHabPreinspeccion(horasPreinspeccion);
                    stTiemposGenerales.setNumHorHabAduana(horasAduana);
                    stTiemposGenerales.setTipConsec(new StTipdia(tipoDiasGeneral));
                    PersistenciaClienteControllerEJB.create(stTiemposGenerales);
                    JsfUtil.addSuccessMessage("El tiempo registrado exitosamente");
                    this.stTiemposGenerales = new StTiempos();
                    this.longProveeGeneral = null;
                    this.horasPreinspeccion = null;
                    this.horasAduana = null;
                    this.horasOnHand = null;
                    this.tipoDiasGeneral = null;
                    this.activadorPanelEdicionCreacionTiemposGenerales = false;

                }
            } catch (Exception e) {
                JsfUtil.addErrorMessage("Error al registrar el tiempo");
            } finally {
//                this.stTiemposGenerales = new StTiempos();
//                this.longProveeGeneral = null;
//                this.horasPreinspeccion = null;
//                this.horasAduana = null;
//                this.horasOnHand = null;
//                this.tipoDiasGeneral = null;
//                this.activadorPanelEdicionCreacionTiemposGenerales = false;
            }
        } else {
            try {
                if (SitUtil.validaCamposNoVaciosTiempoGeneral("E", longProveeGeneral, horasPreinspeccion, horasAduana, tipoDiasGeneral)) {
                    stTiemposGenerales.setCliTpcconsec(new StCliprov(longProveeGeneral));
                    stTiemposGenerales.setNumHorHabPreinspeccion(horasPreinspeccion);
                    stTiemposGenerales.setNumHorHabAduana(horasAduana);
                    stTiemposGenerales.setTipConsec(new StTipdia(tipoDiasGeneral));
                    PersistenciaClienteControllerEJB.updateTimeCurrent(stTiemposGenerales);
                    JsfUtil.addSuccessMessage("El tiempo para el proveedor actualizado exitosamente");
                    this.stTiemposGenerales = new StTiempos();
                    this.longProveeGeneral = null;
                    this.horasPreinspeccion = null;
                    this.horasAduana = null;
                    this.horasOnHand = null;
                    this.tipoDiasGeneral = null;
                    this.activadorPanelEdicionCreacionTiemposGenerales = false;

                }
            } catch (Exception e) {
                JsfUtil.addErrorMessage("Error al registrar el tiempo");
            } finally {
//                this.stTiemposGenerales = new StTiempos();
//                this.longProveeGeneral = null;
//                this.horasPreinspeccion = null;
//                this.horasAduana = null;
//                this.horasOnHand = null;
//                this.tipoDiasGeneral = null;
//                this.activadorPanelEdicionCreacionTiemposGenerales = false;
            }

        }

    }

    public void creacionEdicionHorario() {

        if (stHorario == null) {
            try {
                if (SitUtil.validarCamposHorarios("C", lproveeHorario, tipoDiaHora, horaInicio, horaFin)) {
                    stHorario = new StHorario();
                    stHorario.setHorInicio(SitUtil.convertirAsegundos(horaInicio));
                    stHorario.setHorFin(SitUtil.convertirAsegundos(horaFin));
                    stHorario.setDiaConsec(new StDiasema(tipoDiaHora));
                    stHorario.setCliTpcconsec(new StCliprov(lproveeHorario));
                    PersistenciaClienteControllerEJB.create(stHorario);
                    JsfUtil.addSuccessMessage("El horario registrado exitosamente");
                    this.stHorario = null;
                    this.horaInicio = null;
                    this.horaFin = null;
                    this.tipoDiaHora = null;
                    this.lproveeHorario = null;
                    this.activadorPanelEdicionCreacionHorarios = false;
                    this.activadorProveedorHorario = false;
                }
            } catch (Exception e) {

                this.stHorario = null;
                JsfUtil.addErrorMessage("Error al registrar el horario (No se puede asignar varios horarios al proveedor el mismo dia)");
            }
        } else {
            try {
                if (SitUtil.validarCamposHorarios("E", lproveeHorario, tipoDiaHora, horaInicio, horaFin)) {
                    stHorario.setHorInicio(SitUtil.convertirAsegundos(horaInicio));
                    stHorario.setHorFin(SitUtil.convertirAsegundos(horaFin));
                    stHorario.setDiaConsec(new StDiasema(tipoDiaHora));
                    stHorario.setCliTpcconsec(new StCliprov(lproveeHorario));
                    PersistenciaClienteControllerEJB.updateHoraryCurrent(stHorario);
                    JsfUtil.addSuccessMessage("El horario para el proveedor seleccionado se ha actualizado exitosamente");
                    this.stHorario = null;
                    this.horaInicio = null;
                    this.horaFin = null;
                    this.tipoDiaHora = null;
                    this.lproveeHorario = null;
                    this.activadorPanelEdicionCreacionHorarios = false;
                }
            } catch (Exception e) {
                JsfUtil.addErrorMessage("Error al actualizar el horario");
            }
        }

    }

    public void creacionEdicionDeposito() {
        try {
            if (clienteSeleccionado != null) {
                if (longDeposito != null) {
                    StDepclie stDepclie = new StDepclie(new StDepcliePK(longDeposito, clienteSeleccionado.getCliConsec()));
                    PersistenciaClienteControllerEJB.create(stDepclie);
                    JsfUtil.addSuccessMessage("El deposito se ha asociado exitosamente al cliente: ".concat(clienteSeleccionado.getCliNombre()));
                    this.activadorPanelEdicionCreacionDepositos = false;
                    this.longDeposito = null;
                } else {
                    JsfUtil.addErrorMessage("El combo Deposito debe tener alguna seleccion");
                }
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al asociar el deposito al cliente: ".concat(clienteSeleccionado.getCliNombre()));
            this.activadorPanelEdicionCreacionDepositos = false;
            this.longDeposito = null;
        }
    }
//metodo que activa el panel de asociacion proveedor Logistico

    public void activaFormularioAsociacionLog() {
        this.activadorPanelAsociacionEdicionLog = true;
        this.activadorComponentesAsociarLog = true;
        this.activadorBotonActualizarLog = false;
        //banderas panel formulario asociacion proveedores producto
        this.activadorPanelAsociacionEdicionProveePro = false;
        this.activadorComponentesAsociarProducto = false;
        this.activadorBotonActualizarProducto = false;
    }
//metodo que activa el panel de asociacion proveedor producto

    public void activaFormularioAsociacionProveePro() {
        this.activadorPanelAsociacionEdicionProveePro = true;
        this.activadorComponentesAsociarProducto = true;
        this.activadorBotonActualizarProducto = false;
        //banderas panel formulario asociacion proveedores logisticos
        this.activadorPanelAsociacionEdicionLog = false;
        this.activadorComponentesAsociarLog = false;
        this.activadorBotonActualizarLog = false;
    }
//metodo que activa el panel de edicion de proveedor Logistico

    public void editarProveedorLogistico(StCliprov cliprov) {
        this.activadorComponentesAsociarLog = false;
        this.proveedCurrent = cliprov;
        this.lstTipserv = cliprov.getTipServic().getTipConsec();
        this.activadorPanelAsociacionEdicionLog = true;
        this.activadorBotonActualizarLog = true;
        //banderas para ocultar panel de edicion de Proveedores de producto
        this.activadorPanelAsociacionEdicionProveePro = false;
        this.activadorBotonActualizarProducto = false;
    }

    //metodo que activa el panel de edicion de proveedor de producto
    public void editarProveedorProducto(StCliprov cliprov) {
        this.activadorComponentesAsociarProducto = false;
        this.proveedCurrent = cliprov;
        this.lproducto = cliprov.getTipProduc().getTipConsec();
        this.activadorPanelAsociacionEdicionProveePro = true;
        this.activadorBotonActualizarProducto = true;
        //banderas para ocultar panel de edicion de Proveedores logisticos 
        this.activadorPanelAsociacionEdicionLog = false;
        this.activadorBotonActualizarLog = false;
    }

    //metodo que activa el panel de edicion del producto
    public void editarProducto(StProduct stProduct) {
        this.activadorPanelCreacionEdicionProducto = true;
        this.currentProduct = stProduct;
        this.longProduct = currentProduct.getProConsec();
        this.codProducto = currentProduct.getProCodigo();
        this.pdescripcion = currentProduct.getProDescri();
        this.lcatConsec = currentProduct.getCatConsec().getCatConsec();
        this.lmodConsec = currentProduct.getModConsec().getModConsec();
        this.ltipprodConsec = currentProduct.getTipConsec().getTipConsec();
    }

    //metodo que activa el panel de edicion del tiempo
    public void editarTiempo(StTiempos tiempos) {
        this.stTiempos = tiempos;
        this.tipoDias = tiempos.getTipConsec().getTipConsec();
        this.tiempoTransporte = tiempos.getTieTransporte();
        this.activadorItemsCreacionTiempos = false;
        this.activadorPanelEdicionCreacionTiempos = true;
        this.activadorPanelEdicionCreacionTiemposGenerales = false;
    }

    //metodo que activa el panel de edicion del tiempo general
    public void editarTiempoGeneral(StTiempos tiempos) {
        this.stTiemposGenerales = tiempos;
        this.longProveeGeneral = tiempos.getCliTpcconsec().getCliTpcconsec();
        this.horasPreinspeccion = tiempos.getNumHorHabPreinspeccion();
        this.horasAduana = tiempos.getNumHorHabAduana();
        this.tipoDiasGeneral = tiempos.getTipConsec().getTipConsec();
        this.activadorPanelEdicionCreacionTiemposGenerales = true;
        this.activadorPanelEdicionCreacionTiempos = false;
        this.activadorItemsCreacionTiemposGenerales = false;
    }

    //metodo que activa el panel de edicion del horario
    public void editarHorario(StHorario contextHorario) {
        this.stHorario = PersistenciaClienteControllerEJB.findHorarioByid(contextHorario.getHorConsec());
        this.horaInicio = SitUtil.convertirADate(stHorario.getHorInicio());
        this.horaFin = SitUtil.convertirADate(stHorario.getHorFin());
        this.tipoDiaHora = stHorario.getDiaConsec().getDiaConsec();
        this.lproveeHorario = stHorario.getCliTpcconsec().getCliTpcconsec();
        this.activadorPanelEdicionCreacionHorarios = true;
        this.activadorProveedorHorario = false;
    }

    //metodo que activa el panel creacion de nuevo tiempo
    public void habilitarPanelcrearTiempo() {
        this.ltipoServ = null;
        this.stTiempos = new StTiempos();
        this.tipoDias = null;
        this.tiempoTransporte = null;
        this.activadorPanelEdicionCreacionTiempos = true;
        this.activadorItemsCreacionTiempos = true;
        this.activadorPanelEdicionCreacionTiemposGenerales = false;
    }

    //metodo que activa el panel creacion de nuevo tiempo general
    public void habilitarPanelcrearTiempoGeneral() {

        if (PersistenciaClienteControllerEJB.findAllProvByServGeneral(clienteSeleccionado).isEmpty()) {
            this.activadorPanelEdicionCreacionTiemposGenerales = false;
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_WARN, "", "No existen proveedores disponibles para asignar tiempos"));
        } else {
            this.activadorPanelEdicionCreacionTiemposGenerales = true;
        }
        this.longProveeGeneral = null;
        this.stTiemposGenerales = new StTiempos();
        this.horasPreinspeccion = null;
        this.tipoDiasGeneral = null;
        this.horasAduana = null;
        this.horasOnHand = null;
        this.activadorItemsCreacionTiemposGenerales = true;
        this.activadorPanelEdicionCreacionTiempos = false;
    }

    public void habilitarPanelCrearEditarHorario() {
        this.stHorario = null;
        this.horaInicio = null;
        this.horaFin = null;
        this.tipoDiaHora = null;
        this.lproveeHorario = null;
        this.activadorPanelEdicionCreacionHorarios = true;
        this.activadorProveedorHorario = true;

    }

    //metodo que activa el panel creacion de nuevo deposito
    public void habilitarPanelCrearEditarDeposito() {
        if (PersistenciaClienteControllerEJB.findAllDepositUnassociated().isEmpty()) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_WARN, "", "No existen depositos disponibles para asignar al cliente: ".concat(clienteSeleccionado.getCliNombre())));
            this.activadorPanelEdicionCreacionDepositos = false;
        } else {
            this.activadorPanelEdicionCreacionDepositos = true;
        }
        this.longDeposito = null;
    }

    //metodo que activa el panel creacion de nuevo producto
    public void crearProducto() {
        this.activadorPanelCreacionEdicionProducto = true;
        this.currentProduct = null;
    }

    //metodo que actualiza los datos del proveedor logistico
    public void updateProveedorLog() {
        StTipserv stTipser = new StTipserv(lstTipserv);

        proveedCurrent.setTipServic(stTipser);
        try {
            PersistenciaClienteControllerEJB.updateProveedCurrent(proveedCurrent);
            JsfUtil.addSuccessMessage("Edición del proveedor " + proveedCurrent.getProConsec().getProNombre() + " exitosa");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al editar la informacion del proveedor " + proveedCurrent.getProConsec().getProNombre());
        }
        this.lstTipserv = null;
        this.activadorPanelAsociacionEdicionLog = false;
        proveedCurrent = new StCliprov();
    }

    //metodo que actualiza los datos del proveedor de producto
    public void updateProveedorPro() {
        StTipprod stTipprod = new StTipprod(lproducto);

        proveedCurrent.setTipProduc(stTipprod);
        try {
            PersistenciaClienteControllerEJB.updateProveedCurrent(proveedCurrent);
            JsfUtil.addSuccessMessage("Edición del proveedor " + proveedCurrent.getProConsec().getProNombre() + " exitosa");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al editar la informacion del proveedor " + proveedCurrent.getProConsec().getProNombre());
        }
        this.lproducto = null;
        this.activadorPanelAsociacionEdicionProveePro = false;
        proveedCurrent = new StCliprov();
    }
    //metodo que elimina el tiempo

    public void eliminarDeposito(StDepclie stDepclie) {
        try {
            PersistenciaClienteControllerEJB.remove(stDepclie);
            JsfUtil.addSuccessMessage("El deposito se ha eliminado exitosamente.");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("El deposito no se puede eliminar porque puede estar asociado a una carga");
        } finally {
            this.activadorPanelEdicionCreacionDepositos = false;
        }
    }

    //metodo que elimina el tiempo
    public void eliminarTiempo(StTiempos tiempos) {
        try {
            PersistenciaClienteControllerEJB.remove(tiempos);
            JsfUtil.addSuccessMessage("Tiempo eliminado exitosamente.");

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al eliminar el tiempo seleccionado");
        } finally {
            this.activadorPanelEdicionCreacionTiempos = false;
            this.activadorPanelEdicionCreacionTiemposGenerales = false;
        }
    }

    //metodo que elimina el proveedor
    public void eliminarProveedor(StCliprov cliprov) {
        try {
            if (PersistenciaClienteControllerEJB.findCarga(cliprov).isEmpty()) {
                PersistenciaClienteControllerEJB.remove(cliprov);
                JsfUtil.addSuccessMessage("Proveedor " + cliprov.getProConsec().getProNombre() + " eliminado exitosamente.");
            } else {
                JsfUtil.addErrorMessage("El proveedor no se puede eliminar porque puede estar asociado a una carga");
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("El proveedor no se puede eliminar porque puede estar asociado a una carga");
        }

    }

    //metodo que elimina el producto
    public void eliminarProducto(StProduct product) {
        try {
            if (PersistenciaClienteControllerEJB.findDetfactXproduct(product).isEmpty()) {
                PersistenciaClienteControllerEJB.remove(product);
                JsfUtil.addSuccessMessage("Producto " + product.getProDescri() + " eliminado exitosamente.");
            } else {
                JsfUtil.addErrorMessage("El producto " + product.getProDescri() + " no se puede eliminar porque puede estar asociado a una factura");
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("El producto " + product.getProDescri() + " no se puede eliminar porque puede estar asociado a una factura");
        }
    }

    //metodo que elimina un horario selecionado
    public void eliminarHorario(StHorario horario) {
        try {
            PersistenciaClienteControllerEJB.remove(horario);
            JsfUtil.addSuccessMessage("Horario eliminado exitosamente.");

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al eliminar el horario seleccionado");
        } finally {
            this.activadorPanelEdicionCreacionHorarios = false;
        }
    }

    public SelectItem[] getListaTipoProductos() {

        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StTipprod> entities = PersistenciaClienteControllerEJB.findAllTipoProducto();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StTipprod tipoProducto : entities) {
                items[i++] = new SelectItem(tipoProducto.getTipConsec(), tipoProducto.getTipNombre());
            }
        }
        return items;

    }

    public SelectItem[] getListaProveeProducto() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StProveed> entities = PersistenciaClienteControllerEJB.findAllProveed();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }

            String ciudad;
            for (StProveed tipoProveedor : entities) {

                if (tipoProveedor.getStCiudad() == null) {
                    ciudad = "Ciudad no definida";
                } else {
                    ciudad = tipoProveedor.getStCiudad().getCiuNom();
                }

                items[i++] = new SelectItem(tipoProveedor.getProConsec(), tipoProveedor.getProNombre() + " - " + ciudad);
            }
        }
        return items;

    }

    public SelectItem[] getListaProveeLogisticos() {

        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StProveed> entities = PersistenciaClienteControllerEJB.findAllProveed();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StProveed tipoProveedor : entities) {
                items[i++] = new SelectItem(tipoProveedor.getProConsec(), tipoProveedor.getProNombre());
            }
        }
        return items;

    }

    public SelectItem[] getListaServicios() {

        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StTipserv> entities = PersistenciaClienteControllerEJB.findAllServ();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StTipserv st : entities) {
                items[i++] = new SelectItem(st.getTipConsec(), st.getTipNombre());
            }
        }
        return items;

    }

    public SelectItem[] getListaTipoServicioExcepGeneral() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StTipserv> entities = PersistenciaClienteControllerEJB.findAllServExcepGeneral();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StTipserv stTipoServicio : entities) {
                items[i++] = new SelectItem(stTipoServicio.getTipConsec(), stTipoServicio.getTipNombre());
            }
        }
        return items;
    }

    public SelectItem[] getListaCategoriasXCliente() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StCategor> entities = PersistenciaClienteControllerEJB.findAllCategorByClient(clienteSeleccionado);

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StCategor categor : entities) {
                items[i++] = new SelectItem(categor.getCatConsec(), categor.getCatCodigo());
            }
        }
        return items;
    }

    public SelectItem[] getListaModelosXCliente() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StModelo> entities = PersistenciaClienteControllerEJB.findAllModeloByClient(clienteSeleccionado);

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StModelo modelo : entities) {
                items[i++] = new SelectItem(modelo.getModConsec(), modelo.getModCodigo());
            }
        }
        return items;
    }

    public SelectItem[] getListaTipProXCliente() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StTipprod> entities = PersistenciaClienteControllerEJB.findAllTipProByClient(clienteSeleccionado);

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StTipprod tipprod : entities) {
                items[i++] = new SelectItem(tipprod.getTipConsec(), tipprod.getTipNombre());
            }
        }
        return items;
    }

    public SelectItem[] getListaTipoDias() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StTipdia> entities = PersistenciaClienteControllerEJB.findAllTypesDays();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StTipdia tipoDia : entities) {
                items[i++] = new SelectItem(tipoDia.getTipConsec(), tipoDia.getTipNombre());
            }
        }
        return items;
    }

    public SelectItem[] getListaDiasSemana() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StDiasema> entities = PersistenciaClienteControllerEJB.findAllDaysWeek();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StDiasema diasema : entities) {
                items[i++] = new SelectItem(diasema.getDiaConsec(), diasema.getDiaNombre());
            }
        }
        return items;
    }

    public SelectItem[] getListaTipoDiaHorario() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StTipdia> entities = PersistenciaClienteControllerEJB.findAllTypesDays();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StTipdia tipoDia : entities) {
                items[i++] = new SelectItem(tipoDia.getTipConsec(), tipoDia.getTipNombre());
            }
        }
        return items;
    }

    public SelectItem[] getListaProveeLogisticosXcliente() {

        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StCliprov> entities = PersistenciaClienteControllerEJB.findAllCliProvLogBytipServ(ltipoServ, clienteSeleccionado);

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StCliprov cliprov : entities) {
                items[i++] = new SelectItem(cliprov.getProConsec().getProConsec(), cliprov.getProConsec().getProNombre());
            }
        }
        return items;

    }

    public SelectItem[] getListaProveeXServicGeneral() {

        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StCliprov> entities = PersistenciaClienteControllerEJB.findAllProvByServGeneral(clienteSeleccionado);

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StCliprov cliprov : entities) {
                items[i++] = new SelectItem(cliprov.getCliTpcconsec(), cliprov.getProConsec().getProNombre());
            }
        }
        return items;

    }

    public SelectItem[] getListaProveeOnlyServicGeneral() {

        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StCliprov> entities = PersistenciaClienteControllerEJB.findAllProvOnlyServGeneral(clienteSeleccionado);

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StCliprov cliprov : entities) {
                items[i++] = new SelectItem(cliprov.getCliTpcconsec(), cliprov.getProConsec().getProNombre());
            }
        }
        return items;

    }

    public SelectItem[] getListaDepositosSinAsociar() {

        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StDeposit> entities = PersistenciaClienteControllerEJB.findAllDepositUnassociated();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StDeposit deposit : entities) {
                items[i++] = new SelectItem(deposit.getDepConsec(), deposit.getDepNombre());
            }
        }
        return items;

    }

    public List<StCliprov> getListaProveedoresLogisticos() {

        return PersistenciaClienteControllerEJB.findAllCliProvLog(clienteSeleccionado);
    }

    public List<StCliprov> getListaProveedoresProducto() {

        return PersistenciaClienteControllerEJB.findAllCliProveePro(clienteSeleccionado);
    }

    public List<StProduct> getListaProductosXcliente() {

        return PersistenciaClienteControllerEJB.findAllProductosXcliente(clienteSeleccionado);
    }

    public List<StTiempos> getListaTiempos() {
        return PersistenciaClienteControllerEJB.findAlltimesNormal();
    }

    public List<StTiempos> getListaTiemposGenerales() {
        return PersistenciaClienteControllerEJB.findAlltimesGeneral();
    }

    public List<StHorario> getListaHorarios() {
        List<StHorario> listaHorarios = new ArrayList<StHorario>();
        List<Long> listaHoraMins = new ArrayList<Long>();
        StHorario horarioContext;
        for (StHorario horarioDataBase : PersistenciaClienteControllerEJB.findAllHorarios()) {
            horarioContext = new StHorario();
            horarioContext.setCliTpcconsec(horarioDataBase.getCliTpcconsec());
            horarioContext.setDiaConsec(horarioDataBase.getDiaConsec());
            horarioContext.setHorConsec(horarioDataBase.getHorConsec());
            listaHoraMins = SitUtil.obtenerHoraFormateada(horarioDataBase.getHorInicio());
            horarioContext.setHorInicio((Long) listaHoraMins.get(0));
            horarioContext.setHorMinsIni(SitUtil.validaMinuto(listaHoraMins.get(1)));
            listaHoraMins = SitUtil.obtenerHoraFormateada(horarioDataBase.getHorFin());
            horarioContext.setHorFin((Long) listaHoraMins.get(0));
            horarioContext.setHorMinsfin(SitUtil.validaMinuto(listaHoraMins.get(1)));
            listaHorarios.add(horarioContext);
        }
        return listaHorarios;
    }

    public List<StDepclie> getListaDepositosXCliente() {
        return PersistenciaClienteControllerEJB.findAllDepositByClient(clienteSeleccionado);
    }

    public List<StRuta> getRutas() {
        return PersistenciaClienteControllerEJB.findAllRutas();
    }

    public void cambiarProveedorLogRuta(ValueChangeEvent event) {

    }

    public List<StRutprov> getRutasByfilter(String param) {
        List<StRutprov> listaRutas = new ArrayList<StRutprov>();
        if (this.longProveelog != null) {
            return PersistenciaClienteControllerEJB.findRutasByparams(longProveelog, param);
        }
        return listaRutas;
    }

    public Long getLstProveed() {
        return lstProveed;
    }

    public void setLstProveed(Long lstProveed) {
        this.lstProveed = lstProveed;
    }

    public Long getLstTipserv() {
        return lstTipserv;
    }

    public void setLstTipserv(Long lstTipserv) {
        this.lstTipserv = lstTipserv;
    }

    public boolean isActivadorPanelAsociacionEdicionLog() {
        return activadorPanelAsociacionEdicionLog;
    }

    public void setActivadorPanelAsociacionEdicionLog(boolean activadorPanelAsociacionEdicionLog) {
        this.activadorPanelAsociacionEdicionLog = activadorPanelAsociacionEdicionLog;
    }

    public StCliprov getProveedCurrent() {
        return proveedCurrent;
    }

    public void setProveedCurrent(StCliprov proveedCurrent) {
        this.proveedCurrent = proveedCurrent;
    }

    public boolean isActivadorComponentesAsociarLog() {
        return activadorComponentesAsociarLog;
    }

    public void setActivadorComponentesAsociarLog(boolean activadorComponentesAsociarLog) {
        this.activadorComponentesAsociarLog = activadorComponentesAsociarLog;
    }

    public boolean isActivadorBotonActualizarLog() {
        return activadorBotonActualizarLog;
    }

    public void setActivadorBotonActualizarLog(boolean activadorBotonActualizarLog) {
        this.activadorBotonActualizarLog = activadorBotonActualizarLog;
    }

    public boolean isActivadorComponentesAsociarProducto() {
        return activadorComponentesAsociarProducto;
    }

    public void setActivadorComponentesAsociarProducto(boolean activadorComponentesAsociarProducto) {
        this.activadorComponentesAsociarProducto = activadorComponentesAsociarProducto;
    }

    public Long getLproducto() {
        return lproducto;
    }

    public void setLproducto(Long lproducto) {
        this.lproducto = lproducto;
    }

    public boolean isActivadorPanelAsociacionEdicionProveePro() {
        return activadorPanelAsociacionEdicionProveePro;
    }

    public void setActivadorPanelAsociacionEdicionProveePro(boolean activadorPanelAsociacionEdicionProveePro) {
        this.activadorPanelAsociacionEdicionProveePro = activadorPanelAsociacionEdicionProveePro;
    }

    public boolean isActivadorBotonActualizarProducto() {
        return activadorBotonActualizarProducto;
    }

    public void setActivadorBotonActualizarProducto(boolean activadorBotonActualizarProducto) {
        this.activadorBotonActualizarProducto = activadorBotonActualizarProducto;
    }

    public Long getLproveePro() {
        return lproveePro;
    }

    public void setLproveePro(Long lproveePro) {
        this.lproveePro = lproveePro;
    }

    public boolean isActivadorPanelCreacionEdicionProducto() {
        return activadorPanelCreacionEdicionProducto;
    }

    public void setActivadorPanelCreacionEdicionProducto(boolean activadorPanelCreacionEdicionProducto) {
        this.activadorPanelCreacionEdicionProducto = activadorPanelCreacionEdicionProducto;
    }

    public StProduct getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(StProduct currentProduct) {
        this.currentProduct = currentProduct;
    }

    public String getPdescripcion() {
        return pdescripcion;
    }

    public void setPdescripcion(String pdescripcion) {
        this.pdescripcion = pdescripcion;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public Long getLcatConsec() {
        return lcatConsec;
    }

    public void setLcatConsec(Long lcatConsec) {
        this.lcatConsec = lcatConsec;
    }

    public Long getLmodConsec() {
        return lmodConsec;
    }

    public void setLmodConsec(Long lmodConsec) {
        this.lmodConsec = lmodConsec;
    }

    public Long getLtipprodConsec() {
        return ltipprodConsec;
    }

    public void setLtipprodConsec(Long ltipprodConsec) {
        this.ltipprodConsec = ltipprodConsec;
    }

    public Long getLongProduct() {
        return longProduct;
    }

    public void setLongProduct(Long longProduct) {
        this.longProduct = longProduct;

    }

    public StTiempos getStTiempos() {
        return stTiempos;
    }

    public void setStTiempos(StTiempos stTiempos) {
        this.stTiempos = stTiempos;
    }

    public boolean isActivadorPanelEdicionCreacionTiempos() {
        return activadorPanelEdicionCreacionTiempos;
    }

    public void setActivadorPanelEdicionCreacionTiempos(boolean activadorPanelEdicionCreacionTiempos) {
        this.activadorPanelEdicionCreacionTiempos = activadorPanelEdicionCreacionTiempos;
    }

    public Long getLongProveelog() {
        return longProveelog;
    }

    public void setLongProveelog(Long longProveelog) {
        this.longProveelog = longProveelog;
    }

    public StRutprov getStRutprov() {
        return stRutprov;
    }

    public void setStRutprov(StRutprov stRutprov) {
        this.stRutprov = stRutprov;
    }

    public Short getTiempoTransporte() {
        return tiempoTransporte;
    }

    public void setTiempoTransporte(Short tiempoTransporte) {
        this.tiempoTransporte = tiempoTransporte;
    }

    public Long getTipoDias() {
        return tipoDias;
    }

    public void setTipoDias(Long tipoDias) {
        this.tipoDias = tipoDias;
    }

    public Long getLtipoServ() {
        return ltipoServ;
    }

    public void setLtipoServ(Long ltipoServ) {
        this.ltipoServ = ltipoServ;
    }

    public boolean isActivadorItemsEdicionTiempos() {
        return activadorItemsEdicionTiempos;
    }

    public void setActivadorItemsEdicionTiempos(boolean activadorItemsEdicionTiempos) {
        this.activadorItemsEdicionTiempos = activadorItemsEdicionTiempos;
    }

    public boolean isActivadorItemsCreacionTiempos() {
        return activadorItemsCreacionTiempos;
    }

    public void setActivadorItemsCreacionTiempos(boolean activadorItemsCreacionTiempos) {
        this.activadorItemsCreacionTiempos = activadorItemsCreacionTiempos;
    }

    public Short getHorasPreinspeccion() {
        return horasPreinspeccion;
    }

    public void setHorasPreinspeccion(Short horasPreinspeccion) {
        this.horasPreinspeccion = horasPreinspeccion;
    }

    public Short getHorasAduana() {
        return horasAduana;
    }

    public void setHorasAduana(Short horasAduana) {
        this.horasAduana = horasAduana;
    }

    public Short getHorasOnHand() {
        return horasOnHand;
    }

    public void setHorasOnHand(Short horasOnHand) {
        this.horasOnHand = horasOnHand;
    }

    public boolean isActivadorPanelEdicionCreacionTiemposGenerales() {
        return activadorPanelEdicionCreacionTiemposGenerales;
    }

    public void setActivadorPanelEdicionCreacionTiemposGenerales(boolean activadorPanelEdicionCreacionTiemposGenerales) {
        this.activadorPanelEdicionCreacionTiemposGenerales = activadorPanelEdicionCreacionTiemposGenerales;
    }

    public boolean isActivadorItemsCreacionTiemposGenerales() {
        return activadorItemsCreacionTiemposGenerales;
    }

    public void setActivadorItemsCreacionTiemposGenerales(boolean activadorItemsCreacionTiemposGenerales) {
        this.activadorItemsCreacionTiemposGenerales = activadorItemsCreacionTiemposGenerales;
    }

    public Long getLongProveeGeneral() {
        return longProveeGeneral;
    }

    public void setLongProveeGeneral(Long longProveeGeneral) {
        this.longProveeGeneral = longProveeGeneral;
    }

    public StTiempos getStTiemposGenerales() {
        return stTiemposGenerales;
    }

    public void setStTiemposGenerales(StTiempos stTiemposGenerales) {
        this.stTiemposGenerales = stTiemposGenerales;
    }

    public Long getTipoDiasGeneral() {
        return tipoDiasGeneral;
    }

    public void setTipoDiasGeneral(Long tipoDiasGeneral) {
        this.tipoDiasGeneral = tipoDiasGeneral;
    }

    public boolean isActivadorPanelEdicionCreacionHorarios() {
        return activadorPanelEdicionCreacionHorarios;
    }

    public void setActivadorPanelEdicionCreacionHorarios(boolean activadorPanelEdicionCreacionHorarios) {
        this.activadorPanelEdicionCreacionHorarios = activadorPanelEdicionCreacionHorarios;
    }

    public Long getLproveeHorario() {
        return lproveeHorario;
    }

    public void setLproveeHorario(Long lproveeHorario) {
        this.lproveeHorario = lproveeHorario;
    }

    public boolean isActivadorProveedorHorario() {
        return activadorProveedorHorario;
    }

    public void setActivadorProveedorHorario(boolean activadorProveedorHorario) {
        this.activadorProveedorHorario = activadorProveedorHorario;
    }

    public Long getTipoDiaHora() {
        return tipoDiaHora;
    }

    public void setTipoDiaHora(Long tipoDiaHora) {
        this.tipoDiaHora = tipoDiaHora;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public StHorario getStHorario() {
        return stHorario;
    }

    public void setStHorario(StHorario stHorario) {
        this.stHorario = stHorario;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public boolean isActivadorPanelEdicionCreacionDepositos() {
        return activadorPanelEdicionCreacionDepositos;
    }

    public void setActivadorPanelEdicionCreacionDepositos(boolean activadorPanelEdicionCreacionDepositos) {
        this.activadorPanelEdicionCreacionDepositos = activadorPanelEdicionCreacionDepositos;
    }

    public Long getLongDeposito() {
        return longDeposito;
    }

    public void setLongDeposito(Long longDeposito) {
        this.longDeposito = longDeposito;
    }

    @FacesConverter(value = "stClienteConverter")
    public static class StClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            } else {
                if (value != null && !value.equals("null")) {
                    StClienteController controller = (StClienteController) facesContext.getApplication().getELResolver().
                            getValue(facesContext.getELContext(), null, "stClienteController");

                    return controller.PersistenciaClienteControllerEJB.find(getKey(value));
                } else {
                    return null;
                }

            }
        }

        java.lang.Long getKey(String value) {
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
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof StCliente) {
                StCliente o = (StCliente) object;
                if (o.getCliConsec() == null) {
                    return null;
                }
                return getStringKey(Long.valueOf(o.getCliConsec().toString()));
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + StCliente.class.getName());
            }
        }
    }

    @FacesConverter(value = "rutaConverter")
    public static class RutaConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            } else {
                if (value != null && !value.equals("null")) {
                    StClienteController controller = (StClienteController) facesContext.getApplication().getELResolver().
                            getValue(facesContext.getELContext(), null, "stClienteController");

                    return controller.PersistenciaClienteControllerEJB.findRuta(getKey(value));
                } else {
                    return null;
                }

            }
        }

        java.lang.Long getKey(String value) {
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
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
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
        }
    }

    public StCliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(StCliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public boolean isFlagPrepareEdit() {
        return flagPrepareEdit;
    }

    public void setFlagPrepareEdit(boolean flagPrepareEdit) {
        this.flagPrepareEdit = flagPrepareEdit;
    }

    public StCliprov getStCliProvcurrent() {
        return stCliProvcurrent;
    }

    public void setStCliProvcurrent(StCliprov stCliProvcurrent) {
        this.stCliProvcurrent = stCliProvcurrent;
    }
}
