package br.com.findpark.dto.security;

import br.com.findpark.domain.enums.UserRole;

public record TokenCompletoDTO(String accessToken, String refreshToken, String role) {
}
