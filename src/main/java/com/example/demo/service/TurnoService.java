package com.example.demo.service;

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

    public Turno save(Turno turno) {

        return turnoRepository.save(turno);
    }

    public Optional<Turno> buscar(Integer id) {
        return turnoRepository.findById(id);
    }

    public List<Turno> buscarPorFecha(LocalDate fecha) {
        return turnoRepository.findByFechaTurno(fecha);
    }
    public List<Turno> buscarTodos(){
        return turnoRepository.findAll();
    }

    public List<Turno> buscarPorPaciente(Optional<Paciente> paciente) {
        return turnoRepository.findByPaciente(paciente);
    }

    public List<Turno> buscarPorOdontolgoo(Optional<Odontologo> odontologo) {
        return turnoRepository.findByOdontologo(odontologo);
    }

    public boolean eliminar(Integer id){
        try {
            turnoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
