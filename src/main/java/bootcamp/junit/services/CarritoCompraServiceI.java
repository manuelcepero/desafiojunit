/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.junit.services;

import bootcamp.junit.model.Articulo;
import java.util.List;

/**
 *
 * @author Manuel
 */
public interface CarritoCompraServiceI {
    void limpiarCesta();
    void addArticulo(Articulo a);
    int getNumArticulo();
    List<Articulo> getArticulos();
    Double totalPrice();
    Double calculadorDescuento(double precio, double porcentajeDescuento);
    Double aplicarDescuento(Integer idArticulo, Double porcentaje);
    Integer insertarArticuloId(Articulo articulo);
}
