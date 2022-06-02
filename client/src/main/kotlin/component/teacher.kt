package component

import kotlinx.css.border
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.Props
import react.dom.*
import react.fc
import react.query.useQuery
import react.router.useParams
import ru.altmanea.edu.server.model.*
import styled.css
import styled.styledTable
import wrappers.QueryError
import wrappers.fetchText


    external interface TeacherProps : Props {
        var table:  List<Inform>

    }

fun fcTeacher() = fc("Teacher") { props: TeacherProps ->
    styledTable {
        css {
            descendants("tr", "td") {
                border = "1px solid black"
            }
        }

        tr {
            td{+"№"}
            td{+"Рабочий план"}
            td{+"Факультет"}
            td{+"Блок"}
            td{+"Дисциплина (вид учебной работы)"}
            td{+"Семестр"}
            td{+"Группа"}
            td{+"Кол-во обучающихся"}
            td{+"Вид нагрузки"}
            td{+"Нагрузка, часов"}
            td{+"Преподаватель"}
            td{+"Вид занятости, ставка"}

        }
        props.table.forEach {
            tr {
                td {
                    +it.number
                }
                td {
                    +it.plan
                }
                td {
                    +it.facul
                }
                td {
                    +it.bloc
                }
                td {
                    +it.discipline.discipline
                }
                td {
                    +it.semestr
                }
                td {
                    +it.group.toString()
                }
                td {
                    +it.koll
                }
                td {
                    +it.vid
                }
                td {
                    +it.time
                }
                td {
                    +it.teacher.toString()
                }
                td {
                    +it.zan
                }
            }
        }
    }
}

fun fcContainerTeacher() = fc("QueryTeacher") { _: Props ->
    val teacherParams = useParams()
    val teacherId = teacherParams["name"] ?: "Route param error"
    val query = useQuery<String, QueryError, String, String>(
        teacherId,
        {
            fetchText(
                Config.teacherURL+teacherId
            )
        }
    )
    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val items:  List<Inform> =
            Json.decodeFromString(query.data ?: "")
        child(fcTeacher()) {
            attrs.table = items

        }
    }
}


