package com.example.demo;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.service.OdontologoService;
import org.junit.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTest {


    @Autowired
    private OdontologoService odontologoService;

    @Test
    public void cargarDataSet() throws BadRequestException{
        this.odontologoService.save(new Odontologo("Fabrizio", "Meloni", 348971960));
        System.out.println(odontologoService.buscarTodos());

    }

    @Test
    public void agregarOdontologo() throws BadRequestException{
        this.cargarDataSet();
        Odontologo odontologo = odontologoService.save(new Odontologo("Fabrizio", "Meloni", 348971960));
        Odontologo odontologo2 = odontologoService.save(new Odontologo("Fabrizio", "Meloni", 45555555));
        Assert.assertTrue(odontologo.getId() != null);
        System.out.println(odontologoService.buscarTodos());

    }


    @Test
    public void traerTodos() throws BadRequestException{
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() >= 1);
        System.out.println(odontologos);
    }

}
