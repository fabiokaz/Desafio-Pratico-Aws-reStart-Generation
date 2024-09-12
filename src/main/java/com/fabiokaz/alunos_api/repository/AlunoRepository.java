package com.fabiokaz.alunos_api.repository;

import com.fabiokaz.alunos_api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByAlunoId(Long alunoId);
}
