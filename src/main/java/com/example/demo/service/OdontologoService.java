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
    OdontologoRepository odontologoService;
    public Odontologo save(Odontologo odontologo) {
        return odontologoService.save(odontologo);
    }

    public Optional<Odontologo> buscar(Integer id) {
        return odontologoService.findById(id);
    }

    public List<Odontologo> buscarTodos(){
        return odontologoService.findAll();
    }

    public boolean eliminar(Integer id){
        try {
            odontologoService.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
