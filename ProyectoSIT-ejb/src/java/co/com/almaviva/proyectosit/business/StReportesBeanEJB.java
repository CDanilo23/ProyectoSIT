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
import javax.ejb.Local;

/**
 *
 * @author cordonez
 */

@Local
public interface StReportesBeanEJB {

    public List<StTiempos> findAll();
    
    public List<StTipoper> findAllOperation();
    
    public List<VStCross> fullview();
    
    

}
