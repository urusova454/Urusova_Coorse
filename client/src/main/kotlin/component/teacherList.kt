package component

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.Props
import react.dom.*
import react.fc
import react.query.useQuery
import react.router.dom.Link
import ru.altmanea.edu.server.model.Config
import ru.altmanea.edu.server.model.Teacher
import wrappers.QueryError
import wrappers.fetchText


external interface TeacherListProps : Props {
    var teacher: Set<Teacher>
}

fun fcTeacherList() = fc("TeacherList") { props: TeacherListProps ->
    ol{
        props.teacher.forEach {
            li{
                Link {
                    attrs.to = "/teachers/${it.surname}"
                    +it.toString()
                }
            }
        }
    }
}
fun fcContainerTeacherList() = fc("TeacherListContainer") { _: Props ->
    val query = useQuery<String, QueryError, String, String>(
        "teachers",
        {
            fetchText(
                Config.teacherURL
            )
        }
    )
    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val teacher: Set<Teacher> =
            Json.decodeFromString(query.data ?: "")
        child(fcTeacherList()) {
            attrs.teacher = teacher
        }
    }
}
