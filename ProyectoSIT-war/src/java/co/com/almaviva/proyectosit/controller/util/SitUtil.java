/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.controller.util;

import co.com.almaviva.proyectosit.entity.StRutprov;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author cordonez
 */
public class SitUtil {

    public static Long convertirAsegundos(Date date) {
        int hor, min, horseg, minseg;
        hor = date.getHours();
        min = date.getMinutes();
        horseg = hor * 3600;
        minseg = min * 60;

        return Long.valueOf(horseg + minseg);
    }

    public static ArrayList<Long> obtenerHoraFormateada(Long l) {
        ArrayList<Long> listaHorasMinutos = new ArrayList<Long>();
        Long hor, min;
        hor = l / 3600;
        min = (l - (3600 * hor)) / 60;
        listaHorasMinutos.add(0, hor);
        listaHorasMinutos.add(1, min);

        return listaHorasMinutos;
    }

    public static Date convertirADate(Long l) {
        Integer hor, min;
        Date date = new Date();
        hor = l.intValue() / 3600;
        min = (l.intValue() - (3600 * hor)) / 60;
        date.setHours(hor);
        date.setMinutes(min);
        return date;
    }

    
    public static String validaMinuto(Long param) {
        if (param < 10 && param > 0) {
            return "0".concat(param.toString());
        }
        return param != 0 ? param.toString() : "00";
    }
    
    public static boolean validarCamposHorarios(String tipoOperacion, Long lproveeHorario, Long tipoDiaHora, Date horaInicio, Date horaFin) {
        if (tipoOperacion.equals("C")) {
            if (lproveeHorario == null) {
                JsfUtil.addErrorMessage("El combo Proveedor Logistico General debe tener alguna seleccion");
                return false;
            } else if (tipoDiaHora == null) {
                JsfUtil.addErrorMessage("El combo Dia debe tener alguna seleccion");
                return false;
            } else if (horaInicio == null) {
                JsfUtil.addErrorMessage("El campo Hora Inicio no puede estar vacio");
                return false;
            } else if (horaFin == null) {
                JsfUtil.addErrorMessage("El combo Hora Fin debe tener alguna seleccion");
                return false;
            }else if(horaFin.before(horaInicio) || horaFin.equals(horaInicio)){
                JsfUtil.addErrorMessage("La Hora Inicio no puede ser mayor o igual a la Hora Fin");
                return false;
            }
            return true;
        } else {
            if (tipoDiaHora == null) {
                JsfUtil.addErrorMessage("El combo Dia debe tener alguna seleccion");
                return false;
            } else if (horaInicio == null) {
                JsfUtil.addErrorMessage("El campo Hora Inicio no puede estar vacio");
                return false;
            } else if (horaFin == null) {
                JsfUtil.addErrorMessage("El combo Hora Fin debe tener alguna seleccion");
                return false;
            }else if(horaFin.before(horaInicio) || horaFin.equals(horaInicio)){
                JsfUtil.addErrorMessage("La Hora Inicio no puede ser mayor o igual a la Hora Fin");
                return false;
            }
            return true;

        }
    }

    public static boolean validaCamposNoVaciosProducto(String codProducto, Long lcatConsec, Long lmodConsec, Long ltipprodConsec) {

        if (codProducto.isEmpty()) {
            JsfUtil.addErrorMessage("El campo Codigo no puede estar vacio");
            return false;
        } else if (lcatConsec == null) {
            JsfUtil.addErrorMessage("El combo Categoria debe tener alguna seleccion");
            return false;
        } else if (lmodConsec == null) {
            JsfUtil.addErrorMessage("El combo Modelo debe tener alguna seleccion");
            return false;
        } else if (ltipprodConsec == null) {
            JsfUtil.addErrorMessage("El combo Tipo de producto debe tener alguna seleccion");
            return false;
        }
        return true;
    }

    public static boolean validaCamposNoVaciosTiempo(String tipoOperacion, Short tiempoTransporte, Long tipoDias, StRutprov stRutprov, Long ltipoServ, Long longProveelog) {
        if (tipoOperacion.equals("C")) {
            if (tiempoTransporte == null) {
                JsfUtil.addErrorMessage("El campo Tiempo Transporte no puede ir vacio");
                return false;
            } else if (tipoDias == null) {
                JsfUtil.addErrorMessage("El combo Tipo dias debe tener alguna seleccion");
                return false;
            } else if (stRutprov == null) {
                JsfUtil.addErrorMessage("Se debe seleccionar alguna ruta");
                return false;
            } else if (ltipoServ == null) {
                JsfUtil.addErrorMessage("Se combo Tipo Servicio debe tener alguna seleccion");
                return false;
            } else if (longProveelog == null) {
                JsfUtil.addErrorMessage("El combo Proveedor Logistico debe tener alguna seleccion");
                return false;
            }
            return true;

        } else {
            if (tiempoTransporte == null) {
                JsfUtil.addErrorMessage("El campo Tiempo Transporte no puede ir vacio");
                return false;
            } else if (tipoDias == null) {
                JsfUtil.addErrorMessage("El combo Tipo dias debe tener alguna seleccion");
                return false;
            }
            return true;
        }
    }

    public static boolean validaCamposNoVaciosTiempoGeneral(String tipoOperacion, Long longProveeGeneral, Short horasPreinspeccion, Short horasAduana, Long tipoDiasGeneral) {
        if (tipoOperacion.equals("C")) {
            if (longProveeGeneral == null) {
                JsfUtil.addErrorMessage("El combo Proveedor Logistico General debe tener alguna seleccion");
                return false;
            } else if (horasPreinspeccion == null) {
                JsfUtil.addErrorMessage("El campo Horas Hábiles Preinspeccion no puede estar vacio");
                return false;
            } else if (horasAduana == null) {
                JsfUtil.addErrorMessage("El campo Horas Aduana no puede estar vacio");
                return false;
            } else if (tipoDiasGeneral == null) {
                JsfUtil.addErrorMessage("El combo Tipo dias debe tener alguna seleccion");
                return false;
            }
            return true;
        } else {
            if (tipoDiasGeneral == null) {
                JsfUtil.addErrorMessage("El combo Tipo dias debe tener alguna seleccion");
                return false;
            } else if (horasPreinspeccion == null) {
                JsfUtil.addErrorMessage("El campo Horas Hábiles Preinspeccion no puede estar vacio");
                return false;
            } else if (horasAduana == null) {
                JsfUtil.addErrorMessage("El campo Horas Aduana no puede estar vacio");
                return false;
            }
            return true;

        }

    }
}
