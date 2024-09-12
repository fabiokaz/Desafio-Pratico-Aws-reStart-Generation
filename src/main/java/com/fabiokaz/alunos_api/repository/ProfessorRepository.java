package com.fabiokaz.alunos_api.repository;

import com.fabiokaz.alunos_api.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByNome(String nome);
}
