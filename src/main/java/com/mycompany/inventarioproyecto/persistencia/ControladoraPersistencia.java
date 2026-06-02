package com.mycompany.inventarioproyecto.persistencia;

import com.mycompany.inventarioproyecto.logica.ElectrodomesticoGrande;
import com.mycompany.inventarioproyecto.logica.ElectrodomesticoPequeno;
import com.mycompany.inventarioproyecto.logica.MovimientoInventario;
import com.mycompany.inventarioproyecto.logica.Producto;
import java.util.List;

public class ControladoraPersistencia {
    
    //instancias del controlador JPA
    
    ElectrodomesticoPequenoJpaController electroPequeJpa =
            new ElectrodomesticoPequenoJpaController();
    
    ElectrodomesticoGrandeJpaController electroGrandeJpa =
            new ElectrodomesticoGrandeJpaController();
    
    MovimientoInventarioJpaController movimientoJpa =
            new MovimientoInventarioJpaController();
    
    
//------------------------------------------------------------

    
 //------CRUD ELECTRODOMÉSTICOS PEQUEÑOS TEST--------
    // CREATE
    public void crearElectrodomesticoPequeno(ElectrodomesticoPequeno electro) {
    //Captura cualquier error de persistencia
        try {
            //Guarda el objeto en la base de datos.
            electroPequeJpa.create(electro);

        } catch (Exception e) {
            System.out.println("Error al crear el electrodomestico pequeño: "
                    + e.getMessage());
        }
    }
    
    //READ
    public ElectrodomesticoPequeno buscarElectrodomesticoPequeno(String codigo) {

        return electroPequeJpa.findElectrodomesticoPequeno(codigo);
    }
    
    public List<ElectrodomesticoPequeno> listarElectrodomesticosPequenos() {

    return electroPequeJpa.findElectrodomesticoPequenoEntities();
}
    
    ///UPDATE
    
    public void editarElectrodomesticoPequeno(ElectrodomesticoPequeno electro){

        try{

            electroPequeJpa.edit(electro);

        }catch(Exception e){

            System.out.println("Error al Editar producto");
        }
    }
    ///DELETE

    public void eliminarElectrodomesticoPequeno(String codigo){

        try{

            electroPequeJpa.destroy(codigo);

        }catch(Exception e){

            System.out.println("Error al eliminar producto");
        }
    }
 //-*****METODOS PARA CRUD EN CONSOLA COMO PRODUCTO *******
    
    //READ
    public Producto buscarProducto(String codigo){

    Producto producto =
            electroPequeJpa.findElectrodomesticoPequeno(codigo);

    if(producto == null){

        producto = electroGrandeJpa.findElectrodomesticoGrande(codigo);
    }

    return producto;
}
    ///DELETE
    public void eliminarProducto(String codigo){

        try{

            Producto producto = buscarProducto(codigo);

            if(producto instanceof ElectrodomesticoPequeno){

                electroPequeJpa.destroy(codigo);

            }else if(producto instanceof ElectrodomesticoGrande){

                electroGrandeJpa.destroy(codigo);
            }

        }catch(Exception e){

            System.out.println("Error al Eliminar producto");
        }
    }
    
    ///PARA BUSCAR POR MARCA
    ///
    public List<ElectrodomesticoPequeno> buscarPequenosPorMarca(String marca){

        return electroPequeJpa.buscarPorMarca(marca);
    }
    
    
//---------------CRUD ELECTRODOMÉSTICOS GRANDES----------------
 

    // CREATE 
    public void crearElectrodomesticoGrande(ElectrodomesticoGrande electro) {
        
        //Captura cualquier error de persistencia
        try {
            electroGrandeJpa.create(electro);

        } catch (Exception e) {
            System.out.println("Error al crear el electrodoméstico grande: "
                    + e.getMessage());
        }
    }
    //READ
    public ElectrodomesticoGrande buscarElectrodomesticoGrande(String codigo) {

        return electroGrandeJpa.findElectrodomesticoGrande(codigo);
    }
    
    //LISTAR
    public List<ElectrodomesticoGrande> listarElectrodomesticosGrandes() {

    return electroGrandeJpa.findElectrodomesticoGrandeEntities();
    }
    
    ///UPDATE
       public void editarElectrodomesticoGrande(ElectrodomesticoGrande electro){

        try{

            electroGrandeJpa.edit(electro);

        }catch(Exception e){

            System.out.println("Error al Editar producto");
        }
    }
       
     ///DELETE

    public void eliminarElectrodomesticoGrande(String codigo){

        try{

            electroGrandeJpa.destroy(codigo);

        }catch(Exception e){

            System.out.println("Error al Eliminar producto");
        }
    }
    
    //PARA  BUSCAR POR MARCA
    
    public List<ElectrodomesticoGrande> buscarGrandesPorMarca(String marca){

        return electroGrandeJpa.buscarPorMarca(marca);
    }
    //MOVIMIENTOS INVENTARIO
    
    
    public void registrarMovimiento(
        MovimientoInventario movimiento){

        try{
            movimientoJpa.create(movimiento);
        }catch(Exception e){
            e.printStackTrace();
        }
  
    }
        
        //LISTAR MOVIMIENTOS
    public List<MovimientoInventario> listarMovimientos(){

        return movimientoJpa.findMovimientoInventarioEntities();
    }
} 

 


    
    
    
    
    
