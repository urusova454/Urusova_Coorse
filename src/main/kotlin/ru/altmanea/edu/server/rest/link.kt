package ru.altmanea.edu.server.rest

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.altmanea.edu.server.model.Config.Companion.linkPath
import ru.altmanea.edu.server.repo.importExcel
import ru.altmanea.edu.server.repo.importFile



fun Route.link() {
    route(linkPath) {
        get{
            call.respond(importFile)
        }
        post {
            val file = call.receive<String>()
            importFile = file
            call.respondText("fbvbfbfv", status = HttpStatusCode.Created)
            importExcel(importFile.replace("\"","", true))
        }
    }
}