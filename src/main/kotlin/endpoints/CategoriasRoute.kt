package ies.sequeros.dam.ad.orm.endpoints

import AddCategoriaCommand
import ies.sequeros.dam.ad.orm.application.categorias.AddCategoriaUseCase
import ies.sequeros.dam.ad.orm.application.categorias.DeleteCategoriaUseCase
import ies.sequeros.dam.ad.orm.application.categorias.GetAllCategoriaUseCase
import ies.sequeros.dam.ad.orm.application.categorias.GetCategoriaUseCase

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.parameters
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.receive
import io.ktor.server.request.receiveMultipart
import io.ktor.server.request.uri
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.configureCategoriasRoutes() {
    route("/categorias") {
        get {
            val useCase by inject<GetAllCategoriaUseCase>()
            val items = useCase()
            call.respond(items)
        }
        post {
            val useCase by inject<AddCategoriaUseCase>()
            val command = call.receive<AddCategoriaCommand>()
            var item = useCase(command)
            //location para que el cliente pueda acceder al recurso
            val location = "${call.request.uri}/${item.id}"
            call.response.header(HttpHeaders.Location, location)
            call.respond(HttpStatusCode.Created, item)
        }
        get("/{id}") {
            val useCase by inject<GetCategoriaUseCase>()
            //se valida que exista, seria necesario tambien el formato correcto
            val stringId = call.parameters["id"] ?: throw BadRequestException("El parámetro id es obligatorio")

            val id = UUID.fromString(stringId)
            val cat = useCase(id)
            call.respond(HttpStatusCode.OK, cat)
        }
        delete("/{id}") {
            val useCase by inject<DeleteCategoriaUseCase>()
            //se valida que exista, seria necesario tambien el formato correcto
            val stringId = call.parameters["id"] ?: throw BadRequestException("El parámetro id es obligatorio")
            val id = UUID.fromString(stringId)
            val cat = useCase(id)
            call.respond(HttpStatusCode.OK, cat)
        }
    }


}

