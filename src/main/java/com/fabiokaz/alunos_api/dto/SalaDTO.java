package com.fabiokaz.alunos_api.dto;

import jakarta.validation.constraints.NotNull;

public class SalaDTO {

    private Integer numeroSala;

    public SalaDTO(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    @NotNull(message = "O número da sala é obrigatório.")
    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }
}
