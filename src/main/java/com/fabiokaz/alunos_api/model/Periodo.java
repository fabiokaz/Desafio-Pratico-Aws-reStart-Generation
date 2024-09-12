package com.fabiokaz.alunos_api.model;

public enum Periodo {
    PRIMEIRO_SEMESTRE(1, "1º Semestre"),
    SEGUNDO_SEMESTRE(2, "2º Semestre");

    public final int codigo;
    public final String descricao;

    Periodo(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
