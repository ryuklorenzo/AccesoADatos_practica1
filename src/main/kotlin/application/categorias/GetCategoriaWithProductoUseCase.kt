package ies.sequeros.dam.ad.orm.application.categorias

import ies.sequeros.dam.ad.orm.application.categorias.dtos.CategoriaDto
import ies.sequeros.dam.ad.orm.application.productos.GetProductoUseCase
import ies.sequeros.dam.ad.orm.domain.ICategoriaRepository
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.NotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class GetCategoriaWithProductoUseCase (private val repository: ICategoriaRepository) {
    suspend operator fun invoke(id: UUID): CategoriaDto =
        withContext(Dispatchers.IO) {
            val item = repository.findById(id)

            if (item == null) {
                throw NotFoundException("Categoria not found with id $id")
            }

            CategoriaDto.fromDomain(item)
        }
}