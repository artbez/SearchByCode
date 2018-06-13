package iimetra.infosearch.searchbycode.utils

import kotlinx.coroutines.experimental.await
import org.w3c.fetch.RequestCredentials
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import kotlin.browser.window
import kotlin.js.json

private const val POST = "POST"
private const val GET = "GET"

suspend fun get(url: String, body: dynamic = null): String {
    return request(GET, url, body).text().await()
}

suspend fun post(url: String, body: dynamic = null): String {
    return request(POST, url, body).text().await()
}

private suspend fun request(method: String, url: String, body: dynamic, headers: dynamic = defaultHeaders): Response =
    window.fetch(url, object : RequestInit {
        override var method: String? = method
        override var body: dynamic = body
        override var credentials: RequestCredentials? = "same-origin".asDynamic()
        override var headers: dynamic = headers
    }).await()

private val defaultHeaders = json(
    "Accept" to "application/json",
    "Content-Type" to "application/json;charset=UTF-8"
)
