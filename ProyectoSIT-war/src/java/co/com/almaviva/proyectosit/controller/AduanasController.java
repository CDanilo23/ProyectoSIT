/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.controller;

import co.com.almaviva.proyectosit.business.StCargaServiceBeanLocal;
import co.com.almaviva.proyectosit.controller.util.JsfUtil;
import co.com.almaviva.proyectosit.entity.StAduana;
import co.com.almaviva.proyectosit.entity.StCarga;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author cordonez
 */
@ManagedBean(name = "aduanasController")
@SessionScoped
public class AduanasController {

    @EJB
    private StCargaServiceBeanLocal PersistenciaAduanasControllerEJB;
    private boolean flagPanelEdicionCreacionDeclaracion;
    private StAduana stAduana;
    private StCarga stCarga;
    private boolean activadorBotonPersistir;
    private boolean activadorBotonActualizar;

    public AduanasController() {
    }

    public void init() {
        this.stAduana = new StAduana();
        this.activadorBotonActualizar = false;
        this.activadorBotonPersistir = false;
        this.flagPanelEdicionCreacionDeclaracion = false;
    }

    public void habilitarPanelCreacionEdicionDeclaracion() {
        this.stAduana = new StAduana();
        this.flagPanelEdicionCreacionDeclaracion = true;
        this.activadorBotonPersistir = true;
        this.activadorBotonActualizar = false;
    }

    public void editarDeclaracion(StAduana aduana) {
        this.stAduana = aduana;
        this.flagPanelEdicionCreacionDeclaracion = true;
        this.activadorBotonPersistir = false;
        this.activadorBotonActualizar = true;
    }

    public void crearDeclaracion() {
        try {
            obtenerFechaMayor(stAduana, stCarga);
            stAduana.setCarConsec(stCarga);
            PersistenciaAduanasControllerEJB.create(stAduana);
            JsfUtil.addSuccessMessage("La declaracion registrada exitosamente");
            this.flagPanelEdicionCreacionDeclaracion = false;
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar la declaracion");
        }
    }

    public void actualizarDeclaracion() {
        try {
            PersistenciaAduanasControllerEJB.updateAduana(stAduana);
            JsfUtil.addSuccessMessage("La declaracion se a actualizado exitosamente");
            this.flagPanelEdicionCreacionDeclaracion = false;
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al actualizar la declaracion");
        }
    }

    public void eliminarDeclaracion(StAduana aduana) {
        try {
            this.stAduana = aduana;
            PersistenciaAduanasControllerEJB.remove(stAduana);
            JsfUtil.addSuccessMessage("La declaracion eliminada correctamente");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al eliminar la declaracion");
        }
    }

    public List<StAduana> getListaAduanas() {
        return PersistenciaAduanasControllerEJB.findAllAduanas(stCarga);

    }

    public boolean isFlagPanelEdicionCreacionDeclaracion() {
        return flagPanelEdicionCreacionDeclaracion;
    }

    public void setFlagPanelEdicionCreacionDeclaracion(boolean flagPanelEdicionCreacionDeclaracion) {
        this.flagPanelEdicionCreacionDeclaracion = flagPanelEdicionCreacionDeclaracion;
    }

    public StAduana getStAduana() {
        return stAduana;
    }

    public void setStAduana(StAduana stAduana) {
        this.stAduana = stAduana;
    }

    public StCarga getStCarga() {
        return stCarga;
    }

    public void setStCarga(StCarga stCarga) {
        this.stCarga = stCarga;
    }

    public boolean isActivadorBotonPersistir() {
        return activadorBotonPersistir;
    }

    public void setActivadorBotonPersistir(boolean activadorBotonPersistir) {
        this.activadorBotonPersistir = activadorBotonPersistir;
    }

    public boolean isActivadorBotonActualizar() {
        return activadorBotonActualizar;
    }

    public void setActivadorBotonActualizar(boolean activadorBotonActualizar) {
        this.activadorBotonActualizar = activadorBotonActualizar;
    }

    private void obtenerFechaMayor(StAduana staduana, StCarga stcarga) {
        try {
            Date fechaMayorDB = PersistenciaAduanasControllerEJB.findByMaxFechaLevante(stcarga);
            if (fechaMayorDB != null) {
                if (staduana.getAduFeclevreal().after(fechaMayorDB)) {
                    stcarga.setDepFecaduanareal(staduana.getAduFeclevreal());
                    PersistenciaAduanasControllerEJB.updateCarga(stcarga);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
