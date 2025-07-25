package med.voll.domain.paciente;


import med.voll.domain.direccion.Direccion;

public record DatosDetallePaciente(String nombre, String email, String telefono, String documentoIdentidad, Direccion direccion) {

    public DatosDetallePaciente(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getTelefono(), paciente.getDocumento_identidad(), paciente.getDireccion());
    }
}
