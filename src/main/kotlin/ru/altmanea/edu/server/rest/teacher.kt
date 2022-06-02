package ru.altmanea.edu.server.rest

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.altmanea.edu.server.model.Config.Companion.teacherPath


import ru.altmanea.edu.server.repo.*

fun Route.teacher() {
    route(teacherPath) {
        get {
            if (teachers.isNotEmpty()) {
                call.respond(teachers.keys)
            } else {
                call.respondText("No lessons found", status = HttpStatusCode.NotFound)
            }
        }
        get("{name}") {
            val name = call.parameters["name"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            //ключ - объект. передаем в качестве ключа // возвращает определенного
            val teacher = all.findLast { it.teacher.surname == name }!!.teacher
            val teacherItem =
                teachers[teacher] ?: return@get call.respondText(
                    "No lesson with name $name",
                    status = HttpStatusCode.NotFound
                )
            call.respond(teacherItem)
        }
    }
}


