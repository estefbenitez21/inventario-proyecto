

package com.mycompany.Test;

import com.mycompany.inventarioproyecto.logica.Controladora;
import com.mycompany.inventarioproyecto.logica.ElectrodomesticoGrande;
import com.mycompany.inventarioproyecto.logica.ElectrodomesticoPequeno;
import com.mycompany.inventarioproyecto.persistencia.ControladoraPersistencia;
import java.util.List;

/**
 *
 * @author Estefany
 */
public class Test {
       
    public static void main(String[] args) {
       
        Controladora control = new Controladora();  
        
 ///--------------INSERTAR ELECTRODOMESTICO PEQUEÑO

        ElectrodomesticoPequeno micro = new ElectrodomesticoPequeno(
            "MIC001",
            "Microondas Samsung",
            150.00,
            10,
            "Samsung",
            "MG23",
            12);

        control.crearElectrodomesticoPequeno(micro);

        System.out.println("Producto guardado correctamente");
    

    
    ////Probar búsqueda 
    ElectrodomesticoPequeno electroPe =
        control.buscarElectrodomesticoPequeno("MIC001");

        if (electroPe != null) {

            System.out.println("------BUSCAR------------");
            electroPe.mostrarInfo();
            

        } else {

            System.out.println("No encontrado");
        }
    
     //PROBAR BUSQUEDA POR LISTADO
    
   List<ElectrodomesticoPequeno> lista =
        control.listarElectrodomesticosPequenos();

        for (ElectrodomesticoPequeno electro : lista) {

            System.out.println("-------LISTA-----------");
            electro.mostrarInfo();
            System.out.println("------------------");

        }
        
     //PROBAR UPDATE
    ElectrodomesticoPequeno electroEdi =
        control.buscarElectrodomesticoPequeno("MIC001");

    if(electroEdi != null){

        electroEdi.setNombre("Microondas LG");

        electroEdi.setPrecioBase(200);

        electroEdi.setStock(20);

        control.editarElectrodomesticoPequeno(electroEdi);

        System.out.println("Registro actualizado");

    }else{

        System.out.println("No existe el producto");
    }
    
    ///DELETE

    control.eliminarElectrodomesticoPequeno("MIC001");

    System.out.println("Producto eliminado");
    //--------------------------------

    ElectrodomesticoPequeno electroDele =
        control.buscarElectrodomesticoPequeno("MIC001");

    if(electroDele == null){

        System.out.println("No existe el producto");
    }
    
///-----------INSERTAR ELECTRODOMESTICO GRANDE
        ElectrodomesticoGrande refri = new ElectrodomesticoGrande(
            "MIC002",
            "Refrigeradora Samsung",
            120.00,
            10,
            "Samsung",
            "MG23",
            120,
            true);

        control.crearElectrodomesticoGrande(refri);

        System.out.println("Producto guardado correctamente");
    
     
    
     ////Probar búsqueda 
    ElectrodomesticoGrande electroGra =
        control.buscarElectrodomesticoGrande("MIC002");

    if (electroGra != null) {
        
        System.out.println("--------BUSCAR----------");

        electroGra.mostrarInfo();

    } else {

        System.out.println("No encontrado");
    }
    
    //PROBAR BUSQUEDA por LISTADO
    List<ElectrodomesticoGrande> lista1 =
        control.listarElectrodomesticosGrandes();

    for (ElectrodomesticoGrande electro : lista1) {
        
        System.out.println("---------LISTA---------");
        electro.mostrarInfo();
        System.out.println("------------------");

    }
    
    //UPDATE
    ElectrodomesticoGrande electroEdi2 =
        control.buscarElectrodomesticoGrande("MIC002");

        if(electroEdi2 != null){

            electroEdi2.setNombre("Lavadora LG");

            electroEdi2.setPrecioBase(300);

            electroEdi2.setStock(30);

            control.editarElectrodomesticoGrande(electroEdi2);

            System.out.println("Registro actualizado");

        }else{

            System.out.println("No existe el producto");
        }
        
        ///DELETE

        control.eliminarElectrodomesticoGrande("MIC002");

        System.out.println("Producto eliminado");
//---------------

        ElectrodomesticoPequeno electroDele2 =
        control.buscarElectrodomesticoPequeno("MIC002");

        if(electroDele2 == null){

            System.out.println("No existe el producto");
        }
    
    }
}
