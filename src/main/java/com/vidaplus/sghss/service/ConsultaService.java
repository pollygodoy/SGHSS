package com.vidaplus.sghss.service;

import com.vidaplus.sghss.dto.ConsultaDTO;
import com.vidaplus.sghss.dto.PacienteDTO;
import com.vidaplus.sghss.dto.ProfissionalSaudeDTO;
import com.vidaplus.sghss.model.Consulta;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.model.ProfissionalSaude;
import com.vidaplus.sghss.repository.ConsultaRepository;
import com.vidaplus.sghss.repository.PacienteRepository;
import com.vidaplus.sghss.repository.ProfissionalSaudeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalSaudeRepository profissionalRepository;

    public ConsultaDTO criar(ConsultaDTO consultaDTO) {
        Paciente paciente = pacienteRepository.findById(consultaDTO.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        ProfissionalSaude profissional = profissionalRepository.findById(consultaDTO.getProfissionalId())
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setDataHora(consultaDTO.getDataHora());
        consulta.setStatus("AGENDADA");
        consulta.setPaciente(paciente);
        consulta.setProfissional(profissional);
        consulta.setObservacoes(consultaDTO.getObservacoes());
        consulta.setAtivo(true);

        Consulta salva = consultaRepository.save(consulta);
        return converterParaDTO(salva);
    }

    public ConsultaDTO buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .map(this::converterParaDTO)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
    }

    public List<ConsultaDTO> listar() {
        return consultaRepository.findAll()
                .stream()
                .filter(Consulta::getAtivo)
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ConsultaDTO> buscarPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId)
                .stream()
                .filter(Consulta::getAtivo)
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ConsultaDTO> buscarPorProfissional(Long profissionalId) {
        return consultaRepository.findByProfissionalId(profissionalId)
                .stream()
                .filter(Consulta::getAtivo)
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ConsultaDTO atualizar(Long id, ConsultaDTO consultaDTO) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));

        consulta.setDataHora(consultaDTO.getDataHora());
        consulta.setObservacoes(consultaDTO.getObservacoes());

        Consulta atualizada = consultaRepository.save(consulta);
        return converterParaDTO(atualizada);
    }

    public ConsultaDTO cancelar(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));

        consulta.setStatus("CANCELADA");
        Consulta cancelada = consultaRepository.save(consulta);
        return converterParaDTO(cancelada);
    }

    public void deletar(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
        consulta.setAtivo(false);
        consultaRepository.save(consulta);
    }

    private ConsultaDTO converterParaDTO(Consulta consulta) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setId(consulta.getId());
        dto.setDataHora(consulta.getDataHora());
        dto.setStatus(consulta.getStatus());
        dto.setPacienteId(consulta.getPaciente().getId());
        dto.setProfissionalId(consulta.getProfissional().getId());
        dto.setObservacoes(consulta.getObservacoes());
        dto.setAtivo(consulta.getAtivo());
        return dto;
    }
}

