package com.Perfulandia.ApiSoporte.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROVINCIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provincia")
    private Integer id;

    @Column(name = "nombre_provincia", nullable = false, length = 30)
    private String nombreProvincia;
}