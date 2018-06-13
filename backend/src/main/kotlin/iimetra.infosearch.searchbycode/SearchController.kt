package iimetra.infosearch.searchbycode

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/search")
class SearchController {

    @GetMapping("/usages")
    fun findUsages(@RequestParam("q", required = false) testString: String): String {
        return "$testString heh"
    }
}