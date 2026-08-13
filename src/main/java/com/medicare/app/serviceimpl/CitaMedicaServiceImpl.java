package com.medicare.app.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.medicare.app.domain.CitaMedica;
import com.medicare.app.repository.CitaMedicaRepository;
import com.medicare.app.service.CitaMedicaService;

@Service
public class CitaMedicaServiceImpl implements CitaMedicaService {

    private final CitaMedicaRepository citaMedicaRepository;

    public CitaMedicaServiceImpl(CitaMedicaRepository citaMedicaRepository) {
        this.citaMedicaRepository = citaMedicaRepository;
    }

    @Override
    public List<CitaMedica> listarTodas() {
        return citaMedicaRepository.findAll();
    }

    @Override
    public CitaMedica buscarPorId(Long id) {
        return citaMedicaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cita médica no encontrada con id " + id));
    }

    @Override
    public CitaMedica guardar(CitaMedica cita) {
        if (cita.getActiva() == null) {
            cita.setActiva(Boolean.TRUE);
        }
        return citaMedicaRepository.save(cita);
    }

    @Override
    public void eliminar(Long id) {
        citaMedicaRepository.deleteById(id);
    }

    @Override
    public List<CitaMedica> buscarPorEstado(Boolean activa) {
        return citaMedicaRepository.findByActiva(activa);
    }

    @Override
    public List<CitaMedica> buscarEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return citaMedicaRepository.buscarEntreFechas(fechaInicio, fechaFin);
    }

    @Override
    public List<CitaMedica> buscarPorEspecialidad(String especialidad) {
        return citaMedicaRepository.findByEspecialidadContainingIgnoreCase(especialidad);
    }

    @Override
    public long contarCitasActivas() {
        return citaMedicaRepository.contarCitasActivas();
    }
}
