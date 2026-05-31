package com.mycompany.inventarioproyecto.logica;

import com.mycompany.inventarioproyecto.logica.Controladora;
import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);
    
    private Controladora control = new Controladora();

    public void iniciar() {

        int opcion;

        do {

            System.out.println("\n===== SISTEMA INVENTARIO =====");
            System.out.println("1. Registrar Electrodomestico Pequeño");
            System.out.println("2. Registrar Electrodomestico Grande");
            System.out.println("3. Buscar Producto");
            System.out.println("4. Listar Productos");
            System.out.println("5. Modificar Producto");
            System.out.println("6. Eliminar Producto");
            System.out.println("7. Salir");

            System.out.print("Seleccione una Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    registrarPequeno();
                    break;

                case 2:
                    registrarGrande();
                    break;

                case 3:
                    buscarProducto();
                    break;

                case 4:
                    listarProductos();
                    break;
                    
                case 5:
                    modificarProducto();
                    break;

                case 6:
                    eliminarProducto();
                    break;

                case 7:
                    System.out.println("Hasta pronto");
                    break;

                default:
                    System.out.println("Opcion invelida");
            }

        } while (opcion != 7);
    }
    
 //---------------CRUD PARA ELECTRODOMESTICO PEQUEÑO
    
    ///CREATE
        private void registrarPequeno() {
            
        System.out.print("****Registrar nuevo producto***\n");

        System.out.print("Codigo: ");
        String codigo = sc.nextLine();
        
        Producto existente = control.buscarProducto(codigo);
        if (existente != null) {

            System.out.println("Ya existe un producto con ese código.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Precio: ");
        double precio = sc.nextDouble();

        System.out.print("Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();

        System.out.print("Marca: ");
        String marca = sc.nextLine();

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Garantía (meses): ");
        int garantia = sc.nextInt();
        sc.nextLine();

        ElectrodomesticoPequeno electro =
                new ElectrodomesticoPequeno(
                        codigo,
                        nombre,
                        precio,
                        stock,
                        marca,
                        modelo,
                        garantia);

        control.crearElectrodomesticoPequeno(electro);

        System.out.println("\nProducto registrado correctamente");
    
        }
        
        ///READ
        private void buscarProducto(){
            
        System.out.print("****Buscar***\n");

        System.out.print("Ingrese codigo: ");
        String codigo = sc.nextLine();

        Producto producto = control.buscarProducto(codigo);

        if(producto != null){

            producto.mostrarInfo();

        }else{

            System.out.println("Producto no encontrado");
        }
    }
        //LISTAR
        
    private void listarProductos() {
        System.out.print("****TODOS LOS PRODUCTOS***\n");

        System.out.println("\n===== ELECTRODOMESTICOS PEQUEÑOS =====");

        for (ElectrodomesticoPequeno electro : control.listarElectrodomesticosPequenos()) {

            electro.mostrarInfo();
            System.out.println("------------------------");
        }

        System.out.println("\n===== ELECTRODOMESTICOS GRANDES =====");

        for (ElectrodomesticoGrande electro : 
                control.listarElectrodomesticosGrandes()) {

            electro.mostrarInfo();
            System.out.println("------------------------");
        }
    }
        
        
        //UPDATE
     
    private void modificarProducto() {
        
        System.out.print("****Modificar***\n");

        System.out.print("Ingrese el codigo del producto: ");
        String codigo = sc.nextLine();

        Producto producto = control.buscarProducto(codigo);

        if(producto == null){

            System.out.println("Producto no encontrado");
            return;
        }

        System.out.println("\nProducto encontrado:");
        producto.mostrarInfo();

        System.out.println("\nIngrese los nuevos datos:");

        System.out.print("Nombre: ");
        producto.setNombre(sc.nextLine());

        System.out.print("Precio Base: ");
        producto.setPrecioBase(Double.parseDouble(sc.nextLine()));

        System.out.print("Stock: ");
        producto.setStock(Integer.parseInt(sc.nextLine()));

        System.out.print("Marca: ");
        producto.setMarca(sc.nextLine());

        System.out.print("Modelo: ");
        producto.setModelo(sc.nextLine());

        // Datos específicos
        if(producto instanceof ElectrodomesticoPequeno){

            ElectrodomesticoPequeno electro = (ElectrodomesticoPequeno) producto;

            System.out.print("Garantía (meses): ");
            electro.setGarantiaMeses( Integer.parseInt(sc.nextLine()));

            control.editarElectrodomesticoPequeno(electro);

        }

        else if(producto instanceof ElectrodomesticoGrande){

            ElectrodomesticoGrande electro = (ElectrodomesticoGrande) producto;

            System.out.print("Consumo KW: ");
            electro.setConsumoKW( Double.parseDouble(sc.nextLine()));

            System.out.print("¿Requiere instalacion? (Si/No): ");
            String respuesta = sc.nextLine();

            electro.setRequiereInstalacion(respuesta.equalsIgnoreCase("si"));

            control.editarElectrodomesticoGrande(electro);
        }

        System.out.println("Producto actualizado correctamente.");
    }
    
        //DELETE
    private void eliminarProducto(){
        
        System.out.print("****Eliminar***\n");

        System.out.print("Ingrese codigo: ");
        String codigo = sc.nextLine();

        Producto producto = control.buscarProducto(codigo);

        if(producto != null){

        control.eliminarProducto(codigo);

        System.out.println("Producto eliminado");

    }else{

        System.out.println("Producto no encontrado");
    }
    }
        
     //-----------CRUD PARA ELECTRODOMESTICOS GRANDES----------
        
    private void registrarGrande() {
        
        System.out.print("****Registrar nuevo producto***\n");

        System.out.print("Codigo: ");
        String codigo = sc.nextLine();
        
        Producto existente = control.buscarProducto(codigo);

        if (existente != null) {

            System.out.println("Ya existe un producto con ese código.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Precio: ");
        double precio = sc.nextDouble();

        System.out.print("Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();

        System.out.print("Marca: ");
        String marca = sc.nextLine();

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Consumo (KW): ");
        double consumoKW = sc.nextDouble();

        System.out.print("¿Requiere instalacion? (Si/No): ");
        String respuesta = sc.nextLine();

        boolean requiereInstalacion = respuesta.equalsIgnoreCase("si");
        sc.nextLine();

        ElectrodomesticoGrande electro =
                new ElectrodomesticoGrande(
                        codigo,
                        nombre,
                        precio,
                        stock,
                        marca,
                        modelo,
                        consumoKW,
                        requiereInstalacion);

        control.crearElectrodomesticoGrande(electro);

        System.out.println("Producto registrado correctamente");
    }
    
    
}