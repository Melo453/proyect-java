package com.example.demo;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.persistence.entities.Domicilio;
import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.persistence.entities.Paciente;
import com.example.demo.service.OdontologoService;
import com.example.demo.util.Jsons;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionOdontologoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OdontologoService odontologoService;
    @Test
    public void registrarOdontologo() throws Exception {
        Odontologo od = new Odontologo("Fabrizio", "Meloni", 348971960);
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(od)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void cargarDataSet() throws BadRequestException {
        Odontologo p = odontologoService.save(new Odontologo("Fabrizio", "Meloni", 348971960));
        Odontologo p2 = odontologoService.save(new Odontologo("Fabrizio", "Meloni", 444444));
        System.out.println(p);
        System.out.println(p2);
    }

    @Test
    public void listarOdontologo() throws Exception {
        this.registrarOdontologo();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", 2).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void eliminarOdontologo () throws Exception {
        this.registrarOdontologo();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
