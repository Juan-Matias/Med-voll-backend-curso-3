package med.voll.controller;

import jakarta.validation.Valid;
import med.voll.domain.consulta.DatosCancelamientoConsulta;
import med.voll.domain.consulta.DatosDetalleConsulta;
import med.voll.domain.consulta.DatosReservaConsulta;
import med.voll.domain.consulta.ReservaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ReservaDeConsultas reserva;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {
        reserva.reservar(datos);
        return ResponseEntity.ok(new DatosDetalleConsulta(null, null, null, null));
    }

    /*
    Nuevo funcionalidad que necesitamos :
            El sistema debe tener una funcionalidad que permita el cancelamento de consultas, en la cual deberán llenarse las siguientes informaciones:
        ●     Consulta
        ●     Motivo del cancelamiento
        Las siguientes reglas de negocio deben ser validadas por el sistema:
        ●     Es obligatorio informar el motivo del cancelamiento de la consulta, dentro de las siguientes opciones: paciente desistió, médico canceló u otros;
        ●     Una consulta solamente podrá ser cancelada con antecipación mínima de 24 horas.
     */

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        reserva.cancelar(datos);
        return ResponseEntity.noContent().build();
    }


}
