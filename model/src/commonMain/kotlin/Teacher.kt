package ru.altmanea.edu.server.model

import kotlinx.serialization.Serializable

@Serializable
data class Teacher(
    var surname:String,
    var name:String,
    var middleName:String
) {
    override fun toString() = "\n$surname\n$middleName\n$name"
}