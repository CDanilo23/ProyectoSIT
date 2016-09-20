/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.controller;

import co.com.almaviva.proyectosit.entity.StPedido;
import co.com.almaviva.proyectosit.entity.StCliente;
import co.com.almaviva.proyectosit.entity.StModtran;
import co.com.almaviva.proyectosit.business.StCargaServiceBeanLocal;
import co.com.almaviva.proyectosit.controller.util.JsfUtil;
import co.com.almaviva.proyectosit.entity.StCarga;
import co.com.almaviva.proyectosit.entity.StCliprov;
import co.com.almaviva.proyectosit.entity.StDepclie;
import co.com.almaviva.proyectosit.entity.StDepcliePK;
import co.com.almaviva.proyectosit.entity.StIncoter;
import co.com.almaviva.proyectosit.entity.StTipemba;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import co.com.almaviva.proyectosit.entity.StTipoper;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author egonzalm
 */
@ManagedBean(name = "aperturacargacontroller")
@SessionScoped
public class AperturaCargaController implements Serializable {

    private static final long serialVersionUID = -352451682876200492L;

    @EJB
    private StCargaServiceBeanLocal stCargaService;

    //Panel de busqueda
    private StCliente clienteSeleccionado;

    //Panel de Creación de Carga
    //Variable de TabView
    private boolean flagTabView = false;
    private StCarga stCargaCurrent;
    private Long tipOperacion;
    private Long ModTransporte;
    private Long proAduana;
    private Long depCliente;
    private Long tipEmbarqueCarga;

    public AperturaCargaController() {
    }

    public void init() {
        System.err.println("init");
        this.clienteSeleccionado = new StCliente();
        this.flagTabView = false;
        this.stCargaCurrent = new StCarga();
        this.tipOperacion = null;
        this.ModTransporte = null;
        this.proAduana = null;
        this.depCliente = null;
        this.tipEmbarqueCarga = null;
    }

    public List<StCliente> findAllTerceroDescription(String description) {
        List<StCliente> results = (List<StCliente>) (stCargaService.findAllClienteDescription(description));
        return results;
    }

//    public void handleSelect(ValueChangeEvent valueChangeEvent) {
//        System.err.println("handleSelect");
//        this.flagTabView = false;
//        this.current = this.clienteSeleccionado;
//        this.visMesOk = "true";
//        StCliente ceTerceroTemp = new StCliente();
//        ceTerceroTemp = (StCliente) valueChangeEvent.getNewValue();
//        if (ceTerceroTemp != null && ceTerceroTemp.getTerNit() != null) {
//            this.flagTabView = true;
//        }
//    }
    public void crearCarga() {
        if (this.clienteSeleccionado != null) {
            this.flagTabView = true;
            this.stCargaCurrent = new StCarga();
            this.tipOperacion = null;
            this.ModTransporte = null;
            this.proAduana = null;
            this.depCliente = null;
            this.tipEmbarqueCarga = null;
            this.stCargaCurrent.setCarFecope(new Date());
        }
    }
    
    public void cambiarTipoOperacion(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            this.ModTransporte = null;
        }
    }

    @FacesConverter(value = "stClienteCargaConverter")
    public static class StClienteCargaConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            try {
                if (value == null || value.length() == 0) {
                    return null;
                } else {
                    if (value != null && !value.equals("null")) {
                        AperturaCargaController controller = (AperturaCargaController) facesContext.getApplication().getELResolver().
                                getValue(facesContext.getELContext(), null, "aperturacargacontroller");
                        return controller.stCargaService.findStCliente(getKey(value));
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

                if (object instanceof StCliente) {
                    StCliente o = (StCliente) object;
                    if (o.getCliConsec() == null) {
                        return null;
                    }

                    return getStringKey(Long.valueOf(o.getCliConsec().toString()));
                } else {
                    throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + StCliente.class.getName());
                }
            } catch (Exception e) {
                System.err.println("error= " + e);
                return "";
            }
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

    public void createCarga() {
        if (stCargaCurrent != null) {
            if (depCliente != null) {
                stCargaCurrent.setStDepclie(new StDepclie(new StDepcliePK(this.depCliente, this.clienteSeleccionado.getCliConsec())));
            }

            if (proAduana != null) {
                stCargaCurrent.setCliTpcconsec(new StCliprov(proAduana));
            }
            stCargaCurrent.setTipOpera(new StTipoper(tipOperacion));
            stCargaCurrent.setModConsec(new StModtran(ModTransporte));

            stCargaCurrent.setTipConsec(new StTipemba(tipEmbarqueCarga));
            stCargaCurrent.setCarEstado(BigDecimal.ONE);
            //Creación del pedido
            try {
                StPedido pedido = new StPedido();
                pedido.setPedConsec(stCargaService.getSeqStPedido());
                pedido.setCliConsec(clienteSeleccionado);
                stCargaService.createStPedido(pedido);

                try {
                    //Creación de la Carga
                    stCargaCurrent.setCarConsec(stCargaService.getSeqStCarga());
                    stCargaCurrent.setPedConsec(pedido);
                    stCargaService.create(stCargaCurrent);
                    JsfUtil.addSuccessMessage("La carga ha sido creada con el numero [" + stCargaCurrent.getCarConsec() + "]");
                    crearCarga();
                } catch (Exception e) {
                    stCargaCurrent.setCarConsec(null);
                    JsfUtil.addErrorMessage("Error al crear la carga");
                    System.err.println("Error= " + e);
                }
            } catch (Exception e) {
            }
        }
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
}
