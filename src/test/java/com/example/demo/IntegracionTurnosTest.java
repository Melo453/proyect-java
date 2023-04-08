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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void cargarDataSet() throws BadRequestException {
        Domicilio domicilio = new Domicilio("Av Santa fe", 4444, "CABA", "Buenos Aires");
        Domicilio domicilio2 = new Domicilio("Av Santa fe", 555, "CABA", "Buenos Aires");
        Paciente p = pacienteService.save(new Paciente("Santiago", "Paz", 22222222, LocalDate.of(2000, 1, 1), domicilio));
        Paciente p2 = pacienteService.save(new Paciente("Santiago", "Paz", 44444444, LocalDate.of(2000, 1, 1), domicilio2));
        this.odontologoService.save(new Odontologo("Fabrizio", "Meloni", 348971960));
        this.odontologoService.save(new Odontologo("Fabrizio", "Meloni", 444444));
        turnoService.save(new Turno(LocalDate.of(2000, 1, 1),odontologoService.buscar(1).get(),pacienteService.buscar(1).get()));
        turnoService.save(new Turno(LocalDate.of(2000, 1, 1),odontologoService.buscar(2).get(),pacienteService.buscar(2).get()));

    }
    @Test
    public void listarTurnos() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void eliminarTurno() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.delete("/turnos/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
