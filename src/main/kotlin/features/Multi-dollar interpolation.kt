package org.hvi2ist.features

/**
 * 多重$插值
 * KEEP: https://github.com/Kotlin/KEEP/blob/master/proposals/dollar-escape.md
 */
fun multiDollarInterpolation() {
    val version = "1.0"
    val ordinaryString = "Kotlin $version \n\$id = 1"
    // 输出：Kotlin 1.0
    //      $id = 1
    println(ordinaryString)
    // 上面这种\$无法在多行字符串中使用，因为多行字符串中的字符都会被原样输出，无法使用转义
    //val multiLineString = """Kotlin $version \n\$id = 1""" // 会报错，提示Unresolved reference: id


    // 在Kotlin 2.1.0-beta2中想了个办法，在字符串前面指定用几个$来表示插值，只有连续和字符串前面的$个数相等时才会被解析为插值
    val multiLineString = $$"""Kotlin $$$version $id = 1"""
    // 输出：Kotlin $1.0 $id = 1
    println(multiLineString)
}