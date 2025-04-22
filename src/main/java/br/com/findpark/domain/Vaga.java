package br.com.findpark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vaga")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaga {

    @Id
    private String id;
    private String status;
    private String tipo;
    private double preco;
}
