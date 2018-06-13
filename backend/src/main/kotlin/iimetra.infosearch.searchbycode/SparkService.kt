package iimetra.infosearch.searchbycode

import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaSparkContext
import org.springframework.stereotype.Service

@Service
class SparkService {

    private val conf = SparkConf()
        .setMaster("local")
        .setAppName("Kotlin Spark Test")

    private val sc = JavaSparkContext(conf)

    fun getGrouped(items: MatchResult): List<RepoInfo> {

        val sparkItems =
            items.results.map { fr -> getLines(fr).map { listOf(fr.url, fr.id.toString(), fr.repo, fr.location, fr.filename, it) } }.toList()

        val search = items.searchterm

        val input = sc.parallelize(sparkItems)
        val sparkResult = input
            .flatMap { it.iterator() }
            .map { it + getLabel(search, it[5]) }
            .groupBy { it[2] }
            .mapValues { it.groupBy { it[6] }.mapValues { it.value.groupBy { it[1] } } }
            .collectAsMap()

        return sparkResult.entries.map {
            RepoInfo(it.key, it.value.map {
                LabelInfo(Label.valueOf(it.key), it.value.map {
                    FragmentInfo(it.value[0][0], it.value[0][3], it.value[0][4], it.value.map { it[5] })
                })
            })
        }
    }

    private fun getLines(fragment: FragmentDescription) = fragment.lines.entries
        .sortedBy { it.key.toInt() }
        .map { "${it.key}: ${it.value}" }
}

fun getLabel(query: String, line: String) = when {
    regExpTest(line, classPattern(query)) -> Label.CLASS.name
    else -> Label.OTHER.name
}