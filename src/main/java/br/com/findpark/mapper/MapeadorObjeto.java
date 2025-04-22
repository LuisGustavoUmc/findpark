package br.com.findpark.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class MapeadorObjeto {

    private static Mapper mapeador = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D converterObjeto(O origem, Class<D> destino) {
        return mapeador.map(origem, destino);
    }

    public static <O, D> List<D> converterListaObjetos(List<O> origem, Class<D> destino) {

        List<D> objetosDestino = new ArrayList<>();
        for (O o : origem) {
            objetosDestino.add(mapeador.map(o, destino));
        }
        return objetosDestino;
    }
}
