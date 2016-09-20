/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.almaviva.proyectosit.business;

import co.com.almaviva.proyectosit.entity.StUsuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author egonzalm
 */


@Local
public interface StUsuarioServiceBeanLocal {

    public boolean isValidUsuario(String usuRedId);

    public StUsuario getUsuario(String usuRedId);

    public void create(StUsuario entity);

    public void edit(StUsuario entity);

    public void remove(StUsuario entity);

    public StUsuario find(Object id);

    public List<StUsuario> findAll();

    public List<StUsuario> findRange(int[] range);

    public int count();

    public List<StUsuario> consultaSubordinadoXOficina(Long ofiId, Long usuId, Long perId);

    public List<StUsuario> consultaUsuariosXOficina(Long ofiId, Long perId);

    public List<StUsuario> consultaNombreUsuario(String nombreUsuario);

    public List<StUsuario> consultaNombreUsuarioRed(String nombreUsuario);

    public StUsuario buscarUsuario(String id);

    public List<StUsuario> consultaSubordinadoXUsu(Long usuId);

    public StUsuario consultaUsuarioPorUsuId(Long usuId);
    
    public List<StUsuario> consultaUsuarioPorNombUsuaRedPerf(String descripcion,Long terNit);

}
