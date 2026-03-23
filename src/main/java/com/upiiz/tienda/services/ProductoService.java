package com.upiiz.tienda.services;

import com.upiiz.tienda.dto.ProductoDto;
import com.upiiz.tienda.models.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductoService {
    // Precargamos los datos de la imagen
    private final List<Producto> productos = new ArrayList<>(Arrays.asList(
            new Producto(1L, "Raqueta", 450.00f, "Deportes"),
            new Producto(2L, "Caminadora", 7500.00f, "Deportes"),
            new Producto(3L, "Coca-Cola", 25.00f, "Bebidas"),
            new Producto(4L, "Cien Años de Soledad", 250.00f, "Cultura")
    ));
    private final AtomicLong counter = new AtomicLong(5);

    public List<Producto> obtenerTodos() { return productos; }

    public Producto obtenerPorId(Long id) {
        return productos.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public void guardar(ProductoDto dto) {
        productos.add(new Producto(counter.getAndIncrement(), dto.getNombre(), dto.getPrecio(), dto.getCategoria()));
    }

    public void actualizar(Long id, ProductoDto dto) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                p.setNombre(dto.getNombre());
                p.setPrecio(dto.getPrecio());
                p.setCategoria(dto.getCategoria());
                return;
            }
        }
    }

    public void eliminar(Long id) {
        productos.removeIf(p -> p.getId().equals(id));
    }
}