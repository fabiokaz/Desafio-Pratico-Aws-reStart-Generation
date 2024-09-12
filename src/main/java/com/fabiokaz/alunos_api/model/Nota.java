package com.fabiokaz.alunos_api.model;

import jakarta.persistence.*;

@Entity(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Periodo periodo;

    private Double nota;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sala_id")
    private Sala sala;

    public Nota() {
    }

    public Nota(Periodo periodo, Double nota, Sala sala) {
        this.periodo = periodo;
        this.nota = nota;
        this.sala = sala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}

