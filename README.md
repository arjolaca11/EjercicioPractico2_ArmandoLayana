# EjercicioPractico2_ArmandoLayana

## MediCare - Gestión de Citas Médicas y Usuarios

Aplicación web desarrollada con Spring Boot para la plataforma de servicios de salud MediCare:
administración de usuarios, roles, control de acceso por rol con Spring Security y gestión de
Citas Médicas, incluyendo envío de correo de bienvenida con Spring Mail y consultas avanzadas con JPA/Hibernate.

### Tecnologías

Java, Spring Boot, Spring Web, Spring Data JPA, MySQL Driver, Spring Mail, Spring Security, Thymeleaf, Bootstrap.

### Estructura de paquetes

`domain`, `repository`, `service`, `serviceimpl`, `controllers`, `config`, `templates`.

### Configuración previa

1. **Base de datos**: ejecutar el script `database/script_medicare.sql` en MySQL (crea la base `medicare`,
   las tablas y los datos de prueba).
2. **Credenciales de MySQL**: ajustar `spring.datasource.username` / `spring.datasource.password` en
   `src/main/resources/application.properties` si son distintas a `root` / sin contraseña.
3. **Spring Mail**: reemplazar `spring.mail.username` y `spring.mail.password` en `application.properties`
   por una cuenta de correo real (para Gmail se recomienda una "contraseña de aplicación").

### Ejecución

```bash
./mvnw spring-boot:run
```

La aplicación queda disponible en `http://localhost:78`.

### Usuarios de prueba (definidos en el script oficial)

| Email | Password | Rol |
|---|---|---|
| admin@medicare.com | 12345 | ADMIN |
| medico@medicare.com | 12345 | MEDICO |
| paciente@medicare.com | 12345 | PACIENTE |
