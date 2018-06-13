package iimetra.infosearch.searchbycode

import kotlinx.serialization.Serializable

@Serializable
data class MatchResult(val searchterm: String, val results: List<FragmentDescription>)

@Serializable
data class FragmentDescription(
    val url: String,
    val id: Long,
    val repo: String,
    val location: String,
    val filename: String,
    val lines: Map<String, String>
)