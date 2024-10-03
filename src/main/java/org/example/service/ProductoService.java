package org.example.service;

import org.example.aplication.entity.Producto;
import org.example.repository.ProductoRepository;


import java.util.List;

public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService() {
        this.repository = new ProductoRepository();
    }

    public void crearProducto(Producto producto) {
        repository.crear(producto);
    }

    public Producto obtenerProducto(Long id) {
        return repository.leer(id);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return repository.leerTodos();
    }

    public void actualizarProducto(Producto producto) {
        repository.actualizar(producto);
    }

    public void eliminarProducto(Long id) {
        repository.eliminar(id);
    }

    public void cerrar() {
        repository.cerrar();
    }
}
