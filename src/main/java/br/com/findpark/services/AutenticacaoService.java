package br.com.findpark.services;

import br.com.findpark.infra.security.TokenService;
import br.com.findpark.repository.AutenticacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private AutenticacaoRepository repository;
    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = repository.findByEmail(email);
        if (usuario != null) return usuario;
        else throw new UsernameNotFoundException("Email " + email + " não encontrado");
    }
}