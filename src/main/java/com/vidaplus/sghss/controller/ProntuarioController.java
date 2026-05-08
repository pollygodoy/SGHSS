package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.ProntuarioDTO;
import com.vidaplus.sghss.service.ProntuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prontuarios")
@AllArgsConstructor
@Tag(name = "Prontuários", description = "Endpoints para gerenciamento de prontuários")
public class ProntuarioController {
    private final ProntuarioService prontuarioService;

    @PostMapping
    @Operation(summary = "Criar novo prontuário")
    public ResponseEntity<ProntuarioDTO> criar(@Valid @RequestBody ProntuarioDTO prontuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prontuarioService.criar(prontuarioDTO));
    }

    @GetMapping
    @Operation(summary = "Listar todos os prontuários")
    public ResponseEntity<List<ProntuarioDTO>> listar() {
        return ResponseEntity.ok(prontuarioService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar prontuário por ID")
    public ResponseEntity<ProntuarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prontuarioService.buscarPorId(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(summary = "Listar prontuários de um paciente")
    public ResponseEntity<List<ProntuarioDTO>> buscarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(prontuarioService.buscarPorPaciente(pacienteId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar prontuário")
    public ResponseEntity<ProntuarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProntuarioDTO prontuarioDTO) {
        return ResponseEntity.ok(prontuarioService.atualizar(id, prontuarioDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar prontuário")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        prontuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

