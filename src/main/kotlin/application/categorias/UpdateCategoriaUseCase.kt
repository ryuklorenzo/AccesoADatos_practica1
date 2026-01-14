package ies.sequeros.dam.ad.orm.application.categorias

import ies.sequeros.dam.ad.orm.application.categorias.commands.UpdateCategoriaCommand
import ies.sequeros.dam.ad.orm.application.categorias.dtos.CategoriaDto
import ies.sequeros.dam.ad.orm.domain.Categoria
import ies.sequeros.dam.ad.orm.domain.ICategoriaRepository
import io.ktor.server.plugins.BadRequestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class UpdateCategoriaUseCase (private val repository: ICategoriaRepository) {
    suspend operator fun invoke(id: UUID, command: UpdateCategoriaCommand): CategoriaDto =
        withContext(Dispatchers.IO) {
            val item=repository.findById(id)
            if(item == null) {
                throw BadRequestException("Categoria not found with id $id")
            }
            val newItem = Categoria.Companion.fromComand(command)
            repository.update(newItem)
            CategoriaDto.Companion.fromDomain(newItem)
        }


    //se crea la extension para facilitarlo, se podría poner en una factoria
    fun Categoria.Companion.fromComand(command: UpdateCategoriaCommand): Categoria {
        return Categoria(command.id, command.nombre, command.descripcion, command.activo)
    }
}