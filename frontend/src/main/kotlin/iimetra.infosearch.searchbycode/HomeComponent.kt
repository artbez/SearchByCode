package iimetra.infosearch.searchbycode

import iimetra.infosearch.searchbycode.header.header
import iimetra.infosearch.searchbycode.search.resultSearch
import iimetra.infosearch.searchbycode.search.searchComponent
import iimetra.infosearch.searchbycode.utils.get
import kotlinx.coroutines.experimental.launch
import kotlinx.serialization.json.JSON
import react.*
import react.dom.div
import react.dom.h1
import react.dom.p

class HomeComponent : RComponent<RProps, HomeComponent.State>() {

    private fun updateSearch(query: String) {
        launch {
            val result = get("/api/search/usages?q=$query")
            setState {
                searchText = JSON.parse(result)
            }
        }
    }

    override fun HomeComponent.State.init() {
        searchText = null
    }

    override fun RBuilder.render() {
        header
        div(classes = "jumbotron welcome") {
            h1 {
                +"Search code engine"
            }
            p {
                +"Type code in searching input in order to see files and matching positions."
            }
            searchComponent(::updateSearch)
        }
        resultSearch(state.searchText)
    }

    interface State : RState {
        var searchText: SmartResult?
    }
}