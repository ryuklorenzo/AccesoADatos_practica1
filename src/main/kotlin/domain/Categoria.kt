package ies.sequeros.dam.ad.orm.domain

import java.math.BigDecimal
import java.util.UUID


class Categoria(
    var id: UUID?,
    var nombre: String,
    var descripcion: String,
    var activo: Boolean = false,
    private val _productos: MutableList<Producto> = mutableListOf()
) {
    val productos: List<Producto> get() = _productos.toList()
    fun addSubproducto(producto: Producto) {
        _productos.add(producto)
    }

    fun removeSubproducto(producto: Producto) {
        _productos.remove(producto)
    }

    override fun toString(): String {
        return "Producto(id=$id, nombre='$nombre', activo=$activo, categorias=${productos.size})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Categoria) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    companion object
}