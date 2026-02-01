package ies.sequeros.dam.ad.orm.application.productos

import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import io.ktor.server.plugins.BadRequestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class DeleteProductoUseCase (private val repository: IProductoRepository) {
    suspend operator fun invoke(id: UUID) =
        withContext(Dispatchers.IO) {
            val item = repository.findById(id)
            if (item == null) {
                throw BadRequestException("Producto not found with id $id")
            }
            repository.delete(id)
        }

}