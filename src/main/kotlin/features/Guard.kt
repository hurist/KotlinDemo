package org.hvi2ist.features

enum class Problem {
    CONNECTION, AUTHENTICATION, UNKNOWN
}

sealed interface Status {
    data object Loading: Status
    data class Error(val problem: Problem, val isCritical: Boolean): Status
    data class Ok(val info: List<String>): Status
}


/**
 * KEEP: https://github.com/Kotlin/KEEP/blob/master/proposals/guards.md
 * 强化when表达式的功能，增加了guard子句
 */
public fun guards(status: Status): String {
    /*
    以前的写法: 每个状态只能处理一种情况，如果要判断某个状态的多种情况，则只能只在when表达式中嵌套when表达式
     */
    /*return when (status) {
        is Status.Loading -> "Loading"
        is Status.Error -> {
            when (status.problem) {
                Problem.CONNECTION -> "Connection Error"
                Problem.AUTHENTICATION -> "Authentication Error"
                Problem.UNKNOWN -> "Unknown Error"
            }
        }
        is Status.Ok -> "Ok"
    }*/

    /*
    新的写法: 使用guard子句，可以在一个when表达式中处理多种情况
     */
    return when (status) {
        is Status.Loading -> "Loading"
        is Status.Error if (status.problem == Problem.CONNECTION) -> "Connection Error"
        is Status.Error if (status.problem == Problem.AUTHENTICATION) -> "Authentication Error"
        is Status.Error if (status.problem == Problem.UNKNOWN) -> "Unknown Error"
        is Status.Ok -> "Ok"
        else -> "Unknown"
    }

    return ""
}