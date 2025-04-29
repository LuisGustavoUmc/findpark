package br.com.findpark.controller;

import br.com.findpark.dto.VagaDTO;
import br.com.findpark.services.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/findpark/vaga")
public class VagaController {

    @Autowired
    private VagaService service;

    @GetMapping
    public ResponseEntity<List<VagaDTO>> buscarTodos() {
        List<VagaDTO> vagas = service.buscarTodos();
        return ResponseEntity.ok().body(vagas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaDTO> buscarPorId(@PathVariable String id) {
        VagaDTO vaga = service.buscarPorId(id);
        return ResponseEntity.ok().body(vaga);
    }

    @PostMapping
    public ResponseEntity<VagaDTO> criar(@RequestBody @Valid VagaDTO vagaDTO) {
        VagaDTO novaVaga = service.criar(vagaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVaga);
    }

    @PutMapping
    public ResponseEntity<VagaDTO> atualizar(@RequestBody @Valid VagaDTO vagaDTO) {
        VagaDTO vagaAtualizada = service.atualizar(vagaDTO);
        return ResponseEntity.ok().body(vagaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
