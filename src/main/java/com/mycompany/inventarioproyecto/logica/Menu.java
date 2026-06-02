package com.mycompany.inventarioproyecto.logica;

import com.mycompany.inventarioproyecto.logica.Controladora;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);
    
    private Controladora control = new Controladora();

    public void iniciar() {

        int opcion;

        do {

            System.out.println("\n<<<<<<<<<<<<  SISTEMA INVENTARIO  >>>>>>>>>>>>");
            System.out.println("1. Registrar Electrodomestico Pequeño");
            System.out.println("2. Registrar Electrodomestico Grande");
            System.out.println("3. Buscar Producto");
            System.out.println("4. Listar Productos");
            System.out.println("5. Modificar Producto");
            System.out.println("6. Eliminar Producto");
            System.out.println("7. Otras Consultas");
            System.out.println("8. Gestion de Ventas");

            System.out.println("9. Salir");
            
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
                    menuOtrasConsultas();
                    break;
                    
                case 8:
                    menuGestionVentas();
                    break;
                    
                case 9:
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 9);
    }
    ///OPCION 7 OTRAS CONSULTAS
    private void menuOtrasConsultas(){

    int opcion;

        do{

            System.out.println(" \n<<<<<<<<<<<<  OTRAS CONSULTAS  >>>>>>>>>>>>");

            System.out.println("1. Buscar por Marca");
            System.out.println("2. Reporte de Inventario");
            System.out.println("3. Ordenar por Nombre");
            System.out.println("4. Ordenar por Precio");
            System.out.println("5. Ordenar por Stock");
            System.out.println("6. Volver al Menu Principal");

            System.out.print("\nSeleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){

                case 1:
                    buscarPorMarca();
                    break;

                case 2:
                    mostrarValorInventario();
                    break;

                case 3:
                    ordenarPorNombre();
                    break;

                case 4:
                    ordenarPorPrecio();
                    break;

                case 5:
                    ordenarPorStock();
                    break;

                case 6:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        }while(opcion != 6);
    }
    
    private void menuGestionVentas(){

        int opcion;

        do{

            System.out.println(" \n<<<<<<<<<<<<  GESTION DE VENTAS  >>>>>>>>>>>>");

            System.out.println("1. Registrar Venta");
            System.out.println("2. Historial de movimiento");
            System.out.println("3. Reposicion de Stock");
            System.out.println("4. Facturacion ");
            System.out.println("5. Volver al Menu Principal");

            System.out.print("\nSeleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){

                case 1:
                    registrarVenta();
                    break;

                case 2:
                    historialMovimientos();
                    break;

                case 3:
                    reponerStock();
                    break;

                case 4:
                    ///Facturacion();
                    break;

                case 5:
                    System.out.println("Regresando al menu principal...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        }while(opcion != 5);
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

        System.out.println("\n=================== ELECTRODOMESTICOS PEQUEÑOS ===================");

        for (ElectrodomesticoPequeno electro : control.listarElectrodomesticosPequenos()) {
            System.out.println("------------------------------------------------------------------");
            electro.mostrarInfo();
            //System.out.println("------------------------------------------------------");
        }
        System.out.println("==================================================================");

        System.out.println("\n=================== ELECTRODOMESTICOS GRANDES ===================");

        for (ElectrodomesticoGrande electro : 
                control.listarElectrodomesticosGrandes()) {
            
            System.out.println("------------------------------------------------------------------");
            electro.mostrarInfo();
            
        }
        System.out.println("\n==================================================================");

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
    //BUSCAR POR MARCA
    private void buscarPorMarca(){

        System.out.print("Ingrese la marca: ");
        String marca = sc.nextLine();

        System.out.println("\n**************ELECTRODOMESTICOS PEQUEÑOS*******************");

        for(ElectrodomesticoPequeno e :
                control.buscarPequenosPorMarca(marca)){

            e.mostrarStockMarca();
        }

        System.out.println("\n*******************ELECTRODOMESTICOS GRANDES*******************");

        for(ElectrodomesticoGrande e :
                control.buscarGrandesPorMarca(marca)){

            e.mostrarStockMarca();
        }
    }
    
    //VALOR DEL INVENTARIO
    private void mostrarValorInventario(){
        
        double totalInventario = 0;


        double total = control.calcularValorInventario();

        System.out.println("\n*****************VALOR DEL INVENTARIO*******************");
        System.out.println("***********************************************************");
        
        System.out.printf("%-8s %-22s %-10s %-8s %-10s%n",
            "Codigo", "Producto", "Precio", "Stock", "Total");
        System.out.println("-----------------------------------------------------------");
        
         // Pequeños
        for(ElectrodomesticoPequeno e : control.listarElectrodomesticosPequenos()) {

            double totalProducto =
                    e.getPrecioBase() * e.getStock();

            totalInventario += totalProducto;

            System.out.printf("%-8s %-22s %-10.2f %-8d %-10.2f%n",
                    e.getCodigo(),
                    e.getNombre(),
                    e.getPrecioBase(),
                    e.getStock(),
                    totalProducto);
        }
        
        // Grandes
        for(ElectrodomesticoGrande e : control.listarElectrodomesticosGrandes()) {

            double totalProducto =
                    e.getPrecioBase() * e.getStock();

            totalInventario += totalProducto;

            System.out.printf("%-8s %-22s %-10.2f %-8d %-10.2f%n",
                    e.getCodigo(),
                    e.getNombre(),
                    e.getPrecioBase(),
                    e.getStock(),
                    totalProducto);
        }
        System.out.println("-----------------------------------------------------------");

        System.out.printf("VALOR TOTAL DEL INVENTARIO: $%.2f%n",totalInventario);
        
        System.out.println("\n***********************************************************");
        
    }
    
    //ORDENAR PRODUCTOS
    private void mostrarProductosOrdenados(
            List<Producto> productos){

        System.out.printf(
            "%-8s %-22s %-10s %-8s%n",
            "Codigo",
            "Producto",
            "Precio",
            "Stock");

        System.out.println(
            "------------------------------------------------");

        for(Producto p : productos){

            System.out.printf(
                "%-8s %-22s %-10.2f %-8d%n",
                p.getCodigo(),
                p.getNombre(),
                p.getPrecioBase(),
                p.getStock());
        }
    }
    //ORDENAR POR NOMBRE
    
    private void ordenarPorNombre(){

        List<Producto> productos =
                control.obtenerTodosLosProductos();

        productos.sort(
                Comparator.comparing(Producto::getNombre));

        mostrarProductosOrdenados(productos);
    }
    //ORDENAR POR MARCA
    
        private void ordenarPorPrecio(){

        List<Producto> productos =
                control.obtenerTodosLosProductos();

        productos.sort(
            Comparator.comparing(
                Producto::getPrecioBase));

        mostrarProductosOrdenados(productos);
    }
    //POR STOCK
    private void ordenarPorStock(){

        List<Producto> productos =
                control.obtenerTodosLosProductos();

        productos.sort(
            Comparator.comparing(
                Producto::getStock));

        mostrarProductosOrdenados(productos);
    }
    /// REGISTRAR VENTA
    private void registrarVenta(){

        System.out.println("\n********** REGISTRAR VENTA **********");

        System.out.print("Código del producto: ");
        String codigo = sc.nextLine();

        Producto producto = control.buscarProducto(codigo);

        if(producto == null){

            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        if(!producto.hayDisponibilidad(cantidad)){

            System.out.println("Stock insuficiente.");
            return;
        }

        producto.disminuirStock(cantidad);

        if(producto instanceof ElectrodomesticoPequeno){

            control.editarElectrodomesticoPequeno(
                    (ElectrodomesticoPequeno) producto);

        }else if(producto instanceof ElectrodomesticoGrande){

            control.editarElectrodomesticoGrande(
                    (ElectrodomesticoGrande) producto);
        }
        
        MovimientoInventario movimiento =
                new MovimientoInventario(
                        LocalDate.now().toString(),
                        "VENTA",
                        producto.getCodigo(),
                        producto.getNombre(),
                        cantidad);

        control.registrarMovimiento(movimiento);
        
       double total = producto.getPrecioBase() * cantidad;

        System.out.println("\nVenta realizada correctamente.");

        System.out.println("Producto: " + producto.getNombre());

        System.out.println("Cantidad: " + cantidad);

        System.out.println("Total: $" + total);
    }
    
    //HISTORIAL MOVIMIENTOS
    private void historialMovimientos(){

        List<MovimientoInventario> movimientos =
                control.listarMovimientos();

        if(movimientos.isEmpty()){

            System.out.println("\nNo existen movimientos registrados.");
            return;
        }

        System.out.println("**************** HISTORIAL DE MOVIMIENTOS   ****************");
        System.out.println("*************************************************************");

        System.out.printf("%-5s %-12s %-12s %-10s %-25s %-10s%n",
                "ID","Fecha","Tipo","Codigo", "Producto", "Cantidad");

        System.out.println("--------------------------------------------------------------------------");

        for(MovimientoInventario mov : movimientos){

            System.out.printf("%-5d %-12s %-12s %-10s %-25s %-10d%n",
                    mov.getId(),
                    mov.getFecha(),
                    mov.getTipoMovimiento(),
                    mov.getCodigoProducto(),
                    mov.getNombreProducto(),
                    mov.getCantidad());
        }
        
    }
    
    private void reponerStock(){

        System.out.println("\n******** REPOSICIÓN DE STOCK *********");

        System.out.print("Código del producto: ");
        String codigo = sc.nextLine();

        Producto producto =
                control.buscarProducto(codigo);

        if(producto == null){

            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("Cantidad a ingresar: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        producto.aumentarStock(cantidad);
        
        
        if(producto instanceof ElectrodomesticoPequeno){

            control.editarElectrodomesticoPequeno(
                    (ElectrodomesticoPequeno) producto);

        }else if(producto instanceof ElectrodomesticoGrande){

            control.editarElectrodomesticoGrande(
                    (ElectrodomesticoGrande) producto);
        }
        //Registrar movimiento
        MovimientoInventario movimiento =
        new MovimientoInventario(
                LocalDate.now().toString(),
                "REPOSICION",
                producto.getCodigo(),
                producto.getNombre(),
                cantidad);

        control.registrarMovimiento(movimiento);
        
         //  Mostrar resultado
        System.out.println("\nReposición realizada correctamente.");

        System.out.println("Producto: "
                + producto.getNombre());

        System.out.println("Stock actual: "
                + producto.getStock());
    }


}