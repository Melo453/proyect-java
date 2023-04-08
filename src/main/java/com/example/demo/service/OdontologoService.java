package com.example.demo.service;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.repository.OdontologoRepository;
import com.example.demo.persistence.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;
@Service
public class OdontologoService {

    @Autowired
    OdontologoRepository odontologoRepository;
    public Odontologo save(Odontologo odontologo) throws BadRequestException {
        if (odontologo == null){
            throw new BadRequestException("El odontologo no puede ser nulo");
        }
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscar(Integer id) throws BadRequestException{
        Optional<Odontologo> odontologos = odontologoRepository.findById(id);
        if (odontologos.isEmpty()){
            throw new BadRequestException("El odontologo no existe");
        }
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

    public List<Odontologo> buscarTodos() throws BadRequestException{
        List<Odontologo> odontologos = odontologoRepository.findAll();
        if (odontologos.isEmpty()) {
            throw new BadRequestException("No se encontraron odontólogos");
        }
        return odontologoRepository.findAll();
    }

    public boolean eliminar(Integer id) throws BadRequestException {
        Optional<Odontologo> odontologoExistente = buscar(id);

        if(!odontologoExistente.isPresent()){
            throw new BadRequestException("No existe el odontologo con id " + id);
        }
        odontologoRepository.deleteById(id);
        return true;
    }

    public Odontologo actualizarOdontologo(Odontologo odontologoActualizado) throws BadRequestException{
        Optional<Odontologo> odontologoExistente = odontologoRepository.findById(odontologoActualizado.getId());
        if (!odontologoExistente.isPresent()) {
            throw new BadRequestException("No existe este odontologo");

        }
        return odontologoRepository.save(odontologoActualizado);
    }
}
