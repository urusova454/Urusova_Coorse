package ru.altmanea.edu.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.altmanea.edu.server.model.Config
import ru.altmanea.edu.server.repo.importExcel
import ru.altmanea.edu.server.repo.importFile

import ru.altmanea.edu.server.rest.*

fun main() {
    importExcel(importFile)
    embeddedServer(
        Netty,
        port = Config.serverPort,
        host = Config.serverDomain,
        watchPaths = listOf("classes", "resources")
    ) {
        main()
    }.start(wait = true)
}

fun Application.main(test: Boolean = true) {
    if(test) {

    }
    install(ContentNegotiation) {
        json()
    }
    routing {
        teacher()
        group()
        discipline()
        index()
        link()
    }
}