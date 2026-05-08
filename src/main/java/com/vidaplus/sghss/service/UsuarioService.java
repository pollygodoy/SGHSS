package com.vidaplus.sghss.service;

import com.vidaplus.sghss.config.JwtUtil;
import com.vidaplus.sghss.dto.LoginDTO;
import com.vidaplus.sghss.dto.UsuarioDTO;
import com.vidaplus.sghss.model.Usuario;
import com.vidaplus.sghss.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioDTO registrar(UsuarioDTO usuarioDTO, String senha) {
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new IllegalArgumentException("Email já está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(senha)); // Criptografia com BCrypt
        usuario.setPerfil(usuarioDTO.getPerfil() != null ? usuarioDTO.getPerfil() : "PACIENTE");
        usuario.setAtivo(true);

        Usuario salvo = usuarioRepository.save(usuario);
        return converterParaDTO(salvo);
    }

    public Map<String, Object> login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha incorretos"));

        if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
            throw new IllegalArgumentException("Email ou senha incorretos");
        }

        if (!usuario.getAtivo()) {
            throw new IllegalArgumentException("Usuário inativo");
        }

        String token = jwtUtil.generateToken(usuario.getId(), usuario.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("id", usuario.getId());
        response.put("nome", usuario.getNome());
        response.put("email", usuario.getEmail());
        response.put("perfil", usuario.getPerfil());
        response.put("token", token);

        return response;
    }

    public UsuarioDTO buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::converterParaDTO)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    public UsuarioDTO buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(this::converterParaDTO)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    private UsuarioDTO converterParaDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil(),
                usuario.getAtivo()
        );
    }
}

