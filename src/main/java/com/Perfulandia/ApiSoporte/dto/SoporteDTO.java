package com.Perfulandia.ApiSoporte.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoporteDTO extends RepresentationModel<SoporteDTO> {
    private Integer id;
    private String asunto;
    private String descripcion;
    private String estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaCierre;

    private String nombreUsuario;
    private String nombreRol;
}