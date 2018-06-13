package iimetra.infosearch.searchbycode

import java.util.regex.Pattern

fun regExpTest(testString: String, pattern: String): Boolean {
    val p = Pattern.compile(pattern)
    val m = p.matcher(testString)
    return m.matches();
}

fun classPattern(className: String) = "\\s*(public\\s+|private\\s+)?\\s*(static\\s+)?class\\s+$className\\s*\\{(\\s*.*\\s*)*\\}"
fun typePattern(variableName: String) =
    "\\s*(int(\\[\\])*|boolean(\\[\\])*|String(\\[\\])*|long(\\[\\])*|double(\\[\\])*|char(\\[\\])*|List<.+>|ArrayList<.+>)\\s+(.+,s*)*\\s*$variableName(\\s*.*\\s*)*"

fun methodPattern(methodName: String) =
    "\\s*(public\\s+|private\\s+)?\\s*(static\\s+)?\\s*(int(\\[\\])*|boolean(\\[\\])*|String(\\[\\])*|long(\\[\\])*|double(\\[\\])*|char(\\[\\])*|List<.+>|ArrayList<.+>|void)\\s+$methodName\\s*\\(.*\\)"


