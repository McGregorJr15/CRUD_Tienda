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
    // Precargamos los datos de la imagen
    private final List<Categoria> categorias = new ArrayList<>(Arrays.asList(
            new Categoria(1L, "Bebidas"),
            new Categoria(2L, "Deportes"),
            new Categoria(3L, "Cultura"),
            new Categoria(4L, "Videojuegos")
    ));
    private final AtomicLong counter = new AtomicLong(5);

    public List<Categoria> obtenerTodas() { return categorias; }

    public Categoria obtenerPorId(Long id) {
        return categorias.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public void guardar(CategoriaDto dto) {
        categorias.add(new Categoria(counter.getAndIncrement(), dto.getNombre()));
    }

    public void actualizar(Long id, CategoriaDto dto) {
        for (Categoria c : categorias) {
            if (c.getId().equals(id)) {
                c.setNombre(dto.getNombre());
                return;
            }
        }
    }

    public void eliminar(Long id) {
        categorias.removeIf(c -> c.getId().equals(id));
    }
}
