package co.com.almaviva.proyectosit.controller.util;

import co.com.almaviva.proyectosit.entity.StUsuario;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 * CE- com.comercio.controller.util CeUtil.java Purpose: Implementar mÃ©todos
 * generales del negocio para ser utilizados por los controllers de la
 * aplicaciÃ³n.
 *
 * @author Grupo Desarrollo Java Almaviva S.A
 * @wchaparr
 */
public class CeUtil {

    /**
     * Method Description: Este metodo suma n dias dada una fecha inicial y
     * retorna el resultado en formato Date.
     *
     * Parameters:
     *
     * @param fecha, fecha inicial
     * @param numeroDias, a sumar a la fecha inicial
     *
     * Return:
     * @return nuevaFecha, la nuevaFecha calculada
     */
    public Date getSumarDiasFecha(Date fecha, int numeroDias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DATE, numeroDias);
        Date nuevaFecha = cal.getTime();
        return nuevaFecha;
    }

    /**
     * Method Description: Este metodo devuelve la fecha actual del sistema
     *
     * Parameters:
     *
     * @param N/A
     *
     * Return:
     * @return fecha del sistema
     */
    public Date getSysdate() {
        Date sysdate = Calendar.getInstance().getTime();
        return sysdate;
    }

    /**
     * Method Description: Este metodo valida si una fecha es valida o no dado
     * un formato
     *
     * Parameters:
     *
     * @param fecha,
     * @param formato, formato de fecha dd/MM/yyyy Return:
     * @return TRUE or FALSE
     */
    public boolean isFechaValida(Date fecha, String formato) {
        return true;
    }

    /**
     * Method Description: Este metodo valida si el dato es un numero valido
     *
     *
     * Parameters:
     *
     * @param cadena, Return:
     * @return TRUE or FALSE
     */
    public long longValue(Double valor) {
        try {
            long newValue = valor.longValue();
            return newValue;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Method Description: Este metodo valida si el dato es un numero valido
     *
     *
     * Parameters:
     *
     * @param cadena, Return:
     * @return TRUE or FALSE
     */
    public boolean checkIsNumber(String toCheck) {
        try {
            Long.parseLong(toCheck);
            return true;
        } catch (NumberFormatException numForEx) {
            return false;
        }
    }

    /**
     * Method Description: Este metodo valida la longitud de una cadena
     *
     * * Parameters:
     *
     * @param cadena,
     * * @param longitud Return:
     * @return TRUE or FALSE
     */
    public boolean checkIsValidLengthString(String value, int length) {
        int len = value.length();
        if (len > length) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method Description: Este metodo valida
     *
     * * Parameters:
     *
     * @param cadena,
     * * @param longitud Return:
     * @return TRUE or FALSE
     */
    public boolean checkIsValidStringToBigDecimal(String valor) {
        try {
            Double d = Double.parseDouble(valor);
            BigDecimal bd = BigDecimal.valueOf(d);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method Description: Este metodo valida la longitud de una tipo de dato
     * Long
     *
     * * Parameters:
     *
     * @param numero Long,
     * * @param longitud Return:
     * @return TRUE or FALSE
     */
    public boolean checkIsValidLengthLong(Long value, int length) {
        String val = String.valueOf(value);
        int len = val.length();
        if (len > length) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method Description: Este metodo evalua si una fecha es valida en el
     * formato ddMMyyyy
     *
     * * Parameters:
     *
     * @param Fecha de tipo ddMMyyyy, Return:
     * @return TRUE or FALSE
     */
    public boolean checkIsDate(Date date) {
        try {
            DateFormat dfo = new SimpleDateFormat("ddMMyyyy");
            String fec = dfo.format(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String realizarAjusteSubpartida(String subpartida) {
        String temp = subpartida.replaceAll("[\\s.]", "");

        return temp;
    }

    /**
     * MÃ©todo utilitario que toma la subpartida y le agrega los puntos
     *
     * @param entidad
     */
    public static String convertir(String subpartida) {
        StringBuilder sb = new StringBuilder();
        sb.append(subpartida.substring(0, 4)).append(".");
        sb.append(subpartida.substring(4, 6)).append(".");
        sb.append(subpartida.substring(6, 8)).append(".");
        sb.append(subpartida.substring(8));

        return sb.toString();
    }

    /**
     *
     * @param subNom
     * @return
     */
    /*public static String convertir(Long subNom) {
     String subpartida = subNom.toString();
     StringBuilder sb = new StringBuilder();
     sb.append(subpartida.substring(0, 4)).append(".");
     sb.append(subpartida.substring(4, 6)).append(".");
     sb.append(subpartida.substring(6, 8)).append(".");
     sb.append(subpartida.substring(8));

     return sb.toString();
     }*/
    public static StUsuario getUsuarioSesion() {
        StUsuario ceMaUsuarios = new StUsuario();
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession(false);
            ceMaUsuarios = (StUsuario) request.getSession().getAttribute("usuario");
        } catch (Exception e) {
            System.err.println("No se encuentra un usuario en sesion. Error= " + e);
        }
        return ceMaUsuarios;
    }

    public static String formatearIp() {
        StringBuilder direccion = new StringBuilder();
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            StringBuffer ipCompleta = request.getRequestURL();

            String soloIp = ipCompleta.substring(4, (ipCompleta.lastIndexOf(":") + 5));
            direccion.append("t3");
            direccion.append(soloIp);

        } catch (Exception e) {
            System.err.println("No se obtuvo direccion Ip. Error = " + e);
        }

        return direccion.toString();
    }

    public static Connection obtenerDataSource(String url,String user,String passw) {
        Connection conn = null;
        String driverName = "oracle.jdbc.driver.OracleDriver";
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", passw);

        try {
            Class.forName(driverName);
        } catch (Exception e) {
        }

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@"+url , connectionProps);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Connected to database");
        
        return conn;
    }
    public static void cerrarConexion(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public String obtenerMesFecha(Date fecha) {
        String mes = "";
        int mesInt;
        try {
            mesInt = fecha.getMonth() + 1;
        
            if (mesInt == 1) {mes = "Enero";} 
            else if (mesInt == 2) {mes = "Febrero";} 
            else if (mesInt == 3) {mes = "Marzo";} 
            else if (mesInt == 4) {mes = "Abril";} 
            else if (mesInt == 5) {mes = "Mayo";} 
            else if (mesInt == 6) {mes = "Junio";} 
            else if (mesInt == 7) {mes = "Julio";} 
            else if (mesInt == 8) {mes = "Agosto";} 
            else if (mesInt == 9) {mes = "Septiembre";} 
            else if (mesInt == 10) {mes = "Octubre";} 
            else if (mesInt == 11) {mes = "Noviembre";} 
            else if (mesInt == 12) {mes = "Diciembre";}
        } catch (Exception e) {
            System.err.println("Error al obtener el mes de la fecha recibida" + e);
        }
       return mes ;
    }
    
    public static String convierteCaracteres(String text) {
        String cadena = null;
        try {
            cadena =  new String(text.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Error al convertir a caracteres especiales "+ ex);
        }
        
        return cadena;
    
    }
}
