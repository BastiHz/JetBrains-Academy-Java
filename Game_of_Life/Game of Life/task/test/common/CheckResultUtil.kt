package common


import org.hyperskill.hstest.testcase.CheckResult

operator fun CheckResult.component1(): Boolean = isCorrect
operator fun CheckResult.component2(): String? = feedback

fun fail(message: String) = CheckResult(false, message)  // TODO: add to repo
