package com.fabiokaz.alunos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class AlunoDTO {

    // alunoId, nome, idade, notaPrimeiroSemestre, notaSegundoSemest, nomeProfessor, numeroSala

    @NotNull(message = "Id do aluno é obrigatório")
    @Positive(message = "O id do aluno deve ser positivo")
    @Digits(integer = 10, fraction = 0, message = "O id do aluno deve ter no máximo 10 dígitos")
    @Min(value = 1L, message = "O id do aluno deve ser maior ou igual a 1")
    @Max(value = 9999999999L, message = "O id do aluno deve ser menor ou igual a 9999999999")
    @Schema(example = "1")
    private Long alunoId;

    @NotBlank(message = "Nome do aluno é obrigatório")
    @Size(min = 3, max = 50, message = "O nome do aluno deve ter entre 3 e 50 caracteres")
    @Schema(example = "Aluno 1")
    private String nome;

    @NotNull(message = "A idade não pode ser nula")
    @Min(value = 5, message = "A idade mínima é 5")
    @Max(value = 120, message = "A idade máxima é 120")
    @Schema(example = "23")
    private Integer idade;

    @NotNull(message = "A nota do primeiro semestre é obrigatória")
    @DecimalMin(value = "0.0", message = "A nota do primeiro semestre deve ser maior ou igual a 0.0")
    @DecimalMax(value = "10.0", message = "A nota do primeiro semestre deve ser menor ou igual a 10.0")
    @Digits(integer = 2, fraction = 1, message = "O valor deve ter no máximo 2 dígitos inteiros e 1 dígito decimal")
    @Schema(example = "9.50")
    private Double notaPrimeiroSemestre;

    @NotNull(message = "A nota do segundo semestre é obrigatória")
    @DecimalMin(value = "0.0", message = "A nota do segundo semestre deve ser maior ou igual a 0.0")
    @DecimalMax(value = "10.0", message = "A nota do segundo semestre deve ser menor ou igual a 10.0")
    @Digits(integer = 2, fraction = 1, message = "O valor deve ter no máximo 2 dígitos inteiros e 1 dígito decimal")
    @Schema(example = "8.60")
    private Double notaSegundoSemestre;

    @NotBlank(message = "O nome do professor é obrigatório")
    @Size(min = 3, max = 50, message = "O nome do professor deve ter entre 3 e 50 caracteres")
    @Schema(example = "Dr Professor 1")
    private String nomeProfessor;

    @NotNull(message = "O número da sala é obrigatório")
    @Positive(message = "O número da sala deve ser positivo")
    @Digits(integer = 3, fraction = 0, message = "O número da sala deve ter no máximo 3 dígitos")
    @Min(value = 1, message = "O número da sala deve ser maior ou igual a 1")
    @Max(value = 999, message = "O número da sala deve ser menor ou igual a 999")
    @Schema(example = "110")
    private Integer numeroSala;

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getNotaPrimeiroSemestre() {
        return notaPrimeiroSemestre;
    }

    public void setNotaPrimeiroSemestre(Double notaPrimeiroSemestre) {
        this.notaPrimeiroSemestre = notaPrimeiroSemestre;
    }

    public Double getNotaSegundoSemestre() {
        return notaSegundoSemestre;
    }

    public void setNotaSegundoSemestre(Double notaSegundoSemestre) {
        this.notaSegundoSemestre = notaSegundoSemestre;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }
}

