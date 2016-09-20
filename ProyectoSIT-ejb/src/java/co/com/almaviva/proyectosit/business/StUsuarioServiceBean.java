/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.business;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.com.almaviva.proyectosit.entity.StUsuario;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author egonzalm
 */
@Stateless
public class StUsuarioServiceBean extends AbstractFacade<StUsuario> implements StUsuarioServiceBeanLocal {

    @PersistenceContext(unitName = "SITWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StUsuarioServiceBean() {
        super(StUsuario.class);
    }

    @Override
    public boolean isValidUsuario(String usuRedId) {
        Query query = em.createNamedQuery("StUsuario.findUniqueByUsuRedId");
        query.setParameter("usuRedId", usuRedId);
        List<StUsuario> results = query.getResultList();
        if (results.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public StUsuario getUsuario(String usuRedId) {
        Query query = em.createNamedQuery("StUsuario.findByUsuRedId");
        query.setParameter("usuRedId", usuRedId);
        return (StUsuario) query.getResultList().get(0);
    }

    @Override
    public List<StUsuario> consultaUsuariosXOficina(Long ofiId, Long perId) {
        List<StUsuario> results = new ArrayList<StUsuario>();
        Query query = em.createNamedQuery("StUsuario.findByOfiIdPerId");
        results = query.setParameter("ofiId", ofiId).setParameter("perId", perId).getResultList();
        return results;
    }

    @Override
    public List<StUsuario> consultaSubordinadoXOficina(Long ofiId, Long usuId, Long perId) {
        List<StUsuario> results = new ArrayList<StUsuario>();
        Query query = em.createNamedQuery("StUsuario.findByUsuSubIdPerId");
        query.setParameter("ofiId", ofiId).setParameter("usuId", usuId).setParameter("perId", perId);
        results = (List<StUsuario>) query.getResultList();
        return results;
    }

    @Override
    public List<StUsuario> consultaNombreUsuario(String nombreUsuario) {
        List<StUsuario> ceMaPerfilTemp = new ArrayList<StUsuario>();
        try {
            Query query = em.createNamedQuery("StUsuario.findLikeNombre");
            query.setParameter("usuNom", "%" + nombreUsuario.trim().toLowerCase() + "%");
            ceMaPerfilTemp = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ceMaPerfilTemp;

    }

    @Override
    public List<StUsuario> consultaNombreUsuarioRed(String nombreUsuario) {
        List<StUsuario> ceMaPerfilTemp = new ArrayList<StUsuario>();
        try {
            Query query = em.createNamedQuery("StUsuario.findLikeNombreUsuRedId");
            query.setParameter("usuNom", "%" + nombreUsuario.trim().toLowerCase() + "%");
            query.setParameter("usuRedId", "%" + nombreUsuario.trim().toLowerCase() + "%");
            ceMaPerfilTemp = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ceMaPerfilTemp;

    }

    public List<StUsuario> consultaSubordinadoXUsu(Long usuId) {
        List<StUsuario> ceUsuXSubUsu = new ArrayList<StUsuario>();
        try {
            Query query = em.createNamedQuery("CeUsuPerfil.findSubordinadosXUsu");
            query.setParameter("usuId", usuId);
            ceUsuXSubUsu = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ceUsuXSubUsu;
    }
       
    @Override
    public List<StUsuario> consultaUsuarioPorNombUsuaRedPerf(String descripcion,Long terNit) {
        List<StUsuario> usuarios = new ArrayList<StUsuario>();
        try {
            Query query = em.createNamedQuery("StUsuario.findLikeNombreUsuRedIdPerfilId");
            query.setParameter("usuNom", "%"+descripcion+"%");
            query.setParameter("usuRedId", "%"+descripcion+"%");
            query.setParameter("terNit", terNit);
            usuarios = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public void create(StUsuario entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(StUsuario entity) {
        super.edit(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(StUsuario entity) {
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StUsuario find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StUsuario> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StUsuario> findRange(int[] range) {
        return super.findRange(range); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        return super.count(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StUsuario buscarUsuario(String id) {
        StUsuario resp = null;
        try {

            Query q = em.createNamedQuery("StUsuario.findByUsuNomred").setParameter("usuNomred", id);
            resp = (StUsuario) q.getSingleResult();

        } catch (NoResultException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    public StUsuario consultaUsuarioPorUsuId(Long usuId) {
        StUsuario usuario = null;
        Query q = em.createNamedQuery("StUsuario.findByUsuConsec").setParameter("usuConsec", usuId);
        usuario = (StUsuario) q.getSingleResult();

        return usuario;
    }
}
