@file:UseContextualSerialization(UUID::class)

package ies.sequeros.dam.ad.orm.application.categorias.dtos

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import java.util.UUID

@Serializable
data class ProductoCategoriaDto (val id: UUID,
                                 val nombre:String,
                                 val descripcion:String,
                                 val activo:Boolean)