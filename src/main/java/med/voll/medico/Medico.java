package med.voll.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import med.voll.direccion.Direccion;

@Table(name = "medicos")
@Entity(name="Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean activo;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datos) {
        this.id = null;
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
        this.especialidad = datos.especialidad();
        this.direccion = new Direccion(datos.direccion());
    }

    //Creamos una validacion de cada dato actualizado para que el campo NO quede en : null o vacio
    public void actualizarInformaciones(@Valid DatosActualizacionMedico datos) {
        // Actualiza solo si el nombre no es nulo ni está en blanco
        if (datos.nombre() != null && !datos.nombre().isBlank()) {
            this.nombre = datos.nombre();
        }

        // Actualiza solo si el teléfono no es nulo ni está en blanco
        if (datos.telefono() != null && !datos.telefono().isBlank()) {
            this.telefono = datos.telefono();
        }

        // Actualiza la dirección solo si el objeto no es nulo
        if (datos.direccion() != null) {
            this.direccion.actualizarDireccion(datos.direccion());
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}
