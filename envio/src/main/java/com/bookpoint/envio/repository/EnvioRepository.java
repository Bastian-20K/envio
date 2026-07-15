package com.bookpoint.envio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookpoint.envio.model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Long> {

    List<Envio> findByUsuarioId(Long usuarioId);

}