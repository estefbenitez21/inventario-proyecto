

package com.mycompany.inventarioproyecto;

import com.mycompany.inventarioproyecto.logica.Controladora;
import com.mycompany.inventarioproyecto.logica.ElectrodomesticoGrande;
import com.mycompany.inventarioproyecto.logica.ElectrodomesticoPequeno;
import com.mycompany.inventarioproyecto.logica.Menu;
import com.mycompany.inventarioproyecto.persistencia.ControladoraPersistencia;
import java.util.List;

/**
 *
 * @author Estefany
 */
public class InventarioProyecto {
       
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.iniciar();
    }
}
