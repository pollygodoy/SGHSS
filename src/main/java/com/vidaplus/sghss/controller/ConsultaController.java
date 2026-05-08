package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.ConsultaDTO;
import com.vidaplus.sghss.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@AllArgsConstructor
@Tag(name = "Consultas", description = "Endpoints para gerenciamento de consultas")
public class ConsultaController {
    private final ConsultaService consultaService;

    @PostMapping
    @Operation(summary = "Agendar nova consulta")
    public ResponseEntity<ConsultaDTO> criar(@Valid @RequestBody ConsultaDTO consultaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.criar(consultaDTO));
    }

    @GetMapping
    @Operation(summary = "Listar todas as consultas")
    public ResponseEntity<List<ConsultaDTO>> listar() {
        return ResponseEntity.ok(consultaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar consulta por ID")
    public ResponseEntity<ConsultaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(summary = "Listar consultas de um paciente")
    public ResponseEntity<List<ConsultaDTO>> buscarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(consultaService.buscarPorPaciente(pacienteId));
    }

    @GetMapping("/profissional/{profissionalId}")
    @Operation(summary = "Listar consultas de um profissional")
    public ResponseEntity<List<ConsultaDTO>> buscarPorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(consultaService.buscarPorProfissional(profissionalId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar consulta")
    public ResponseEntity<ConsultaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ConsultaDTO consultaDTO) {
        return ResponseEntity.ok(consultaService.atualizar(id, consultaDTO));
    }

    @PutMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar consulta")
    public ResponseEntity<ConsultaDTO> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.cancelar(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar consulta")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consultaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

