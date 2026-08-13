package com.medicare.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RedireccionPorRolHandler redireccionPorRolHandler;

    public SecurityConfig(RedireccionPorRolHandler redireccionPorRolHandler) {
        this.redireccionPorRolHandler = redireccionPorRolHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new ClaveTextoPlanoEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/citas/nueva", "/citas/editar/**").hasAnyRole("ADMIN", "MEDICO")
                        .requestMatchers(HttpMethod.POST, "/citas/**").hasAnyRole("ADMIN", "MEDICO")
                        .requestMatchers(HttpMethod.GET, "/citas/**").hasAnyRole("ADMIN", "MEDICO", "PACIENTE")
                        .requestMatchers("/consultas/**").hasAnyRole("ADMIN", "MEDICO", "PACIENTE")
                        .requestMatchers("/usuarios/**", "/roles/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(redireccionPorRolHandler)
                        .failureUrl("/login?error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

        return http.build();
    }
}
