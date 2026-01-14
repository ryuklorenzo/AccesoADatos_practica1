@file:UseContextualSerialization(UUID::class)

package ies.sequeros.dam.ad.orm.application.categorias.dtos

import ies.sequeros.dam.ad.orm.domain.Categoria
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import java.util.UUID
@Serializable
data class CategoriaDto(val id: UUID,
                   val nombre:String,
                   val descripcion:String,
                   val activo:Boolean) {
    companion object{
        public fun fromDomain(item: Categoria): CategoriaDto {

           return  CategoriaDto(item.id!!,item.nombre,item.descripcion,item.activo)
        }

    }
}