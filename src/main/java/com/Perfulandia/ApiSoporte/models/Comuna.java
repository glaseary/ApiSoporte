package com.Perfulandia.ApiSoporte.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COMUNA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comuna")
    private Integer id;

    @Column(name = "nombre_comuna", nullable = false, length = 40)
    private String nombreComuna;

    @ManyToOne
    @JoinColumn(name = "PROVINCIA_id_provincia", nullable = false)
    private Provincia provincia;
}