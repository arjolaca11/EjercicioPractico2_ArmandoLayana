package com.medicare.app.serviceimpl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.medicare.app.domain.Usuario;
import com.medicare.app.service.NotificacionCorreoService;

@Service
public class NotificacionCorreoServiceImpl implements NotificacionCorreoService {

    private final JavaMailSender javaMailSender;

    public NotificacionCorreoServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void enviarCorreoBienvenida(Usuario usuario) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(usuario.getEmail());
        mensaje.setSubject("Bienvenido a MediCare");
        mensaje.setText("Hola " + usuario.getNombre() + ",\n\n"
                + "Tu cuenta ha sido creada exitosamente en la plataforma MediCare.\n"
                + "Correo registrado: " + usuario.getEmail() + "\n\n"
                + "Gracias por confiar en nuestros servicios de salud.\n\n"
                + "Equipo MediCare");
        javaMailSender.send(mensaje);
    }
}
