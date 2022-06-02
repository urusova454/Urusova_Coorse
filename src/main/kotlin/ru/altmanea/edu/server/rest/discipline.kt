package ru.altmanea.edu.server.rest

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.altmanea.edu.server.model.Config.Companion.disciplinePath



import ru.altmanea.edu.server.repo.*

fun Route.discipline() {
    route(disciplinePath) {
        get {
            if (discipline.isNotEmpty()) {
                call.respond(discipline.keys)
            } else {
                call.respondText("No lessons found", status = HttpStatusCode.NotFound)
            }
        }
        get("{name}") {
            val name = call.parameters["name"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val disciplines = all.findLast { it.discipline.discipline == name }!!.discipline
            val disciplineItem =
                discipline[disciplines] ?: return@get call.respondText(
                    "No lesson with name $name",
                    status = HttpStatusCode.NotFound
                )
            call.respond(disciplineItem)
        }

    }
}