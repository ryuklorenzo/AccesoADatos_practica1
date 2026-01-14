package ies.sequeros.dam.ad.orm.application.categorias

import AddCategoriaCommand

import ies.sequeros.dam.ad.orm.application.categorias.dtos.CategoriaDto
import ies.sequeros.dam.ad.orm.domain.Categoria
import ies.sequeros.dam.ad.orm.domain.ICategoriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class AddCategoriaUseCase (private val repository: ICategoriaRepository) {
    suspend operator fun invoke(command: AddCategoriaCommand): CategoriaDto =
        withContext(Dispatchers.IO) {
            val item= Categoria.fromComand(command)
           repository.create(item)
           CategoriaDto.fromDomain(item)
        }


    //se crea la extension para facilitarlo, se podría poner en una factoria
    fun Categoria.Companion.fromComand(command: AddCategoriaCommand): Categoria {
        return Categoria(UUID.randomUUID(),command.nombre,command.descripcion,command.activo)
    }
}
