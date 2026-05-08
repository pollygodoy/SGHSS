package com.vidaplus.sghss.service;

import com.vidaplus.sghss.dto.ProfissionalSaudeDTO;
import com.vidaplus.sghss.model.ProfissionalSaude;
import com.vidaplus.sghss.repository.ProfissionalSaudeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfissionalSaudeService {
    private final ProfissionalSaudeRepository profissionalRepository;

    public ProfissionalSaudeDTO criar(ProfissionalSaudeDTO profissionalDTO) {
        if (profissionalRepository.findByCrm(profissionalDTO.getCrm()).isPresent()) {
            throw new IllegalArgumentException("Profissional com este CRM já existe");
        }

        ProfissionalSaude profissional = new ProfissionalSaude();
        profissional.setNome(profissionalDTO.getNome());
        profissional.setEspecialidade(profissionalDTO.getEspecialidade());
        profissional.setCrm(profissionalDTO.getCrm());
        profissional.setEmail(profissionalDTO.getEmail());
        profissional.setAtivo(true);

        ProfissionalSaude salvo = profissionalRepository.save(profissional);
        return converterParaDTO(salvo);
    }

    public ProfissionalSaudeDTO buscarPorId(Long id) {
        return profissionalRepository.findById(id)
                .map(this::converterParaDTO)
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));
    }

    public List<ProfissionalSaudeDTO> listar() {
        return profissionalRepository.findByAtivoTrue()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ProfissionalSaudeDTO> buscarPorEspecialidade(String especialidade) {
        return profissionalRepository.findByEspecialidade(especialidade)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ProfissionalSaudeDTO atualizar(Long id, ProfissionalSaudeDTO profissionalDTO) {
        ProfissionalSaude profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        profissional.setNome(profissionalDTO.getNome());
        profissional.setEspecialidade(profissionalDTO.getEspecialidade());
        profissional.setEmail(profissionalDTO.getEmail());

        ProfissionalSaude atualizado = profissionalRepository.save(profissional);
        return converterParaDTO(atualizado);
    }

    public void deletar(Long id) {
        ProfissionalSaude profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));
        profissional.setAtivo(false);
        profissionalRepository.save(profissional);
    }

    private ProfissionalSaudeDTO converterParaDTO(ProfissionalSaude profissional) {
        return new ProfissionalSaudeDTO(
                profissional.getId(),
                profissional.getNome(),
                profissional.getEspecialidade(),
                profissional.getCrm(),
                profissional.getEmail(),
                profissional.getAtivo()
        );
    }
}

