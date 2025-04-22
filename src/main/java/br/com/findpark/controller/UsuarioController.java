package br.com.findpark.controller;

import br.com.findpark.dto.UsuarioDTO;
import br.com.findpark.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/findpark/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
        List<UsuarioDTO> usuarios = service.buscarTodos();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable String id) {
        UsuarioDTO usuario = service.buscarPorId(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable String id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioAtualizada = service.atualizar(id, usuarioDTO);
        return ResponseEntity.ok().body(usuarioAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
