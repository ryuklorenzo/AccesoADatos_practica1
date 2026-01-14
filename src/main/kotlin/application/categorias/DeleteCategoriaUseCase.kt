package ies.sequeros.dam.ad.orm.application.categorias

import ies.sequeros.dam.ad.orm.application.categorias.commands.UpdateCategoriaCommand
import ies.sequeros.dam.ad.orm.application.categorias.dtos.CategoriaDto
import ies.sequeros.dam.ad.orm.domain.Categoria
import ies.sequeros.dam.ad.orm.domain.ICategoriaRepository
import io.ktor.server.plugins.BadRequestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class DeleteCategoriaUseCase (private val repository: ICategoriaRepository) {
    suspend operator fun invoke(id: UUID)  =
        withContext(Dispatchers.IO) {
            val item=repository.findById(id)
            if(item == null) {
                throw BadRequestException("Categoria not found with id $id")
            }
            repository.delete(id)

        }



}