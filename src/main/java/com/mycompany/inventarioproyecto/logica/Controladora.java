//todos los metodos que se van a utilizar, llamarlos y comunicarse con la persistencia

package com.mycompany.inventarioproyecto.logica;

import com.mycompany.inventarioproyecto.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estefany
 */
public class Controladora {

    ControladoraPersistencia controlPersis =
            new ControladoraPersistencia();

 //-------------------------PEQUEÑO---------------------------

    // CREATE
    public void crearElectrodomesticoPequeno(ElectrodomesticoPequeno electro) {
        controlPersis.crearElectrodomesticoPequeno(electro);
    }

    //READ
    public ElectrodomesticoPequeno buscarElectrodomesticoPequeno(String codigo) {

    return controlPersis.buscarElectrodomesticoPequeno(codigo);
    }
    
    //PARA LISTAR
    public List<ElectrodomesticoPequeno> listarElectrodomesticosPequenos() {

    return controlPersis.listarElectrodomesticosPequenos();
}
    
    public void editarElectrodomesticoPequeno(ElectrodomesticoPequeno electro){

    controlPersis.editarElectrodomesticoPequeno(electro);
    }
    
    
//DELETE
    
    public void eliminarElectrodomesticoPequeno(String codigo){

        controlPersis.eliminarElectrodomesticoPequeno(codigo);
    }
   //*********************************************
    //BUSCAR COMO PRODUCTO
    public Producto buscarProducto(String codigo){

        return controlPersis.buscarProducto(codigo);
    }
    
    ///ELIMINAR COMO PRODUCTO
    public void eliminarProducto(String codigo){

        controlPersis.eliminarProducto(codigo);
    }
    //*********************************************
    
    //PARA BUSCAR POR MARCA
    public List<ElectrodomesticoPequeno> buscarPequenosPorMarca(String marca){

        return controlPersis.buscarPequenosPorMarca(marca);
    }
    
 //-------------------------GRANDE---------------------------
   
       // CREATE
    public void crearElectrodomesticoGrande (ElectrodomesticoGrande electro) {
        controlPersis.crearElectrodomesticoGrande (electro);
    }
    
    //READ
    public ElectrodomesticoGrande buscarElectrodomesticoGrande(String codigo) {

        return controlPersis.buscarElectrodomesticoGrande(codigo);
    }
    
    //LISTAR
    public List<ElectrodomesticoGrande> listarElectrodomesticosGrandes() {

    return controlPersis.listarElectrodomesticosGrandes();
    }
    
     public void editarElectrodomesticoGrande(ElectrodomesticoGrande electro){

        controlPersis.editarElectrodomesticoGrande(electro);
    }
     
    //DELETE
     public void eliminarElectrodomesticoGrande(String codigo){

        controlPersis.eliminarElectrodomesticoGrande(codigo);
    }
     
    //PARA BUSCAR COMO MARCA
     public List<ElectrodomesticoGrande> buscarGrandesPorMarca(String marca){

        return controlPersis.buscarGrandesPorMarca(marca);
    }
     
    //MOSTRAR VALOR TOTAL DEL INVENTARIO
    //Valor Producto = Precio × Stock
    public double calcularValorInventario() {

        double total = 0;

        for(ElectrodomesticoPequeno e :
                listarElectrodomesticosPequenos()) {

            total += e.getPrecioBase() * e.getStock();
        }

        for(ElectrodomesticoGrande e :
                listarElectrodomesticosGrandes()) {

            total += e.getPrecioBase() * e.getStock();
        }

        return total;
    }
    
    //LISTA DE PRODUCTOS
        public List<Producto> obtenerTodosLosProductos(){

        List<Producto> productos = new ArrayList<>();

        productos.addAll(listarElectrodomesticosPequenos());
        productos.addAll(listarElectrodomesticosGrandes());

        return productos;
    }
        
        public void registrarMovimiento(
            MovimientoInventario movimiento){

        controlPersis.registrarMovimiento(movimiento);
    }
     //LITAR MOVIMIENTOS
        public List<MovimientoInventario> listarMovimientos(){

            return controlPersis.listarMovimientos();
    }
}