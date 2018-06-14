package iimetra.infosearch.searchbycode.search

import iimetra.infosearch.searchbycode.Label
import iimetra.infosearch.searchbycode.LabelInfo
import iimetra.infosearch.searchbycode.RepoInfo
import iimetra.infosearch.searchbycode.SmartResult
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*

class SearchResultComponent : RComponent<SearchResultComponent.Props, SearchResultComponent.State>() {

    override fun SearchResultComponent.State.init() {
        repoIndex = null
        label = null
    }

    override fun RBuilder.render() {
        val res = props.result
        res?.let {
            div("container-fluid") {
                div("row") {
                    div("col-sm-1") {}
                    div("col-sm-4") {
                        ul("list-group") {
                            res.result.mapIndexed { index: Int, repoInfo: RepoInfo ->
                                val className = "list-group-item" + if (state.repoIndex == index) " active" else ""
                                li(className) {
                                    +repoInfo.repo
                                    attrs {
                                        onClickFunction = {
                                            it.stopPropagation()
                                            it.preventDefault()
                                            setState {
                                                repoIndex = index
                                                label = null
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    div("col-sm-2") {
                        state.repoIndex?.let {
                            ul("list-group") {
                                res.result[it].types.forEach { labelInfo: LabelInfo ->
                                    val className = "list-group-item" + if (state.label == labelInfo.label) " active" else ""
                                    li(className) {
                                        +labelInfo.label.name
                                        attrs {
                                            onClickFunction = {
                                                it.stopPropagation()
                                                it.preventDefault()
                                                setState {
                                                    label = labelInfo.label
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    div("col-sm-4") {
                        val index = state.repoIndex
                        val label = state.label
                        if (index != null && label != null) {
                            val code = res.result[index].types.find { it.label == label }!!.lines
                            code.forEach {
                                div("file-block") {
                                    div {
                                        +"Filename: ${it.filename}"
                                    }
                                    div {
                                        +"Location: ${it.location}"
                                    }
                                    div {
                                        span {
                                            +"url: "
                                        }
                                        a {
                                            +it.url
                                        }
                                    }
                                    val allLines = it.lines.joinToString("\r\n")
                                    div("input-group") {
                                        textArea(classes = "code-area") {
                                            attrs {
                                                disabled = true
                                                value = allLines
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    div("col-sm-2") {}
                }
            }
        }
    }

    interface Props : RProps {
        var result: SmartResult?
    }

    interface State : RState {
        var repoIndex: Int?
        var label: Label?
    }
}

fun RBuilder.resultSearch(result: SmartResult?) = child(SearchResultComponent::class) {
    attrs.result = result
}