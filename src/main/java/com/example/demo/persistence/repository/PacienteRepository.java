package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
