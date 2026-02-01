package ies.sequeros.dam.ad.orm.infraestructure.mappers;

import ies.sequeros.dam.ad.orm.domain.Categoria;
import ies.sequeros.dam.ad.orm.infraestructure.entities.CategoriaJPA;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoriaMapper {
    private CategoriaMapper() {}

    public static Categoria toCategoria(CategoriaJPA jpa) {

        Categoria item = new Categoria(
                jpa.getId(),
                jpa.getNombre(),
                jpa.getDescripcion(),
                jpa.getActivo(), Collections.emptyList()

        );
        return item;
    }
    public static CategoriaJPA toJpa(Categoria domain) {
       CategoriaJPA jpa = new CategoriaJPA();
       jpa.setId(domain.getId());
               jpa.setNombre(domain.getNombre());
               jpa.setDescripcion(domain.getDescripcion());
               jpa.setActivo(domain.getActivo());

        return jpa;
    }
}
