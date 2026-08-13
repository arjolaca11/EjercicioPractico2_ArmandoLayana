package com.medicare.app.service;

import java.util.List;

import com.medicare.app.domain.Rol;

public interface RolService {

    List<Rol> listarTodos();

    Rol buscarPorId(Long id);

    Rol guardar(Rol rol);

    void eliminar(Long id);
}
