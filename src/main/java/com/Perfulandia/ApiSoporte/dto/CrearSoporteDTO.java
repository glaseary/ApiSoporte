package com.Perfulandia.ApiSoporte.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearSoporteDTO {
    private String asunto;
    private String descripcion;
    private String estado;
    private LocalDate fechaCreacion;
    private Integer idUsuario;
}