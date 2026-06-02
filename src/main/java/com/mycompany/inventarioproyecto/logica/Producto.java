package com.mycompany.inventarioproyecto.logica;

import jakarta.persistence.*;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

@Table(name="productos")
public abstract class Producto implements Serializable {

    @Id
    protected String codigo;
    
    protected String nombre;
    protected double precioBase;
    protected int stock;
    protected String marca;
    protected String modelo;
    
    public Producto() {
    }

   
    public Producto(String codigo, String nombre, double precioBase, int stock, String marca, String modelo) {
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;
        this.marca = marca;
        this.modelo = modelo;

    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public int getStock() {
        return stock;
    }

    public String getMarca() {
        return marca;
    }
       public String getModelo() {
        return modelo;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
        
    public abstract double calcularPrecioFinal();
    
    public boolean hayDisponibilidad(int cantidad) { 
        
        return this.stock >= cantidad; 
    }
    
    
    public void mostrarInfo() {
        System.out.printf(
            "[%s] %s - Marca: %s || Modelo: %s || Precio: $%.2f || Stock: %d%n",
            codigo,
            nombre,
            marca,
            modelo,
            precioBase,
            stock
        );
    }
    
    //Control de Stock
    public boolean stockBajo() {
        return stock <= 10;
    }
    
    public void disminuirStock(int cantidad) {
        this.stock -= cantidad;
        
    }
    public void aumentarStock(int cantidad) {
        this.stock += cantidad;
    }
    
    public void mostrarStockMarca() {

        System.out.printf(
            "[%s] %s (%s) - Precio Base: $%.2f | Stock: %d unidades\n",
            codigo, nombre, marca, precioBase, stock
        );

        if(stockBajo()) {
            System.out.println(" STOCK BAJO!!");
        }
    
    }
}


