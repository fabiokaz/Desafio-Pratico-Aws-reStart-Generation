package com.fabiokaz.alunos_api.repository;

import com.fabiokaz.alunos_api.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByAlunoAlunoId(Long alunoId);
}
