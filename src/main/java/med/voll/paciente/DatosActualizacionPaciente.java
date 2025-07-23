package med.voll.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.direccion.DatosDireccion;

public record DatosActualizacionPaciente(
        @NotNull Long id,
        String nombre,
        String telefono,
        @Valid DatosDireccion direccion
) {
}
