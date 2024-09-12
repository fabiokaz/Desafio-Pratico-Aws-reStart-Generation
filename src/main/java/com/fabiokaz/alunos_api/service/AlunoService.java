package com.fabiokaz.alunos_api.service;

import com.fabiokaz.alunos_api.dto.AlunoDTO;
import com.fabiokaz.alunos_api.exception.ForbiddenException;
import com.fabiokaz.alunos_api.exception.ResourceFoundException;
import com.fabiokaz.alunos_api.exception.ResourceNotFoundException;
import com.fabiokaz.alunos_api.model.Aluno;
import com.fabiokaz.alunos_api.model.Nota;
import com.fabiokaz.alunos_api.model.Professor;
import com.fabiokaz.alunos_api.model.Sala;
import com.fabiokaz.alunos_api.repository.AlunoRepository;
import com.fabiokaz.alunos_api.repository.NotaRepository;
import com.fabiokaz.alunos_api.repository.ProfessorRepository;
import com.fabiokaz.alunos_api.repository.SalaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private NotaRepository notaRepository;

    private Aluno aluno;

    public AlunoDTO createAluno(@Valid AlunoDTO alunoDTO) {

        if(alunoRepository.findByAlunoId(alunoDTO.getAlunoId()).isPresent()){
            throw new ResourceFoundException("Aluno já cadastrado com ID: " + alunoDTO.getAlunoId());
        }

        if(alunoRepository.count() >= 10){
            throw new ForbiddenException("Você pode cadastrar no máximo 10 alunos. Desculpe pelo inconveniente. ");
        }

        this.aluno = AlunoMapperUtil.dtoToAluno(alunoDTO);
        return this.persistAlunoDTO(alunoDTO);
    }

    public List<AlunoDTO> getAllAlunos() {
        List<Nota> notas = notaRepository.findAll();
        if(notas.isEmpty()){
            throw new ResourceNotFoundException("Nenhum aluno encontrado");
        }
        return AlunoMapperUtil.notasListToAlunoDtoList(notas);
    }

    public AlunoDTO getAlunoById(Long alunoId) {
        try {
            List<Nota> notas = notaRepository.findByAlunoAlunoId(alunoId);
            if(notas.isEmpty()){
                throw new ResourceNotFoundException("Aluno não encontrado com o ID : " + alunoId);
            }
            return AlunoMapperUtil.notasListToAlunoDto(notas);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public AlunoDTO updateAluno(Long alunoId, @Valid AlunoDTO alunoDTO) {
        List<Nota> notas = notaRepository.findByAlunoAlunoId(alunoId);
        if(notas.isEmpty()){
            throw new ResourceNotFoundException("Aluno não encontrado com ID : " + alunoId);
        }
        if(alunoDTO.getAlunoId() != null && !alunoDTO.getAlunoId().equals(alunoId)){
            throw new ResourceNotFoundException("Os IdAluno são diferentes");
        }

        Aluno alunoRequest = AlunoMapperUtil.dtoToAluno(alunoDTO);
        this.aluno = alunoRepository.findByAlunoId(alunoRequest.getAlunoId()).get();
        this.aluno = AlunoMapperUtil.updateAlunoFromAlunoDto(this.aluno, alunoRequest);

        AlunoDTO updatedAluno = AlunoMapperUtil.updateAlunoDTOFromAlunoDto(notas.get(0).getAluno(), alunoDTO);

        return persistAlunoDTO(updatedAluno);
    }

    public void deleteAluno(Long alunoId) {
        List<Nota> notas = notaRepository.findByAlunoAlunoId(alunoId);
        if(notas.isEmpty()){
            throw new ResourceNotFoundException("Aluno não encontrado com ID : " + alunoId);
        }
        notaRepository.deleteAll(notas);
    }



    public AlunoDTO persistAlunoDTO(AlunoDTO alunoDTO) {

        Professor professorRequest = AlunoMapperUtil.dtoToProfessor(alunoDTO);
        Professor professor = professorRepository.findByNome(professorRequest.getNome()).orElse(professorRequest);
        Sala salaRequest = AlunoMapperUtil.dtoToSala(alunoDTO);
        Sala sala = salaRepository.findByNumeroSala(salaRequest.getNumeroSala()).orElse(salaRequest);

        List<Nota> notas;
        if(this.aluno.getId() != null){
            notas = AlunoMapperUtil.dtoToNota(notaRepository.findByAlunoAlunoId(alunoDTO.getAlunoId()), alunoDTO, sala);
        }else{
            notas = new ArrayList<>(AlunoMapperUtil.dtoToNota((new ArrayList<>()), alunoDTO, sala));
        }

        notas.forEach(nota -> nota.setAluno(aluno));
        notas.forEach(nota -> nota.setProfessor(professor));

        notaRepository.saveAllAndFlush(notas);

        return AlunoMapperUtil.notasListToAlunoDto(notas);
    }
}
