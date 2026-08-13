package com.medicare.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medicare.app.domain.CitaMedica;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {

    // Consulta derivada 1: citas por estado (activas/inactivas)
    List<CitaMedica> findByActiva(Boolean activa);

    // Consulta personalizada 2: citas dentro de un rango de fechas
    @Query("SELECT c FROM CitaMedica c WHERE c.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY c.fecha ASC")
    List<CitaMedica> buscarEntreFechas(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    // Consulta derivada 3: coincidencia parcial de especialidad
    List<CitaMedica> findByEspecialidadContainingIgnoreCase(String especialidad);

    // Consulta personalizada 4: total de citas activas
    @Query("SELECT COUNT(c) FROM CitaMedica c WHERE c.activa = true")
    long contarCitasActivas();
}
