/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.controller;

import co.com.almaviva.proyectosit.entity.StCarga;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author egonzalm
 */
@ManagedBean(name = "indFechasController")
@SessionScoped
public class IndFechasController implements Serializable{
    private static final long serialVersionUID = -7038587966405121819L;

    private StCarga stCargaFechasCurrent;
    
    public IndFechasController() {
    }
    
    public void init(){
        stCargaFechasCurrent = new StCarga();
    }
    
    public void consultaCargasClientehandleSelect() {
        stCargaFechasCurrent = new StCarga();
    }
    
    public void consultaFechasCarga(StCarga stCarga) {
        stCargaFechasCurrent = stCarga;
    }

    public StCarga getStCargaFechasCurrent() {
        return stCargaFechasCurrent;
    }

    public void setStCargaFechasCurrent(StCarga stCargaFechasCurrent) {
        this.stCargaFechasCurrent = stCargaFechasCurrent;
    }   
}