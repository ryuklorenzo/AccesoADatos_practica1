package ies.sequeros.dam.ad.orm.application.productos

import application.productos.commands.AddProductoCommand
import ies.sequeros.dam.ad.orm.application.productos.dto.ProductoDto
import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import ies.sequeros.dam.ad.orm.domain.Producto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class AddProductoUseCase(private val repository: IProductoRepository) {
    suspend operator fun invoke(command: AddProductoCommand): ProductoDto =
        withContext(Dispatchers.IO) {
            val item = Producto.fromCommand(command)
            repository.create(item)
            ProductoDto.fromDomain(item)
        }

    fun Producto.Companion.fromCommand(command: AddProductoCommand): Producto {
        return Producto(
            id = UUID.randomUUID(),
            nombre = command.nombre,
            descripcion = command.descripcion,
            precio = command.precio,
            activo = command.activo,
            categoriaId = command.categoriaId
        )
    }
}