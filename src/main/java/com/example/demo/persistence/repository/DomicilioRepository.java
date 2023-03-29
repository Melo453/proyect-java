package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {

    @Query("SELECT d FROM Domicilio d WHERE d.calle = ?1 AND d.numero = ?2 AND d.localidad = ?3 AND d.provincia = ?4")
    Domicilio findByAdressExists(String calle, int numero, String localidad, String provincia);
}
