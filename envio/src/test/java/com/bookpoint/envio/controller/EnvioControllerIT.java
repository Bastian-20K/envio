package com.bookpoint.envio.controller;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookpoint.envio.model.Envio;
import com.bookpoint.envio.model.EstadoEnvio;
import com.bookpoint.envio.repository.EnvioRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class EnvioControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnvioRepository repository;

    private Long envioId;

    @BeforeEach
    void setup() {

        repository.deleteAll();

        Envio envio = repository.save(
                Envio.builder()
                        .pedidoId(1L)
                        .usuarioId(1L)
                        .direccion("Direccion prueba")
                        .estado(EstadoEnvio.PENDIENTE)
                        .fechaCreacion(LocalDateTime.now())
                        .build()
        );

        envioId = envio.getIdEnvio();
    }

    @Test
    void listar_deberiaRetornar200() throws Exception {

        mockMvc.perform(
                get("/api/v1/envios")
        )
        .andExpect(
                status().isOk()
        );
    }

    @Test
    void buscarPorId_deberiaRetornar200() throws Exception {

        mockMvc.perform(
                get("/api/v1/envios/" + envioId)
        )
        .andExpect(
                status().isOk()
        );
    }

    @Test
    void buscarPorUsuario_deberiaRetornar200() throws Exception {

        mockMvc.perform(
                get("/api/v1/envios/usuario/1")
        )
        .andExpect(
                status().isOk()
        );
    }

    @Test
    void crear_deberiaRetornar200() throws Exception {

        mockMvc.perform(
                post("/api/v1/envios")
                        .contentType("application/json")
                        .content("""
                                {
                                  "pedidoId":1,
                                  "usuarioId":1,
                                  "direccion":"Direccion prueba"
                                }
                                """)
        )
        .andExpect(
                status().isOk()
        );
    }

    @Test
    void asignarRepartidor_deberiaRetornar200() throws Exception {

        mockMvc.perform(
                put("/api/v1/envios/" + envioId + "/asignar")
                        .contentType("application/json")
                        .content("""
                                {
                                  "repartidor":"Pedro Soto",
                                  "telefonoContacto":"987654321"
                                }
                                """)
        )
        .andExpect(
                status().isOk()
        );
    }

    @Test
    void actualizarEstado_deberiaRetornar200() throws Exception {

        mockMvc.perform(
                put("/api/v1/envios/" + envioId + "/estado")
                        .contentType("application/json")
                        .content("""
                                {
                                  "estado":"EN_CAMINO"
                                }
                                """)
        )
        .andExpect(
                status().isOk()
        );
    }

}