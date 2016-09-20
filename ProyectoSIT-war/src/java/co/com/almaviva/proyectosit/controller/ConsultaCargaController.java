
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.controller;

import co.com.almaviva.proyectosit.entity.StCliente;
import co.com.almaviva.proyectosit.entity.StModtran;
import co.com.almaviva.proyectosit.entity.StMoneda;
import co.com.almaviva.proyectosit.business.StCargaServiceBeanLocal;
import co.com.almaviva.proyectosit.controller.util.JsfUtil;
import co.com.almaviva.proyectosit.entity.StCarga;
import co.com.almaviva.proyectosit.entity.StCliprov;
import co.com.almaviva.proyectosit.entity.StDepclie;
import co.com.almaviva.proyectosit.entity.StDepcliePK;
import co.com.almaviva.proyectosit.entity.StIncoter;
import co.com.almaviva.proyectosit.entity.StOrdcomp;
import co.com.almaviva.proyectosit.entity.StTipemba;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import co.com.almaviva.proyectosit.entity.StTipoper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author egonzalm
 */
@ManagedBean(name = "consultaCargaController")
@SessionScoped
public class ConsultaCargaController implements Serializable {

    private static final long serialVersionUID = -352451682876200492L;

    @EJB
    private StCargaServiceBeanLocal stCargaService;

    @ManagedProperty(value = "#{facturasController}")
    private FacturasController facturasController;

    @ManagedProperty(value = "#{transporteController}")
    private TransporteController transporteController;

    @ManagedProperty(value = "#{contenedoresController}")
    private ContenedoresController contenedoresController;

    @ManagedProperty(value = "#{aduanasController}")
    private AduanasController aduanasController;
    
    @ManagedProperty(value = "#{indFechasController}")
    private IndFechasController indFechasController;

    //Indice Tab
    //Panel de busqueda
    private StCliente clienteSeleccionadoAutocomplete;
    private StCliente clienteSeleccionado;

    //Panel de busqueda de cargas
    private List<StCarga> stCargas = new ArrayList<StCarga>();
    private Boolean panelCargas = false;

    //Panel de Actualización de Carga
    //Variable de TabView
    private boolean flagTabView = false;
    private StCarga stCargaCurrent;
    private Long tipOperacion;
    private Long ModTransporte;
    private Long proAduana;
    private Long depCliente;
    private Long tipEmbarqueCarga;
    private Boolean flagdisableTipOperacion = false;
    private boolean flagContenedores = false;

    public ConsultaCargaController() {
    }

    public void init() {
        System.err.println("init");
        //Panel de busqueda de cargas
        stCargas = new ArrayList<StCarga>();
        this.panelCargas = false;

        //Mostrar tab de contenedores
        this.flagContenedores = false;

        //Panel de actualizacion informacion de la carga
        this.clienteSeleccionadoAutocomplete = new StCliente();

        this.clienteSeleccionado = new StCliente();

        this.flagTabView = false;
        this.stCargaCurrent = new StCarga();
        tipOperacion = null;
        this.ModTransporte = null;
        this.proAduana = null;
        this.depCliente = null;
        this.tipEmbarqueCarga = null;

        //Inicianizaliza variables de las facturas
        facturasController.init();

        //Inicianizaliza variables de transporte
        transporteController.init();

        //Inicianizaliza variables de contenedores
        contenedoresController.init();

        //Inicianizaliza variables de aduanas
        aduanasController.init();
        
        //Inicializa variables de Indicadores y fechas
        indFechasController.init();
    }

    public List<StCliente> findAllTerceroDescription(String description) {
        List<StCliente> results = (List<StCliente>) (stCargaService.findAllClienteDescription(description));
        return results;
    }

    public void consultaCargasClientehandleSelect(ValueChangeEvent valueChangeEvent) {
        this.panelCargas = false;
        this.clienteSeleccionado = (StCliente) valueChangeEvent.getNewValue();

        //Mostrar tab de contenedores
        this.flagContenedores = false;

        //Panel de actualizacion de carga
        this.flagTabView = false;
        this.stCargaCurrent = new StCarga();
        this.tipOperacion = null;
        this.ModTransporte = null;
        this.proAduana = null;
        this.depCliente = null;
        this.tipEmbarqueCarga = null;

        //Panel de facturas
        facturasController.consultaCargasClientehandleSelect();

        //Panel Transporte
        transporteController.consultaCargasClientehandleSelect();

        //Panel de contenedores
        contenedoresController.consultaCargasClientehandleSelect();
        
        //Panel de Indicadores y fechas
        indFechasController.consultaCargasClientehandleSelect();
    }

    public void consultaCargasCliente() {
        this.panelCargas = false;
        //Panel de busqueda de cargas
        if (this.clienteSeleccionadoAutocomplete != null) {
            this.clienteSeleccionado = this.clienteSeleccionadoAutocomplete;
            stCargas = stCargaService.getCargasCliente(clienteSeleccionado.getCliConsec());
            this.panelCargas = true;
        }
    }

    public void consultaCarga(StCarga stCarga) {
        //Panel de actualizacion de carga
        this.flagTabView = true;
        this.stCargaCurrent = new StCarga();
        this.stCargaCurrent = stCarga;
        this.tipOperacion = stCarga.getTipOpera().getTipConsec();
        this.ModTransporte = stCarga.getModConsec().getModConsec();
        this.tipEmbarqueCarga = stCarga.getTipConsec().getTipConsec();

        //Mostrar Tab de contenedores
        this.flagContenedores = stCarga.getModConsec().getModConsec() == 1;

        if (stCarga.getCliTpcconsec() != null) {
            this.proAduana = stCarga.getCliTpcconsec().getCliTpcconsec();
        } else {
            this.proAduana = null;
        }

        if (stCarga.getStDepclie() != null) {
            this.depCliente = stCarga.getStDepclie().getStDepcliePK().getDepConsec();
        } else {
            this.depCliente = null;
        }

        //Panel de facturas
        facturasController.consultaFacturasCarga(stCarga);
        facturasController.consultaOrdenesCarga(stCarga);

        //Transporte Internacional
        transporteController.consultaTransportesCarga(stCarga, clienteSeleccionado);

        //Contenedores
        if (flagContenedores) {
            contenedoresController.consultaContenedoresCarga(stCarga);
            //Habilita cambios del modo de transporte si ya hay cargas internacionales o contenedores
            flagdisableTipOperacion = !transporteController.getStDetcargsAereas().isEmpty() || !transporteController.getStDetcargsMaritimas().isEmpty() || !contenedoresController.getStContenedores().isEmpty();
        } else {
            //Habilita cambios del modo de transporte si ya hay cargas internacionales
            flagdisableTipOperacion = !transporteController.getStDetcargsAereas().isEmpty() || !transporteController.getStDetcargsMaritimas().isEmpty();
        }

        //envia carga a bean 
        aduanasController.init();
        aduanasController.setStCarga(stCarga);
        
        //Panel de Indicadores y fechas
        indFechasController.consultaFechasCarga(stCarga);
    }

    public void cambiarTipoOperacion(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            this.ModTransporte = null;
        }
    }

    public void cambioTab(TabChangeEvent e) {
        consultaCarga(stCargaService.find(stCargaCurrent.getCarConsec()));

        if (this.flagContenedores) {
            flagdisableTipOperacion = !transporteController.getStDetcargsAereas().isEmpty() || !transporteController.getStDetcargsMaritimas().isEmpty() || !contenedoresController.getStContenedores().isEmpty();
        } else {
            flagdisableTipOperacion = !transporteController.getStDetcargsAereas().isEmpty() || !transporteController.getStDetcargsMaritimas().isEmpty();
        }
    }

    public SelectItem[] getItemsStModTran() {
        SelectItem[] items = null;
        if (this.tipOperacion != null) {
            Long modoTransporte = null;
            if (this.tipOperacion == 1 || this.tipOperacion == 2 || this.tipOperacion == 3 || this.tipOperacion == 8) {
                modoTransporte = 1L;
            } else if (this.tipOperacion == 4 || this.tipOperacion == 5 || this.tipOperacion == 7) {
                modoTransporte = 2L;
            } else if (this.tipOperacion == 6 || this.tipOperacion == 9 || this.tipOperacion == 10) {
                modoTransporte = 0L;
            }

            List<StModtran> entities = stCargaService.findStModTran(modoTransporte);

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StModtran x : entities) {
                items[i++] = new SelectItem(x.getModConsec(), x.getModNombre());
            }
        }
        return items;
    }

    public SelectItem[] getItemsStTipoper() {
        List<StTipoper> entities = stCargaService.findStTipoper();

        int size = true ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (true) {
            items[0] = new SelectItem("", "-seleccione uno-");
            i++;
        }
        for (StTipoper x : entities) {
            items[i++] = new SelectItem(x.getTipConsec(), x.getTipNombre());
        }
        return items;
    }

    public SelectItem[] getProveeAduana() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StCliprov> entities = stCargaService.findStCliprovAduana(this.clienteSeleccionado.getCliConsec());

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StCliprov x : entities) {
                items[i++] = new SelectItem(x.getCliTpcconsec(), x.getProConsec().getProNombre());
            }
        }
        return items;
    }

    public SelectItem[] getDepositosCliente() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StDepclie> entities = stCargaService.findStDepclie(this.clienteSeleccionado.getCliConsec());

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StDepclie x : entities) {
                items[i++] = new SelectItem(x.getStDepcliePK().getDepConsec(), x.getStDeposit().getDepNombre());
            }
        }
        return items;
    }

    public SelectItem[] getIncoterms() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StIncoter> entities = stCargaService.findStIncoter();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StIncoter x : entities) {
                items[i++] = new SelectItem(x.getIncConsec(), x.getIncNombre() + " - " + x.getIncDescri());
            }
        }
        return items;
    }

    public SelectItem[] getTiposEmbarque() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StTipemba> entities = stCargaService.findStTipemba();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StTipemba x : entities) {
                items[i++] = new SelectItem(x.getTipConsec(), x.getTipNombre());
            }
        }
        return items;
    }

    public SelectItem[] getItemsStMoneda() {
        List<StMoneda> entities = stCargaService.getStMoneda();

        int size = true ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (true) {
            items[0] = new SelectItem("", "-seleccione uno-");
            i++;
        }
        for (StMoneda x : entities) {
            items[i++] = new SelectItem(x.getMonConsec(), x.getMonNombre());
        }
        return items;
    }

    public SelectItem[] getProveeProducto() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null) {
            List<StCliprov> entities = stCargaService.findStCliprovProducto(this.clienteSeleccionado.getCliConsec());

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StCliprov x : entities) {
                items[i++] = new SelectItem(x.getCliTpcconsec(), x.getProConsec().getProNombre());
            }
        }
        return items;
    }

    public SelectItem[] getOrdenesCompra() {
        SelectItem[] items = null;
        if (this.clienteSeleccionado != null && this.stCargaCurrent != null) {
            List<StOrdcomp> entities = stCargaService.getStOrdCompraCarga(this.stCargaCurrent.getCarConsec());

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StOrdcomp x : entities) {
                items[i++] = new SelectItem(x.getOrdConsec(), x.getOrdNumero());
            }
        }
        return items;
    }

    public void updateCarga() {
        if (stCargaCurrent != null) {
            if (depCliente != null) {
                stCargaCurrent.setStDepclie(new StDepclie(new StDepcliePK(this.depCliente, this.clienteSeleccionado.getCliConsec())));
            } else {
                stCargaCurrent.setStDepclie(null);
            }

            if (proAduana != null) {
                stCargaCurrent.setCliTpcconsec(new StCliprov(proAduana));
            } else {
                stCargaCurrent.setCliTpcconsec(null);
            }

            stCargaCurrent.setTipOpera(new StTipoper(tipOperacion));
            stCargaCurrent.setModConsec(new StModtran(ModTransporte));

            stCargaCurrent.setTipConsec(new StTipemba(tipEmbarqueCarga));

            try {
                //Edición de la Carga
                stCargaService.edit(stCargaCurrent);
                JsfUtil.addSuccessMessage("La carga ha sido actualizada.");

                stCargas = stCargaService.getCargasCliente(clienteSeleccionado.getCliConsec());

                transporteController.setFlagPanelCargMaritima(stCargaCurrent.getModConsec().getModConsec() == 1);
                transporteController.setFlagPanelCargAerea(stCargaCurrent.getModConsec().getModConsec() == 2);

                this.flagContenedores = stCargaCurrent.getModConsec().getModConsec() == 1;
                //Contenedores
                if (flagContenedores) {
                    //Habilita cambios del modo de transporte si ya hay cargas internacionales o contenedores
                    flagdisableTipOperacion = !transporteController.getStDetcargsAereas().isEmpty() || !transporteController.getStDetcargsMaritimas().isEmpty() || !contenedoresController.getStContenedores().isEmpty();
                } else {
                    //Habilita cambios del modo de transporte si ya hay cargas internacionales
                    flagdisableTipOperacion = !transporteController.getStDetcargsAereas().isEmpty() || !transporteController.getStDetcargsMaritimas().isEmpty();
                }
                
                consultaCarga(stCargaCurrent);
            } catch (Exception e) {
                JsfUtil.addErrorMessage("Error al crear la carga");
                System.err.println("Error= " + e);
            }
        }
    }

    public void inactivarCargaGeneral() {
        try {
            stCargaCurrent.setCarEstado(BigDecimal.ZERO);
            stCargaService.edit(stCargaCurrent);

            DataTable dt1 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":search:dataTableCargas");
            dt1.setFilteredValue(null);
            Map<String, String> map = dt1.getFilters();
            stCargas = stCargaService.getCargasCliente(clienteSeleccionado.getCliConsec());
            //Mostrar tab de contenedores
            this.flagContenedores = false;

            //Panel de actualizacion de carga
            this.flagTabView = false;
            this.stCargaCurrent = new StCarga();
            this.tipOperacion = null;
            this.ModTransporte = null;
            this.proAduana = null;
            this.depCliente = null;
            this.tipEmbarqueCarga = null;

            //Panel de facturas
            facturasController.consultaCargasClientehandleSelect();

            //Panel Transporte
            transporteController.consultaCargasClientehandleSelect();

            //Panel de contenedores
            contenedoresController.consultaCargasClientehandleSelect();

            aduanasController.init();

            JsfUtil.addSuccessMessage("La carga general ha sido inactivada");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error al inactivar la carga general.");
            System.err.println("Error al inactivar la carga general. = " + ex);
        }
    }

    public List<StCliente> findAllRutasXProveedor(String description) {
        List<StCliente> results = (List<StCliente>) (stCargaService.findAllClienteDescription(description));
        return results;
    }

    public StCliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(StCliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public boolean getFlagTabView() {
        return flagTabView;
    }

    public void setFlagTabView(boolean flagTabView) {
        this.flagTabView = flagTabView;
    }

    public StCarga getStCargaCurrent() {
        return stCargaCurrent;
    }

    public void setStCargaCurrent(StCarga stCargaCurrent) {
        this.stCargaCurrent = stCargaCurrent;
    }

    public Long getTipOperacion() {
        return tipOperacion;
    }

    public void setTipOperacion(Long tipOperacion) {
        this.tipOperacion = tipOperacion;
    }

    public Long getModTransporte() {
        return ModTransporte;
    }

    public void setModTransporte(Long ModTransporte) {
        this.ModTransporte = ModTransporte;
    }

    public Long getProAduana() {
        return proAduana;
    }

    public void setProAduana(Long proAduana) {
        this.proAduana = proAduana;
    }

    public Long getDepCliente() {
        return depCliente;
    }

    public void setDepCliente(Long depCliente) {
        this.depCliente = depCliente;
    }

    public Long getTipEmbarqueCarga() {
        return tipEmbarqueCarga;
    }

    public void setTipEmbarqueCarga(Long tipEmbarqueCarga) {
        this.tipEmbarqueCarga = tipEmbarqueCarga;
    }

    public List<StCarga> getStCargas() {
        return stCargas;
    }

    public void setStCargas(List<StCarga> stCargas) {
        this.stCargas = stCargas;
    }

    public Boolean getPanelCargas() {
        return panelCargas;
    }

    public void setPanelCargas(Boolean panelCargas) {
        this.panelCargas = panelCargas;
    }

    public FacturasController getFacturasController() {
        return facturasController;
    }

    public void setFacturasController(FacturasController facturasController) {
        this.facturasController = facturasController;
    }

    public TransporteController getTransporteController() {
        return transporteController;
    }

    public void setTransporteController(TransporteController transporteController) {
        this.transporteController = transporteController;
    }

    public StCliente getClienteSeleccionadoAutocomplete() {
        return clienteSeleccionadoAutocomplete;
    }

    public void setClienteSeleccionadoAutocomplete(StCliente clienteSeleccionadoAutocomplete) {
        this.clienteSeleccionadoAutocomplete = clienteSeleccionadoAutocomplete;
    }

    public Boolean getFlagdisableTipOperacion() {
        return flagdisableTipOperacion;
    }

    public void setFlagdisableTipOperacion(Boolean flagdisableTipOperacion) {
        this.flagdisableTipOperacion = flagdisableTipOperacion;
    }

    public ContenedoresController getContenedoresController() {
        return contenedoresController;
    }

    public void setContenedoresController(ContenedoresController contenedoresController) {
        this.contenedoresController = contenedoresController;
    }

    public boolean getFlagContenedores() {
        return flagContenedores;
    }

    public void setFlagContenedores(boolean flagContenedores) {
        this.flagContenedores = flagContenedores;
    }

    public AduanasController getAduanasController() {
        return aduanasController;
    }

    public void setAduanasController(AduanasController aduanasController) {
        this.aduanasController = aduanasController;
    }

    public IndFechasController getIndFechasController() {
        return indFechasController;
    }

    public void setIndFechasController(IndFechasController indFechasController) {
        this.indFechasController = indFechasController;
    }
}
