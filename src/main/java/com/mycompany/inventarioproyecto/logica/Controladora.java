//todos los metodos que se van a utilizar, llamarlos y comunicarse con la persistencia

package com.mycompany.inventarioproyecto.logica;

import com.mycompany.inventarioproyecto.persistencia.ControladoraPersistencia;
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
    
    public Producto buscarProducto(String codigo){

        return controlPersis.buscarProducto(codigo);
    }
    
    ///ELIMINAR
    public void eliminarProducto(String codigo){

        controlPersis.eliminarProducto(codigo);
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
}