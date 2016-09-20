/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.controller;

import co.com.almaviva.proyectosit.business.StCargaServiceBeanLocal;
import co.com.almaviva.proyectosit.controller.util.JsfUtil;
import co.com.almaviva.proyectosit.entity.StCarga;
import co.com.almaviva.proyectosit.entity.StContene;
import co.com.almaviva.proyectosit.entity.StUnicarg;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author egonzalm
 */
@ManagedBean(name = "contenedoresController")
@SessionScoped
public class ContenedoresController {

    @EJB
    private StCargaServiceBeanLocal stCargaService;

    //Identificación de la carga
    private StCarga stCargaCurrent;

    //Panel de Contenedores
    private List<StContene> stContenedores = new ArrayList<StContene>();
    private StContene stContenedorCurrent;
    private Boolean flagContenedores = false;
    private Long unidadCarga;

    public ContenedoresController() {
    }

    public void init() {
        //Panel de facturas
        this.stContenedores = new ArrayList<StContene>();
        this.stContenedorCurrent = new StContene();
        this.flagContenedores = false;
        this.unidadCarga = null;
    }

    public void consultaCargasClientehandleSelect() {
        //Panel de facturas
        this.stContenedores = new ArrayList<StContene>();
        this.stContenedorCurrent = new StContene();
        this.flagContenedores = false;
        this.unidadCarga = null;
    }

    public void consultaContenedoresCarga(StCarga stCarga) {
        this.stCargaCurrent = stCarga;
        this.stContenedores = stCargaService.getStConteneCarga(stCarga.getCarConsec());
        this.stContenedorCurrent = new StContene();
        this.flagContenedores = false;
        this.unidadCarga = null;
    }

    public void consultaContenedor() {
        this.flagContenedores = true;
        this.unidadCarga = null;
        this.stContenedorCurrent = new StContene();
    }

    public void consultaContenedorEdit(StContene stContene) {
        try {
            this.flagContenedores = true;
            this.stContenedorCurrent = new StContene();
            this.stContenedorCurrent = stContene;
            this.unidadCarga = stContenedorCurrent.getUniConsec().getUniConsec();
        } catch (Exception e) {
            System.err.println("Exception consultaFacturaEdit= " + e);
        }
    }

    public void updateContenedor() {
        if (stContenedorCurrent != null) {

            stContenedorCurrent.setUniConsec(new StUnicarg(this.unidadCarga));
            //Nuevo Registro
            if (stContenedorCurrent.getConConsec() == null) {
                stContenedorCurrent.setCarConsec(stCargaCurrent);

                try {
                    //Creación de la Factura
                    stCargaService.createStContene(stContenedorCurrent);
                    JsfUtil.addSuccessMessage("El contenedor ha sido creado.");

                    stContenedores = stCargaService.getStConteneCarga(stCargaCurrent.getCarConsec());
                    flagContenedores = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al crear el contenedor");
                    System.err.println("Error= " + e);
                }
            } else {//Actualizar registro
                try {
                    stCargaService.editStContene(stContenedorCurrent);
                    JsfUtil.addSuccessMessage("El contenedor ha sido actualizada.");

                    stContenedores = stCargaService.getStConteneCarga(stCargaCurrent.getCarConsec());
                    flagContenedores = false;
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Error al actualizar el contenedor");
                    System.err.println("Error= " + e);
                }
            }

        }
    }

    public void eliminarContenedor(StContene stContene) {
        try {
            stCargaService.removeStContene(stContene);
            this.stContenedores = stCargaService.getStConteneCarga(stCargaCurrent.getCarConsec());
            this.stContenedorCurrent = new StContene();
            this.flagContenedores = false;
            this.unidadCarga = null;

            JsfUtil.addSuccessMessage("El contenedor ha sido eliminado exitosamente");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error al eliminar el contenedor.");
            System.err.println("Error al eliminar la factura. = " + ex);
        }
    }
    
    public void cancelarContenedor() {        
        this.stContenedorCurrent = null;
        this.flagContenedores = false;
    }
    
    public SelectItem[] getItemsStUnicarg() {
        List<StUnicarg> entities = stCargaService.getStUnicarg();

        int size = true ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (true) {
            items[0] = new SelectItem("", "-seleccione uno-");
            i++;
        }
        for (StUnicarg x : entities) {
            items[i++] = new SelectItem(x.getUniConsec(), x.getUniNombre());
        }
        return items;
    }

    public StCarga getStCargaCurrent() {
        return stCargaCurrent;
    }

    public void setStCargaCurrent(StCarga stCargaCurrent) {
        this.stCargaCurrent = stCargaCurrent;
    }

    public List<StContene> getStContenedores() {
        return stContenedores;
    }

    public void setStContenedores(List<StContene> stContenedores) {
        this.stContenedores = stContenedores;
    }

    public StContene getStContenedorCurrent() {
        return stContenedorCurrent;
    }

    public void setStContenedorCurrent(StContene stContenedorCurrent) {
        this.stContenedorCurrent = stContenedorCurrent;
    }

    public Boolean getFlagContenedores() {
        return flagContenedores;
    }

    public void setFlagContenedores(Boolean flagContenedores) {
        this.flagContenedores = flagContenedores;
    }

    public Long getUnidadCarga() {
        return unidadCarga;
    }

    public void setUnidadCarga(Long unidadCarga) {
        this.unidadCarga = unidadCarga;
    }
}
