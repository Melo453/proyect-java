package com.example.demo.controller;

import com.example.demo.persistence.entities.Paciente;
import com.example.demo.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping()
    public ResponseEntity<List<Paciente>> buscarTodos() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }
    @PostMapping("/crear")
    public ResponseEntity<String> registrarPaciente(@RequestBody Paciente paciente) {
        ResponseEntity<String> respuesta = null;
        if (pacienteService.save(paciente) != null) {
            respuesta = ResponseEntity.ok("paciente Resgistrado");
        }else {
            respuesta = ResponseEntity.internalServerError().body("Ooops");
        }

        return respuesta;
    }

    @GetMapping(path = "/{id}")
    public Optional<Paciente> buscarPaciente(@PathVariable("id") Integer id) {
        return this.pacienteService.buscar(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        boolean ok = this.pacienteService.eliminar(id);
        if (pacienteService.eliminar(id)) {
            return ResponseEntity.ok("paciente eliminado");
        }else {
            return ResponseEntity.internalServerError().body("Ooops");
        }
    }

}
