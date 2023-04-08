package com.example.demo.service;

import com.example.demo.exceptions.BadRequestException;
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
    public Paciente save(Paciente paciente) throws BadRequestException {
        if (paciente == null){
            throw new BadRequestException("El paciente no puede ser nulo");
        }
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscar(Integer id) throws BadRequestException {
        Optional<Paciente> pacientes = pacienteRepository.findById(id);
        if (pacientes.isEmpty()){
            throw new BadRequestException("El paciente no existe");
        }
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos() throws BadRequestException{
        List<Paciente> pacientes = pacienteRepository.findAll();
        if (pacientes.isEmpty()) {
            throw new BadRequestException("No se encontraron pacientes");
        }
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

    public boolean eliminar(Integer id) throws BadRequestException{
        Optional<Paciente> pacienteExistente = buscar(id);

        if(!pacienteExistente.isPresent()){
            throw new BadRequestException("No existe el paciente con id " + id);
        }
            pacienteRepository.deleteById(id);
            return true;
    }

    public Paciente actualizarPaciente(Paciente pacienteActualizado) throws BadRequestException {
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(pacienteActualizado.getId());
        if (!pacienteExistente.isPresent()) {
            throw new BadRequestException("No existe este paciente");

        }
            return pacienteRepository.save(pacienteActualizado);
    }
}
