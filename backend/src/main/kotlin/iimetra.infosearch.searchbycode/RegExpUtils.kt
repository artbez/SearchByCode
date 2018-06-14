package iimetra.infosearch.searchbycode

import java.util.regex.Pattern

fun regExpTest(testString: String, pattern: String): Boolean {
    val p = Pattern.compile(pattern)
    val m = p.matcher(testString)
    return m.matches();
}

fun classPattern(className: String) = "\\s*(public\\s+|private\\s+)?\\s*(static\\s+)?class\\s+" + className + "\\s+((implements|extends)\\s+\\S+\\s*)*"
fun typePattern(variableName: String) =
    "(\\s*\\S+\\s+(.+,s*)*\\s*" + variableName + "(\\s*.*\\s*)*|.*\"" + variableName + "\")"

fun methodPattern(methodName: String) =
    "\\s*(public\\s+|private\\s+)?\\s*(static\\s+)?\\s*\\S+\\s+" + methodName + "\\s*\\(.*\\)?"

fun callNamePattern(callName: String) = "\\s*(\\S+\\.|(\\S\\..*\\.)+)" + callName + "\\(.*\\)?"

fun docsPattern(commentName: String) = "\\s*(//|/\\*|\\*)" + "(.*\\s+)*\\s*" + commentName + "(\\s+.*)*"


