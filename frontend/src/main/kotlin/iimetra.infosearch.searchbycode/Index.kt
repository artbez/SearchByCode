package iimetra.infosearch.searchbycode

import kotlinext.js.invoke
import react.dom.render
import kotlin.browser.document

fun main(args: Array<String>) {
    kotlinext.js.require.invoke("styles/common.scss")
    render(document.getElementById("root")) {
        child(HomeComponent::class) {}
    }
}
