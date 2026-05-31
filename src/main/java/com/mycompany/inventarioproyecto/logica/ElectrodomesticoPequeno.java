package com.mycompany.inventarioproyecto.logica;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "electrodomesticos_pequenos")

public class ElectrodomesticoPequeno extends Producto implements Serializable {

    // Tiempo de garantía en meses
    private int garantiaMeses;

    public ElectrodomesticoPequeno() {
        
    }
      
    public ElectrodomesticoPequeno(String codigo,
            String nombre,
            double precioBase,
            int stock,
            String marca,
            String modelo,
            int garantiaMeses) {

        super(codigo, nombre, precioBase, stock, marca, modelo);

        this.garantiaMeses = garantiaMeses;
    }

    /*
     * Getter garantiaMeses
     */
    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    /*
     * Setter garantiaMeses
     */
    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }


    @Override
    public double calcularPrecioFinal() {
        return this.precioBase;
    }
    
    
    @Override
    public void mostrarInfo() {

        // Información heredada
        super.mostrarInfo();

        // Información adicional
        System.out.println("Garantia: " + garantiaMeses + " meses");
    }
}