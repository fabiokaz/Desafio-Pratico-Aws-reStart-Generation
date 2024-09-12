package com.fabiokaz.alunos_api.repository;

import com.fabiokaz.alunos_api.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    Optional<Sala> findByNumeroSala(int numeroSala);
}
