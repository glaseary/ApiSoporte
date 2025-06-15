package com.Perfulandia.ApiSoporte.controllers;

import com.Perfulandia.ApiSoporte.dto.CrearSoporteDTO;
import com.Perfulandia.ApiSoporte.dto.SoporteDTO;
import com.Perfulandia.ApiSoporte.services.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @PostMapping
    public ResponseEntity<SoporteDTO> crear(@RequestBody CrearSoporteDTO dto) {
        return ResponseEntity.ok(soporteService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<SoporteDTO>> listar() {
        return ResponseEntity.ok(soporteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoporteDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(soporteService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoporteDTO> actualizar(@PathVariable Integer id, @RequestBody SoporteDTO dto) {
        return ResponseEntity.ok(soporteService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        soporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}