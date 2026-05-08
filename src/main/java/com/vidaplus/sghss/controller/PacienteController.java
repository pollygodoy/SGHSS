package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.PacienteDTO;
import com.vidaplus.sghss.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@AllArgsConstructor
@Tag(name = "Pacientes", description = "Endpoints para gerenciamento de pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @PostMapping
    @Operation(summary = "Criar novo paciente")
    public ResponseEntity<PacienteDTO> criar(@Valid @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.criar(pacienteDTO));
    }

    @GetMapping
    @Operation(summary = "Listar todos os pacientes")
    public ResponseEntity<List<PacienteDTO>> listar() {
        return ResponseEntity.ok(pacienteService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por ID")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar paciente")
    public ResponseEntity<PacienteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.atualizar(id, pacienteDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar paciente")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

