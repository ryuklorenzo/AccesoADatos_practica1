package ies.sequeros.dam.ad.orm.application.productos.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductoWithCategoriaDto (
    val id : String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val activo: Boolean
){
}