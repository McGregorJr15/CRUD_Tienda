package com.upiiz.tienda.services;

import com.upiiz.tienda.dto.CategoriaDto;
import com.upiiz.tienda.models.Categoria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CategoriaService {
    // Precargamos los datos incluyendo la descripción de tu imagen
    private final List<Categoria> categorias = new ArrayList<>(Arrays.asList(
            new Categoria(1L, "Bebidas", "Aquí se agrupan todas las bebidas"),
            new Categoria(2L, "Deportes", "Artículos y accesorios deportivos"),
            new Categoria(3L, "Cultura", "Libros, arte y entretenimiento cultural"),
            new Categoria(4L, "Videojuegos", "Consolas, juegos físicos y digitales")
    ));
    private final AtomicLong counter = new AtomicLong(5);

    public List<Categoria> obtenerTodas() { return categorias; }

    public Categoria obtenerPorId(Long id) {
        return categorias.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public void guardar(CategoriaDto dto) {
        categorias.add(new Categoria(counter.getAndIncrement(), dto.getNombre(), dto.getDescripcion()));
    }

    public void actualizar(Long id, CategoriaDto dto) {
        for (Categoria c : categorias) {
            if (c.getId().equals(id)) {
                c.setNombre(dto.getNombre());
                c.setDescripcion(dto.getDescripcion()); // Actualizamos descripción
                return;
            }
        }
    }

    public void eliminar(Long id) {
        categorias.removeIf(c -> c.getId().equals(id));
    }
}