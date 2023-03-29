package com.example.demo.controller;

import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.service.OdontologoService;
import com.example.demo.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping()
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
    @PostMapping("/crear")
    public ResponseEntity<String> registrarOdontologo(@RequestBody Odontologo odontologo) {
        ResponseEntity<String> respuesta = null;
        if (odontologoService.save(odontologo) != null) {
            respuesta = ResponseEntity.ok("odontologo Resgistrado");
        }else {
            respuesta = ResponseEntity.internalServerError().body("Ooops");
        }

        return respuesta;
    }

    @GetMapping(path = "/{id}")
    public Optional<Odontologo> buscarOdontologo(@PathVariable("id") Integer id) {
        return this.odontologoService.buscar(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        boolean ok = this.odontologoService.eliminar(id);
        if (ok) {
            return ResponseEntity.ok("Odontólogo eliminado");
        } else {
            return ResponseEntity.internalServerError().body("Ooops");
        }
    }

}
