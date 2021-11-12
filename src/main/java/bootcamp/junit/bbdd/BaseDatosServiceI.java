/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.junit.bbdd;

import bootcamp.junit.model.Articulo;

/**
 *
 * @author Manuel
 */
public interface BaseDatosServiceI {
    
    public void initDB();
    
    public Articulo findArticuloById(Integer identificador);
    
    public Integer insertarArticulo(Articulo articulo);
        
}
