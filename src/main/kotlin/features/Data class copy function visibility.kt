package org.hvi2ist.features

/**
 * 在目前的Kotlin版本中，data class的copy函数是public的，但以后可能会和构造函数的可见性保持一致
 * 所以在2.0.20版本以后，构造函数的可见性为private时，编译器会给出警告，如果想消除警告，官方给出了两种解决方案：
 * 1. @ConsistentCopyVisibility 注解：直接启用这个特性，构造函数和copy函数的可见性保持一致，如果构造函数是private的，copy函数也是private，
 * 向编译器添加-Xconsistent-data-class-copy-visibility可达到相同效果
 * 2. @ExposedCopyVisibility 注解：copy函数的可见性保持public，不受构造函数可见性的影响，但编译器会给出警告
 */
@ExposedCopyVisibility
data class PositiveInteger private constructor(val number: Int) {
    companion object {
        fun create(number: Int): PositiveInteger? = if (number > 0) PositiveInteger(number) else null
    }
}

fun dataClassCopyFunctionVisibility() {
    val positiveInteger = PositiveInteger.create(1)
    // 如果data class的构造函数是private的，这里会报警告（目前使用ConsistentCopyVisibility注解会直接触发这种异常，后续这个特性转正
    // 后，不需要这个注解，编译器会直接报错）
    val copy = positiveInteger?.copy(number = 2)
    println(copy)
}