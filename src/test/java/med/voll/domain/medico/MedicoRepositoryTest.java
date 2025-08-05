package med.voll.domain.medico;

import jakarta.persistence.EntityManager;
import med.voll.domain.consulta.Consulta;
import med.voll.domain.direccion.DatosDireccion;
import med.voll.domain.paciente.Paciente;
import med.voll.domain.paciente.DatosRegistroPaciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Debería devolver null cuando el médico buscado existe pero no está disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario1() {
        // Arrange
        var proximoLunesALas10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = registrarMedico("Medico1", "medico@email.com", "123456", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Lucía", "lucia@email.com", "654321");
        registrarConsulta(medico, paciente, proximoLunesALas10);

        // Act
        var medicoLibre = medicoRepository
                .elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, proximoLunesALas10);

        // Assert
        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("Debería devolver medico cuando el médico buscado esta disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario2() {
        // Arrange
        var proximoLunesALas10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = registrarMedico("Medico1", "medico@email.com", "123456", Especialidad.CARDIOLOGIA);

        // Act
        var medicoLibre = medicoRepository
                .elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, proximoLunesALas10);

        // Assert
        assertThat(medicoLibre).isEqualTo(medico);
    }


    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(null, medico, paciente, fecha));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento) {
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosRegistroMedico(
                nombre,
                email,
                "6145489789",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento) {
        return new DatosRegistroPaciente(
                nombre,
                email,
                "1234564",
                documento,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "Calle X",
                "Distrito Y",
                "Ciudad Z",
                "123",
                "1",
                "Ciudada x",
                "Estado x"
        );
    }
}
