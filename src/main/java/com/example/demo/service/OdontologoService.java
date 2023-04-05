package com.example.demo.service;

import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.repository.OdontologoRepository;
import com.example.demo.persistence.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OdontologoService {

    @Autowired
    OdontologoRepository odontologoRepository;
    public Odontologo save(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscar(Integer id) {
        return odontologoRepository.findById(id);
    }


    public Optional<Odontologo> buscarPorMatricula(Integer matricula) {
        List<Odontologo> odontologos = odontologoRepository.findByMatricula(matricula);
        if (odontologos.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(odontologos.get(0));
        }
    }

    public List<Odontologo> buscarTodos(){
        return odontologoRepository.findAll();
    }

    public boolean eliminar(Integer id){
        try {
            odontologoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
