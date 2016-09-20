/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.almaviva.proyectosit.business;

import co.com.almaviva.proyectosit.entity.StTiempos;
import co.com.almaviva.proyectosit.entity.StTipoper;
import co.com.almaviva.proyectosit.entity.VStCross;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cordonez
 */
@Stateless(mappedName = "StReportesBean", name = "StReportesBean")
public class StReportesBean extends AbstractFacade<StTiempos> implements StReportesBeanEJB {

    @PersistenceContext(unitName = "SITWebPU")
    private EntityManager em;

    public StReportesBean() {
        super(StTiempos.class);
    }

    @Override
    public List<StTiempos> findAll() {
        Query query = em.createNamedQuery("StTiempos.findAll");
        return query.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<StTipoper> findAllOperation() {
        Query query = em.createNamedQuery("StTipoper.findByCrossDocking");
        return query.getResultList();
    }

    @Override
    public List<VStCross> fullview() {
        Query query = em.createNamedQuery("VStCross.findAll");
        return query.getResultList();
    }

}
