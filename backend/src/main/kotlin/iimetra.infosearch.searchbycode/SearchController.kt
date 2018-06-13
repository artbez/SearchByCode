package iimetra.infosearch.searchbycode

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/search")
class SearchController(val searchService: SearchService, val sparkService: SparkService) {

    @GetMapping("/usages")
    fun findUsages(@RequestParam("q", required = false) testString: String): SmartResult {
        val allMatches = searchService.search(testString)
        return SmartResult(sparkService.getGrouped(allMatches))
    }
}