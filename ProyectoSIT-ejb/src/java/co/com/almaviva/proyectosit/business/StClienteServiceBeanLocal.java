/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.business;

import co.com.almaviva.proyectosit.entity.StCarga;
import co.com.almaviva.proyectosit.entity.StCategor;
import co.com.almaviva.proyectosit.entity.StCliente;
import co.com.almaviva.proyectosit.entity.StCliprov;
import co.com.almaviva.proyectosit.entity.StDepclie;
import co.com.almaviva.proyectosit.entity.StDeposit;
import co.com.almaviva.proyectosit.entity.StDetfact;
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
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author egonzalm
 */
@Local
public interface StClienteServiceBeanLocal {

    public int count();

    public List<StCliente> findRange(int[] range);

    public List<StCliente> findAll();

    public StCliente find(Object id);

    public StRutprov findRuta(Object id);
    
    public StHorario findHorarioByid(Long id);

    public void remove(StCliente entity);

    public void remove(StProduct entity);
    
    public void remove(StTiempos entity);
    
    public void remove(StDepclie entity);

    public void edit(StCliente entity);

    public void create(StCliente entity);

    public void create(StCliprov entity);

    public void create(StProduct entity);

    public void create(StTiempos entity);
    
    public void create(StHorario entity);
    
    public void create(StDepclie entity);

    public void updateProductCurrent(StProduct entity);

    public void updateTimeCurrent(StTiempos stTiempos);
    
    public void updateHoraryCurrent(StHorario stHorario);

    public boolean isValidTerceroByNit(Long Nit);

    public boolean editTerceroFacade(StCliente ceTercero);

    public List<StCliente> findAllCustomersByNit(String NitCustomer);

    public List<StCliente> findAllCustomersByName(String NameCustomer);

    public List<StCliente> findAllCustomersByName2(String NameCustomer);

    public List<StCliente> findAllCustomersByNit2(String NitCustomer);

    public List<StCliente> findAllCustomersByNameX(String NameCustomer);

    public List<StCliente> findAllCustomersByNitX(String NitCustomer);

    public List<StCliente> findAllClienteDescription(String description);

    public StCliente obtenerTerceroPorNit(Long nit);

    public StCliente getStClienteAlmaviva();

    public List<StTippro> findAllTipProd();

    public List<StProveed> findAllProveed();

    public List<StTipprod> findAllTipoProducto();

    public List<StTipserv> findAllServ();
    
    public List<StTipserv> findAllServExcepGeneral();

    public List<StCliprov> findAllCliProvLogBytipServ(Long tipoServ, StCliente cliente);
    
    public List<StCliprov> findAllProvByServGeneral(StCliente cliente);
    
    public List<StCliprov> findAllProvOnlyServGeneral(StCliente cliente);
    
    public List<StCliprov> findAllCliProvLog( StCliente cliente);

    public List<StTipserv> findService(Long l);

    public List<StTipserv> findService();

    public void updateProveedCurrent(StCliprov cliprov);

    public void remove(StCliprov entity);
    
    public void remove(StHorario entity);

    public List<StCarga> findCarga(StCliprov stCliprov);

    public List<StCliprov> findAllCliProveePro(StCliente clienteSeleccionado);

    public List<StProduct> findAllProductosXcliente(StCliente stCliente);

    public List<StCategor> findAllCategorByClient(StCliente stCliente);

    public List<StModelo> findAllModeloByClient(StCliente stCliente);

    public List<StTipprod> findAllTipProByClient(StCliente stCliente);

    public List<StDetfact> findDetfactXproduct(StProduct stProduct);

    public List<StTiempos> findAlltimesNormal();

    public List<StTiempos> findAlltimesGeneral();

    public List<StRuta> findAllRutas();

    public List<StRutprov> findRutasByparams(Long l, String s);

    public List<StTipdia> findAllTypesDays();

    public StCliprov findIdCliProveeXtiempo(Long ltipoServ, Long cli_tpcconsec);

    public List<StHorario> findAllHorarios(); 
    
    public List<StDiasema> findAllDaysWeek();

    public List<StDepclie> findAllDepositByClient(StCliente clienteSeleccionado);

    public List<StDeposit> findAllDepositUnassociated();

}
