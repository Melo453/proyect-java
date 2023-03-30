package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface TurnoRepository extends JpaRepository<Turno, Integer> {
    @Query("SELECT t FROM Turno t WHERE t.paciente = :paciente")
    List<Turno> findByPaciente(@Param("paciente") Optional<Paciente> paciente);

    @Query("SELECT t FROM Turno t WHERE t.odontologo = :odontologo")
    List<Turno> findByOdontologo(@Param("odontologo") Optional<Odontologo> odontologo);
}
