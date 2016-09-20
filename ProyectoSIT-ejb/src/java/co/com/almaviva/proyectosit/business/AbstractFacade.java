/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.business;

import co.com.almaviva.proyectosit.entity.StCliprov;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author egonzalm
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().clear();
    }
    
    

    public void edit(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
        getEntityManager().clear();
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
        getEntityManager().clear();
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }


    public List consultarNativeQuery(Class entidad, String nativeQuery, List params) throws Exception {
        EntityManager em = getEntityManager();
        Query query;
        List retorno;

        try {
            if (entidad != null) {
                query = em.createNativeQuery(nativeQuery);
            } else {
                query = em.createNativeQuery(nativeQuery, entidad);
            }

            int i = 1;
            if (params != null && !params.isEmpty()) {
                for (Object campo : params) {

                    if (campo.getClass().getName().equals(Date.class.getName())) {
                        query.setParameter(i, (Date) campo, TemporalType.DATE);
                    } else if (campo.getClass().getName().equals(Timestamp.class.getName())) {
                        query.setParameter(i, (Timestamp) campo, TemporalType.TIMESTAMP);
                    } else {
                        query.setParameter(i, campo);
                    }
                    i++;
                }
            }

            retorno = query.getResultList();
        } catch (Exception e) {
            throw e;
        } 
        return retorno;
    }
}
