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

    fun getGrouped(items: List<MatchResult>): List<RepoInfo> {

        val sparkItems =
            items.flatMap { t ->
                t.results.map { fr ->
                    getLines(fr).map {
                        listOf(
                            fr.url,
                            fr.id.toString(),
                            fr.repo,
                            fr.location,
                            fr.filename,
                            it,
                            t.searchterm
                        )
                    }
                }
            }.toList()

        val input = sc.parallelize(sparkItems)
        val sparkResult = input
            .flatMap { it.iterator() }
            .map { it + getLabel(it[6], it[5]) }
            .groupBy { it[2] }
            .mapValues { it.groupBy { it[7] }.mapValues { it.value.groupBy { it[1] } } }
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

fun getLabel(query: String, line: String): String {
    val corLine = line.substring(line.indexOf(":") + 1)
    return when {
        regExpTest(corLine, classPattern(query)) -> Label.CLASS.name
        else -> Label.OTHER.name
    }
}