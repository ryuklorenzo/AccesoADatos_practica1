package ies.sequeros.dam.ad.orm.domain

import java.math.BigDecimal
import java.util.UUID

class Producto(
    var id: UUID?,
    var nombre: String,
    var descripcion: String,
    var categoriaId: UUID,
    var precio: BigDecimal,
    var activo: Boolean = false,

) {


    override fun toString(): String {
        return "Producto(id=$id, nombre='$nombre', precio=$precio, activo=$activo)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Producto) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
    companion object

}