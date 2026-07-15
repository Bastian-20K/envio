package com.bookpoint.envio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookpoint.envio.dto.AsignarRepartidorDTO;
import com.bookpoint.envio.dto.EnvioDTO;
import com.bookpoint.envio.dto.EstadoEnvioDTO;
import com.bookpoint.envio.model.Envio;
import com.bookpoint.envio.service.EnvioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/envios")
@RequiredArgsConstructor
public class EnvioController {
    private final EnvioService service;

    @PostMapping
    public Envio crear(
            @RequestBody EnvioDTO request) {

        return service.crearEnvio(request);
    }

    @GetMapping
    public List<Envio> listar() {

        return service.listarEnvios();
    }

    @GetMapping("/{id}")
    public Envio buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Envio> buscarPorUsuario(
            @PathVariable Long usuarioId) {

        return service.buscarPorUsuario(usuarioId);
    }

    @PutMapping("/{id}/asignar")
    public Envio asignarRepartidor(
            @PathVariable Long id,
            @RequestBody AsignarRepartidorDTO request) {

        return service.asignarRepartidor(
                id,
                request
        );
    }

    @PutMapping("/{id}/estado")
    public Envio actualizarEstado(
            @PathVariable Long id,
            @RequestBody EstadoEnvioDTO request) {

        return service.actualizarEstado(
                id,
                request
        );
    }
}
