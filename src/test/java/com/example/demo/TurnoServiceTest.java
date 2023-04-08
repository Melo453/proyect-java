package com.example.demo;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.persistence.entities.Domicilio;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.persistence.entities.Turno;
import com.example.demo.service.OdontologoService;
import com.example.demo.service.PacienteService;
import com.example.demo.service.TurnoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTest {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    @Test
    public void cargarDataSet()  throws BadRequestException {
        Domicilio domicilio = new Domicilio("Av Santa fe", 5252, "CABA", "Buenos Aires");
        Paciente p = pacienteService.save(new Paciente("Santiago", "Paz", 22222222, LocalDate.of(2000, 1, 1), domicilio));
        this.odontologoService.save(new Odontologo("Fabrizio", "Meloni", 348971960));
        System.out.println(pacienteService.buscarTodos());

    }
    @Test
    public void altaTurnoTest() throws BadRequestException{
        this.cargarDataSet();
        turnoService.save(new Turno(LocalDate.of(2000, 1, 1),odontologoService.buscar(1).get(),pacienteService.buscar(1).get()));
        Assert.assertNotNull(turnoService.buscar(1));
        System.out.println(pacienteService.buscarTodos());

    }
    @Test
    public void buscarTurnoTest() throws BadRequestException{
        Assert.assertNotNull(turnoService.buscar(1));
        System.out.println(pacienteService.buscar(1));

    }
}
