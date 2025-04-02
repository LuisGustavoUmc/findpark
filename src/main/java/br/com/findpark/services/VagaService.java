package br.com.findpark.services;

import br.com.findpark.domain.Vaga;
import br.com.findpark.dto.VagaDTO;
import br.com.findpark.exception.ObjetoNaoEncontradoException;
import br.com.findpark.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.findpark.mapper.MapeadorObjeto.converterListaObjetos;
import static br.com.findpark.mapper.MapeadorObjeto.converterObjeto;

@Service
public class VagaService {

    @Autowired
    private VagaRepository repository;

    public List<VagaDTO> buscarTodos() {
        return converterListaObjetos(repository.findAll(), VagaDTO.class);
    }

    public VagaDTO buscarPorId(String id) {
        var entidade = repository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Vaga não encontrada! ID: " + id));
        return converterObjeto(entidade, VagaDTO.class);
    }

    public VagaDTO criar(VagaDTO obj) {
        var entidade = converterObjeto(obj, Vaga.class);
        return converterObjeto(repository.save(entidade), VagaDTO.class);
    }

    public VagaDTO atualizar(String id, VagaDTO obj) {
        Vaga entidade = repository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Vaga não encontrada! ID: " + id));

        entidade.setStatus(obj.getStatus());
        entidade.setTipo(obj.getTipo());
        entidade.setPreco(obj.getPreco());

        return converterObjeto(repository.save(entidade), VagaDTO.class);
    }

    public void deletar(String id) {
        Vaga obj = repository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Vaga não encontrada! ID: " + id));
        repository.delete(obj);
    }
}
