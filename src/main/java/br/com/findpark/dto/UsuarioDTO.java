package br.com.findpark.dto;

import br.com.findpark.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @Id
    private String id;
    private String email;
    private String senha;
    private String telefone;
    private UserRole role;
}