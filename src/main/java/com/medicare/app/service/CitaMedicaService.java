package com.medicare.app.service;

import java.time.LocalDate;
import java.util.List;

import com.medicare.app.domain.CitaMedica;

public interface CitaMedicaService {

    List<CitaMedica> listarTodas();

    CitaMedica buscarPorId(Long id);

    CitaMedica guardar(CitaMedica cita);

    void eliminar(Long id);

    List<CitaMedica> buscarPorEstado(Boolean activa);

    List<CitaMedica> buscarEntreFechas(LocalDate fechaInicio, LocalDate fechaFin);

    List<CitaMedica> buscarPorEspecialidad(String especialidad);

    long contarCitasActivas();
}
