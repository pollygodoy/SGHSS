package com.vidaplus.sghss.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDTO {
    private Long id;

    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;

    private String status;

    @NotNull(message = "ID do paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "ID do profissional é obrigatório")
    private Long profissionalId;

    private String observacoes;

    private boolean ativo;

    // Para retornar informações completas
    private PacienteDTO paciente;
    private ProfissionalSaudeDTO profissional;
}

