package mockit

import kotlin.reflect.full.primaryConstructor

@PublishedApi internal inline fun <reified T: Any> Expectations.anyK(): T {
    return with(type<T>())
            ?: T::class.primaryConstructor!!.call()
}

@PublishedApi internal inline fun <reified T: Any> Verifications.anyK(): T {
    return with(type<T>())
            ?: T::class.primaryConstructor!!.call()
}

fun <T : Any> type(): Delegate<T> {
    return object : Delegate<T> {
        fun check(value: T): Boolean {
            return true
        }
    }
}
