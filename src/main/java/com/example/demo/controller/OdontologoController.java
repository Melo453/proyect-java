package com.example.demo.controller;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.entities.Turno;
import com.example.demo.service.OdontologoService;
import com.example.demo.service.PacienteService;
import com.example.demo.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    @GetMapping()
    public ResponseEntity<List<Odontologo>> buscarTodos() throws BadRequestException {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
    @PostMapping("/crear")
    public ResponseEntity<String> registrarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        ResponseEntity<String> respuesta = null;
        if (odontologo == null){
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: El objeto odontologo esta vacio");
        } else if (odontologo.getNombre() == null || odontologo.getNombre().isEmpty()) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: El nombre esta vacío");
        } else if (odontologo.getApellido() == null || odontologo.getApellido().isEmpty()) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: El apellido esta vacío");
        } else if (odontologo.getMatricula() == null || odontologo.getMatricula() <= 0) {
            respuesta = ResponseEntity.badRequest().body("Datos obligatorios faltantes: La matricula esta vacía o es incorrecta");
        } else {
            Optional<Odontologo> odontologoExistente = odontologoService.buscarPorMatricula(odontologo.getMatricula());
            if (odontologoExistente.isPresent()) {
                respuesta = ResponseEntity.badRequest().body("Ya existe un odontólogo con esta matrícula");
            } else if (odontologoService.save(odontologo) != null) {
                respuesta = ResponseEntity.ok("Odontólogo registrado");
            } else {
                respuesta = ResponseEntity.internalServerError().body("No se puede registrar el odontologo");
            }
        }

        return respuesta;
    }

    @GetMapping(path = "/{id}")
    public Optional<Odontologo> buscarOdontologo(@PathVariable("id") Integer id) throws BadRequestException{
        return this.odontologoService.buscar(id);
    }

    @GetMapping(path = "/turno/{id}")
    public List<Turno> obtenerTurnosPorPaciente(@PathVariable("id") Integer id) throws BadRequestException{
        Optional<Odontologo> odontologo = odontologoService.buscar(id);
        List<Turno> turnos = turnoService.buscarPorOdontolgoo(odontologo);
        return turnos;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) throws BadRequestException{
        Optional<Odontologo> odontologoExistente = odontologoService.buscar(id);
        if (odontologoExistente.isPresent()) {
            this.odontologoService.eliminar(id);
            return ResponseEntity.ok("Odontólogo eliminado");
        } else {
            return ResponseEntity.badRequest().body("No se puede eliminar un odontólogo no existente");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarOdontologo(@PathVariable Integer id, @RequestBody Odontologo odontologo) throws BadRequestException{
        Optional<Odontologo> odontologoExistente = odontologoService.buscar(id);
        if (odontologoExistente.isPresent()) {
            odontologo.setId(id);
            Odontologo odontologoActualizado = odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontólogo actualizado");
        } else {
            return ResponseEntity.badRequest().body("No se puede actualizar un odontólogo no existente");
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarError(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
