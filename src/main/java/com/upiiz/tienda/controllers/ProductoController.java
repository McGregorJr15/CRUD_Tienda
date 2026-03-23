package com.upiiz.tienda.controllers;

import com.upiiz.tienda.dto.ProductoDto;
import com.upiiz.tienda.models.Producto;
import com.upiiz.tienda.services.CategoriaService;
import com.upiiz.tienda.services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.obtenerTodos());
        return "listado-productos";
    }

    @GetMapping("/agregar")
    public String agregar(Model model) {
        model.addAttribute("productoDto", new ProductoDto());
        model.addAttribute("categorias", categoriaService.obtenerTodas());
        return "formulario-agregar-producto";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("productoDto") ProductoDto dto) {
        productoService.guardar(dto);
        return "redirect:/productos";
    }

    @GetMapping("/actualizar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto p = productoService.obtenerPorId(id);
        if (p == null) return "redirect:/productos";
        model.addAttribute("productoDto", new ProductoDto(p.getNombre(), p.getPrecio(), p.getCategoria()));
        model.addAttribute("id", id);
        model.addAttribute("categorias", categoriaService.obtenerTodas());
        return "formulario-actualizar-producto";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute("productoDto") ProductoDto dto) {
        productoService.actualizar(id, dto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String confirmarEliminar(@PathVariable Long id, Model model) {
        Producto p = productoService.obtenerPorId(id);
        if (p == null) return "redirect:/productos";
        model.addAttribute("producto", p);
        model.addAttribute("categorias", categoriaService.obtenerTodas());
        return "formulario-eliminar-producto";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}
