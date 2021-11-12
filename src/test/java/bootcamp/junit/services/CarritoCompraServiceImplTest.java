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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.internal.verification.AtLeast;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Manuel
 */
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class CarritoCompraServiceImplTest {

    @InjectMocks
    CarritoCompraServiceImpl carritoCompraService;

    @Mock
    BaseDatosServiceImpl bbddService;

    public CarritoCompraServiceImplTest() {
        carritoCompraService = new CarritoCompraServiceImpl();
    }

    @BeforeEach
    public void setUp() {
        carritoCompraService.addArticulo(new Articulo("Camisa", 20.0));
    }

    @AfterEach
    public void tearDown() {
        carritoCompraService.getArticulos().clear();
    }

    /**
     * Test of addArticulo method, of class CarritoCompraServiceImpl.
     */
    @Test
    @Order(1)
    public void testAddArticulo() {
        System.out.println("Ejecutando addArticulo");     
        assertTrue(!carritoCompraService.getArticulos().isEmpty());
    }

    /**
     * Test of totalPrice method, of class CarritoCompraServiceImpl.
     */
    @Test
    @Order(2)
    public void testTotalPrice() {
        System.out.println("Ejecutando totalPrice");
        assertTrue(carritoCompraService.totalPrice() > 0);
    }

    /**
     * Test of calculadorDescuento method, of class CarritoCompraServiceImpl.
     */
    @Test
    @Order(3)
    public void testCalculadorDescuento() {
        System.out.println("Ejecutando calcularDescuento");
        Articulo articulo = carritoCompraService.getArticulos().get(0);
        assertNotEquals(articulo.getPrecio(), carritoCompraService.calculadorDescuento(articulo.getPrecio(), 10));
    }

    /**
     * Test of limpiarCesta method, of class CarritoCompraServiceImpl.
     */
    @Test
    @Order(4)
    public void testLimpiarCesta() {
        System.out.println("Ejecutando limpiarCesta");

        carritoCompraService.limpiarCesta();
        assertEquals(0, carritoCompraService.getArticulos().size());
    }

    /**
     * Test of getNumArticulo method, of class CarritoCompraServiceImpl.
     */
    @Test
    @Order(5)
    public void testGetNumArticulo() {
        System.out.println("Ejecutando getNumArticulo");
        assertEquals(1, carritoCompraService.getNumArticulo());
    }

    /**
     * Test of getArticulos method, of class CarritoCompraServiceImpl.
     */
    @Test
    @Order(6)
    public void testGetArticulos() {
        System.out.println("Ejecutando getArticulos");
        
        assertTrue(!carritoCompraService.getArticulos().isEmpty());
    }

    /**
     * Test of aplicarDescuento method, of class CarritoCompraServiceImpl.
     */
    @Test
    @Order(7)
    public void testAplicarDescuento() {
        System.out.println("Ejecutando aplicarDescuento");
        Articulo articulo = new Articulo("Pantalón", 10.0);
        //when(bbddService.findArticuloById(1)).thenReturn(articulo);
        when(bbddService.findArticuloById(any(Integer.class))).thenReturn(articulo);
        Double res = carritoCompraService.aplicarDescuento(1, 12D);
        assertEquals(8.8, res);
        
        verify(bbddService, times(1)).findArticuloById(1);
    }
    
    @Test
    @Order(8)
    public void testAddArticuloById(){
        Articulo articulo = new Articulo("Bufanda", 8.90);
        Integer lastId = bbddService.lastIndex()+1;
        
        when(carritoCompraService.insertarArticuloId(articulo)).thenReturn(lastId);
        Integer id = carritoCompraService.insertarArticuloId(articulo);
        assertEquals(id,lastId);
        
        assertTrue(carritoCompraService.getArticulos().contains(articulo));
        verify(bbddService, atLeast(1)).insertarArticulo(articulo);
    }

    @Test
    public void testVacio() {
    }

}
