package com.medicare.app.service;

import java.util.List;

import com.medicare.app.domain.Usuario;

public interface UsuarioService {

    List<Usuario> listarTodos();

    Usuario buscarPorId(Long id);

    Usuario registrarUsuario(Usuario usuario);

    Usuario actualizarUsuario(Usuario usuario);

    void eliminar(Long id);

    List<Usuario> buscarPorRol(String nombreRol);
}
