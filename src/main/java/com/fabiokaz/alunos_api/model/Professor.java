package com.fabiokaz.alunos_api.model;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "professores")
public class Professor extends Pessoa {

    @OneToMany(mappedBy = "professor")
    private List<Nota> notas;

    public Professor() {
        super();
    }

    public Professor(String nome) {
        super(nome);
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

}
