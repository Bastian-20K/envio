package com.bookpoint.envio.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnvio;

    private Long pedidoId;

    private Long usuarioId;

    private String direccion;

    private String repartidor;

    private String telefonoContacto;

    @Enumerated(EnumType.STRING)
    private EstadoEnvio estado;

    private LocalDateTime fechaCreacion;

}
