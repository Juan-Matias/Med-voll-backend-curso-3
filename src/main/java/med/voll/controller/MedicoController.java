package med.voll.controller;

import jakarta.validation.Valid;
import med.voll.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    //Verbo http Post de crear
    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroMedico datos, UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = new Medico(datos);
        repository.save(medico);
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleMedico(medico)); // 201 Created con ubicación del nuevo recurso
    }

    // Verbo http Get Leer
    @GetMapping
    public ResponseEntity<Page<DatosListaMedico>> listar(
            @PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {

        Page<DatosListaMedico> pagina = repository
                .findAllByActivoTrue(paginacion)
                .map(DatosListaMedico::new);

        // HTTP 200 OK
        return ResponseEntity.ok(pagina);
    }


    // Verbo http Put Actualizar
    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos) {
        Medico medico = repository.getReferenceById(datos.id());
        medico.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

    // Verbo http Delete Eliminar
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.eliminar();
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleMedico(medico)); // 200 No Content
    }
}
