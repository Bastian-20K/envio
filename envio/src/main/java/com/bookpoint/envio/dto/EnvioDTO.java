package com.bookpoint.envio.dto;

import lombok.Data;

@Data
public class EnvioDTO {
    private Long pedidoId;

    private Long usuarioId;

    private String direccion;
}
