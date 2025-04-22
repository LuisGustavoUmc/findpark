package br.com.findpark.services;

import br.com.findpark.domain.Usuario;
import br.com.findpark.dto.UsuarioDTO;
import br.com.findpark.exception.ObjetoNaoEncontradoException;
import br.com.findpark.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.findpark.mapper.MapeadorObjeto.converterListaObjetos;
import static br.com.findpark.mapper.MapeadorObjeto.converterObjeto;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioDTO> buscarTodos() {
        return converterListaObjetos(repository.findAll(), UsuarioDTO.class);
    }

    public UsuarioDTO buscarPorId(String id) {
        var entidade = repository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Usuario não encontrada! ID: " + id));
        return converterObjeto(entidade, UsuarioDTO.class);
    }

    public UsuarioDTO atualizar(String id, UsuarioDTO obj) {
        Usuario entidade = repository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Usuario não encontrada! ID: " + id));

        entidade.setEmail(obj.getEmail());
        entidade.setSenha(obj.getSenha());
        entidade.setTelefone(obj.getTelefone());

        return converterObjeto(repository.save(entidade), UsuarioDTO.class);
    }

    public void deletar(String id) {
        Usuario obj = repository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Usuario não encontrada! ID: " + id));
        repository.delete(obj);
    }
}
