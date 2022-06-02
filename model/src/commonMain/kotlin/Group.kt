package ru.altmanea.edu.server.model

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    var group: String,
    var specialization: String
)
{
    override fun toString() = "\n$group\n$specialization\n"
}
