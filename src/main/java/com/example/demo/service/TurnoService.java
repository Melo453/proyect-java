package com.example.demo.service;

import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.entities.Turno;
import com.example.demo.persistence.repository.OdontologoRepository;
import com.example.demo.persistence.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Turno> buscarTodos(){
        return turnoRepository.findAll();
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
