/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.controller;

import co.com.almaviva.proyectosit.business.StReportesBeanEJB;
import co.com.almaviva.proyectosit.entity.StTiempos;
import co.com.almaviva.proyectosit.entity.StTipoper;
import co.com.almaviva.proyectosit.entity.VStCross;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author cordonez
 */
@ManagedBean(name = "crossdockingController")
@SessionScoped
public class CrossdockingController implements Serializable {

    @EJB
    private StReportesBeanEJB PersistenciaReportesEJB;
    private Date fechaInicial;
    private Date fechaFinal;
    private Long ltipoper;
    private Long ldocTrans;
    private List <VStCross> listaDataTable;
    public CrossdockingController() {
        this.listaDataTable = new ArrayList<VStCross>();
    }

    public void consultarCargasImportadas() {
        
        System.out.println("fecha inicial: "+fechaInicial+" ---- fecha final:  "+fechaFinal);
        this.listaDataTable = PersistenciaReportesEJB.fullview();
        
    }

    public void generalExcel() throws IOException, JRException {

        try {
            List<VStCross> lista = PersistenciaReportesEJB.fullview();

            byte[] bytes = null;
            String ruta;
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("pFechaInicial", fechaInicial);
            parametros.put("pFechaFinal", fechaFinal);

            ruta = this.getClass().getResource("../reportesJasper/logo.png").toString();
            System.out.println("Ruta: "+ ruta);
            parametros.put("pLogo", ruta);

            InputStream archivoJasper = this.getClass().getResourceAsStream("../reportesJasper/reporteCrossdocking.jasper");

            String nombreArchivo = "reporteCrossdocking.xlsx";

            JasperPrint jasperPrint = JasperFillManager.fillReport(archivoJasper, parametros, new JRBeanCollectionDataSource(lista));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, nombreArchivo);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
            exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, true);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, true);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, true);

            exporter.exportReport();

            ExternalContext ext = context.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) ext.getResponse();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "inline; filename=" + nombreArchivo);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(byteArrayOutputStream.toByteArray());
            servletOutputStream.flush();
            servletOutputStream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public SelectItem[] getListaOperacion() {

        SelectItem[] items = null;
   
            List<StTipoper> entities = PersistenciaReportesEJB.findAllOperation();

            int size = true ? entities.size() + 1 : entities.size();
            items = new SelectItem[size];
            int i = 0;
            if (true) {
                items[0] = new SelectItem("", "-seleccione uno-");
                i++;
            }
            for (StTipoper tipoper : entities) {
                items[i++] = new SelectItem(tipoper.getTipConsec(), tipoper.getTipNombre());
            }
        
        return items;

    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Long getLtipoper() {
        return ltipoper;
    }

    public void setLtipoper(Long ltipoper) {
        this.ltipoper = ltipoper;
    }

    public Long getLdocTrans() {
        return ldocTrans;
    }

    public void setLdocTrans(Long ldocTrans) {
        this.ldocTrans = ldocTrans;
    }

    public List <VStCross> getListaDataTable() {
        return listaDataTable;
    }

    public void setListaDataTable(List <VStCross> listaDataTable) {
        this.listaDataTable = listaDataTable;
    }

}
