package org.hvi2ist.features


/**
 * 非局部返回： non-local return
 * 在 Kotlin 中，您只能使用普通的、不带标签的return退出命名函数或匿名函数。
 * 要退出 lambda，请使用标签return。lambda内部禁止使用裸函数，因为 lambda 无法使封闭函数return：
 * 这个是之前的版本就支持了，放在这里只是为了方便理解非局部这个概念
 */

fun nonLocalReturn(): Int {
    Any().ordinaryFunction {
        // 不带标签且在 lambda 内部使用 return, 且函数不是内联函数会报错，
        // 带标签的 return 可以正常使用，但只能返回退出 lambda， 无法退出NonLocalReturn函数
        return@ordinaryFunction 1
    }

    Any().inlineFunction {
        // 如果函数是内联函数，那么 lambda 内部的 return 会直接退出封闭函数NonLocalReturn
        // 这种行为称为非局部返回
        return 1
    }
}

/**
 * 非局部 break 和 continue
 * KEEP: https://github.com/Kotlin/KEEP/blob/master/proposals/break-continue-in-inline-lambdas.md
 */
fun nonLocalBreakAndContinue() {
    for (i in 1..10) {
        Any().ordinaryFunction {
            // 不带标签且在 lambda 内部使用 break 或 continue, 且函数不是内联函数会报错，
            // 带标签的 break 或 continue 可以正常使用，但只能退出 lambda， 无法退出 for 循环
            if (i == 5) {
                //break; //错误的，非内联函数不能使用 break 或 continue
            }
            println("i = $i")
        }
    }

    for (i in 1..10) {
        Any().inlineFunction {
            // 如果函数是内联函数，那么 lambda 内部的 break 或 continue 会直接退出或跳过 for 循环
            // 这种行为称为非局部 break 或 continue
            if (i == 5) {
                // TODO: 暂时还不知道怎么启用这个特性
                //break // 在Kotlin 2.1.0-beta2中，这里会直接退出 for 循环
                //continue // 在Kotlin 2.1.0-beta2中，这里会跳过 i == 5 的情况
            }
            println("i = $i")
        }
    }
}

private inline fun <T, R> T.inlineFunction(block: (T) -> R): R {
    return block(this)
}

private fun <T, R> T.ordinaryFunction(block: (T) -> R): R {
    return block(this)
}