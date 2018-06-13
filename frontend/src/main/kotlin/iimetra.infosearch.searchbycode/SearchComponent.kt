package iimetra.infosearch.searchbycode

import iimetra.infosearch.searchbycode.utils.get
import kotlinx.coroutines.experimental.launch
import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*

class SearchComponent : RComponent<RProps, SearchComponent.State>() {

    private fun sendSearchRequest(event: Event) {
        event.preventDefault()
        event.stopPropagation()
        val newValue = state.searchText
        launch {
            val response = get("/api/search/usages?q=$newValue")
            println(response)
        }
    }

    override fun SearchComponent.State.init() {
        searchText = null
    }

    override fun RBuilder.render() {
        div {
            attrs {
                id = "custom-search-input"
            }
            div("input-group") {
                input(InputType.text, classes = "form-control input-lg") {
                    attrs {
                        placeholder = "Type code search..."
                        value = state.searchText?.let { it } ?: ""
                        onChangeFunction = { event ->
                            val newValue = event.target.unsafeCast<HTMLInputElement>().value
                            setState {
                                searchText = newValue
                            }
                        }
                    }
                }
                span("input-group-btn") {
                    button(type = ButtonType.button, classes = "btn btn-info btn-lg") {
                        attrs {
                            onClickFunction = ::sendSearchRequest
                        }
                        i("glyphicon glyphicon-search") {}
                    }
                }
            }
        }
    }

    interface State : RState {
        var searchText: String?
    }
}

fun RBuilder.searchComponent() = child(SearchComponent::class) { }