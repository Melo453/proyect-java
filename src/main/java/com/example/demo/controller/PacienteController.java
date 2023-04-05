package com.example.demo.controller;

import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.entities.Turno;
import com.example.demo.service.PacienteService;
import com.example.demo.service.TurnoService;
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
    @Autowired
    private TurnoService turnoService;

    @GetMapping()
    public ResponseEntity<List<Paciente>> buscarTodos() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }
    @PostMapping("/crear")
    public ResponseEntity<String> registrarPaciente(@RequestBody Paciente paciente) {
        ResponseEntity<String> respuesta = null;

        if (paciente == null) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: El objeto paciente esta vacio");
        } else if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: El nombre esta vacío");
        } else if (paciente.getApellido() == null || paciente.getApellido().isEmpty()) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: El apellido esta vacío");
        } else if (paciente.getDni() == null ) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: El DNI esta vacío");
        } else if(String.valueOf(paciente.getDni()).length() != 8){
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: El DNI es incorrecto, son 8 digitos");
        } else if (paciente.getDomicilio() == null) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: El domicilio esta vacio");
        } else if (paciente.getDomicilio().getCalle() == null || paciente.getDomicilio().getCalle().isEmpty()) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: La calle del domicilio esta vacia");
        } else if (paciente.getDomicilio().getNumero() == null) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: El numero del domicilio esta vacio");
        } else if (paciente.getDomicilio().getLocalidad() == null || paciente.getDomicilio().getLocalidad().isEmpty()) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: La localidad del domicilio esta vacia");
        } else if (paciente.getDomicilio().getProvincia() == null || paciente.getDomicilio().getProvincia().isEmpty()) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: La provincia del domicilio esta vacia");
        } else {
            Optional<Paciente> pacienteExistente = pacienteService.buscarPorDni(paciente.getDni());
            if (pacienteExistente.isPresent()) {
                respuesta = ResponseEntity.badRequest().body("Ya existe un paciente con este DNI");
            } else if (pacienteService.save(paciente) != null) {
                respuesta = ResponseEntity.ok("Paciente registrado");
            } else {
                respuesta = ResponseEntity.internalServerError().body("No se puede registrar el Paciente");
            }
        }

        return respuesta;
    }

    @GetMapping(path = "/{id}")
    public Optional<Paciente> buscarPaciente(@PathVariable("id") Integer id, Paciente paciente) {

        if (paciente.getId() == null) {
            ResponseEntity.badRequest().body("Este paciente no existe");
        }
        return this.pacienteService.buscar(id);
    }

    @GetMapping(path = "/turno/{id}")
    public List<Turno> obtenerTurnosPorPaciente(@PathVariable("id") Integer id) {
        Optional<Paciente> paciente = pacienteService.buscar(id);
        List<Turno> turnos = turnoService.buscarPorPaciente(paciente);
        return turnos;
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Optional<Paciente> pacienteExistente = pacienteService.buscar(id);
        if (pacienteExistente.isPresent()) {
            this.pacienteService.eliminar(id);
            return ResponseEntity.ok("Paciente eliminado");
        } else {
            return ResponseEntity.badRequest().body("No se puede eliminar un paciente no existente");
        }
    }

}
