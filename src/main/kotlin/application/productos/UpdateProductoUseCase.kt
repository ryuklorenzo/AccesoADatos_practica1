package ies.sequeros.dam.ad.orm.application.productos

import application.productos.commands.UpdateProductoCommand
import ies.sequeros.dam.ad.orm.application.productos.dto.ProductoDto
import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import io.ktor.server.plugins.BadRequestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ies.sequeros.dam.ad.orm.domain.Producto
import java.util.UUID

class UpdateProductoUseCase (private val repository: IProductoRepository) {
    suspend operator fun invoke(id: UUID, command: UpdateProductoCommand): ProductoDto =
        withContext(Dispatchers.IO) {
            val item = repository.findById(id)
            if (item == null) {
                throw BadRequestException("Producto not found with id $id")
            }
            val newItem = Producto.Companion.fromComand(command)
            repository.update(newItem)
            ProductoDto.Companion.fromDomain(newItem)
        }
    fun Producto.Companion.fromComand(command: UpdateProductoCommand): Producto {
        return Producto(command.id, command.nombre, command.descripcion, command.categoriaId, command.precio, command.activo )
    }
}