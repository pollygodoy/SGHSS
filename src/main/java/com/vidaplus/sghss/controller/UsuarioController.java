package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.LoginDTO;
import com.vidaplus.sghss.dto.UsuarioDTO;
import com.vidaplus.sghss.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para login e registro de usuários")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    @Operation(summary = "Registrar novo usuário")
    public ResponseEntity<UsuarioDTO> registrar(@Valid @RequestBody UsuarioDTO usuarioDTO,
                                                 @RequestParam String senha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrar(usuarioDTO, senha));
    }

    @PostMapping("/login")
    @Operation(summary = "Realizar login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(usuarioService.login(loginDTO));
    }

    @GetMapping("/usuario/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping("/usuario/email/{email}")
    @Operation(summary = "Buscar usuário por email")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.buscarPorEmail(email));
    }
}

