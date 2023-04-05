package com.example.demo.controller;

import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.entities.Turno;
import com.example.demo.service.OdontologoService;
import com.example.demo.service.PacienteService;
import com.example.demo.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    @GetMapping()
    public ResponseEntity<List<Turno>> buscarTodos() {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    //verificar que si intento agregar 2 veces el mismo odontologo y paciente me tire que ya existe el turno con ese odontologo y paciente
    @PostMapping("/crear")
    public ResponseEntity<String> registrarTurno(@RequestBody Turno turno) {
        ResponseEntity<String> respuesta = null;

        if (turno == null) {
            respuesta = ResponseEntity.badRequest().body("El objeto turno no puede ser nulo");
            return respuesta;
        }else if( turno.getFechaTurno() == null){
            respuesta = ResponseEntity.badRequest().body("La fecha del turno no puede ser nula");
            return respuesta;
        }else if( turno.getPaciente() == null){
            respuesta = ResponseEntity.badRequest().body("El paciente no puede ser nulo");
            return respuesta;
        }else if( turno.getOdontologo() == null){
            respuesta = ResponseEntity.badRequest().body("El odontologo no puede ser nulo");
            return respuesta;
        }

        // Verificar la existencia del paciente y el odontólogo
        Optional<Paciente> paciente = pacienteService.buscar(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoService.buscar(turno.getOdontologo().getId());
        if ( !paciente.isPresent() || !odontologo.isPresent()) {
            respuesta = ResponseEntity.badRequest().body("El turno no se pudo registrar, paciente u odontologo no existen");
            return respuesta;
        }
        List<Turno> turnosRepetidos = turnoService.buscarTodos();

        for (Turno t : turnosRepetidos) {
            if (t.getOdontologo().getId().equals(turno.getOdontologo().getId()) && t.getPaciente().getId().equals(turno.getPaciente().getId())) {
                respuesta = ResponseEntity.badRequest().body("Ya existe un turno para el odontologo ese odontologo junto a ese paciente");
                return respuesta;
            }
        }


        // Verificar la disponibilidad de la fecha del turno
        List<Turno> turnos = turnoService.buscarTodos();
        for (Turno t : turnos) {
            if (t.getFechaTurno().equals(turno.getFechaTurno())) {
                respuesta = ResponseEntity.badRequest().body("Ya existe un turno para la fecha : " + turno.getFechaTurno());
                return respuesta;
            }
        }

        // Guardar el turno y responder con éxito
        Turno turnoGuardado = turnoService.save(turno);
        respuesta = ResponseEntity.ok("Turno registrado para la fecha " + turnoGuardado.getFechaTurno() + " con éxito" );
        return respuesta;
    }

    @GetMapping(path = "/{id}")
    public Optional<Turno> buscarTurno(@PathVariable("id") Integer id) {
        return this.turnoService.buscar(id);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Optional<Turno> turnoExistente = turnoService.buscar(id);
        if (turnoExistente.isPresent()) {
            this.turnoService.eliminar(id);
            return ResponseEntity.ok("Turno eliminado");
        } else {
            return ResponseEntity.badRequest().body("No se puede eliminar un turno no existente");
        }
    }
}
