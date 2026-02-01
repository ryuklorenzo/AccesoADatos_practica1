package ies.sequeros.dam.ad.orm.application.productos

import ies.sequeros.dam.ad.orm.application.productos.dto.ProductoDto
import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllProductosUseCase (private val repository: IProductoRepository){

    suspend operator fun invoke() : List<ProductoDto> =
        withContext(Dispatchers.IO) {
            repository.all().map { ProductoDto.fromDomain(it) }
        }
}