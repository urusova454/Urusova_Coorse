package component

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.Props
import react.dom.*
import react.fc
import react.query.useQuery
import react.router.dom.Link
import ru.altmanea.edu.server.model.Config
import ru.altmanea.edu.server.model.Discipline
import wrappers.QueryError
import wrappers.fetchText


external interface DisciplineListProps : Props {
    var discipline: Set<Discipline>
}

fun fcDisciplineList() = fc("DisciplineList") { props: DisciplineListProps ->
    ol{
        props.discipline.forEach {
            li{
                Link {
                    attrs.to = "/discipline/${it.discipline}"
                    +it.discipline
                }
            }
        }
    }
}
fun fcContainerDisciplineList() = fc("DisciplineListContainer") { _: Props ->
    val query = useQuery<String, QueryError, String, String>(
        "discipline",
        {
            fetchText(
                Config.disciplineURL
            )
        }
    )
    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val discipline: Set<Discipline> = Json.decodeFromString(query.data ?: "")
        child(fcDisciplineList()) {
            attrs.discipline = discipline
        }
    }
}