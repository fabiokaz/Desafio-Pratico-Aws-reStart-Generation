package com.fabiokaz.alunos_api.controller;

import com.fabiokaz.alunos_api.dto.AlunoDTO;
import com.fabiokaz.alunos_api.exception.ForbiddenException;
import com.fabiokaz.alunos_api.exception.ResourceFoundException;
import com.fabiokaz.alunos_api.exception.ResourceNotFoundException;
import com.fabiokaz.alunos_api.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private static final Logger logger = LoggerFactory.getLogger(AlunoController.class);

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Object> createAluno(@Valid @RequestBody AlunoDTO alunoDTO) {
        logger.info("### Received request to create aluno: {}", alunoDTO);
        try {
            AlunoDTO alunoResponse = alunoService.createAluno(alunoDTO);
            logger.info("### Aluno criado com sucesso: {}", alunoResponse);
            return new ResponseEntity<>(alunoResponse, HttpStatus.CREATED);
        } catch (ResourceFoundException e) {
            return ResponseEntity.status(HttpStatus.FOUND).body(e.getMessage());
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllAlunos() {
        logger.info("### Received request to get all alunos");
        try {
            List<AlunoDTO> alunos = alunoService.getAllAlunos();
            logger.info("### Retrieved {} alunos", alunos.size());
            return new ResponseEntity<>(alunos, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAlunoById(@PathVariable Long id) {
        logger.info("### Received request to get aluno by ID: {}", id);
        try {
            AlunoDTO alunoResponse = alunoService.getAlunoById(id);
            logger.info("### Retrieved aluno by ID: {}", id);
            return new ResponseEntity<>(alunoResponse, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoDTO alunoDTO) {
        logger.info("### Updating Aluno by id: {} with data: {}", id, alunoDTO);
        try {
            AlunoDTO alunoResponse = alunoService.updateAluno(id, alunoDTO);
            logger.info("### Aluno updated successfully: {}", alunoResponse);
            return new ResponseEntity<>(alunoResponse, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAluno(@PathVariable Long id) {
        logger.info("### Deleting Aluno by id: {}", id);
        // Implementar a lógica de exclusão do aluno
        try {
            alunoService.deleteAluno(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}