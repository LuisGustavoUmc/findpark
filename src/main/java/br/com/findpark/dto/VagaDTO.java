package br.com.findpark.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VagaDTO {

    private String id;

    @NotBlank(message = "Status não pode estar em branco")
    private String status;

    @NotBlank(message = "Tipo não pode estar em branco")
    private String tipo;

    @NotNull(message = "Preço não pode ser nulo")
    @Min(value = 0, message = "O preço deve ser um número positivo")
    private Double preco;
}