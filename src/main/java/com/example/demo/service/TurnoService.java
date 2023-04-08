package com.example.demo.service;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.entities.Turno;
import com.example.demo.persistence.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    TurnoRepository turnoRepository;

    public Turno save(Turno turno)  {
        return turnoRepository.save(turno);
    }

    public Optional<Turno> buscar(Integer id) throws BadRequestException {
        Optional<Turno> turnos = turnoRepository.findById(id);
        if (turnos.isEmpty()){
            throw new BadRequestException("El turno no existe");
        }
        return turnoRepository.findById(id);
    }

    public List<Turno> buscarPorFecha(LocalDate fecha) {
        return turnoRepository.findByFechaTurno(fecha);
    }
    public List<Turno> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        if (!turnos.isEmpty()) {
            return turnoRepository.findAll();
        }
        return null;
    }

    public List<Turno> buscarPorPaciente(Optional<Paciente> paciente) {
        return turnoRepository.findByPaciente(paciente);
    }

    public List<Turno> buscarPorOdontolgoo(Optional<Odontologo> odontologo) {
        return turnoRepository.findByOdontologo(odontologo);
    }

    public boolean eliminar(Integer id) throws BadRequestException{
        Optional<Turno> turnoExistente = buscar(id);

        if(!turnoExistente.isPresent()){
            throw new BadRequestException("No existe el turno con id " + id);
        }
            turnoRepository.deleteById(id);
            return true;

    }

    public Turno actualizarTurno(Turno turnoActualizado) throws BadRequestException{
        Optional<Turno> turnoExistente = turnoRepository.findById(turnoActualizado.getId());
        if (!turnoExistente.isPresent()) {
            throw new BadRequestException("No existe este turno");

        }
            return turnoRepository.save(turnoActualizado);

    }

}
