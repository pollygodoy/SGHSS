package com.vidaplus.sghss.service;

import com.vidaplus.sghss.dto.ProntuarioDTO;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.model.ProfissionalSaude;
import com.vidaplus.sghss.model.Prontuario;
import com.vidaplus.sghss.repository.PacienteRepository;
import com.vidaplus.sghss.repository.ProfissionalSaudeRepository;
import com.vidaplus.sghss.repository.ProntuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProntuarioService {
    private final ProntuarioRepository prontuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalSaudeRepository profissionalRepository;

    public ProntuarioDTO criar(ProntuarioDTO prontuarioDTO) {
        Paciente paciente = pacienteRepository.findById(prontuarioDTO.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        ProfissionalSaude profissional = profissionalRepository.findById(prontuarioDTO.getProfissionalId())
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        Prontuario prontuario = new Prontuario();
        prontuario.setDescricao(prontuarioDTO.getDescricao());
        prontuario.setDataRegistro(prontuarioDTO.getDataRegistro() != null ? prontuarioDTO.getDataRegistro() : LocalDateTime.now());
        prontuario.setPaciente(paciente);
        prontuario.setProfissional(profissional);
        prontuario.setDiagnostico(prontuarioDTO.getDiagnostico());
        prontuario.setTratamento(prontuarioDTO.getTratamento());
        prontuario.setAtivo(true);

        Prontuario salvo = prontuarioRepository.save(prontuario);
        return converterParaDTO(salvo);
    }

    public ProntuarioDTO buscarPorId(Long id) {
        return prontuarioRepository.findById(id)
                .map(this::converterParaDTO)
                .orElseThrow(() -> new IllegalArgumentException("Prontuário não encontrado"));
    }

    public List<ProntuarioDTO> listar() {
        return prontuarioRepository.findAll()
                .stream()
                .filter(Prontuario::getAtivo)
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ProntuarioDTO> buscarPorPaciente(Long pacienteId) {
        return prontuarioRepository.findByPacienteId(pacienteId)
                .stream()
                .filter(Prontuario::getAtivo)
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ProntuarioDTO atualizar(Long id, ProntuarioDTO prontuarioDTO) {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prontuário não encontrado"));

        prontuario.setDescricao(prontuarioDTO.getDescricao());
        prontuario.setDiagnostico(prontuarioDTO.getDiagnostico());
        prontuario.setTratamento(prontuarioDTO.getTratamento());

        Prontuario atualizado = prontuarioRepository.save(prontuario);
        return converterParaDTO(atualizado);
    }

    public void deletar(Long id) {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prontuário não encontrado"));
        prontuario.setAtivo(false);
        prontuarioRepository.save(prontuario);
    }

    private ProntuarioDTO converterParaDTO(Prontuario prontuario) {
        ProntuarioDTO dto = new ProntuarioDTO();
        dto.setId(prontuario.getId());
        dto.setDescricao(prontuario.getDescricao());
        dto.setDataRegistro(prontuario.getDataRegistro());
        dto.setPacienteId(prontuario.getPaciente().getId());
        dto.setProfissionalId(prontuario.getProfissional().getId());
        dto.setDiagnostico(prontuario.getDiagnostico());
        dto.setTratamento(prontuario.getTratamento());
        dto.setAtivo(prontuario.getAtivo());
        return dto;
    }
}


