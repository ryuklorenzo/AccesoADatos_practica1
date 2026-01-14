package ies.sequeros.dam.ad.orm.application.categorias


import ies.sequeros.dam.ad.orm.application.categorias.dtos.CategoriaDto
import ies.sequeros.dam.ad.orm.domain.Categoria
import ies.sequeros.dam.ad.orm.domain.ICategoriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class GetAllCategoriaUseCase (private val repository: ICategoriaRepository) {
    suspend operator fun invoke(): List<CategoriaDto> =
        withContext(Dispatchers.IO) {

            repository.all().map { CategoriaDto.fromDomain(it) }
        }
}