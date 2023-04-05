package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {

    List<Odontologo> findByMatricula(Integer matricula);

}
