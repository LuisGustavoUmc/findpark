package br.com.findpark.dto.security;

import br.com.findpark.domain.enums.UserRole;

public record CadastroDTO(String email, String senha, String telefone, UserRole role) {
}