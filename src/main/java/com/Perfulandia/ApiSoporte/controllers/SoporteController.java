package com.Perfulandia.ApiSoporte.controllers;

import com.Perfulandia.ApiSoporte.dto.CrearSoporteDTO;
import com.Perfulandia.ApiSoporte.dto.SoporteDTO;
import com.Perfulandia.ApiSoporte.services.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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

    @GetMapping("/hateoas/{id}")
    public SoporteDTO obtenerHATEOAS(@PathVariable Integer id) {
        SoporteDTO dto = soporteService.obtenerPorId(id);

        // La ruta base en el Gateway para este recurso
        String gatewayUrl = "http://localhost:8888/api/proxy/soporte";

        // Link a sí mismo
        dto.add(Link.of(gatewayUrl + "/hateoas/" + id).withSelfRel());

        // Link a la lista de todos los tickets
        dto.add(Link.of(gatewayUrl + "/hateoas").withRel("todos-los-soportes"));

        // Link para eliminar el ticket
        dto.add(Link.of(gatewayUrl + "/" + id).withRel("eliminar").withType("DELETE"));

        // Link para actualizar el ticket
        dto.add(Link.of(gatewayUrl + "/" + id).withRel("actualizar").withType("PUT"));

        return dto;
    }

    /**
     * Obtiene todos los tickets de soporte y añade enlaces HATEOAS a cada uno.
     */
    @GetMapping("/hateoas")
    public List<SoporteDTO> listarHATEOAS() {
        List<SoporteDTO> soportes = soporteService.listar();
        String gatewayUrl = "http://localhost:8888/api/proxy/soporte";

        for (SoporteDTO dto : soportes) {
            // Link a los detalles de este ticket
            dto.add(Link.of(gatewayUrl + "/hateoas/" + dto.getId()).withSelfRel());

            // Link para crear un nuevo ticket
            dto.add(Link.of(gatewayUrl).withRel("crear-nuevo-soporte").withType("POST"));
            dto.add(Link.of(gatewayUrl).withRel("editar-soporte").withType("PUT"));
            dto.add(Link.of(gatewayUrl).withRel("eliminar-soporte").withType("DELETE"));
        }

        return soportes;
    }
}