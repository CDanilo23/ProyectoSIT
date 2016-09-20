/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.business;

import co.com.almaviva.proyectosit.entity.StAduana;
import co.com.almaviva.proyectosit.entity.StCarga;
import java.util.ArrayList;
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
import co.com.almaviva.proyectosit.entity.StTipemba;
import co.com.almaviva.proyectosit.entity.StTipoper;
import co.com.almaviva.proyectosit.entity.StRutprov;
import co.com.almaviva.proyectosit.entity.StTiempos;
import co.com.almaviva.proyectosit.entity.StTipserv;
import co.com.almaviva.proyectosit.entity.StTransbo;
import co.com.almaviva.proyectosit.entity.StUnicarg;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author egonzalm
 */
@Stateless
public class StCargaServiceBean extends AbstractFacade<StCarga> implements StCargaServiceBeanLocal {

    @PersistenceContext(unitName = "SITWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StCargaServiceBean() {
        super(StCarga.class);
    }

    @Override
    public void create(StCarga entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(StCarga entity) {
        super.edit(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(StCarga entity) {
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StCarga find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StCarga> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StCarga> findRange(int[] range) {
        return super.findRange(range); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        return super.count(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List consultarNativeQuery(Class entidad, String nativeQuery, List params) throws Exception {
        return super.consultarNativeQuery(entidad, nativeQuery, params);
    }

    @Override
    public List<StCliente> findAllClienteDescription(String description) {
        List<StCliente> stClientes;

        if (isNumeric(description)) {
            Long nitTercero = Long.parseLong(description);
            Query query = em.createNamedQuery("StCliente.findByCliNit");
            stClientes = query.setParameter("cliNit", nitTercero).getResultList();
        } else {
            Query query = em.createQuery("SELECT s FROM StCliente s WHERE TRIM(LOWER(s.cliNombre)) like :cliNombre");
            stClientes = query.setParameter("cliNombre", "%" + description.trim().toLowerCase() + "%").getResultList();
        }
        return stClientes;
    }

    @Override
    public StCliente findStCliente(Object id) {
        return getEntityManager().find(StCliente.class, id);
    }

    @Override
    public StRutprov findStRutprovConverter(Object id, Long proConsec, String descripcion, Long cliConsec, Long modTransporte, Long tipServicio) {
        StRutprov stRutprov = null;
        List<StRutprov> stRutprovs = getStRutasXProveedor(proConsec, descripcion, cliConsec, modTransporte);
        for (int i = 0; i < stRutprovs.size(); i++) {
            if (Long.parseLong(id.toString()) == stRutprovs.get(i).getRutRpconsec()) {
                stRutprov = stRutprovs.get(i);
            }
        }
        return stRutprov;
    }

    @Override
    public StRutprov findStRutprovDestinoConverter(Object id, Long proConsec, String descripcion, Long cliConsec, Long tipServicio) {
        StRutprov stRutprov = null;
        List<StRutprov> stRutprovs = getStRutasXProveedorDestino(proConsec, descripcion, cliConsec);
        for (int i = 0; i < stRutprovs.size(); i++) {
            if (Long.parseLong(id.toString()) == stRutprovs.get(i).getRutRpconsec()) {
                stRutprov = stRutprovs.get(i);
            }
        }
        return stRutprov;
    }

    @Override
    public List<StTipoper> findStTipoper() {
        Query query = em.createNamedQuery("StTipoper.findAll");
        return query.getResultList();
    }

    @Override
    public List<StModtran> findStModTran(Long modoTransporte) {
        Query query;
        if (modoTransporte == null) {
            query = em.createNamedQuery("StModtran.findAll");
        } else if (modoTransporte == 0) {
            query = em.createQuery("SELECT s FROM StModtran s WHERE (s.modConsec = :modConsec or s.modConsec = :modConsec2)").setParameter("modConsec", 1).setParameter("modConsec2", 2);
        } else {
            query = em.createNamedQuery("StModtran.findByModConsec").setParameter("modConsec", modoTransporte);
        }

        return query.getResultList();
    }

    @Override
    public List<StCliprov> findStCliprovAduana(Long cliConsec) {
        Query query = em.createQuery("select c from StCliprov c where c.cliConsec.cliConsec = :cliConsec and c.tipServic.tipConsec = 5 and c.tipProveed.tipCodigo = 2").setParameter("cliConsec", cliConsec);
        return query.getResultList();
    }

    @Override
    public List<StCliprov> findStCliprovProducto(Long cliConsec) {
        Query query = em.createQuery("select c from StCliprov c where c.cliConsec.cliConsec = :cliConsec and c.tipProveed.tipCodigo = 1").setParameter("cliConsec", cliConsec);
        return query.getResultList();
    }

    @Override
    public List<StDepclie> findStDepclie(Long cliConsec) {
        Query query = em.createQuery("select c from StDepclie c where c.stCliente.cliConsec = :cliConsec").setParameter("cliConsec", cliConsec);
        return query.getResultList();
    }

    @Override
    public List<StIncoter> findStIncoter() {
        Query query = em.createNamedQuery("StIncoter.findAll");
        return query.getResultList();
    }

    @Override
    public List<StTipemba> findStTipemba() {
        Query query = em.createNamedQuery("StTipemba.findAll");
        return query.getResultList();
    }

    @Override
    public Long getSeqStPedido() {
        Query query = em.createNativeQuery("SELECT SEQ_ST_PEDIDO.nextval FROM dual");
        return Long.parseLong(query.getSingleResult().toString());
    }

    @Override
    public Long getSeqStCarga() {
        Query query = em.createNativeQuery("SELECT SEQ_ST_CARGA.nextval FROM dual");
        return Long.parseLong(query.getSingleResult().toString());
    }

    @Override
    public void createStPedido(StPedido stPedido) {
        getEntityManager().persist(stPedido);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void createStLogdetcarg(StLogdetcarg stLogdetcarg) {
        getEntityManager().persist(stLogdetcarg);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public List<StCarga> getCargasCliente(Long cliConsec) {
        Query query = em.createQuery("select c from StCarga c where c.pedConsec.cliConsec.cliConsec = :cliConsec and c.carEstado = 1").setParameter("cliConsec", cliConsec);
        return query.getResultList();
    }

    @Override
    public List<StMoneda> getStMoneda() {
        Query query = em.createNamedQuery("StMoneda.findAll");
        return query.getResultList();
    }

    @Override
    public List<StUnicarg> getStUnicarg() {
        Query query = em.createNamedQuery("StUnicarg.findAll");
        return query.getResultList();
    }

    @Override
    public void createStFactura(StFactura stFactura) {
        try {
            getEntityManager().persist(stFactura);
            getEntityManager().flush();
            getEntityManager().clear();
        } catch (EJBException e) {
            @SuppressWarnings("ThrowableResultIgnored")
            Exception cause = e.getCausedByException();
            if (cause instanceof ConstraintViolationException) {
                @SuppressWarnings("ThrowableResultIgnored")
                ConstraintViolationException cve = (ConstraintViolationException) e.getCausedByException();
                for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<? extends Object> v = it.next();
                    System.err.println(v);
                    System.err.println("==>>" + v.getMessage());
                }
            }
        }
    }

    @Override
    public void editStFactura(StFactura stFactura) {
        getEntityManager().merge(stFactura);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void removeStFactura(StFactura stFactura) {
        getEntityManager().remove(getEntityManager().merge(stFactura));
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void createStContene(StContene stContene) {
        getEntityManager().persist(stContene);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void editStContene(StContene stContene) {
        getEntityManager().merge(stContene);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void removeStContene(StContene stContene) {
        getEntityManager().remove(getEntityManager().merge(stContene));
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void createStOrdcomp(StOrdcomp stOrdcomp) {
        getEntityManager().persist(stOrdcomp);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void editStOrdcomp(StOrdcomp stOrdcomp) {
        getEntityManager().merge(stOrdcomp);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void removeStOrdcomp(StOrdcomp stOrdcomp) {
        getEntityManager().remove(getEntityManager().merge(stOrdcomp));
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public List<StFactura> getStFacturaCarga(Long carConsec) {
        Query query = em.createQuery("select c from StFactura c where c.carConsec.carConsec = :carConsec").setParameter("carConsec", carConsec);
        return query.getResultList();
    }

    @Override
    public List<StContene> getStConteneCarga(Long carConsec) {
        Query query = em.createQuery("select c from StContene c where c.carConsec.carConsec = :carConsec").setParameter("carConsec", carConsec);
        return query.getResultList();
    }

    @Override
    public List<StOrdcomp> getStOrdCompraCarga(Long carConsec) {
        Query query = em.createQuery("select c from StOrdcomp c where c.carConsec.carConsec = :carConsec").setParameter("carConsec", carConsec);
        return query.getResultList();
    }

    @Override
    public List<StDetcarg> getStDetcargOrigen(Long carConsec) {
        Query query = em.createQuery("select c from StDetcarg c where c.carConsec.carConsec = :carConsec and c.tipConsec.tipCodigo = 1").setParameter("carConsec", carConsec);
        return query.getResultList();
    }

    @Override
    public List<StDetcarg> getStDetcargMaritima(Long carConsec) {
        Query query = em.createQuery("select c from StDetcarg c where c.carConsec.carConsec = :carConsec and c.tipConsec.tipCodigo = 2 and c.detEstado = 1").setParameter("carConsec", carConsec);
        return query.getResultList();
    }

    @Override
    public List<StDetcarg> getStDetcargAerea(Long carConsec) {
        Query query = em.createQuery("select c from StDetcarg c where c.carConsec.carConsec = :carConsec and c.tipConsec.tipCodigo = 3 and c.detEstado = 1").setParameter("carConsec", carConsec);
        return query.getResultList();
    }

    @Override
    public List<StDetcarg> getStDetcargDestino(Long carConsec) {
        Query query = em.createQuery("select c from StDetcarg c where c.carConsec.carConsec = :carConsec and c.tipConsec.tipCodigo = 4 and c.detEstado = 1").setParameter("carConsec", carConsec);
        return query.getResultList();
    }

    @Override
    public Long getProveedorXServicio(Long cliConsec, Long tipServicio, Long proConsec) {
//        Query query = em.createQuery("select c from StCliprov c where c.tipProveed.tipConsec = 2 and c.c.cliConsec.cliConsec = :cliConsec and c.tipServic.tipConsec = :tipServicio and c.proConsec.proConsec = :proConsec and exists (select s from StRutprov s where c.proConsec.proConsec = s.proConsec.proConsec)").setParameter("cliConsec", cliConsec).setParameter("tipServicio", tipServicio).setParameter("proConsec", proConsec);
        Query query = em.createQuery("SELECT k FROM StCliprov k where k.cliConsec.cliConsec = :cliConsec and k.tipProveed.tipConsec = 2 and k.tipServic.tipConsec = :tipServicio and k.proConsec.proConsec = :proConsec and exists (select s from StRutprov s where k.proConsec.proConsec = s.proConsec.proConsec)").setParameter("cliConsec", cliConsec).setParameter("tipServicio", tipServicio).setParameter("proConsec", proConsec);
        return ((StCliprov) query.getResultList().get(0)).getCliTpcconsec();
    }

    @Override
    public List<StCliprov> getProveedorXServicio(Long cliConsec, Long tipServicio) {
        Query query = em.createQuery("SELECT k FROM StCliprov k where k.cliConsec.cliConsec = :cliConsec and k.tipProveed.tipConsec = 2 and k.tipServic.tipConsec = :tipServicio and exists (select s from StRutprov s where k.proConsec.proConsec = s.proConsec.proConsec)").setParameter("cliConsec", cliConsec).setParameter("tipServicio", tipServicio);
        return query.getResultList();
    }

    @Override
    public List<StRutprov> getStRutasXProveedor(Long proConsec, String descripcion, Long cliConsec, Long modTransporte) {

        List<StRutprov> stRutprovs = new ArrayList<StRutprov>();

        descripcion = "%" + descripcion.trim().toLowerCase() + "%";
        Query tiempos = em.createQuery("select j from StTiempos j "
                + "where j.rutRpconsec.rutConsec.modConsec.modConsec = :modConsec "
                + "and j.cliTpcconsec.cliConsec.cliConsec = :cliConsec "
                + "and j.rutRpconsec.proConsec.proConsec = :proConsec "
                + "and (TRIM(LOWER(j.rutRpconsec.rutConsec.rutNombre)) like :rutNombre "
                + "or TRIM(LOWER(j.rutRpconsec.rutConsec.pueOrigen.pueNombre)) like :pueNombreOrigen "
                + "or TRIM(LOWER(j.rutRpconsec.rutConsec.pueDestino.pueNombre)) like :pueNombreDestino "
                + "or TRIM(LOWER(j.rutRpconsec.rutConsec.pueOrigen.stCiudad.stDpto.stPais.paiNom)) like :paiNomOrigen "
                + "or TRIM(LOWER(j.rutRpconsec.rutConsec.pueDestino.stCiudad.stDpto.stPais.paiNom)) like :paiNomDestino "
                + "or TRIM(LOWER(j.rutRpconsec.rutConsec.pueDestino.ubiConsec.ubiNombre)) like :ubiNombre)");

        tiempos.setParameter("modConsec", modTransporte);
        tiempos.setParameter("cliConsec", cliConsec);
        tiempos.setParameter("proConsec", proConsec);
        tiempos.setParameter("rutNombre", descripcion);
        tiempos.setParameter("pueNombreOrigen", descripcion);
        tiempos.setParameter("pueNombreDestino", descripcion);
        tiempos.setParameter("paiNomOrigen", descripcion);
        tiempos.setParameter("paiNomDestino", descripcion);
        tiempos.setParameter("ubiNombre", descripcion);

        Query rutasxproveed = em.createQuery("SELECT k FROM StRutprov k "
                + "where k.proConsec.proConsec = :proConsec "
                + "and k.rutConsec.modConsec.modConsec = :modConsec "
                + "and not exists  "
                + "(select j from StTiempos j "
                + " where j.rutRpconsec.rutConsec.modConsec.modConsec = k.rutConsec.modConsec.modConsec "
                + " and j.cliTpcconsec.cliConsec.cliConsec = :cliConsec "
                + " and j.rutRpconsec.proConsec.proConsec = k.proConsec.proConsec "
                + " and j.rutRpconsec.rutConsec.rutConsec = k.rutConsec.rutConsec"
                + " and j.rutRpconsec.rutRpconsec = k.rutRpconsec) "
                + "and (TRIM(LOWER(k.rutConsec.rutNombre)) like :rutNombre "
                + "or TRIM(LOWER(k.rutConsec.pueOrigen.pueNombre)) like :pueNombreOrigen "
                + "or TRIM(LOWER(k.rutConsec.pueDestino.pueNombre)) like :pueNombreDestino "
                + "or TRIM(LOWER(k.rutConsec.pueOrigen.stCiudad.stDpto.stPais.paiNom)) like :paiNomOrigen "
                + "or TRIM(LOWER(k.rutConsec.pueDestino.stCiudad.stDpto.stPais.paiNom)) like :paiNomDestino "
                + "or TRIM(LOWER(k.rutConsec.pueDestino.ubiConsec.ubiNombre)) like :ubiNombre) "
        );

        rutasxproveed.setParameter("proConsec", proConsec);
        rutasxproveed.setParameter("modConsec", modTransporte);
        rutasxproveed.setParameter("rutNombre", descripcion);
        rutasxproveed.setParameter("pueNombreOrigen", descripcion);
        rutasxproveed.setParameter("pueNombreDestino", descripcion);
        rutasxproveed.setParameter("paiNomOrigen", descripcion);
        rutasxproveed.setParameter("paiNomDestino", descripcion);
        rutasxproveed.setParameter("ubiNombre", descripcion);
        rutasxproveed.setParameter("cliConsec", cliConsec);

        for (int i = 0; i < tiempos.getResultList().size(); i++) {
            StTiempos stTiempos = (StTiempos) tiempos.getResultList().get(i);
            String id = "" + stTiempos.getRutRpconsec().getProConsec().getProConsec() + stTiempos.getRutRpconsec().getRutConsec().getRutConsec() + stTiempos.getTieTransporte() + stTiempos.getTipConsec().getTipConsec();
            StRutprov stRutprov = new StRutprov(Long.parseLong(id));

            stRutprov.setProConsec(stTiempos.getRutRpconsec().getProConsec());
            stRutprov.setRutConsec(stTiempos.getRutRpconsec().getRutConsec());
            stRutprov.setTieTransporte(stTiempos.getTieTransporte());
            stRutprov.setIndTiempos(true);
            stRutprov.setTipDia(stTiempos.getTipConsec().getTipConsec());
            stRutprov.setTipDiaNombre(stTiempos.getTipConsec().getTipConsec() == 1 ? "Días Calendario" : "Días Habiles");
            stRutprov.setIdPrincipal(stTiempos.getRutRpconsec().getRutRpconsec());
            stRutprovs.add(stRutprov);
        }
//        System.err.println("size= " + stRutprovs.size());

        for (int i = 0; i < rutasxproveed.getResultList().size(); i++) {
            ((StRutprov) rutasxproveed.getResultList().get(i)).setIdPrincipal(((StRutprov) rutasxproveed.getResultList().get(i)).getRutRpconsec());
            stRutprovs.add((StRutprov) rutasxproveed.getResultList().get(i));
        }

        return stRutprovs;
    }

    @Override
    public List<StRutprov> getStRutasXProveedorDestino(Long proConsec, String descripcion, Long cliConsec) {

        List<StRutprov> stRutprovs = new ArrayList<StRutprov>();

        descripcion = "%" + descripcion.trim().toLowerCase() + "%";
        Query tiempos = em.createQuery("select j from StTiempos j "
                + "where j.cliTpcconsec.cliConsec.cliConsec = :cliConsec "
                + "and j.rutRpconsec.proConsec.proConsec = :proConsec "
                + "and (TRIM(LOWER(j.rutRpconsec.rutConsec.rutNombre)) like :rutNombre "
                + "or TRIM(LOWER(j.rutRpconsec.rutConsec.pueOrigen.pueNombre)) like :pueNombreOrigen "
                + "or TRIM(LOWER(j.rutRpconsec.rutConsec.pueDestino.pueNombre)) like :pueNombreDestino "
                + "or TRIM(LOWER(j.rutRpconsec.rutConsec.pueOrigen.stCiudad.stDpto.stPais.paiNom)) like :paiNomOrigen "
                + "or TRIM(LOWER(j.rutRpconsec.rutConsec.pueDestino.stCiudad.stDpto.stPais.paiNom)) like :paiNomDestino "
                + "or TRIM(LOWER(j.rutRpconsec.rutConsec.pueDestino.ubiConsec.ubiNombre)) like :ubiNombre)");

        tiempos.setParameter("cliConsec", cliConsec);
        tiempos.setParameter("proConsec", proConsec);
        tiempos.setParameter("rutNombre", descripcion);
        tiempos.setParameter("pueNombreOrigen", descripcion);
        tiempos.setParameter("pueNombreDestino", descripcion);
        tiempos.setParameter("paiNomOrigen", descripcion);
        tiempos.setParameter("paiNomDestino", descripcion);
        tiempos.setParameter("ubiNombre", descripcion);

        Query rutasxproveed = em.createQuery("SELECT k FROM StRutprov k "
                + "where k.proConsec.proConsec = :proConsec "
                + "and not exists  "
                + "(select j from StTiempos j "
                + " where j.cliTpcconsec.cliConsec.cliConsec = :cliConsec "
                + " and j.rutRpconsec.proConsec.proConsec = k.proConsec.proConsec "
                + " and j.rutRpconsec.rutConsec.rutConsec = k.rutConsec.rutConsec"
                + " and j.rutRpconsec.rutRpconsec = k.rutRpconsec) "
                + "and (TRIM(LOWER(k.rutConsec.rutNombre)) like :rutNombre "
                + "or TRIM(LOWER(k.rutConsec.pueOrigen.pueNombre)) like :pueNombreOrigen "
                + "or TRIM(LOWER(k.rutConsec.pueDestino.pueNombre)) like :pueNombreDestino "
                + "or TRIM(LOWER(k.rutConsec.pueOrigen.stCiudad.stDpto.stPais.paiNom)) like :paiNomOrigen "
                + "or TRIM(LOWER(k.rutConsec.pueDestino.stCiudad.stDpto.stPais.paiNom)) like :paiNomDestino "
                + "or TRIM(LOWER(k.rutConsec.pueDestino.ubiConsec.ubiNombre)) like :ubiNombre) "
        );

        rutasxproveed.setParameter("proConsec", proConsec);
        rutasxproveed.setParameter("rutNombre", descripcion);
        rutasxproveed.setParameter("pueNombreOrigen", descripcion);
        rutasxproveed.setParameter("pueNombreDestino", descripcion);
        rutasxproveed.setParameter("paiNomOrigen", descripcion);
        rutasxproveed.setParameter("paiNomDestino", descripcion);
        rutasxproveed.setParameter("ubiNombre", descripcion);
        rutasxproveed.setParameter("cliConsec", cliConsec);

        for (int i = 0; i < tiempos.getResultList().size(); i++) {
            StTiempos stTiempos = (StTiempos) tiempos.getResultList().get(i);
            String id = "" + stTiempos.getRutRpconsec().getProConsec().getProConsec() + stTiempos.getRutRpconsec().getRutConsec().getRutConsec() + stTiempos.getTieTransporte() + stTiempos.getTipConsec().getTipConsec();
            StRutprov stRutprov = new StRutprov(Long.parseLong(id));

            stRutprov.setProConsec(stTiempos.getRutRpconsec().getProConsec());
            stRutprov.setRutConsec(stTiempos.getRutRpconsec().getRutConsec());
            stRutprov.setTieTransporte(stTiempos.getTieTransporte());
            stRutprov.setIndTiempos(true);
            stRutprov.setTipDia(stTiempos.getTipConsec().getTipConsec());
            stRutprov.setTipDiaNombre(stTiempos.getTipConsec().getTipConsec() == 1 ? "Días Calendario" : "Días Habiles");
            stRutprov.setIdPrincipal(stTiempos.getRutRpconsec().getRutRpconsec());
            stRutprovs.add(stRutprov);
        }
//        System.err.println("size= " + stRutprovs.size());

        for (int i = 0; i < rutasxproveed.getResultList().size(); i++) {
            ((StRutprov) rutasxproveed.getResultList().get(i)).setIdPrincipal(((StRutprov) rutasxproveed.getResultList().get(i)).getRutRpconsec());
            stRutprovs.add((StRutprov) rutasxproveed.getResultList().get(i));
        }

        return stRutprovs;
    }

    @Override
    public void createStDetcarg(StDetcarg stDetcarg
    ) {
        getEntityManager().persist(stDetcarg);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void editStDetcarg(StDetcarg stDetcarg
    ) {
        getEntityManager().merge(stDetcarg);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void removeStDetcarg(StDetcarg stDetcarg
    ) {
        getEntityManager().remove(getEntityManager().merge(stDetcarg));
        getEntityManager().flush();
        getEntityManager().clear();
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Long getNumDiasFestivo(Date fecInicio, Date fecFin) {
        Query query = em.createQuery("SELECT s FROM StFestivo s where s.fesFecha between :fecInicio and :fecFin");
        query.setParameter("fecInicio", fecInicio, TemporalType.DATE);
        query.setParameter("fecFin", fecFin, TemporalType.DATE);
        if (query.getResultList().isEmpty()) {
            return 0L;
        } else {
            return (long) query.getResultList().size();
        }
    }

    @Override
    public StDetcarg findStDetCarga(Object id) {
        return getEntityManager().find(StDetcarg.class, id);
    }

    @Override
    public boolean verificaCambiosLog(StDetcarg stDetcarg) {
        Date detFecsalestnulo = (stDetcarg.getDetFecsalest() != null ? new Date(stDetcarg.getDetFecsalest().getTime() + new Date().getTime()) : new Date());
        Date detFecrsalrealnulo = (stDetcarg.getDetFecrsalreal() != null ? new Date(stDetcarg.getDetFecrsalreal().getTime() + new Date().getTime()) : new Date());
        Date detFecllegestnulo = (stDetcarg.getDetFecllegest() != null ? new Date(stDetcarg.getDetFecllegest().getTime() + new Date().getTime()) : new Date());
        Date detFecllegrealnulo = (stDetcarg.getDetFecllegreal() != null ? new Date(stDetcarg.getDetFecllegreal().getTime() + new Date().getTime()) : new Date());
        Long tieTransporte = (stDetcarg.getTie_transporte() == null ? -1L : stDetcarg.getTie_transporte());
        Long tipConsec = 0L;
        if (stDetcarg.getTipDia() != null && stDetcarg.getTipDia().getTipConsec() != null) {
            tipConsec = stDetcarg.getTipDia().getTipConsec();
        }

        Query query = em.createQuery("SELECT k FROM StDetcarg k where k.detConsec = :detConsec and (COALESCE(k.detFecsalest,:detFecsalestnulo) != :detFecsalest or k.tie_transporte != :tie_transporte or COALESCE(k.detFecrsalreal,:detFecrsalrealnulo) != :detFecrsalreal or COALESCE(k.detFecllegest, :detFecllegestnulo) != :detFecllegest or COALESCE(k.detFecllegreal, :detFecllegrealnulo) != :detFecllegreal or k.tipDia.tipConsec != :tipConsec)");
        query.setParameter("detConsec", stDetcarg.getDetConsec());
        query.setParameter("detFecsalest", stDetcarg.getDetFecsalest());
        query.setParameter("detFecsalestnulo", detFecsalestnulo);
        query.setParameter("tie_transporte", tieTransporte);
        query.setParameter("detFecrsalrealnulo", detFecrsalrealnulo);
        query.setParameter("detFecrsalreal", stDetcarg.getDetFecrsalreal());
        query.setParameter("detFecllegestnulo", detFecllegestnulo);
        query.setParameter("detFecllegest", stDetcarg.getDetFecllegest());
        query.setParameter("detFecllegrealnulo", detFecllegrealnulo);
        query.setParameter("detFecllegreal", stDetcarg.getDetFecllegreal());
        query.setParameter("tipConsec", tipConsec);

        List<StDetcarg> detcargs = query.getResultList();
        System.err.println("Empty= " + detcargs.isEmpty());
        return !detcargs.isEmpty();
    }

    @Override
    public List<StLogdetcarg> getStLogdetcarg(Long detConsec) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Query query = em.createQuery("select c from StLogdetcarg c where c.detConsec.detConsec = :detConsec ORDER BY c.logTraza DESC").setParameter("detConsec", detConsec);
        List<StLogdetcarg> l = query.getResultList();

        for (StLogdetcarg logdetcarg : l) {
            logdetcarg.setLogTrazaFormato(dateFormat.format(logdetcarg.getLogTraza()));
        }
        return query.getResultList();
    }

    @Override
    public List<StAduana> findAllAduanas(StCarga carga) {
        Query query = em.createNamedQuery("StAduana.findAduBycarConsec", StAduana.class).setParameter("carConsec", carga);
        return query.getResultList();
    }

    @Override
    public void create(StAduana stAduana) {
        getEntityManager().persist(stAduana);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void updateAduana(StAduana stAduana) {
        getEntityManager().merge(stAduana);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void updateCarga(StCarga stCarga) {
        getEntityManager().merge(stCarga);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void remove(StAduana entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public List<StTipserv> findAllStServicio() {
        Query query = em.createQuery("SELECT s FROM StTipserv s where s.tipConsec != 5", StTipserv.class);
        return query.getResultList();
    }

    @Override
    public List<StTransbo> getStTransbo(Long detConsec) {
        Query query = em.createQuery("select c from StTransbo c where c.detConsec.detConsec = :detConsec").setParameter("detConsec", detConsec);
        return query.getResultList();
    }

    @Override
    public List<StPuerto> findAllStPuerto() {
        Query query = em.createQuery("SELECT s FROM StPuerto s ");
        return query.getResultList();
    }

    @Override
    public void createStTransbo(StTransbo stTransbo) {
        getEntityManager().persist(stTransbo);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public void removeStTransbo(StTransbo stTransbo) {
        StTransbo stTransboRemoved = getEntityManager().merge(stTransbo);
        getEntityManager().remove(stTransboRemoved);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    @Override
    public Date findByMaxFechaLevante(StCarga stCarga) {
        Query query = em.createNamedQuery("StAduana.findByMaxFechaLevante", StAduana.class).setParameter("carConsec", stCarga);
        return (Date) query.getSingleResult();
    }
}
