package iimetra.infosearch.searchbycode.header

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*

class HeaderComponent : RComponent<RProps, RState>() {

    companion object {
        init {
            kotlinext.js.require("styles/header.scss")
        }
    }

    override fun RBuilder.render() {
        nav("navbar navbar-default header-nav navbar-fixed-top") {
            div("container-fluid") {
                ul("nav navbar-nav") {
                    li("nav-item active") {
                        a(classes = "nav-link", href = "#") {
                            +"Home"
                        }
                    }
                }
            }
        }
    }
}

val RBuilder.header
    get() = child(HeaderComponent::class) {}
