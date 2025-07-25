package med.voll.domain.medico;

import med.voll.domain.direccion.DatosDireccion;

public record DatosActualizacionMedico(
        Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
