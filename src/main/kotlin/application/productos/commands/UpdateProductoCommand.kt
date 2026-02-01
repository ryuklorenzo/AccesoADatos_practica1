@file:UseContextualSerialization(UUID::class)

package application.productos.commands

import ies.sequeros.dam.ad.orm.application.serializers.BigDecimalSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import java.math.BigDecimal
import java.util.UUID

@Serializable
data class UpdateProductoCommand(
    val id: UUID,
    val nombre: String,
    val descripcion: String,
    val activo: Boolean,
    @Serializable(with = BigDecimalSerializer::class)
    val precio: BigDecimal,
    val categoriaId: UUID
)