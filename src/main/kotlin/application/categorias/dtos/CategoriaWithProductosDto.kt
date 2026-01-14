@file:UseContextualSerialization(UUID::class)

package ies.sequeros.dam.ad.orm.application.categorias.dtos

import ies.sequeros.dam.ad.orm.domain.Categoria
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import java.util.UUID


@Serializable
data class CategoriaWithProductosDto(
    val id: UUID,
    val nombre: String,
    val descripcion: String,
    val activo: Boolean,
    val productos: List<ProductoCategoriaDto>
) {

}
