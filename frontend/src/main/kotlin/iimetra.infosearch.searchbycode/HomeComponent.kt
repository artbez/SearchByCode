package iimetra.infosearch.searchbycode

import iimetra.infosearch.searchbycode.header.header
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h1
import react.dom.p

class HomeComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        header
        div(classes = "jumbotron welcome") {
            h1 {
                +"Search code engine"
            }
            p {
                +"Type code in searching input in order to see files and matching positions."
            }
            searchComponent()
        }
    }
}