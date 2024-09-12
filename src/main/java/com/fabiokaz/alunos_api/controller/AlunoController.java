package com.fabiokaz.alunos_api.controller;

import com.fabiokaz.alunos_api.dto.AlunoDTO;
import com.fabiokaz.alunos_api.exception.ResourceFoundException;
import com.fabiokaz.alunos_api.exception.ResourceNotFoundException;
import com.fabiokaz.alunos_api.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Object> createAluno(@Valid @RequestBody AlunoDTO alunoDTO) {
        try {
            AlunoDTO alunoResponse = alunoService.createAluno(alunoDTO);
            return new ResponseEntity<>(alunoResponse, HttpStatus.CREATED);
        } catch (ResourceFoundException e) {
            return ResponseEntity.status(HttpStatus.FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllAlunos() {
        try {
            List<AlunoDTO> alunos = alunoService.getAllAlunos();
            return new ResponseEntity<>(alunos, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAlunoById(@PathVariable Long id) {
        try {
            AlunoDTO alunoResponse = alunoService.getAlunoById(id);
            return new ResponseEntity<>(alunoResponse, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoDTO alunoDTO) {
        try {
            AlunoDTO alunoResponse = alunoService.updateAluno(id, alunoDTO);
            return new ResponseEntity<>(alunoResponse, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAluno(@PathVariable Long id) {
        try {
            alunoService.deleteAluno(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}