package com.medicare.app.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medicare.app.domain.Usuario;
import com.medicare.app.repository.UsuarioRepository;
import com.medicare.app.service.NotificacionCorreoService;
import com.medicare.app.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioRepository usuarioRepository;
    private final NotificacionCorreoService notificacionCorreoService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
            NotificacionCorreoService notificacionCorreoService,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.notificacionCorreoService = notificacionCorreoService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id " + id));
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setActivo(usuario.getActivo() == null ? Boolean.TRUE : usuario.getActivo());
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        try {
            notificacionCorreoService.enviarCorreoBienvenida(usuarioGuardado);
        } catch (Exception correoError) {
            LOGGER.warn("No fue posible enviar el correo de bienvenida a {}: {}",
                    usuarioGuardado.getEmail(), correoError.getMessage());
        }
        return usuarioGuardado;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        Usuario usuarioExistente = buscarPorId(usuario.getId());
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setRol(usuario.getRol());
        usuarioExistente.setActivo(usuario.getActivo());
        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> buscarPorRol(String nombreRol) {
        return usuarioRepository.findByRol_NombreIgnoreCase(nombreRol);
    }
}
