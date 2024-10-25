package org.hvi2ist.features

// TODO: 这个待整理

private class Scope {
    fun printInfo() {
        println("Scope")
    }
}
private class Scope1 {
    fun printInfo() {
        println("Scope1")
    }
}
private class Type

private fun Type.equalTo(other: Type): Boolean = false

/**
 * 上下文接收器，在后续版本中会被逐渐替换为更加通用的上下文参数
 */
context(Scope)
private fun Type.equalTo(other: Type): Boolean = true

context(Scope1)
private fun Type.equalTo(other: Type): Boolean = false

context(Scope, Scope1)
private fun Type.printInfos() {
    this@Scope.printInfo() // 输出：Scope, 可以通过this@Scope指定调用哪个printInfo
}

/* TODO：暂时还不支持
context(scope: Scope)
private fun Type.equalTo(other: Type): Boolean = false
*/

/**
 * 上下文参数
 */
fun contextParam() {

    // 上下文接收器
    with(Scope()) {
        with(Scope1()) {
            /*val b = Type().equalTo(Type()) //报错，不知道调用哪个equalTo
            println(b)*/
            printInfo() // 输出：Scope1
            Type().printInfos()
        }
    }
}