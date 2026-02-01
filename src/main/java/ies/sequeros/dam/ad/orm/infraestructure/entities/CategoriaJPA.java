package ies.sequeros.dam.ad.orm.infraestructure.entities;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "categoria")
public class CategoriaJPA {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProductoJPA> productos = new LinkedHashSet<>();

    public CategoriaJPA() {
    }

    // Getters y Setters básicos

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Set<ProductoJPA> getProductos() {
        return productos;
    }

    public void setProductos(Set<ProductoJPA> productos) {
        this.productos = productos;
    }


    public void addProducto(ProductoJPA producto) {
        productos.add(producto);
        producto.setCategoria(this);
    }

    public void removeProducto(ProductoJPA producto) {
        productos.remove(producto);
        producto.setCategoria(null);
    }
}