package application.categorias.commands

import kotlinx.serialization.Serializable

@Serializable
data class AddCategoriaCommand(val nombre:String,
                          val descripcion:String,
                          val activo:Boolean) {
}