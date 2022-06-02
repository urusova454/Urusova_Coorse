package ru.altmanea.edu.server.model

import kotlinx.serialization.Serializable

@Serializable
data class Inform(
    val number: String,
    val plan: String,
    val facul: String,
    val bloc: String,
    val discipline: Discipline,
    val semestr: String,
    val group: Group,
    val koll: String,
    val vid: String,
    val time: String,
    val teacher: Teacher,
    val zan: String
)
