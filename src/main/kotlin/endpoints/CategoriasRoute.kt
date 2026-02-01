package ies.sequeros.dam.ad.orm.endpoints

import application.categorias.commands.AddCategoriaCommand
import ies.sequeros.dam.ad.orm.application.categorias.*
import ies.sequeros.dam.ad.orm.application.categorias.commands.UpdateCategoriaCommand
import io.ktor.http.*
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.configureCategoriasRoutes() {
    route("/categorias") {
        get {
            val useCase by inject<GetAllCategoriaUseCase>()
            call.respond(useCase())
        }
        post {
            val useCase by inject<AddCategoriaUseCase>()
            val command = call.receive<AddCategoriaCommand>()
            val item = useCase(command)
            call.respond(HttpStatusCode.Created, item)
        }

        //rutas con ID
        route("/{id}") {
            get {
                val useCase by inject<GetCategoriaUseCase>()
                val id = UUID.fromString(call.parameters["id"])
                call.respond(HttpStatusCode.OK, useCase(id))
            }
            delete {
                val useCase by inject<DeleteCategoriaUseCase>()
                val id = UUID.fromString(call.parameters["id"])
                useCase(id)
                call.respond(HttpStatusCode.NoContent)
            }
            put {
                val useCase by inject<UpdateCategoriaUseCase>()
                val idString = call.parameters["id"] ?: throw BadRequestException("ID obligatorio")
                val id = UUID.fromString(idString)
                val command = call.receive<UpdateCategoriaCommand>()
                val item = useCase(id, command)

                call.respond(HttpStatusCode.OK, item)
            }
        }
    }
}