package med.voll.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class Direccion {
    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String codigo_postal;
    private String ciudad;
    private String estado;

    public Direccion() {
        // Constructor sin argumentos para Hibernate
    }

    public Direccion(DatosDireccion datosDireccion) {
        this.calle = datosDireccion.calle();
        this.numero = datosDireccion.numero();
        this.complemento = datosDireccion.complemento();
        this.barrio = datosDireccion.barrio();
        this.codigo_postal = datosDireccion.codigo_postal();
        this.ciudad = datosDireccion.ciudad();
        this.estado = datosDireccion.estado();
    }

    public void actualizarDireccion(DatosDireccion datos) {
        // Solo actualiza los campos que no sean nulos ni estén en blanco
        if (datos.calle() != null && !datos.calle().isBlank()) {
            this.calle = datos.calle();
        }
        if (datos.numero() != null && !datos.numero().isBlank()) {
            this.numero = datos.numero();
        }
        if (datos.complemento() != null && !datos.complemento().isBlank()) {
            this.complemento = datos.complemento();
        }
        if (datos.barrio() != null && !datos.barrio().isBlank()) {
            this.barrio = datos.barrio();
        }
        if (datos.codigo_postal() != null && !datos.codigo_postal().isBlank()) {
            this.codigo_postal = datos.codigo_postal();
        }
        if (datos.ciudad() != null && !datos.ciudad().isBlank()) {
            this.ciudad = datos.ciudad();
        }
        if (datos.estado() != null && !datos.estado().isBlank()) {
            this.estado = datos.estado();
        }
    }

}
