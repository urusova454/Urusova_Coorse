
import component.*
import kotlinx.browser.document
import kotlinx.css.WhiteSpace
import kotlinx.css.margin
import kotlinx.css.px
import kotlinx.css.whiteSpace
import react.createElement
import react.dom.br
import react.dom.render
import react.query.QueryClient
import react.query.QueryClientProvider
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import react.router.dom.Link
import styled.css
import styled.styledSpan
import wrappers.cReactQueryDevtools

val queryClient = QueryClient()

fun main() {
    render(document.getElementById("root")!!) {
        HashRouter {
            QueryClientProvider {
                attrs.client = queryClient
                styledSpan {
                    css {
                        whiteSpace = WhiteSpace.preWrap
                        margin(top = 10.px, bottom = 10.px)
                    }
                    Link {
                        attrs.to = "/teachers"
                        +"Teacher"
                    }
                    br{ }
                    Link {
                        attrs.to = "/group"
                        +"Group"
                    }
                    br{ }
                    Link {
                        attrs.to = "/discipline"
                        +"Discipline"
                    }
                    br{ }
                    Link {
                        attrs.to = "/"
                        +"Link"
                    }
                }
                Routes {
                    Route {
                        attrs.path = "/teachers"
                        attrs.element =
                            createElement(fcContainerTeacherList())
                    }
                    Route {
                        attrs.path = "/teachers/:name"
                        attrs.element =
                            createElement(fcContainerTeacher())
                    }
                    Route {
                        attrs.path = "/group"
                        attrs.element =
                            createElement(fcContainerGroupList())
                    }
                    Route {
                        attrs.path = "/group/:name"
                        attrs.element =
                            createElement(fcContainerGroup())
                    }
                    Route {
                        attrs.path = "/discipline"
                        attrs.element =
                            createElement(fcContainerDisciplineList())
                    }
                    Route {
                        attrs.path = "/discipline/:name"
                        attrs.element =
                            createElement(fcContainerDiscipline())
                    }
                    Route {
                        attrs.index = true
                        attrs.element =
                            createElement(fcContainerRolesList())
                    }
                }
                child(cReactQueryDevtools()) {}
            }
        }
    }
}

