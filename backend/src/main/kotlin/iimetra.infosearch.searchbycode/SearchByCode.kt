package iimetra.infosearch.searchbycode

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SearchByCode

fun main(args: Array<String>) {
    SpringApplication.run(SearchByCode::class.java, *args)
}
