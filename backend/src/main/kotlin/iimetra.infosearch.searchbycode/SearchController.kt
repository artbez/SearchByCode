package iimetra.infosearch.searchbycode

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/search")
class SearchController(val searchService: SearchService, val sparkService: SparkService) {

    @GetMapping("/usages")
    fun findUsages(@RequestParam("q", required = false) testString: String): SmartResult {
        val res = runBlocking {
            (0..1).map {
                async {
                    searchService.search(testString, it)
                }
            }.map { it.await() }
        }
        return SmartResult(sparkService.getGrouped(res))
    }
}