package ru.altmanea.edu.server.model

class Config {
    companion object {
        const val serverDomain = "localhost"
        const val serverPort = 8000
        const val serverApi = "1"
        const val serverUrl = "http://$serverDomain:$serverPort/"
        const val pathPrefix = "api$serverApi/"

        const val teacherPath = "${pathPrefix}teacher/"
        const val teacherURL = "$serverUrl$teacherPath"

        const val groupPath = "${pathPrefix}group/"
        const val groupURL = "$serverUrl$groupPath"

        const val disciplinePath = "${pathPrefix}discipline/"
        const val disciplineURL = "$serverUrl$disciplinePath"

        const val linkPath = "${pathPrefix}link/"
        const val linkURL = "$serverUrl$linkPath"
    }
}