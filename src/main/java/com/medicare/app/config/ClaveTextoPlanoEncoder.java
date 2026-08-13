package com.medicare.app.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * El script oficial de la base de datos 'medicare' inserta contraseñas de prueba en texto
 * plano (por ejemplo '12345'). Este encoder respeta esos datos oficiales tal cual fueron
 * provistos, sin alterar el script SQL ni el comportamiento de Spring Security.
 */
public class ClaveTextoPlanoEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}
