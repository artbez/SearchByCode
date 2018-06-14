package iimetra.infosearch.searchbycode

import kotlinx.serialization.json.JSON
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class SearchService {

    fun search(query: String, page: Int = 0): MatchResult {
        val restTemplate = RestTemplate()
        val fooResourceUrl = "https://searchcode.com/api/codesearch_I/?q=$query&lan=23&p=$page&per_page=100"
        val response = restTemplate.getForEntity(fooResourceUrl, String::class.java)
        return JSON.nonstrict.parse(response.body!!)
    }
}