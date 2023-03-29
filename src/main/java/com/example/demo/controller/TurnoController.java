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
    @PostMapping("/crear")
    public ResponseEntity<String> registrarTurno(@RequestBody Turno turno) {


        ResponseEntity<String> respuesta = null;
        Optional<Paciente> paciente = pacienteService.buscar(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoService.buscar(turno.getOdontologo().getId());

        if (turno != null && paciente.isPresent() && odontologo.isPresent()) {

            List<Turno> turnos = turnoService.buscarTodos();
            for (Turno t : turnos) {
                if (t.getFechaTurno().equals(turno.getFechaTurno())) {
                    respuesta = ResponseEntity.badRequest().body("Ya existe un turno para la fecha : " + turno.getFechaTurno());
                    return respuesta;
                }
                Turno turno1 = turnoService.save(turno);
                respuesta = ResponseEntity.ok("turno Resgistrado" + turno1);
                return respuesta;
            }
        }else
            respuesta = ResponseEntity.badRequest().body("El turno no se pudo registrar, paciente u odontologo no existen");

        return respuesta;

    }

    @GetMapping(path = "/{id}")
    public Optional<Turno> buscarTurno(@PathVariable("id") Integer id) {
        return this.turnoService.buscar(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        boolean ok = this.turnoService.eliminar(id);
        if (turnoService.eliminar(id)) {
            return ResponseEntity.ok("turno eliminado");
        }else {
            return ResponseEntity.internalServerError().body("Ooops");
        }
    }
}
