package ies.sequeros.dam.ad.orm.endpoints

import application.productos.commands.AddProductoCommand
import application.productos.commands.UpdateProductoCommand
import ies.sequeros.dam.ad.orm.application.productos.AddProductoUseCase
import ies.sequeros.dam.ad.orm.application.productos.DeleteProductoUseCase
import ies.sequeros.dam.ad.orm.application.productos.GetAllProductosUseCase
import ies.sequeros.dam.ad.orm.application.productos.GetProductoUseCase
import ies.sequeros.dam.ad.orm.application.productos.UpdateProductoUseCase
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.receive
import io.ktor.server.request.uri
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.configureProductosRoutes() {
    route("/productos") {
        // OBTENER TODOS
        get {
            val useCase by inject<GetAllProductosUseCase>()
            val items = useCase()
            call.respond(items)
        }

        // CREAR NUEVO
        post {
            val useCase by inject<AddProductoUseCase>()
            // Recibe el comando para añadir
            val command = call.receive<AddProductoCommand>()
            val item = useCase(command)

            // Devuelve la location y el objeto creado
            val location = "${call.request.uri}/${item.id}"
            call.response.header(HttpHeaders.Location, location)
            call.respond(HttpStatusCode.Created, item)
        }

        // RUTAS CON ID (/productos/{id})
        route("/{id}") {
            // OBTENER UNO
            get {
                val useCase by inject<GetProductoUseCase>()
                val idString = call.parameters["id"] ?: throw BadRequestException("ID obligatorio")
                val id = UUID.fromString(idString)
                val item = useCase(id)
                call.respond(HttpStatusCode.OK, item)
            }

            // BORRAR
            delete {
                val useCase by inject<DeleteProductoUseCase>()
                val idString = call.parameters["id"] ?: throw BadRequestException("ID obligatorio")
                val id = UUID.fromString(idString)
                useCase(id)
                call.respond(HttpStatusCode.NoContent)
            }

            // EDITAR (PUT)
            put {
                val useCase by inject<UpdateProductoUseCase>()
                val idString = call.parameters["id"] ?: throw BadRequestException("ID obligatorio")
                val id = UUID.fromString(idString)

                // Recibe el comando de actualización
                val command = call.receive<UpdateProductoCommand>()

                // Llama al caso de uso pasando ID y datos nuevos
                val item = useCase(id, command)
                call.respond(HttpStatusCode.OK, item)
            }
        }
    }
}