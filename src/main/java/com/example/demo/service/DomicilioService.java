package com.example.demo.service;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;
@Service
public class DomicilioService {

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
