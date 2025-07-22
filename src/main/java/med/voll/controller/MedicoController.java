package med.voll.controller;

import jakarta.validation.Valid;
import med.voll.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    //Pueda crear una instancion que tenga heredado de esa interfaz
    @Autowired
    private MedicoRepository repository;

    //Vamos estar haciendo modificaciones en la base de datos
    //Por eso usamos esta Anotacion
    @Transactional
    //Verbo HTTP metodo Post
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroMedico datos) {

        repository.save(new Medico(datos));
    }

    // Maneja solicitudes HTTP GET hacia la URL "/medicos"
    @GetMapping
    public Page<DatosListaMedico> listar(@PageableDefault(size = 10,sort={"nombre"}) Pageable paginacion) {
        return repository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);
    }

    // Solicitud put
    //
    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
        var medico = repository.getReferenceById(datos.id());
        medico.actualizarInformaciones(datos);

    }

    //Solicitud Delete
    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.eliminar();
    }


}
