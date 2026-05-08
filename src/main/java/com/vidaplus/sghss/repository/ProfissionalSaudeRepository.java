package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.model.ProfissionalSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaude, Long> {
    Optional<ProfissionalSaude> findByCrm(String crm);
    List<ProfissionalSaude> findByEspecialidade(String especialidade);
    List<ProfissionalSaude> findByAtivoTrue();
    Optional<ProfissionalSaude> findByEmail(String email);
}

