package com.example.demo.service;

import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.entities.Turno;
import com.example.demo.persistence.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscar(Integer id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorDni(Integer dni) {
        List<Paciente> odontologos = pacienteRepository.findByDni(dni);
        if (odontologos.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(odontologos.get(0));
        }
    }

    public boolean eliminar(Integer id){
        try {
            pacienteRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Paciente actualizar(Integer id,Paciente pacienteUpdate){
        Paciente paciente = pacienteRepository.findById(id).orElseThrow();
        paciente.setNombre(pacienteUpdate.getNombre());
        paciente.setApellido(pacienteUpdate.getApellido());
        paciente.setDni(pacienteUpdate.getDni());
        paciente.setFechaingreso(pacienteUpdate.getFechaingreso());
        paciente.setDomicilio(pacienteUpdate.getDomicilio());
        return pacienteRepository.save(paciente);
    }
}
