package com.medicare.app.config;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.medicare.app.domain.Usuario;
import com.medicare.app.repository.UsuarioRepository;

@Service
public class AutenticacionUsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AutenticacionUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No existe un usuario con el correo " + email));

        if (usuario.getRol() == null) {
            throw new UsernameNotFoundException("El usuario " + email + " no tiene un rol asignado");
        }

        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                Boolean.TRUE.equals(usuario.getActivo()),
                true, true, true,
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre().toUpperCase())));
    }
}
