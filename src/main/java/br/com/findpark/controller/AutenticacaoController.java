package br.com.findpark.controller;

import br.com.findpark.domain.Usuario;
import br.com.findpark.dto.security.AutenticacaoDTO;
import br.com.findpark.dto.security.CadastroDTO;
import br.com.findpark.dto.security.TokenCompletoDTO;
import br.com.findpark.dto.security.TokenDTO;
import br.com.findpark.infra.security.TokenService;
import br.com.findpark.repository.AutenticacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/findpark/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AutenticacaoRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenCompletoDTO> login(@RequestBody @Valid AutenticacaoDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = authenticationManager.authenticate(usernamePassword);

        var usuario = (Usuario) auth.getPrincipal();
        var accessToken = tokenService.gerarToken(usuario);
        var refreshToken = tokenService.gerarRefreshToken(usuario);

        return ResponseEntity.ok(new TokenCompletoDTO(accessToken, refreshToken));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrar(@RequestBody @Valid CadastroDTO data) {
        if (repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario novoUsuario = new Usuario(data.email(), encryptedPassword, data.telefone(), data.role());

        repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDTO> refresh(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).build();
        }

        var refreshToken = authHeader.replace("Bearer ", "").trim();
        var email = tokenService.validarToken(refreshToken);

        if (email.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        var usuario = repository.findByEmail(email);

        if (usuario == null) {
            return ResponseEntity.status(404).build();
        }

        var accessToken = tokenService.gerarToken((Usuario) usuario);

        return ResponseEntity.ok(new TokenDTO(accessToken));
    }

}
