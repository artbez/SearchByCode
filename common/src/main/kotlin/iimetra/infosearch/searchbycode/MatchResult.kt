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

@Serializable
data class SmartResult(val result: List<RepoInfo>)

@Serializable
data class RepoInfo(val repo: String, var types: List<LabelInfo>)

@Serializable
data class LabelInfo(val label: Label, val lines: List<FragmentInfo>)

@Serializable
data class FragmentInfo(val url: String, var location: String, val filename: String, val lines: List<String>)

enum class Label {
    CLASS, OTHER, METHOD, VARIABLE, METHOD_CALL, DOCS
}