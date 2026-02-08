# loanApplicationMDR
Este proyecto sigue la arquitectura hexagonal (también conocida como Ports & Adapters). El objetivo es aislar la lógica 
de negocio (dominio) de detalles técnicos (p. ej. HTTP, BD, mensajería) mediante puertos (interfaces) y adaptadores 
(implementaciones).

## Estructura y responsabilidades

- `com.bankName.loanApplication.domain`
    - Entidades y enums de dominio.
    - Puertos (interfaces) de salida/entrada relacionados con la lógica.
    - Aquí no hay dependencias a Spring ni a bibliotecas de infraestructura.

- `com.bankName.loanApplication.application` (o `usecase`)
    - Casos de uso / Application Services.
    - Orquestan llamadas al dominio usando puertos, validaciones de flujo y transacciones.

- `com.bankName.loanApplication.infrastructure.database.h2.`
    - Entidades JPA que representan tablas. Solo contienen campos de persistencia y anotaciones JPA.
    - Repositorios Spring Data. Estos repositorios sirven para acceso CRUD.
    - Implementaciones de los puertos de salida del dominio (adaptadores).

- `com.bankName.loanApplication.infrastructure.rest`
    - Controladores HTTP (`@RestController`) que reciben DTOs y llaman a los casos de uso (application/usecase).
    - Deben recibir/retornar DTOs, no entidades de persistencia.
    - DTOs para entrada/salida HTTP.
    - Mapeadores entre entidades/dominios/DTOs.

## Tecnologías y configuración

- Spring Boot: 4.0.2
- Java: 21
- Base de datos: H2 (in-memory) configurada mediante JPA
- Maven para gestión de dependencias y construcción
- Lombok para reducir código boilerplate (getters/setters)



## Ejecución del proyecto

### Requisitos
- Java 21 (Asegurar `JAVA_HOME` apuntando a JDK 21)
- Maven 3.8+ o usar el wrapper (`mvnw.cmd`)
- IntelliJ IDEA (opcional)
- Puerto por defecto: `8080`

### Clonar y compilar
```powershell
# Clonar
git clone <repositorio>
cd <repositorio>

# Compilar y ejecutar tests
mvn clean package

# O compilar sin tests (rápido)
mvn clean package -DskipTests

# Ejecutar con Spring Boot (maven plugin)
mvn spring-boot:run

# O ejecutar el jar generado
java -jar target\*.jar
```

## Mejoras Transversales

### 1. Gestión de Excepciones
Implementar un control centralizado de errores mediante `@RestControllerAdvice`.

### 2. Validaciones
Mejorar la validación en las entidades de dominio como en DTOs para proteger el dominio de datos inconsistentes.

### 3. Estrategia de Logging
Implementar logs mediante **SLF4J / Logback**:
* **Trazabilidad:** Registro de eventos críticos como la creación de solicitudes de préstamos y transiciones de estado.
* **Diagnóstico:** Captura de errores inesperados con stacktraces detallados (Nivel `ERROR`).
* **Mantenibilidad:** Configuración de niveles de log ajustables según el entorno de ejecución.

---

## Mejoras y Extensiones Futuras

Dada la naturaleza del proyecto y con más tiempo de desarrollo, se proponen las siguientes mejoras:

### Técnicas y Arquitecturales
* **Seguridad (Spring Security + JWT):** Implementación de autenticación y autorización basada en roles (Cliente vs. Gestor).
* **Documentación con Swagger/OpenAPI:** Generación automática de la documentación de los endpoints y entorno de pruebas interactivo.
* **MapStruct:** Automatización del mapeo entre DTOs, Entidades JPA y Objetos de Dominio para reducir el código repetitivo.
* **Testcontainers:** Sustituir H2 por una base de datos real corriendo en contenedores Docker.

### Funcionales
* **Auditoría:** Registro histórico de qué usuario realizó cada cambio de estado y en qué fecha exacta.
* **Motor de Reglas:** Integración de un sistema de scoring automático para aprobar o rechazar solicitudes basado en el importe y perfil del cliente.
* **Notificaciones:** Implementar un sistema de eventos (Spring Events) para notificar al cliente vía email cuando su solicitud cambie de estado.