package ru.altmanea.edu.server.rest

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.altmanea.edu.server.model.Config.Companion.groupPath
import ru.altmanea.edu.server.model.Config.Companion.teacherPath


import ru.altmanea.edu.server.repo.*

fun Route.group() {
    route(groupPath) {
        get {
            if (group.isNotEmpty()) {
                call.respond(group.keys)
            } else {
                call.respondText("No lessons found", status = HttpStatusCode.NotFound)
            }
        }
        get("{name}") {
            val name = call.parameters["name"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val groups = all.findLast { it.group.group == name }!!.group
            val groupItem =
                group[groups] ?: return@get call.respondText(
                    "No lesson with name $name",
                    status = HttpStatusCode.NotFound
                )
            call.respond(groupItem)
        }
    }
}