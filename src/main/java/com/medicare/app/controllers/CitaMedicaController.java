package com.medicare.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medicare.app.domain.CitaMedica;
import com.medicare.app.service.CitaMedicaService;

@Controller
@RequestMapping("/citas")
public class CitaMedicaController {

    private final CitaMedicaService citaMedicaService;

    public CitaMedicaController(CitaMedicaService citaMedicaService) {
        this.citaMedicaService = citaMedicaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("citas", citaMedicaService.listarTodas());
        return "citas/lista";
    }

    @GetMapping("/nueva")
    public String formularioNueva(Model model) {
        model.addAttribute("cita", new CitaMedica());
        return "citas/formulario";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("cita", citaMedicaService.buscarPorId(id));
        return "citas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("cita") CitaMedica cita) {
        citaMedicaService.guardar(cita);
        return "redirect:/citas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        citaMedicaService.eliminar(id);
        return "redirect:/citas";
    }
}
