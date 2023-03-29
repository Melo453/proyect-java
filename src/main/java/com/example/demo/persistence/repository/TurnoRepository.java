package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TurnoRepository extends JpaRepository<Turno, Integer> {
}
