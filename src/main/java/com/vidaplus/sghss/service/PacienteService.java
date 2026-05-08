package com.vidaplus.sghss.service;

import com.vidaplus.sghss.dto.PacienteDTO;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteDTO criar(PacienteDTO pacienteDTO) {
        if (pacienteRepository.findByCpf(pacienteDTO.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Paciente com este CPF já existe");
        }

        Paciente paciente = new Paciente();
        paciente.setNome(pacienteDTO.getNome());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setTelefone(pacienteDTO.getTelefone());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        paciente.setAtivo(true);

        Paciente salvo = pacienteRepository.save(paciente);
        return converterParaDTO(salvo);
    }

    public PacienteDTO buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .map(this::converterParaDTO)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
    }

    public List<PacienteDTO> listar() {
        return pacienteRepository.findByAtivoTrue()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public PacienteDTO atualizar(Long id, PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        paciente.setNome(pacienteDTO.getNome());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setTelefone(pacienteDTO.getTelefone());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());

        Paciente atualizado = pacienteRepository.save(paciente);
        return converterParaDTO(atualizado);
    }

    public void deletar(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        paciente.setAtivo(false);
        pacienteRepository.save(paciente);
    }

    private PacienteDTO converterParaDTO(Paciente paciente) {
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getDataNascimento(),
                paciente.getAtivo()
        );
    }
}

