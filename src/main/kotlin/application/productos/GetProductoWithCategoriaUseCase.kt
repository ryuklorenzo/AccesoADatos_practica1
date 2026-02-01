package ies.sequeros.dam.ad.orm.application.productos

import ies.sequeros.dam.ad.orm.application.productos.dto.ProductoDto
import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class GetProductoWithCategoriaUseCase (private val repository : IProductoRepository) {
    suspend operator fun  invoke(id: UUID): ProductoDto =
        withContext(Dispatchers.IO){
            val producto = repository.findById(id) ?: throw Exception("Producto no encontrado")
            ProductoDto.fromDomain(producto)
        }
}