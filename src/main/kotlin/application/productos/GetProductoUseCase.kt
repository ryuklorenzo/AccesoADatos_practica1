package ies.sequeros.dam.ad.orm.application.productos

import ies.sequeros.dam.ad.orm.application.productos.dto.ProductoDto
import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import io.ktor.server.plugins.BadRequestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class GetProductoUseCase (private val repository: IProductoRepository){
    suspend operator fun invoke(id: UUID) : ProductoDto =
        withContext(Dispatchers.IO) {
            val item = repository.findById(id)
            if (item == null) {
                throw BadRequestException("Producto not found with id $id")
            }
            ProductoDto.fromDomain(item)
        }

}