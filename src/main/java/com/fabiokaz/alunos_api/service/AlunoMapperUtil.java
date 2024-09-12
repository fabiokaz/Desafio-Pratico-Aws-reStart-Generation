package com.fabiokaz.alunos_api.service;

import com.fabiokaz.alunos_api.dto.AlunoDTO;
import com.fabiokaz.alunos_api.model.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AlunoMapperUtil {

    public static Aluno dtoToAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno(alunoDTO.getNome());
        aluno.setAlunoId(alunoDTO.getAlunoId());
        aluno.setIdade(alunoDTO.getIdade());
        return aluno;
    }

    public static Professor dtoToProfessor(AlunoDTO alunoDTO) {
        return new Professor(alunoDTO.getNomeProfessor());
    }

    public static Sala dtoToSala(AlunoDTO alunoDTO) {
        return new Sala(alunoDTO.getNumeroSala());
    }

    public static List<Nota> dtoToNota(List<Nota> notas, AlunoDTO alunoDTO, Sala sala) {
        if(notas.isEmpty()) {
            notas.add(new Nota(Periodo.PRIMEIRO_SEMESTRE, alunoDTO.getNotaPrimeiroSemestre(), sala));
            notas.add(new Nota(Periodo.SEGUNDO_SEMESTRE, alunoDTO.getNotaSegundoSemestre(), sala));
        }else{
            notas.get(0).setNota(alunoDTO.getNotaPrimeiroSemestre());
            notas.get(0).setSala(sala);
            notas.get(1).setNota(alunoDTO.getNotaSegundoSemestre());
            notas.get(1).setSala(sala);
        }
        return notas;
    }


    public static AlunoDTO notasListToAlunoDto(List<Nota> notas) {
        // alunoId, nome, idade, notaPrimeiroSemestre, notaSegundoSemest, nomeProfessor, numeroSala

        AlunoDTO alunoDTO = new AlunoDTO();

        Nota nota = notas.get(0);
        Professor professor = nota.getProfessor();

        alunoDTO.setAlunoId(nota.getAluno().getAlunoId());
        alunoDTO.setNome(nota.getAluno().getNome());
        alunoDTO.setIdade(nota.getAluno().getIdade());
        alunoDTO.setNomeProfessor(professor != null ? professor.getNome() : "Professor não encontrado");

        alunoDTO.setNumeroSala(nota.getSala().getNumeroSala());

        alunoDTO.setNotaPrimeiroSemestre(
                notas.stream()
                        .filter(n -> Periodo.PRIMEIRO_SEMESTRE.equals(n.getPeriodo()))
                        .map(Nota::getNota)
                        .findFirst()
                        .orElse(null)
        );

        alunoDTO.setNotaSegundoSemestre(
                notas.stream()
                        .filter(n -> Periodo.SEGUNDO_SEMESTRE.equals(n.getPeriodo()))
                        .map(Nota::getNota)
                        .findFirst()
                        .orElse(null)
        );

        return alunoDTO;
    }

    public static List<AlunoDTO> notasListToAlunoDtoList(List<Nota> notas) {
        List<AlunoDTO> alunoDTOs = notas.stream()
                .collect(Collectors.groupingBy(nota -> nota.getAluno().getAlunoId()))
                .entrySet().stream()
                .map(entry -> {
                    List<Nota> alunoNotas = entry.getValue();

                    Double notaPrimeiroSemestre = alunoNotas.stream()
                            .filter(nota -> Periodo.PRIMEIRO_SEMESTRE.equals(nota.getPeriodo()))
                            .map(Nota::getNota)
                            .findFirst()
                            .orElse(null);

                    Double notaSegundoSemestre = alunoNotas.stream()
                            .filter(nota -> Periodo.SEGUNDO_SEMESTRE.equals(nota.getPeriodo()))
                            .map(Nota::getNota)
                            .findFirst()
                            .orElse(null);
                    return getAlunoDTO(alunoNotas, notaPrimeiroSemestre, notaSegundoSemestre);
                })
                .collect(Collectors.toList());

        return alunoDTOs;
    }

    private static AlunoDTO getAlunoDTO(List<Nota> alunoNotas, Double notaPrimeiroSemestre, Double notaSegundoSemestre) {
        AlunoDTO  alunoDTO = new AlunoDTO();
        alunoDTO.setAlunoId(alunoNotas.get(0).getAluno().getAlunoId());
        alunoDTO.setNome(alunoNotas.get(0).getAluno().getNome());
        alunoDTO.setIdade(alunoNotas.get(0).getAluno().getIdade());
        alunoDTO.setNotaPrimeiroSemestre(notaPrimeiroSemestre);
        alunoDTO.setNotaSegundoSemestre(notaSegundoSemestre);
        alunoDTO.setNomeProfessor(alunoNotas.get(0).getProfessor().getNome());
        alunoDTO.setNumeroSala(alunoNotas.get(0).getSala().getNumeroSala());
        return alunoDTO;
    }

    public static AlunoDTO notaToAlunoDto(Nota nota) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setAlunoId(nota.getAluno().getAlunoId());
        alunoDTO.setNome(nota.getAluno().getNome());
        alunoDTO.setIdade(nota.getAluno().getIdade());
        alunoDTO.setNomeProfessor(nota.getProfessor().getNome());
        alunoDTO.setNumeroSala(nota.getSala().getNumeroSala());
        return alunoDTO;
    }

    public static AlunoDTO updateAlunoDTOFromAlunoDto(Aluno aluno, AlunoDTO alunoDTO) {
        AlunoDTO newAlunoDTO = new AlunoDTO();
        newAlunoDTO.setAlunoId(aluno.getAlunoId());
        newAlunoDTO.setNome(alunoDTO.getNome());
        newAlunoDTO.setIdade(alunoDTO.getIdade());
        newAlunoDTO.setNotaPrimeiroSemestre(alunoDTO.getNotaPrimeiroSemestre());
        newAlunoDTO.setNotaSegundoSemestre(alunoDTO.getNotaSegundoSemestre());
        newAlunoDTO.setNomeProfessor(alunoDTO.getNomeProfessor());
        newAlunoDTO.setNumeroSala(alunoDTO.getNumeroSala());
        return newAlunoDTO;
    }

    public static Aluno updateAlunoFromAlunoDto(Aluno aluno, Aluno alunoRequest) {
        aluno.setNome(alunoRequest.getNome());
        aluno.setIdade(alunoRequest.getIdade());
        return aluno;
    }

    private static void atualizarNotas(AlunoDTO alunoDTO, Aluno aluno, Sala sala) {
        Optional<Nota> notaPrimeiroSemestre = aluno.getNotas().stream()
                .filter(nota -> Periodo.PRIMEIRO_SEMESTRE.equals(nota.getPeriodo()))
                .findFirst();

        if (notaPrimeiroSemestre.isPresent()) {
            notaPrimeiroSemestre.get().setNota(alunoDTO.getNotaPrimeiroSemestre());
            notaPrimeiroSemestre.get().setAluno(aluno);
        } else {
            Nota novaNotaPrimeiroSemestre = new Nota(Periodo.PRIMEIRO_SEMESTRE, alunoDTO.getNotaPrimeiroSemestre(), sala);
            novaNotaPrimeiroSemestre.setAluno(aluno);
            aluno.getNotas().add(novaNotaPrimeiroSemestre);
        }

        Optional<Nota> notaSegundoSemestre = aluno.getNotas().stream()
                .filter(nota -> Periodo.SEGUNDO_SEMESTRE.equals(nota.getPeriodo()))
                .findFirst();
        if (notaSegundoSemestre.isPresent()) {
            notaSegundoSemestre.get().setNota(alunoDTO.getNotaSegundoSemestre());
            notaSegundoSemestre.get().setAluno(aluno);
        } else {
            Nota novaNotaSegundoSemestre = new Nota(Periodo.SEGUNDO_SEMESTRE, alunoDTO.getNotaSegundoSemestre(), sala);
            novaNotaSegundoSemestre.setAluno(aluno);
            aluno.getNotas().add(novaNotaSegundoSemestre);
        }
    }
}
