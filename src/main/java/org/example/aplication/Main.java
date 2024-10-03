package org.example.aplication;


import org.example.aplication.entity.Producto;
import org.example.service.ProductoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ProductoService productoService = new ProductoService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nProductos");
            System.out.println("1. crea el producto");
            System.out.println("2. Leer productos");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar productos");
            System.out.println("5. Lista de los productos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearProducto();
                case 2 -> leerProducto();
                case 3 -> actualizarProducto();
                case 4 -> eliminarProducto();
                case 5 -> listarProductos();
                case 6 -> salir = true;
                default -> System.out.println("no valido");
            }
        }
        productoService.cerrar();
        scanner.close();
    }

    private static void crearProducto() {
        System.out.print("Nombre producto ");
        String nombre = scanner.nextLine();
        System.out.print("Precio producto ");
        BigDecimal precio = scanner.nextBigDecimal();

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);

        productoService.crearProducto(producto);
        System.out.println("Producto creado ");
    }

    private static void leerProducto() {
        System.out.print("ID producto ");
        Long id = scanner.nextLong();
        Producto producto = productoService.obtenerProducto(id);
        if (producto != null) {
            System.out.println(producto);
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    private static void actualizarProducto() {
        System.out.print("ID del producto  ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir el salto de línea

        Producto producto = productoService.obtenerProducto(id);
        if (producto != null) {
            System.out.print("Nuevo nombre  ");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                producto.setNombre(nombre);
            }

            System.out.print("Nuevo precio ");
            BigDecimal precio = scanner.nextBigDecimal();
            if (precio.compareTo(BigDecimal.ZERO) != 0) {
                producto.setPrecio(precio);
            }

            productoService.actualizarProducto(producto);
            System.out.println("Producto actualizado ");
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    private static void eliminarProducto() {
        System.out.print("ID del producto  ");
        Long id = scanner.nextLong();
        productoService.eliminarProducto(id);
        System.out.println("Producto eliminado");
    }

    private static void listarProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos ");
        } else {
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }
}