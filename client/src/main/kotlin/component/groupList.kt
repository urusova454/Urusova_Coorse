package component

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.Props
import react.dom.*
import react.fc
import react.query.useQuery
import react.router.dom.Link
import ru.altmanea.edu.server.model.Config
import ru.altmanea.edu.server.model.Group
import wrappers.QueryError
import wrappers.fetchText


external interface GroupListProps : Props {
    var group: Set<Group>
}

fun fcGroupList() = fc("GroupList") { props: GroupListProps ->
    ol{
        props.group.forEach {
            li{
                Link {
                    attrs.to = "/group/${it.group}"
                    +it.group
                }
            }
        }
    }
}
fun fcContainerGroupList() = fc("GroupListContainer") { _: Props ->
    val query = useQuery<String, QueryError, String, String>(
        "group",
        {
            fetchText(
                Config.groupURL
            )
        }
    )
    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val group: Set<Group> =
            Json.decodeFromString(query.data ?: "")
        child(fcGroupList()) {
            attrs.group = group
        }
    }
}
