/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.junit.services;

import bootcamp.junit.bbdd.BaseDatosServiceI;
import bootcamp.junit.bbdd.BaseDatosServiceImpl;
import bootcamp.junit.model.Articulo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manuel
 */
@Service
public class CarritoCompraServiceImpl implements CarritoCompraServiceI {

    @Autowired
    private BaseDatosServiceImpl baseDatosService;

    List<Articulo> listaArticulos = new ArrayList();

    @Override
    public void limpiarCesta() {
        listaArticulos.clear();
    }

    @Override
    public void addArticulo(Articulo a) {
        listaArticulos.add(a);
    }

    @Override
    public int getNumArticulo() {
        return listaArticulos.size();
    }

    @Override
    public List<Articulo> getArticulos() {
        return listaArticulos;
    }

    @Override
    public Double totalPrice() {
        Double total = 0D;
        for (int i = 0; i < listaArticulos.size(); i++) {
            total += listaArticulos.get(i).getPrecio();
        }

        return total;
    }

    @Override
    public Double calculadorDescuento(double precio, double porcentajeDescuento) {
        return precio - (precio * porcentajeDescuento / 100);
    }

    @Override
    public Double aplicarDescuento(Integer idArticulo, Double porcentaje) {
        Double resultado = null;
        Articulo articulo = baseDatosService.findArticuloById(idArticulo);

        if (articulo != null) {
            resultado = calculadorDescuento(articulo.getPrecio(), porcentaje);

        } else {
            System.out.println("No se ha encontrado ningún artículo con ID: " + idArticulo);
        }

        return resultado;
    }

    @Override
    public Integer insertarArticuloId(Articulo articulo) {
        listaArticulos.add(articulo);
        return baseDatosService.insertarArticulo(articulo);
    }

}
