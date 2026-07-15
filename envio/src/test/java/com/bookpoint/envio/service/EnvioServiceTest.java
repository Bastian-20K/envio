package com.bookpoint.envio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookpoint.envio.dto.AsignarRepartidorDTO;
import com.bookpoint.envio.dto.EnvioDTO;
import com.bookpoint.envio.dto.EstadoEnvioDTO;
import com.bookpoint.envio.model.Envio;
import com.bookpoint.envio.model.EstadoEnvio;
import com.bookpoint.envio.repository.EnvioRepository;

@ExtendWith(MockitoExtension.class)
public class EnvioServiceTest {

    @Mock
    private EnvioRepository repository;

    @InjectMocks
    private EnvioService service;

    private Envio envioMock() {

        return Envio.builder()
                .idEnvio(1L)
                .pedidoId(10L)
                .usuarioId(5L)
                .direccion("Av. Siempre Viva")
                .repartidor("Pedro")
                .telefonoContacto("999999999")
                .estado(EstadoEnvio.EN_CAMINO)
                .fechaCreacion(LocalDateTime.now())
                .build();
    }

    @Test
    void crearEnvio_deberiaCrearEnvio() {

        EnvioDTO dto = new EnvioDTO();
        dto.setPedidoId(10L);
        dto.setUsuarioId(5L);
        dto.setDireccion("Av. Siempre Viva");

        when(repository.save(org.mockito.ArgumentMatchers.any(Envio.class)))
                .thenReturn(envioMock());

        Envio resultado = service.crearEnvio(dto);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getPedidoId());
    }

    @Test
    void listarEnvios_deberiaRetornarLista() {

        when(repository.findAll())
                .thenReturn(List.of(envioMock()));

        assertEquals(
                1,
                service.listarEnvios().size()
        );
    }

    @Test
    void buscarPorId_deberiaRetornarEnvio() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(envioMock()));

        assertNotNull(service.buscarPorId(1L));
    }

    @Test
    void buscarPorUsuario_deberiaRetornarLista() {

        when(repository.findByUsuarioId(5L))
                .thenReturn(List.of(envioMock()));

        assertEquals(
                1,
                service.buscarPorUsuario(5L).size()
        );
    }

    @Test
    void asignarRepartidor_deberiaActualizarDatos() {

        AsignarRepartidorDTO dto = new AsignarRepartidorDTO();
        dto.setRepartidor("Juan");
        dto.setTelefonoContacto("123456789");

        when(repository.findById(1L))
                .thenReturn(Optional.of(envioMock()));

        when(repository.save(org.mockito.ArgumentMatchers.any(Envio.class)))
                .thenReturn(envioMock());

        assertNotNull(
                service.asignarRepartidor(1L, dto)
        );
    }

    @Test
    void actualizarEstado_deberiaCambiarEstado() {

        EstadoEnvioDTO dto = new EstadoEnvioDTO();
        dto.setEstado(EstadoEnvio.ENTREGADO);

        when(repository.findById(1L))
                .thenReturn(Optional.of(envioMock()));

        when(repository.save(org.mockito.ArgumentMatchers.any(Envio.class)))
                .thenReturn(envioMock());

        assertNotNull(
                service.actualizarEstado(1L, dto)
        );
    }

}