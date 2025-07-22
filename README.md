# Medicina Alura Latam ![Java Version](https://img.shields.io/badge/Java-17-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1-green)

## Estado del Proyecto
🚧 En desarrollo (v0.1.0)  
✅ Funcionalidades completadas: CRUD Médicos  
⏳ Próximas features: Autenticación JWT, Sistema de Citas

## Descripción
Proyecto backend para gestión médica desarrollado durante el curso de Alura Latam, utilizando Spring Boot 3 y Java 17.

##

## Stack Tecnológico
| Capa           | Tecnologías              |
|----------------|--------------------------|
| Backend        | Spring Boot 3, Java 17   |
| Persistencia   | MySQL, Hibernate, Flyway |
| Herramientas   | Lombok, Maven, Insomnia  |

## Configuración Básica
```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinica
spring.jpa.hibernate.ddl-auto=validate
```

##

## Versiones LTS / ¿Qué son?

Las versiones LTS (Long Term Support) son versiones con soporte extendido y actualizaciones de seguridad por varios años.  
Se recomienda usarlas en producción para mayor estabilidad.

##

## Nomenclatura de comentarios de Git

| Prefijo   | Descripción                                         |
| --------- | ------------------------------------------------- |
| `feat:`   | Nueva funcionalidad                                |
| `fix:`    | Corrección de bugs                                 |
| `docs:`   | Cambios en documentación                           |
| `style:`  | Cambios de formato (no funcionales)               |
| `refactor:` | Reestructuración de código                       |
| `test:`   | Añadir o mejorar pruebas                           |
| `chore:`  | Tareas menores (actualizar dependencias, config) |

##

## Instalación y Ejecución

Para correr el proyecto backend:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

```mermaid
classDiagram
    direction LR

    %% ==== CONTROLADORES ====
    class MedicoController {
        <<Controller>>
        +registrar(DatosRegistroMedico)
        +listar(Pageable): Page<DatosListaMedico>
        +actualizar(DatosActualizacionMedico)
        +eliminar(Long)
    }

    %% ==== CONFIGURACIÓN ====
    class corsConfiguration {
        <<Configuration>>
        +addCorsMappings(CorsRegistry)
    }

    %% ==== APLICACIÓN ====
    class ApiApplication {
        <<SpringBootApplication>>
    }

    %% ==== ENTIDAD PRINCIPAL ====
    class Medico {
        <<Entity>>
        - Long id
        - Boolean activo
        - String nombre
        - String email
        - String telefono
        - String documento
        - Especialidad especialidad
        - Direccion direccion
        +actualizarInformaciones(DatosActualizacionMedico)
        +eliminar()
    }

    %% ==== ENUM ====
    class Especialidad {
        <<Enumeration>>
        ORTOPEDIA
        CARDIOLOGIA
        GINECOLOGIA
        DERMATOLOGIA
    }

    %% ==== EMBEBIDA ====
    class Direccion {
        <<Embeddable>>
        - String calle
        - String numero
        - String complemento
        - String barrio
        - String codigo_postal
        - String ciudad
        - String estado
        +actualizarDireccion(DatosDireccion)
    }

    %% ==== REPOSITORY ====
    class MedicoRepository {
        <<Repository>>
        +findAllByActivoTrue(Pageable): Page<Medico>
    }

    %% ==== DTOs ====
    class DatosRegistroMedico {
        <<Record>>
        +String nombre()
        +String email()
        +String telefono()
        +String documento()
        +Especialidad especialidad()
        +DatosDireccion direccion()
    }

    class DatosActualizacionMedico {
        <<Record>>
        +Long id()
        +String nombre()
        +String telefono()
        +DatosDireccion direccion()
    }

    class DatosListaMedico {
        <<Record>>
        +Long id
        +String nombre
        +String email
        +String documento
        +Especialidad especialidad
    }

    class DatosDireccion {
        <<Record>>
        +String calle()
        +String numero()
        +String complemento()
        +String barrio()
        +String codigo_postal()
        +String ciudad()
        +String estado()
    }

    %% ==== RELACIONES MEJORADAS ====
    MedicoController --> MedicoRepository : "usa"
    MedicoController ..> DatosRegistroMedico : "procesa"
    MedicoController ..> DatosActualizacionMedico : "procesa"
    MedicoController ..> DatosListaMedico : "genera"

    Medico *-- Direccion : "contiene"
    Medico --> Especialidad : "tiene"
    Medico ..> DatosRegistroMedico : "se construye con"
    Medico ..> DatosActualizacionMedico : "actualiza con"

    Direccion ..> DatosDireccion : "mapea desde"

    DatosListaMedico ..|> Medico : "transforma a"

    MedicoRepository "1" --> "M" Medico : "persiste"

    ApiApplication ..> Medico : "incluye"
    ApiApplication ..> Direccion : "incluye"
```