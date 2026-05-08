package com.vidaplus.sghss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profissionais_saude", uniqueConstraints = @UniqueConstraint(columnNames = "crm"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Especialidade é obrigatória")
    @Column(nullable = false)
    private String especialidade;

    @NotBlank(message = "CRM é obrigatório")
    @Column(nullable = false, unique = true)
    private String crm;

    @Email(message = "Email inválido")
    @Column(unique = true)
    private String email;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
}

