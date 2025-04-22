package br.com.findpark.repository;

import br.com.findpark.domain.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AutenticacaoRepository extends MongoRepository<Usuario, String> {
    UserDetails findByEmail(String email);
}
