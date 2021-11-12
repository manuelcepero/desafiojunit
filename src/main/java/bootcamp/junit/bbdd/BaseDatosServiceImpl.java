/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.junit.bbdd;

import bootcamp.junit.model.Articulo;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manuel
 */
@Service
public class BaseDatosServiceImpl implements BaseDatosServiceI{

    Map<Integer, Articulo> storage;
    
    @Override
    public void initDB() {
        storage = new HashMap<>();
        storage.put(1, new Articulo("Camiseta", 18.95));
        storage.put(2, new Articulo("Jersey", 21.95));
        storage.put(3, new Articulo("Cinturón", 30.95));
        storage.put(4, new Articulo("Sudadera", 15.95));
        storage.put(5, new Articulo("Pantalón", 25.95));
        storage.put(6, new Articulo("Zapato", 44.95));
        storage.put(7, new Articulo("Chaqueta", 65.95));
        storage.put(8, new Articulo("Chandal", 70.95));
    }

    @Override
    public Articulo findArticuloById(Integer identificador) {
        System.out.println("Buscando el artículo con ID: " + identificador);
        return storage.get(identificador);
    }

    @Override
    public Integer insertarArticulo(Articulo articulo) {
        System.out.println("Insertando un artículo con nombre: " + articulo.getNombre());
        storage.put(storage.size()+1, articulo);
        return storage.size();
    }

    public Integer lastIndex() {
        return storage.size();
    }
     
}
