package com.fabiokaz.alunos_api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sala_id", nullable = false)
    private Integer numeroSala;

    @OneToMany(mappedBy = "sala")
    private List<Nota> notas;

    public Sala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Sala() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
