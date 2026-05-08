package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.ProfissionalSaudeDTO;
import com.vidaplus.sghss.service.ProfissionalSaudeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
@AllArgsConstructor
@Tag(name = "Profissionais de Saúde", description = "Endpoints para gerenciamento de profissionais")
public class ProfissionalSaudeController {
    private final ProfissionalSaudeService profissionalService;

    @PostMapping
    @Operation(summary = "Criar novo profissional")
    public ResponseEntity<ProfissionalSaudeDTO> criar(@Valid @RequestBody ProfissionalSaudeDTO profissionalDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalService.criar(profissionalDTO));
    }

    @GetMapping
    @Operation(summary = "Listar todos os profissionais")
    public ResponseEntity<List<ProfissionalSaudeDTO>> listar() {
        return ResponseEntity.ok(profissionalService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar profissional por ID")
    public ResponseEntity<ProfissionalSaudeDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(profissionalService.buscarPorId(id));
    }

    @GetMapping("/especialidade/{especialidade}")
    @Operation(summary = "Buscar profissionais por especialidade")
    public ResponseEntity<List<ProfissionalSaudeDTO>> buscarPorEspecialidade(@PathVariable String especialidade) {
        return ResponseEntity.ok(profissionalService.buscarPorEspecialidade(especialidade));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar profissional")
    public ResponseEntity<ProfissionalSaudeDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProfissionalSaudeDTO profissionalDTO) {
        return ResponseEntity.ok(profissionalService.atualizar(id, profissionalDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar profissional")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        profissionalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

