package com.medicare.app.controllers;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medicare.app.service.CitaMedicaService;
import com.medicare.app.service.UsuarioService;

@Controller
@RequestMapping("/consultas")
public class ConsultaAvanzadaController {

    private final CitaMedicaService citaMedicaService;
    private final UsuarioService usuarioService;

    public ConsultaAvanzadaController(CitaMedicaService citaMedicaService, UsuarioService usuarioService) {
        this.citaMedicaService = citaMedicaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String panelConsultas(Model model) {
        model.addAttribute("totalCitasActivas", citaMedicaService.contarCitasActivas());
        return "consultas/index";
    }

    @GetMapping("/por-estado")
    public String porEstado(@RequestParam Boolean activa, Model model) {
        model.addAttribute("resultadoEstado", citaMedicaService.buscarPorEstado(activa));
        model.addAttribute("filtroActiva", activa);
        model.addAttribute("totalCitasActivas", citaMedicaService.contarCitasActivas());
        return "consultas/index";
    }

    @GetMapping("/por-rango-fechas")
    public String porRangoFechas(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin, Model model) {
        model.addAttribute("resultadoRango", citaMedicaService.buscarEntreFechas(fechaInicio, fechaFin));
        model.addAttribute("filtroFechaInicio", fechaInicio);
        model.addAttribute("filtroFechaFin", fechaFin);
        model.addAttribute("totalCitasActivas", citaMedicaService.contarCitasActivas());
        return "consultas/index";
    }

    @GetMapping("/por-especialidad")
    public String porEspecialidad(@RequestParam String texto, Model model) {
        model.addAttribute("resultadoEspecialidad", citaMedicaService.buscarPorEspecialidad(texto));
        model.addAttribute("filtroEspecialidad", texto);
        model.addAttribute("totalCitasActivas", citaMedicaService.contarCitasActivas());
        return "consultas/index";
    }

    @GetMapping("/por-rol")
    public String porRol(@RequestParam String nombreRol, Model model) {
        model.addAttribute("resultadoUsuariosRol", usuarioService.buscarPorRol(nombreRol));
        model.addAttribute("filtroRol", nombreRol);
        model.addAttribute("totalCitasActivas", citaMedicaService.contarCitasActivas());
        return "consultas/index";
    }
}
