package com.mycompany.inventarioproyecto.logica;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "electrodomesticos_grandes")
public class ElectrodomesticoGrande extends Producto implements Serializable {

    // Consumo eléctrico en kilowatts
    private double consumoKW;
    // Indica si necesita instalación especializada
    private boolean requiereInstalacion;
    
    
    public ElectrodomesticoGrande() {
        
    }

    // Constructor
    public ElectrodomesticoGrande(String codigo,
            String nombre,
            double precioBase,
            int stock,
            String marca,
            String modelo,
            double consumoKW,
            boolean requiereInstalacion) {

        super(codigo, nombre, precioBase, stock, marca, modelo);

        this.consumoKW = consumoKW;
        this.requiereInstalacion = requiereInstalacion;
    }
    
    

    // Getter consumoKW
    public double getConsumoKW() {
        return consumoKW;
    }

    // Setter consumoKW
    public void setConsumoKW(double consumoKW) {
        this.consumoKW = consumoKW;
    }

    // Getter requiereInstalacion
    public boolean isRequiereInstalacion() {
        return requiereInstalacion;
    }

    // Setter requiereInstalacion
    public void setRequiereInstalacion(boolean requiereInstalacion) {
        this.requiereInstalacion = requiereInstalacion;
    }
//polimorfismo  
    @Override
    public double calcularPrecioFinal() {
        return requiereInstalacion ? this.precioBase + 25.00 : this.precioBase;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("   -> [Instalacion: " + (requiereInstalacion ? "Si (+$25)" : "No") + 
                           " | Consumo: " + consumoKW + " kW]");
    
    }
}
