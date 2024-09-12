package com.fabiokaz.alunos_api.model;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "alunos")
public class Aluno extends Pessoa {

    @Column(name = "aluno_id",  nullable = false)
    private Long alunoId;

    @OneToMany(mappedBy = "aluno")
    private List<Nota> notas;

    public Aluno() {
        super();
    }

    public Aluno(String nome, Long alunoId) {
        super(nome);
        this.alunoId = alunoId;
    }

    public Aluno(String nome) {
        super(nome);
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
