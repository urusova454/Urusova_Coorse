package component

import kotlinext.js.jso
import kotlinx.html.INPUT
import kotlinx.html.js.onClickFunction
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import react.Props
import react.dom.*
import react.fc
import react.query.useMutation
import react.query.useQuery
import react.query.useQueryClient
import react.useRef
import ru.altmanea.edu.server.model.*
import ru.altmanea.edu.server.model.Config.Companion.linkURL
import wrappers.QueryError
import wrappers.axios
import wrappers.fetchText
import kotlin.js.json

external interface RolesListProps : Props {
    //var messenge: String
    //var link: String
    var addLink: (String) -> Unit
}
//val proverka = Regex("([А-я]+|[A-z]+|[\\:]+|[0-9]+).xlsx")
fun fcRolesList() = fc("LessonList") { props: RolesListProps ->
    div{
        +"По умолчанию данные загружены с файла: НагрузкаАМ.xls"
    }
    div{
        +"Выгрузить еще данные? Введите файл с которого будут дозгружены файлы"
    }
    val nameRef = useRef<INPUT>()
    div{
        //+props.messenge
    }

    span {
        p {
            +"Link: "
            input {
                ref = nameRef
            }
        }
        button {
            +"Add Way"
            attrs.onClickFunction = {
                nameRef.current?.value?.let {
                    props.addLink(it)
                }
            }
        }
    }
}

fun fcContainerRolesList() = fc("LinkListContainer") { _: Props ->
    val queryClient = useQueryClient()
    val addLinkMutation = useMutation<Any, Any, String, Any>(
        { link: String ->
            axios<String>(jso {
                url = linkURL
                method = "Post"
                headers = json(
                    "Content-Type" to "application/json",
                )
                data = link
            })
        },
        options = jso {
            onSuccess = { _: Any, _: Any, _: Any? ->
                queryClient.invalidateQueries<Any>("teachers")
            }
        }
    )
        child(fcRolesList()) {
            //attrs.messenge = messenge
            attrs.addLink = {
                addLinkMutation.mutate(it, null)
            }
        }
}

