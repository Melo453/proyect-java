package com.example.demo;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.persistence.entities.Domicilio;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.service.PacienteService;
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
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;


    public void cargarDataSet() throws BadRequestException {
        Domicilio domicilio = new Domicilio("Av Santa fe", 444, "CABA", "Buenos Aires");
        Paciente p = pacienteService.save(new Paciente("Santiago", "Paz", 22222222, LocalDate.of(2000, 1, 1), domicilio));
        Domicilio domicilio1 = new Domicilio("Av Avellaneda", 333, "CABA", "Buenos Aires");
        Paciente p1 = pacienteService.save(new Paciente("Micaela", "Perez", 99999999, LocalDate.of(2000, 1, 1), domicilio1));

    }

    @Test
    public void agregarYBuscarPacienteTest()  throws BadRequestException{
        this.cargarDataSet();
        Domicilio domicilio = new Domicilio("Calle", 22222222, "Temperley", "Buenos Aires");
        Paciente p = pacienteService.save(new Paciente("Tomas", "Pereyra", 33333333, LocalDate.of(2000, 1, 1), domicilio));

        Assert.assertNotNull(pacienteService.buscar(p.getId()));
        System.out.println(p);
    }



    @Test
    public void traerTodos() throws BadRequestException {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() >= 1);
        System.out.println(pacientes);
    }
}
