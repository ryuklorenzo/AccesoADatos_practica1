@file:UseContextualSerialization(UUID::class, BigDecimal::class)
package ies.sequeros.dam.ad.orm.application.productos.dto
import ies.sequeros.dam.ad.orm.application.serializers.BigDecimalSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import java.math.BigDecimal
import java.util.UUID

@Serializable
data class ProductoDto (
    val id : UUID,
    val nombre: String,
    val descripcion: String,
    @Serializable(with = BigDecimalSerializer::class)
    val precio: BigDecimal,
    val activo: Boolean,
    val categoriaId: UUID
)
{
    companion object{
        public fun fromDomain(item: ies.sequeros.dam.ad.orm.domain.Producto): ProductoDto {

           return  ProductoDto(
               item.id!!,
               item.nombre,
               item.descripcion,
               item.precio,
               item.activo,
               item.categoriaId
           )
        }

    }
}

