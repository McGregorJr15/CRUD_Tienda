package com.upiiz.tienda.controllers;

import com.upiiz.tienda.dto.CategoriaDto;
import com.upiiz.tienda.models.Categoria;
import com.upiiz.tienda.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) { this.categoriaService = categoriaService; }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.obtenerTodas());
        return "listado-categorias";
    }

    @GetMapping("/agregar")
    public String agregar(Model model) {
        model.addAttribute("categoriaDto", new CategoriaDto());
        return "formulario-agregar-categoria";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("categoriaDto") CategoriaDto dto) {
        categoriaService.guardar(dto);
        return "redirect:/categorias";
    }

    @GetMapping("/actualizar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Categoria c = categoriaService.obtenerPorId(id);
        if (c == null) return "redirect:/categorias";
        model.addAttribute("categoriaDto", new CategoriaDto(c.getNombre()));
        model.addAttribute("id", id);
        return "formulario-actualizar-categoria";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute("categoriaDto") CategoriaDto dto) {
        categoriaService.actualizar(id, dto);
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String confirmarEliminar(@PathVariable Long id, Model model) {
        Categoria c = categoriaService.obtenerPorId(id);
        if (c == null) return "redirect:/categorias";
        model.addAttribute("categoria", c);
        return "formulario-eliminar-categoria";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return "redirect:/categorias";
    }
}