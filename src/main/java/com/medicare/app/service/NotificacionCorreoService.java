package com.medicare.app.service;

import com.medicare.app.domain.Usuario;

public interface NotificacionCorreoService {

    void enviarCorreoBienvenida(Usuario usuario);
}
