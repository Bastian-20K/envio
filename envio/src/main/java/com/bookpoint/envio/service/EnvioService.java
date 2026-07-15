package com.bookpoint.envio.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bookpoint.envio.dto.AsignarRepartidorDTO;
import com.bookpoint.envio.dto.EnvioDTO;
import com.bookpoint.envio.dto.EstadoEnvioDTO;
import com.bookpoint.envio.model.Envio;
import com.bookpoint.envio.model.EstadoEnvio;
import com.bookpoint.envio.repository.EnvioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnvioService {
    private final EnvioRepository repository;

    public Envio crearEnvio(EnvioDTO request) {

        Envio envio = Envio.builder()
                .pedidoId(request.getPedidoId())
                .usuarioId(request.getUsuarioId())
                .direccion(request.getDireccion())
                .repartidor(null)
                .telefonoContacto(null)
                .estado(EstadoEnvio.PENDIENTE)
                .fechaCreacion(LocalDateTime.now())
                .build();

        return repository.save(envio);
    }

    public List<Envio> listarEnvios() {
        return repository.findAll();
    }

    public Envio buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Envío no encontrado"));
    }

    public List<Envio> buscarPorUsuario(Long usuarioId) {

        return repository.findByUsuarioId(usuarioId);
    }

    public Envio asignarRepartidor(
            Long envioId,
            AsignarRepartidorDTO request) {

        Envio envio = buscarPorId(envioId);

        envio.setRepartidor(request.getRepartidor());
        envio.setTelefonoContacto(request.getTelefonoContacto());
        envio.setEstado(EstadoEnvio.EN_PREPARACION);

        return repository.save(envio);
    }

    public Envio actualizarEstado(
            Long envioId,
            EstadoEnvioDTO request) {

        Envio envio = buscarPorId(envioId);

        envio.setEstado(request.getEstado());

        return repository.save(envio);
    }
}
