package com.Perfulandia.ApiSoporte.services;

import com.Perfulandia.ApiSoporte.dto.CrearSoporteDTO;
import com.Perfulandia.ApiSoporte.dto.SoporteDTO;
import com.Perfulandia.ApiSoporte.models.Soporte;
import com.Perfulandia.ApiSoporte.models.Usuario;
import com.Perfulandia.ApiSoporte.repository.SoporteRepository;
import com.Perfulandia.ApiSoporte.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private SoporteDTO toDTO(Soporte soporte) {
        return new SoporteDTO(
                soporte.getId(),
                soporte.getAsunto(),
                soporte.getDescripcion(),
                soporte.getEstado(),
                soporte.getFechaCreacion(),
                soporte.getFechaCierre(),
                soporte.getUsuario().getNombreUsuario(),
                soporte.getUsuario().getRol().getNombreRol()
        );
    }

    private Soporte toEntity(SoporteDTO dto, Usuario usuario) {
        return new Soporte(
                dto.getId(),
                dto.getAsunto(),
                dto.getDescripcion(),
                dto.getEstado(),
                dto.getFechaCreacion(),
                dto.getFechaCierre(),
                usuario
        );
    }

    public SoporteDTO crear(CrearSoporteDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + dto.getIdUsuario() + " no encontrado"));

        Soporte soporte = new Soporte(
                null,
                dto.getAsunto(),
                dto.getDescripcion(),
                dto.getEstado(),
                dto.getFechaCreacion(),
                dto.getEstado().equalsIgnoreCase("Resuelto") ? LocalDate.now() : null, // Fecha de cierre automática si está resuelto
                usuario
        );

        return toDTO(soporteRepository.save(soporte));
    }

    public List<SoporteDTO> listar() {
        return soporteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SoporteDTO obtenerPorId(Integer id) {
        Soporte soporte = soporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket de soporte no encontrado"));
        return toDTO(soporte);
    }

    public SoporteDTO actualizar(Integer id, SoporteDTO dto) {
        Soporte existente = soporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket de soporte no encontrado"));

        Soporte actualizado = toEntity(dto, existente.getUsuario());
        return toDTO(soporteRepository.save(actualizado));
    }

    public void eliminar(Integer id) {
        if (!soporteRepository.existsById(id)) {
            throw new RuntimeException("El ticket de soporte con ID " + id + " no existe");
        }
        soporteRepository.deleteById(id);
    }
}