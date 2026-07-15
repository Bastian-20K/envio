package com.bookpoint.envio.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookpoint.envio.model.Envio;
import com.bookpoint.envio.service.EnvioService;

@WebMvcTest(EnvioController.class)
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EnvioService service;

    @Test
    void listar_deberiaRetornar200() throws Exception {

        when(service.listarEnvios())
                .thenReturn(List.of(new Envio()));

        mockMvc.perform(get("/api/v1/envios"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorId_deberiaRetornar200() throws Exception {

        when(service.buscarPorId(1L))
                .thenReturn(new Envio());

        mockMvc.perform(get("/api/v1/envios/1"))
                .andExpect(status().isOk());
    }

    @Test
    void crear_deberiaRetornar200() throws Exception {

        when(service.crearEnvio(org.mockito.ArgumentMatchers.any()))
                .thenReturn(new Envio());

        mockMvc.perform(post("/api/v1/envios")
                .contentType("application/json")
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void asignar_deberiaRetornar200() throws Exception {

        when(service.asignarRepartidor(
                org.mockito.ArgumentMatchers.eq(1L),
                org.mockito.ArgumentMatchers.any()))
                .thenReturn(new Envio());

        mockMvc.perform(put("/api/v1/envios/1/asignar")
                .contentType("application/json")
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void estado_deberiaRetornar200() throws Exception {

        when(service.actualizarEstado(
                org.mockito.ArgumentMatchers.eq(1L),
                org.mockito.ArgumentMatchers.any()))
                .thenReturn(new Envio());

        mockMvc.perform(put("/api/v1/envios/1/estado")
                .contentType("application/json")
                .content("{}"))
                .andExpect(status().isOk());
    }

}