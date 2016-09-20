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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author egonzalm
 */
@Stateless
public class StClienteServiceBean extends AbstractFacade<StCliente> implements StClienteServiceBeanLocal {

    @PersistenceContext(unitName = "SITWebPU")
    private EntityManager em;
    private List<StCliente> listaC;

    static final Long NIT_ALMAVIVA = 860002153L;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StClienteServiceBean() {
        super(StCliente.class);
    }

    @Override
    public List<StCliente> findAllCustomersByNitX(String NitCustomer) {
        return (List<StCliente>) em.createNamedQuery("StCliente.findByTerNit")
                .setParameter("terNit", NitCustomer)
                .getResultList();
    }

    @Override
    public List<StCliente> findAllCustomersByNameX(String NameCustomer) {
        return (List<StCliente>) em.createNamedQuery("StCliente.findByTerNom")
                .setParameter("terNom", NameCustomer)
                .getResultList();
    }

    @Override
    public List<StCliente> findAllCustomersByNit2(String NitCustomer) {
        List<StCliente> listaC = null;
        listaC = (List<StCliente>) em.createNamedQuery("StCliente.findByTerNit").setParameter("terNit", NitCustomer).getResultList();
        return listaC;
    }

    @Override
    public List<StCliente> findAllCustomersByName2(String NameCustomer) {
        listaC = new ArrayList<StCliente>();
        listaC = (List<StCliente>) em.createNamedQuery("StCliente.findByTerNom").setParameter("terNom", NameCustomer).getResultList();
        return listaC;
    }

    @Override
    public List<StCliente> findAllCustomersByName(String NameCustomer) {
        Query query = em.createNamedQuery("StCliente.findByName");
        query.setParameter(1, "%" + NameCustomer + "%");
        List<StCliente> results = query.getResultList();
        return results;
    }

    @Override
    public List<StCliente> findAllCustomersByNit(String NitCustomer) {
        Query query = em.createNamedQuery("StCliente.findByNit");
        query.setParameter(1, "%" + NitCustomer + "%");
        List<StCliente> results = query.getResultList();
        System.out.println("**results size: " + results.size());
        return results;
    }

    @Override
    public boolean editTerceroFacade(StCliente ceTercero) {
        try {
            getEntityManager().merge(ceTercero);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isValidTerceroByNit(Long Nit) {
        Query query = em.createNamedQuery("StCliente.findUniqueByTerNit");
        query.setParameter("terNit", Nit);
        List<StCliente> results = query.getResultList();
        if (results.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<StCliente> findAllClienteDescription(String description) {
        List<StCliente> terceroResultado = new ArrayList<StCliente>();
        List<StCliente> results = new ArrayList<StCliente>();
        //List<StCliente> results = query.setParameter("terNom", description).getResultList();
        if (isNumeric(description)) {
            Long nitTercero = Long.parseLong(description);
            Query query = em.createNamedQuery("StCliente.findByCliNit");
            results = query.setParameter("cliNit", nitTercero).getResultList();
        } else {
            Query query = em.createNamedQuery("StCliente.findByCliLikeNombre");
            results = query.setParameter("cliNombre", "%" + description.trim().toUpperCase() + "%").getResultList();
        }
        return results;
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método utilitario que retorna la información de Almaviva
     *
     * @return
     */
    @Override
    public StCliente getStClienteAlmaviva() {
        return this.find(NIT_ALMAVIVA);
    }

    @Override
    public void create(StCliente entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(StCliente entity) {
        super.edit(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(StCliente entity) {
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StCliente find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StCliente> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StCliente> findRange(int[] range) {
        return super.findRange(range); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        return super.count(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StCliente obtenerTerceroPorNit(Long nit) {
        StCliente resultado = null;
        Query q = em.createNamedQuery("StCliente.findByTerNit").setParameter("terNit", nit);
        resultado = (StCliente) q.getSingleResult();

        return resultado;
    }

    @Override
    public List<StProveed> findAllProveed() {
        Query query = em.createNamedQuery("StProveed.findAll", StProveed.class);
        return query.getResultList();
    }

    @Override
    public List<StTippro> findAllTipProd() {
        Query query = em.createNamedQuery("StTipprod.findAll");
        return query.getResultList();
    }

    @Override
    public List<StTipserv> findAllServ() {
        Query query = em.createNamedQuery("StTipserv.findAll", StTipserv.class);
        return query.getResultList();
    }

    @Override
    public List<StTipserv> findAllServExcepGeneral() {
        Query query = em.createNamedQuery("StTipserv.findAllexceptGeneral", StTipserv.class);
        return query.getResultList();
    }

    @Override
    public List<StCliprov> findAllCliProvLogBytipServ(Long tipServ, StCliente cliente) {
        Query query = em.createNamedQuery("StCliprov.findproveeXcliANDservic", StCliprov.class).setParameter("cliConsec", cliente).setParameter("tipServic", tipServ);

        if (query.getResultList().isEmpty() || query.getResultList() == null) {
            return new ArrayList<StCliprov>();
        }
        return query.getResultList();
    }

    @Override
    public List<StCliprov> findAllProvByServGeneral(StCliente cliente) {
        Query query = em.createNamedQuery("StCliprov.findproveeXServicGeneral", StCliprov.class).setParameter("cliConsec", cliente);
        return query.getResultList();
    }

    @Override
    public List<StCliprov> findAllProvOnlyServGeneral(StCliente cliente) {
        Query query = em.createNamedQuery("StCliprov.findproveeXcliOnlyGeneral", StCliprov.class).setParameter("cliConsec", cliente);
        return query.getResultList();
    }

    @Override
    public List<StCliprov> findAllCliProvLog(StCliente cliente) {
        Query query = em.createNamedQuery("StCliprov.findproveeXcli", StCliprov.class).setParameter("cliConsec", cliente);

        if (query.getResultList().isEmpty() || query.getResultList() == null) {
            return new ArrayList<StCliprov>();
        }
        return query.getResultList();
    }

    @Override
    public List<StTipserv> findService(Long l) {
        Query query = em.createNamedQuery("StTipserv.findByTipConsec", StTipserv.class);
        query.setParameter("tipConsec", l);
        return query.getResultList();
    }

    @Override
    public List<StTipserv> findService() {
        Query query = em.createNamedQuery("StTipserv.findAll", StTipserv.class);
        return query.getResultList();
    }

    @Override
    public void updateProveedCurrent(StCliprov entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void updateProductCurrent(StProduct entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void create(StCliprov entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void create(StProduct entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void create(StTiempos entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void create(StHorario entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void create(StDepclie entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void remove(StHorario entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void remove(StCliprov entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void remove(StProduct entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void remove(StTiempos entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void remove(StDepclie entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public List<StCarga> findCarga(StCliprov stCliprov) {
        Query query = em.createQuery("select c from StCarga c where c.cliTpcconsec = :cliTpcconsec", StCarga.class).setParameter("cliTpcconsec", stCliprov);
        return query.getResultList();
    }

    @Override
    public List<StCliprov> findAllCliProveePro(StCliente clienteSeleccionado) {
        Query query = em.createNamedQuery("StCliprov.findproveeProXcli", StCliprov.class).setParameter("cliConsec", clienteSeleccionado);
        return query.getResultList();
    }

    @Override
    public List<StTipprod> findAllTipoProducto() {
        Query query = em.createNamedQuery("StTipprod.findAll", StTipprod.class);
        return query.getResultList();
    }

    @Override
    public List<StProduct> findAllProductosXcliente(StCliente stCliente) {
        Query query = em.createNamedQuery("StTipprod.findByProductoXcliente", StProduct.class).setParameter("cliConsec", stCliente);
        return query.getResultList();
    }

    @Override
    public List<StCategor> findAllCategorByClient(StCliente stCliente) {
        Query query = em.createNamedQuery("StCategor.findByCatXcliente", StCategor.class).setParameter("cliConsec", stCliente);
        return query.getResultList();
    }

    @Override
    public List<StModelo> findAllModeloByClient(StCliente stCliente) {
        Query query = em.createNamedQuery("StModelo.findByModeloByClient", StModelo.class).setParameter("cliConsec", stCliente);
        return query.getResultList();
    }

    @Override
    public List<StTipprod> findAllTipProByClient(StCliente stCliente) {
        Query query = em.createNamedQuery("StTipprod.findByTipprodByClient", StTipprod.class).setParameter("cliConsec", stCliente);
        return query.getResultList();
    }

    @Override
    public List<StDetfact> findDetfactXproduct(StProduct stProduct) {
        Query query = em.createNamedQuery("StDetfact.findByDetFactXproduct", StDetfact.class).setParameter("proConsec", stProduct);
        return query.getResultList();
    }

    @Override
    public List<StTiempos> findAlltimesNormal() {
        Query query = em.createNamedQuery("StTiempos.findByCliTpcConsecServicNotGeneral", StTiempos.class);
        return query.getResultList();
    }

    @Override
    public List<StRuta> findAllRutas() {
        Query query = em.createNamedQuery("StRuta.findAll", StRuta.class);
        return query.getResultList();
    }

    @Override
    public List<StRutprov> findRutasByparams(Long longProveelog, String description) {

        List<StRutprov> results = new ArrayList<StRutprov>();

        description = "%" + description.trim().toLowerCase() + "%";
//              Query query = em.createNamedQuery("StRutprov.findByRutaXproveed",StRutprov.class).setParameter("proConsec",longProveelog);
//              return query.getResultList();
        Query query = em.createNamedQuery("StRutprov.findByParams", StRutprov.class);
        query.setParameter("proConsec", longProveelog);
        query.setParameter("rutCodigo", description);
        query.setParameter("rutNombre", description);
        query.setParameter("pueNombreDestino", description);
        query.setParameter("paiNomDestino", description);
        query.setParameter("ciuNomDestino", description);
        query.setParameter("pueNombreOrigen", description);
        query.setParameter("paiNomOrigen", description);
        query.setParameter("ciuNomOrigen", description);
        query.setParameter("ubiNombre", description);
        results = query.getResultList();

        return results;
    }

    @Override
    public StRutprov findRuta(Object id) {
        return getEntityManager().find(StRutprov.class, id);
    }

    @Override
    public StHorario findHorarioByid(Long id) {
        Query query = em.createNamedQuery("StHorario.findByHorConsec", StHorario.class).setParameter("horConsec", id);
        return (StHorario) query.getSingleResult();
    }

    @Override
    public List<StTipdia> findAllTypesDays() {
        Query query = em.createNamedQuery("StTipdia.findAll", StTipdia.class);
        return query.getResultList();
    }

    @Override
    public List<StDiasema> findAllDaysWeek() {
        Query query = em.createNamedQuery("StDiasema.findAll", StDiasema.class);
        return query.getResultList();
    }

    @Override
    public void updateTimeCurrent(StTiempos entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void updateHoraryCurrent(StHorario entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public StCliprov findIdCliProveeXtiempo(Long ltipoServ, Long pro_consec) {

        Query query = em.createNamedQuery("StCliprov.findByproConsecANDtipServic", StCliprov.class).setParameter("proConsec", pro_consec).setParameter("tipConsec", ltipoServ);

        if (query.getResultList().isEmpty() || query.getResultList().contains(null)) {
            return null;
        }

        return (StCliprov) query.getResultList().get(0);
    }

    @Override
    public List<StTiempos> findAlltimesGeneral() {
        Query query = em.createNamedQuery("StTiempos.findByCliTpcConsec", StTiempos.class);
        return query.getResultList();
    }

    @Override
    public List<StHorario> findAllHorarios() {
        Query query = em.createNamedQuery("StHorario.findAll", StHorario.class);
        return query.getResultList();
    }

    @Override
    public List<StDepclie> findAllDepositByClient(StCliente clienteSeleccionado) {
        Query query = em.createNamedQuery("StDepclie.findByCliConsec", StDepclie.class).setParameter("cliConsec", clienteSeleccionado.getCliConsec());
        return query.getResultList();
    }

    @Override
    public List<StDeposit> findAllDepositUnassociated() {
        List<StDeposit> listaDepositos = new ArrayList<StDeposit>();
        Query query = em.createNamedQuery("StDeposit.findByStDepcliePK", StDeposit.class);
        listaDepositos = query.getResultList();
        String namedQuery = "SELECT s FROM StDeposit s WHERE s.depConsec NOT IN (";
        for (StDeposit stDeposit : listaDepositos) {
            namedQuery = namedQuery + stDeposit.getDepConsec().toString().concat(",");
        }
        namedQuery = namedQuery.concat(")");
        namedQuery = namedQuery.replace(",)", ")");
        Query query1 = em.createQuery(namedQuery);
        return query1.getResultList();
    }

}
