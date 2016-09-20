/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.business;

import co.com.almaviva.proyectosit.entity.StAduana;
import co.com.almaviva.proyectosit.entity.StCarga;
import co.com.almaviva.proyectosit.entity.StCliente;
import co.com.almaviva.proyectosit.entity.StCliprov;
import co.com.almaviva.proyectosit.entity.StContene;
import co.com.almaviva.proyectosit.entity.StDepclie;
import co.com.almaviva.proyectosit.entity.StDetcarg;
import co.com.almaviva.proyectosit.entity.StFactura;
import co.com.almaviva.proyectosit.entity.StFestivo;
import co.com.almaviva.proyectosit.entity.StIncoter;
import co.com.almaviva.proyectosit.entity.StLogdetcarg;
import co.com.almaviva.proyectosit.entity.StModtran;
import co.com.almaviva.proyectosit.entity.StMoneda;
import co.com.almaviva.proyectosit.entity.StOrdcomp;
import co.com.almaviva.proyectosit.entity.StPedido;
import co.com.almaviva.proyectosit.entity.StPuerto;
import co.com.almaviva.proyectosit.entity.StRutprov;
import co.com.almaviva.proyectosit.entity.StTipemba;
import co.com.almaviva.proyectosit.entity.StTipoper;
import co.com.almaviva.proyectosit.entity.StTipserv;
import co.com.almaviva.proyectosit.entity.StTransbo;
import co.com.almaviva.proyectosit.entity.StUnicarg;
import java.util.Date;
import java.util.List;

/**
 *
 * @author egonzalm
 */
public interface StCargaServiceBeanLocal {

    public int count();

    public List<StCarga> findRange(int[] range);

    public List<StCarga> findAll();

    public StCarga find(Object id);

    public void remove(StCarga entity);

    public void edit(StCarga entity);

    public void create(StCarga entity);

    public List consultarNativeQuery(Class entidad, String nativeQuery, List params) throws Exception;

    public List<StCliente> findAllClienteDescription(String description);

    public StCliente findStCliente(Object id);

    public StRutprov findStRutprovConverter(Object id, Long proConsec, String descripcion, Long cliConsec, Long modTransporte, Long tipServicio);

    public List<StTipoper> findStTipoper();

    public List<StModtran> findStModTran(Long modoTransporte);

    public List<StCliprov> findStCliprovAduana(Long cliConsec);

    public List<StCliprov> findStCliprovProducto(Long cliConsec);

    public List<StDepclie> findStDepclie(Long cliConsec);

    public List<StIncoter> findStIncoter();

    public List<StTipemba> findStTipemba();

    public void createStPedido(StPedido stPedido);

    public Long getSeqStPedido();

    public Long getSeqStCarga();

    public List<StCarga> getCargasCliente(Long cliConsec);

    public List<StMoneda> getStMoneda();

    public void createStFactura(StFactura stFactura);

    public void editStFactura(StFactura stFactura);

    public void removeStFactura(StFactura stFactura);

    public List<StFactura> getStFacturaCarga(Long carConsec);

    public List<StOrdcomp> getStOrdCompraCarga(Long carConsec);

    public void createStOrdcomp(StOrdcomp stOrdcomp);

    public void editStOrdcomp(StOrdcomp stOrdcomp);

    public void removeStOrdcomp(StOrdcomp stOrdcomp);

    public List<StDetcarg> getStDetcargOrigen(Long carConsec);

    public List<StDetcarg> getStDetcargMaritima(Long carConsec);

    public List<StDetcarg> getStDetcargAerea(Long carConsec);

    public List<StDetcarg> getStDetcargDestino(Long carConsec);

    public List<StCliprov> getProveedorXServicio(Long cliConsec, Long tipServicio);

    public Long getProveedorXServicio(Long cliConsec, Long tipServicio, Long proConsec);

    public List<StRutprov> getStRutasXProveedor(Long proConsec, String descripcion, Long cliConsec, Long modTransporte);

    public void createStDetcarg(StDetcarg stDetcarg);

    public void editStDetcarg(StDetcarg stDetcarg);

    public void removeStDetcarg(StDetcarg stDetcarg);

    public void createStLogdetcarg(StLogdetcarg stLogdetcarg);

    public List<StContene> getStConteneCarga(Long carConsec);

    public void createStContene(StContene stContene);

    public void editStContene(StContene stContene);

    public void removeStContene(StContene stContene);

    public List<StUnicarg> getStUnicarg();

    public Long getNumDiasFestivo(Date fecInicio, Date fecFin);

    public StDetcarg findStDetCarga(Object id);

    public boolean verificaCambiosLog(StDetcarg stDetcarg);

    public List<StLogdetcarg> getStLogdetcarg(Long detConsec);

    public List<StAduana> findAllAduanas(StCarga carga);

    public void create(StAduana stAduana);

    public void updateAduana(StAduana stAduana);
    
    public void updateCarga(StCarga stCarga);

    public void remove(StAduana aduana);

    public List<StTipserv> findAllStServicio();

    public List<StRutprov> getStRutasXProveedorDestino(Long proConsec, String descripcion, Long cliConsec);

    public StRutprov findStRutprovDestinoConverter(Object id, Long proConsec, String descripcion, Long cliConsec, Long tipServicio);

    public List<StTransbo> getStTransbo(Long detConsec);

    public List<StPuerto> findAllStPuerto();

    public void createStTransbo(StTransbo stTransbo);

    public void removeStTransbo(StTransbo stTransbo);

    public Date findByMaxFechaLevante(StCarga stCarga);
}
