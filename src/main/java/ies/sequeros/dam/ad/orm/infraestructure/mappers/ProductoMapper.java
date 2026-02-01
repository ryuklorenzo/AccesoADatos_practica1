package ies.sequeros.dam.ad.orm.infraestructure.mappers;

import ies.sequeros.dam.ad.orm.domain.Producto;
import ies.sequeros.dam.ad.orm.infraestructure.entities.CategoriaJPA;
import ies.sequeros.dam.ad.orm.infraestructure.entities.ProductoJPA;

public class ProductoMapper {
    private ProductoMapper() {}

    public static Producto toProducto(ProductoJPA jpa) {
        if (jpa == null) return null;

        // categoria de la entidad que esta relacionada
        java.util.UUID catId = (jpa.getCategoria() != null) ? jpa.getCategoria().getId() : null;

        return new Producto(
                jpa.getId(),
                jpa.getNombre(),
                jpa.getDescripcion(),
                catId,
                jpa.getPrecio(),
                jpa.getActivo()
        );
    }

    public static ProductoJPA toJpa(Producto domain) {
        if (domain == null) return null;

        ProductoJPA jpa = new ProductoJPA();
        jpa.setId(domain.getId());
        jpa.setNombre(domain.getNombre());
        jpa.setDescripcion(domain.getDescripcion());
        jpa.setPrecio(domain.getPrecio());
        jpa.setActivo(domain.getActivo());

        if (domain.getCategoriaId() != null) {
            CategoriaJPA categoriaRef = new CategoriaJPA();
            categoriaRef.setId(domain.getCategoriaId());
            jpa.setCategoria(categoriaRef);
        }

        return jpa;
    }
}